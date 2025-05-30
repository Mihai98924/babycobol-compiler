package se.group5.build;

import se.group5.ast.*;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.data.Representation;
import se.group5.ast.identity.IdentityTable;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.Literal;
import se.group5.ast.literal.NumericLiteral;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.*;
import se.group5.parser.CoBabyBoL;
import se.group5.parser.CoBabyBoLBaseVisitor;
import se.group5.processor.Processor;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        int level = Integer.parseInt(ctx.level().LEVEL().getText());
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
    public Node visitDisplay(CoBabyBoL.DisplayContext ctx) {
        boolean noAdvancing = ctx.WITH_NO_ADVANCING() != null;
        Display display = new Display(noAdvancing);
        for (var displayAtomicClause : ctx.display_atomic_clause()) {
            Atomic atomic = (Atomic) visit(displayAtomicClause.atomic());

            if (displayAtomicClause.SPACE() != null) {
                display.addAtomic(atomic, Display.DelimiterType.SPACE);
            } else if (displayAtomicClause.SIZE() != null) {
                display.addAtomic(atomic, Display.DelimiterType.SIZE);
            } else if (displayAtomicClause.literal() != null) {
                Literal literal = (Literal) visit(displayAtomicClause.literal());
                display.addAtomic(atomic, literal);
            } else {
                display.addAtomic(atomic);
            }
        }

        procedures.add(display);
        return display;
    }


    @Override
    public Node visitAtomic(CoBabyBoL.AtomicContext ctx) {
        if (ctx.identifier() != null) {
            Identifier identifier = (Identifier) visit(ctx.identifier());
            String name = identifier.toString();
            Optional<DataDefinition> def = symbolTable.resolve(name);
            if (def.isEmpty() || !(def.get() instanceof DataElement))
                throw new IllegalStateException("Identifier reference in Atomic '" + name + "' is not a data definition or not declared");
            return new Atomic((DataElement) def.get());
        }

        Literal literal = (Literal) visit(ctx.literal());
        return new Atomic(literal);
    }

    @Override
    public Node visitIdentifier(CoBabyBoL.IdentifierContext ctx) {
        if (ctx.identifier() != null) {
            Identifier identifier = (Identifier) visit(ctx.identifier());
            return new Identifier(
                    identifier.value() + "." + ctx.IDENTIFIER().getText()
            );
        }
        return new Identifier(ctx.IDENTIFIER().getText());
    }


    @Override
    public Node visitAccept(CoBabyBoL.AcceptContext ctx) {
        List<Identifier> targets = ctx.IDENTIFIER()
                .stream()
                .map(t -> {
                    var name = t.getText();
                    var identifier = symbolTable.resolveIdentifier(name);
                    if (identifier.isEmpty()) {
                        throw new IllegalStateException("LIKE reference '" + t + "' is not an element or not declared");
                    }
                    return identifier.get();
                })
                .toList();
        Accept accept = new Accept(targets);
        procedures.add(accept);
        return accept;
    }


    @Override
    public Arithmetic visitAdd(CoBabyBoL.AddContext ctx) {
        List<Atomic> addends = ctx.add_atomic().stream()
                .flatMap(aa -> aa.atomic().stream())
                .map(a -> (Atomic) visitAtomic(a)).toList();
        Atomic target = (Atomic) visitAtomic(ctx.to_atomic().atomic());

        List<DataElement> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream().map(i -> (DataElement) symbolTable.resolve(i.getText()).get()).toList();
        Arithmetic add = Arithmetic.add(addends, target, giving);
        procedures.add(add);
        return add;
    }


    @Override
    public Arithmetic visitDivide(CoBabyBoL.DivideContext ctx) {
        Atomic divisor = (Atomic) visitAtomic(ctx.atomic());

        List<Atomic> dividends = ctx.into_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        List<DataElement> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream()
                .map(g -> (DataElement) symbolTable
                        .resolve(g.getText())
                        .get())
                .toList();

        DataElement remainder = null;
        if (ctx.remainder() != null) {
            String remName = ctx.remainder().REMAINDER().getText();
            remainder = (DataElement) symbolTable.resolve(remName).get();
        }

        Arithmetic divide = Arithmetic.divide(divisor, dividends, giving, remainder);
        procedures.add(divide);
        return divide;
    }

    @Override
    public Arithmetic visitMultiply(CoBabyBoL.MultiplyContext ctx) {
        Atomic multiplier = (Atomic) visitAtomic(ctx.atomic());

        List<Atomic> multiplicands = ctx.by_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        DataElement giving = null;
        if (ctx.giving_identifier() != null) {
            String id = ctx.giving_identifier().IDENTIFIER().getText();
            giving = (DataElement) symbolTable.resolve(id).get();
        }

        Arithmetic multiply = Arithmetic.multiply(multiplier, multiplicands, giving);
        procedures.add(multiply);
        return multiply;
    }

    @Override
    public Arithmetic visitSubtract(CoBabyBoL.SubtractContext ctx) {
        List<Atomic> subtrahends = ctx.from_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        List<Atomic> minuends = ctx.from_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        List<DataElement> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream()
                .map(i -> (DataElement) symbolTable
                        .resolve(i.getText())
                        .get())
                .toList();

        Arithmetic subtract = Arithmetic.subtract(subtrahends, minuends, giving);
        procedures.add(subtract);
        return subtract;
    }

    public Node visitMove(CoBabyBoL.MoveContext ctx) {
        Object moveType;
        if(ctx.move_arg().HIGH_VALUES() != null){
            moveType = Move.MoveType.HIGH_VALUES;
        } else if (ctx.move_arg().LOW_VALUES() != null){
            moveType = Move.MoveType.LOW_VALUES;
        } else if (ctx.move_arg().SPACES() != null){
            moveType = Move.MoveType.SPACES;
        } else {
            moveType = (Atomic) visitAtomic(ctx.move_arg().atomic());
        }

        List<Identifier> targets = ctx.identifier().stream().map(
                t -> {
                    var name = t.getText();
                    var identifier = symbolTable.resolveIdentifier(name);
                    Representation repr = ((DataElement) symbolTable.resolve(name).get()).picture();
                    if (moveType instanceof Atomic){
                        if(((Atomic) moveType).getLiteral() != null && !repr.matches(((Atomic) moveType).getLiteral().raw())) {
                            throw new IllegalStateException("Move with literal '" + ((Atomic) moveType).getLiteral().raw() + "' does not match target '" + repr + "'");
                        } else if (((Atomic) moveType).getElement() != null && !repr.matches(((Atomic) moveType).getElement().picture().toString())) {
                            throw new IllegalStateException("Move with identifier '" + ((Atomic) moveType).getElement().name() + "' does not match target '" + identifier.get() + "'");
                        }
                    }
                    return identifier.get();
                }
        ).toList();
        Move move = new Move(moveType, targets);
        procedures.add(move);
        return move;
    }

    public Node visitCall(CoBabyBoL.CallContext ctx) {
        AlphanumericLiteral filename = null;
        List<Object> args = new ArrayList<>();
        if(ctx.call_option_1() != null){
            filename = new AlphanumericLiteral(ctx.call_option_1().file_name().getText());
            File file = new File(filename.raw()+ ".baby");
            if(!file.exists()){
                throw new IllegalStateException("File '" + filename.raw()+ ".baby" + "' cannot be found");
            }
            if(ctx.call_option_1().USING() != null){
                var options = ctx.call_option_1().by_clause();
                for (var option : options) {
                    if (option.by_reference() != null) {
                        if(option.by_reference().IDENTIFIER() == null || option.by_reference().atomic() != null) {
                            throw new IllegalStateException("An identifier must be used for BY REFERENCE option of CALL");
                        }
                        var identifier = symbolTable.resolveIdentifier(option.by_reference().IDENTIFIER().getText());
                        if(identifier.isEmpty()) {
                            throw new IllegalStateException("Identifier '" + option.by_reference().IDENTIFIER().getText() + "' is not an element or not declared");
                        }
                        args.add(identifier.get());
                    } else if (option.by_content() != null) {
                        if(option.by_content().atomic() != null) {
                            var atomic = (Atomic) visit(option.by_content().atomic());
                            if(atomic.getElement() != null) {
                                args.add(atomic.getElement());
                            } else if(atomic.getLiteral() != null) {
                                args.add(atomic.getLiteral());
                            }
                        }
                    } else if(option.by_value() != null) {
                        if(option.by_value().atomic() != null) {
                            var atomic = (Atomic) visit(option.by_value().atomic());
                            if(atomic.getElement() != null) {
                                args.add(atomic.getElement());
                            } else if(atomic.getLiteral() != null) {
                                args.add(atomic.getLiteral());
                            }
                        }
                    }
                }
            }

        } else if(ctx.call_option_2() != null){
            filename = new AlphanumericLiteral(ctx.call_option_2().file_name().getText());
            File file = new File(filename.raw()+ ".baby");
            if(!file.exists()){
                throw new IllegalStateException("File '" + filename.raw()+ ".baby" + "' cannot be found");
            }
            Processor processor = new Processor();
            try {
                Program program = processor.parse(String.valueOf(file));
            } catch (IOException e) {
                throw new IllegalStateException("Error parsing file '" + filename.raw() + ".baby': " + e.getMessage(), e);
            }
        }
        Call call = new Call(filename, args);
        procedures.add(call);
        return call;
    }


    @Override
    protected Node defaultResult() {
        return null;
    }

    @Override
    protected Node aggregateResult(Node aggregate, Node next) {
        return next != null ? next : aggregate;
    }

    @Override
    public Node visitLiteral(CoBabyBoL.LiteralContext ctx) {
        if (ctx.numeric_literal() != null) {
            return visit(ctx.numeric_literal());
        }
        return visit(ctx.alphanumeric_literal());
    }
}

