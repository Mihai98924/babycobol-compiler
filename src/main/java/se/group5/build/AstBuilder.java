package se.group5.build;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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
import se.group5.parser.CoBabyBoLLexer;
import se.group5.processor.Processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        if (ctx.function() != null) {
            for (var function : ctx.function()) {
                visitFunction(function);
            }
        }
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
            Identifier ref = (Identifier) visit(ctx.like_clause(0).identifier());

            String partiallyQualifiedIdentifier = ref.value();
            List<String> identifierPath = Arrays.stream(partiallyQualifiedIdentifier.split("\\.")).toList();
            String identifier = identifierPath.get(identifierPath.size() - 1);

            String fullyQualifiedIdentifier = symbolTable.getFullyQualifiedIdentifier(identifierPath, identifier);

            pic = symbolTable.resolve(fullyQualifiedIdentifier)
                    .filter(DataElement.class::isInstance)
                    .map(DataElement.class::cast).map(DataElement::picture)
                    .orElse(null);

            if (pic == null) {
                String identifierPrefix = fullyQualifiedIdentifier != null &&
                        fullyQualifiedIdentifier.indexOf('.') != -1 ?
                        fullyQualifiedIdentifier.substring(0, fullyQualifiedIdentifier.lastIndexOf('.')) :
                        fullyQualifiedIdentifier;

                id = new Identifier(identifierPrefix + "." + id.value());
            }
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
            String fullyQualifiedIdentifier = symbolTable.getFullyQualifiedIdentifier(identifier.value());
            if (fullyQualifiedIdentifier == null) {
                throw new IllegalStateException("Identifier '" + identifier.value() +
                        "' is ambiguous or not declared!");
            }

            Optional<DataDefinition> def = symbolTable.resolve(fullyQualifiedIdentifier);
            if (def.isEmpty() || !(def.get() instanceof DataElement || def.get() instanceof DataGroup))
                throw new IllegalStateException("Identifier reference in Atomic '" + identifier.value()
                        + "' is not a data definition or not declared");
            if (def.get() instanceof DataGroup)
                return new Atomic((DataGroup) def.get());
            else
                return new Atomic((DataElement) def.get());
        }

        Literal literal = (Literal) visit(ctx.literal());
        return new Atomic(literal);
    }

    private int recurrenceLevel = 0;

    @Override
    public Node visitIdentifier(CoBabyBoL.IdentifierContext ctx) {
        recurrenceLevel++;
        if (ctx.identifier() != null) {
            Identifier identifier = (Identifier) visit(ctx.identifier());
            recurrenceLevel--;
            String currentIdentifierText = ctx.IDENTIFIER().getText();
            String fullIdentifier = identifier.value() + "." + currentIdentifierText;
            if (recurrenceLevel == 0) {
                List<String> pathElements = Arrays.stream(fullIdentifier.split("\\.")).toList();
                boolean isIdentifierAmbiguous =
                        symbolTable.getFullyQualifiedIdentifier(pathElements, currentIdentifierText) == null;
                if (isIdentifierAmbiguous) {
                    throw new IllegalStateException("Identifier '" + currentIdentifierText + "' is ambiguous in the context of " + pathElements);
                }
            }
            return new Identifier(
                    fullIdentifier
            );
        }
        recurrenceLevel--;
        return new Identifier(ctx.IDENTIFIER().getText());
    }


    @Override
    public Node visitAccept(CoBabyBoL.AcceptContext ctx) {
        List<Identifier> targets = ctx.identifier().stream()
                .map(idCtx -> {
                    Identifier id = (Identifier) visit(idCtx);

                    String partiallyQualifiedIdentifier = id.value();
                    List<String> identifierPath = Arrays.stream(partiallyQualifiedIdentifier.split("\\.")).toList();
                    String identifier = identifierPath.get(identifierPath.size() - 1);

                    String fullyQualifiedIdentifier = symbolTable.getFullyQualifiedIdentifier(identifierPath, identifier);
                    return new Identifier(fullyQualifiedIdentifier);
                })
                .toList();
        Accept accept = new Accept(targets);
        procedures.add(accept);
        return accept;
    }


    @Override
    public Arithmetic visitAdd(CoBabyBoL.AddContext ctx) {
        List<Atomic> addends = ctx.add_atomic().atomic().stream()
                .map(a -> (Atomic) visitAtomic(a)).toList();
        Atomic target = (Atomic) visitAtomic(ctx.to_atomic().atomic());

        List<DataDefinition> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream().map(i -> (DataDefinition) symbolTable.resolve(
                        symbolTable.getFullyQualifiedIdentifier(i.getText())).get()).toList();
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

        List<DataDefinition> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream()
                .map(g -> symbolTable
                        .resolve(g.getText())
                        .get())
                .toList();

        DataElement remainder = null;
        if (ctx.remainder() != null) {
            String remName = ctx.remainder().IDENTIFIER().getText();
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
        List<Atomic> subtrahends = ctx.sub_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        List<Atomic> minuends = ctx.from_atomic().atomic()
                .stream()
                .map(a -> (Atomic) visitAtomic(a))
                .toList();

        List<DataDefinition> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream()
                .map(i -> symbolTable
                        .resolve(i.getText())
                        .get())
                .toList();

        Arithmetic subtract = Arithmetic.subtract(subtrahends, minuends, giving);
        procedures.add(subtract);
        return subtract;
    }

    public Node visitMove(CoBabyBoL.MoveContext ctx) {
        Object moveType;
        if (ctx.move_arg().HIGH_VALUES() != null) {
            moveType = Move.MoveType.HIGH_VALUES;
        } else if (ctx.move_arg().LOW_VALUES() != null) {
            moveType = Move.MoveType.LOW_VALUES;
        } else if (ctx.move_arg().SPACES() != null) {
            moveType = Move.MoveType.SPACES;
        } else {
            moveType = (Atomic) visitAtomic(ctx.move_arg().atomic());
        }

        List<Identifier> targets = ctx.identifier().stream().map(
                t -> {
                    var identifier = (Identifier) visitIdentifier(t);
                    var name = identifier.value();
                    var fullyQualifiedIdentifier = symbolTable.getFullyQualifiedIdentifier(identifier.value());
                    identifier = symbolTable.resolveIdentifier(fullyQualifiedIdentifier).get();
                    var dataRepresentation = symbolTable.resolve(fullyQualifiedIdentifier);

                    if (dataRepresentation.isEmpty())
                        throw new IllegalStateException("Identifier '" + name + "' is not defined in the program");

                    if (moveType instanceof Atomic moveAtomic) {
                        if (dataRepresentation.get() instanceof DataElement dataElement) {
                            var repr = dataElement.picture();
                            if (moveAtomic.isLiteral() && !repr.matches(moveAtomic.getLiteral().raw())) {
                                throw new IllegalStateException("Move with literal '" +
                                        moveAtomic.getLiteral().raw() + "' does not match target '" + repr + "'");

                            } else if (moveAtomic.isElement() && !repr.matches(moveAtomic.getElement().picture().toString())) {
                                throw new IllegalStateException("Move with identifier '" +
                                        moveAtomic.getElement().name() + "' does not match target '" + identifier + "'");
                            }
                        } else if (dataRepresentation.get() instanceof DataGroup dataGroup) {
                            for (DataDefinition child : dataGroup.children.values()) {
                                if (child instanceof DataElement dataElement) {
                                    var repr = dataElement.picture();
                                    if (moveAtomic.isLiteral() && !repr.matches(moveAtomic.getLiteral().raw())) {
                                        throw new IllegalStateException("Move with literal '" +
                                                moveAtomic.getLiteral().raw() + "' does not match target '" + repr + "'");
                                    } else if (moveAtomic.isElement() && !repr.matches(moveAtomic.getElement().picture().toString())) {
                                        throw new IllegalStateException("Move with identifier '" +
                                                moveAtomic.getElement().name() + "' does not match target '" + identifier + "'");
                                    }
                                }
                            }
                        }
                    }
                    return identifier;
                }
        ).toList();
        Move move = new Move(moveType, targets);
        procedures.add(move);
        return move;
    }

    public Node visitCall(CoBabyBoL.CallContext ctx) {

        var filename = new AlphanumericLiteral(ctx.file_name().getText()).value().replace("\"", "");
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalStateException("File '" + filename + "' cannot be found");
        }
        Processor processor = new Processor();
        Program program = null;
        try {
            program = processor.parseFile(file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Error processing file '" + filename + "': " + e.getMessage(), e);
        }
        HashMap<Call.CallArgs, Object> args = new HashMap<>();
        if (ctx.call_function().isEmpty()) {
            if (ctx.using_clause() != null) {
                var options = ctx.using_clause().by_with_as();
                for (var option : options) {
                    if (option.as_clause().size() > 1) {
                        throw new IllegalStateException("AS clause is not supported in CALL without a function name");
                    }
                    if (option.by_clause() != null) {
                        if (option.by_clause().by_reference() != null) {
                            if (option.by_clause().by_reference().atomic().identifier().IDENTIFIER() == null) {
                                throw new IllegalStateException("An identifier must be used for BY REFERENCE option of CALL");
                            } else {
                                args.put(Call.CallArgs.BY_REFERENCE, visitAtomic(option.by_clause().by_reference().atomic()));
                            }
                        }
                        if (option.by_clause().by_value() != null) {
                            args.put(Call.CallArgs.BY_VALUE, visitAtomic(option.by_clause().by_value().atomic()));
                        }
                        if (option.by_clause().by_content() != null) {
                            args.put(Call.CallArgs.BY_CONTENT, visitAtomic(option.by_clause().by_content().atomic()));
                        }
                    }
                }
            }
        }
        Call call = new Call(program, args);
        procedures.add(call);
        return call;
    }

    @Override
    public Node visitGoto(CoBabyBoL.GotoContext ctx) {
        var identifier = ctx.procedure_name().IDENTIFIER().getText();
        try {
            Function function = symbolTable.resolveFunc(identifier).get();
            GoTo goTo = new GoTo(function);
            procedures.add(goTo);
            return goTo;
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Goto label '" + identifier + "' is not defined in the program");
        }
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

    @Override
    public Node visitFile_name(CoBabyBoL.File_nameContext ctx) {
        return visit(ctx.alphanumeric_literal());
    }

    @Override
    public Node visitCopy(CoBabyBoL.CopyContext ctx) {
        try {
            Literal fileNameLiteral = (Literal) visit(ctx.file_name());
            String fileName = fileNameLiteral.raw();
            fileName = fileName.substring(1, fileName.length() - 1);

            Path path = Paths.get("/Users/jakubstepniewski/Downloads/recombined_formatted/" + fileName);
            String content = Files.readString(path);

            if (ctx.REPLACING() != null) {
                List<CoBabyBoL.Argument_literalContext> args = ctx.argument_literal();
                for (int i = 0; i < args.size(); i += 2) {
                    String from = args.get(i).ARG_LIT_ATOMIC().getText();
                    from = from.substring(0, from.length() - 3);

                    String to = args.get(i + 1).ARG_LIT_ATOMIC().getText();
                    to = to.substring(0, to.length() - 3);

                    content = content.replaceAll("(?<=\\s)" + from + "(?=\\s)", to);
                }
            }

            CharStream charStream = CharStreams.fromString(content);
            CoBabyBoLLexer lexer = new CoBabyBoLLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CoBabyBoL parser = new CoBabyBoL(tokens);

            ParseTree tree = parser.program();

            return this.visit(tree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Node visitFunction(CoBabyBoL.FunctionContext ctx) {
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
        visitChildren(ctx);
        Function function = new Function(
                symbolTable,
                procedures
        );
        procedures.clear();
        symbolTable.registerFunc(identifier, function);
        return function;
    }
}

