package se.group5.build;

import se.group5.ast.*;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.data.Representation;
import se.group5.ast.identity.IdentityTable;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.NumericLiteral;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.Accept;
import se.group5.parser.CoBabyBoL;
import se.group5.parser.CoBabyBoLBaseVisitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Visits the parse‑tree and constructs a hierarchical AST using the new model
 */
public final class AstBuilder extends CoBabyBoLBaseVisitor<Node> {
    private final IdentityTable identityTable = new IdentityTable();
    private final SymbolTable symbolTable = new SymbolTable();
    private final ProcedureList procedures = new ProcedureList();
    private final Deque<DataGroup> groupStack = new ArrayDeque<>();

    @Override
    public Node visitProgram(CoBabyBoL.ProgramContext ctx) {
        if (ctx.data_division() != null) visit(ctx.data_division());
        if (ctx.identification_division() != null) visit(ctx.identification_division());
        if (ctx.procedure_division() != null) visit(ctx.procedure_division());

        return new Program(
                identityTable,
                symbolTable,
                procedures
        );
    }

    @Override
    public Node visitIdentification_division(CoBabyBoL.Identification_divisionContext ctx) {
        for (var idClause : ctx.identification_clause()) {
            identityTable.register(idClause.clause_name().getText(), idClause.clause_value().getText());
        }
        return identityTable;
    }

    @Override
    public Node visitData_division(CoBabyBoL.Data_divisionContext ctx) {
        for (var itemCtx : ctx.data_item()) {
            visit(itemCtx);
        }
        return symbolTable;
    }

    // === DATA DIVISION =======================================================
    @Override
    public Node visitData_item(CoBabyBoL.Data_itemContext ctx) {
        // level number & identifier
        int level = Integer.parseInt(ctx.level().INTEGERLITERAL().getText());
        Identifier id = new Identifier(ctx.IDENTIFIER().getText());

        // Determine the picture (may come from LIKE, or explicit PIC, or none)
        Representation pic = null;

        if (ctx.picture_clause(0) != null) {
            pic = (Representation) visit(ctx.picture_clause(0));

        } else if (ctx.like_clause(0) != null) {
            Identifier ref = new Identifier(ctx.like_clause(0).IDENTIFIER().getText());

            pic = symbolTable.resolve(ref.value())
                    .filter(DataElement.class::isInstance)
                    .map(DataElement.class::cast).map(DataElement::picture)
                    .orElseThrow(() -> new IllegalStateException("LIKE reference '" + ref + "' is not an element or not declared"));
        }

        // Instantiate either an element or a group
        DataDefinition def;
        if (pic == null) {                 // GROUP
            def = new DataGroup(level, id);
        } else {                           // ELEMENT (maybe OCCURS array)
            int occurs = ctx.occurs_clause(0) == null ? 0 : Integer.parseInt(ctx.occurs_clause(0).INTEGERLITERAL().getText());
            def = new DataElement(level, id, pic, occurs);
        }

        // Maintain the group-stack  (pop until parent is found, then push)
        while (!groupStack.isEmpty() && groupStack.peek().level() >= level) {
            groupStack.pop();
        }
        if (!groupStack.isEmpty()) {
            groupStack.peek().register(id.toString(), def);
        }

        List<Identifier> qualification = new ArrayList<>();
        groupStack.descendingIterator().forEachRemaining(g -> qualification.add(g.name()));
        qualification.add(id);
        symbolTable.register(qualification, def);

        if (def instanceof DataGroup g) {
            groupStack.push(g);
        }

        return def;
    }

    @Override
    public Representation visitPicture_clause(CoBabyBoL.Picture_clauseContext ctx) {
        return new Representation(ctx.REPRESENTATION().getText());
    }

    @Override
    public Node visitNumeric_literal(CoBabyBoL.Numeric_literalContext ctx) {
        return new NumericLiteral(ctx.getText());
    }

    @Override
    public Node visitAlphanumeric_literal(CoBabyBoL.Alphanumeric_literalContext ctx) {
        return new AlphanumericLiteral(ctx.STRINGLITERAL().getText());
    }

    @Override
    public Node visitAtomic(CoBabyBoL.AtomicContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Node visitAccept(CoBabyBoL.AcceptContext ctx) {
        List<Identifier> targets = ctx.IDENTIFIER()
                .stream()
                .map(t -> {
                    var name = t.getText();
                    if (symbolTable.resolve(name).isEmpty()) {
                        throw new IllegalStateException("LIKE reference '" + t + "' is not an element or not declared");
                    }
                    return symbolTable.resolve(name).get().name();
                })
                .toList();
        Accept accept = new Accept(targets);
        procedures.add(accept);
        return accept;
    }

    @Override
    protected Node defaultResult() {
        return null;
    }

    @Override
    protected Node aggregateResult(Node aggregate, Node next) {
        return next != null ? next : aggregate;
    }
}
