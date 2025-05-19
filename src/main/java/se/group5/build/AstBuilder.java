package se.group5.build;

import lombok.Getter;
import se.group5.ast.Identifier;
import se.group5.ast.Node;
import se.group5.ast.SymbolTable;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.data.Representation;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.NumericLiteral;
import se.group5.ast.statement.Accept;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import se.group5.parser.*;

/**
 * Visits the parse‑tree and constructs a hierarchical AST using the new model
 */
public final class AstBuilder extends CoBabyBoLBaseVisitor<Node> {
    @Getter
    private final SymbolTable symbols = new SymbolTable();
    private final Deque<DataGroup> groupStack = new ArrayDeque<>();

    @Override
    public Node visitProgram(CoBabyBoL.ProgramContext ctx) {
        return visitChildren(ctx);
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

            pic = symbols.resolve(ref.value())
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
            groupStack.peek().addChild(def);
        }
        if (def instanceof DataGroup g) {
            groupStack.push(g);
        }

        // Register fully-qualified name in the symbol table
        List<Identifier> qualification = new ArrayList<>();
        groupStack.descendingIterator().forEachRemaining(g -> qualification.add(g.name()));
        qualification.add(id);
        symbols.register(qualification, def);

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
                .map(t -> new Identifier(t.getText()))
                .toList();
        return new Accept(targets);
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
