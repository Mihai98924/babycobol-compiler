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
import se.group5.processor.Processor;
import se.group5.parser.CoBabyBoLLexer;

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
            var paragraphs = ctx.function().stream().map(
                    t-> t.IDENTIFIER().getText()
            ).toList();
            for (String name : paragraphs) {
                if (symbolTable.resolve(name).isPresent()) {
                    throw new IllegalStateException("Function with name '" + name + "' already exists as an identifier.");
                }
                symbolTable.registerParagraph(name);
            }
            ctx.function().forEach(this::visit);
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

            if(pic == null)
                id = new Identifier(fullyQualifiedIdentifier + "." + id.value());
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

        procedures.add(display, ctx);
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

    private static int recurrenceLevel = 0;
    @Override
    public Node visitIdentifier(CoBabyBoL.IdentifierContext ctx) {
        recurrenceLevel++;
        if (ctx.identifier() != null) {
            Identifier identifier = (Identifier) visit(ctx.identifier());
            recurrenceLevel--;
            String currentIdentifierText = ctx.IDENTIFIER().getText();
            String fullIdentifier = identifier.value() + "." + currentIdentifierText;
            if(recurrenceLevel == 0)
            {
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
        procedures.add(accept, ctx);
        return accept;
    }


    @Override
    public Arithmetic visitAdd(CoBabyBoL.AddContext ctx) {
        List<Atomic> addends = ctx.add_atomic().atomic().stream()
                .map(a -> (Atomic) visitAtomic(a)).toList();
        Atomic target = (Atomic) visitAtomic(ctx.to_atomic().atomic());

        List<DataElement> giving = ctx.giving_identifier_list() == null
                ? List.of()
                : ctx.giving_identifier_list().IDENTIFIER()
                .stream().map(i -> (DataElement) symbolTable.resolve(i.getText()).get()).toList();
        Arithmetic add = Arithmetic.add(addends, target, giving);
        procedures.add(add, ctx);
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
        procedures.add(divide, ctx);
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
        procedures.add(multiply, ctx);
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
        procedures.add(subtract, ctx);
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
                    var name = t.getText();
                    var identifier = symbolTable.resolveIdentifier(name);
                    Representation repr = ((DataElement) symbolTable.resolve(name).get()).picture();
                    if (moveType instanceof Atomic) {
                        if (((Atomic) moveType).getLiteral() != null && !repr.matches(((Atomic) moveType).getLiteral().raw())) {
                            throw new IllegalStateException("Move with literal '" + ((Atomic) moveType).getLiteral().raw() + "' does not match target '" + repr + "'");
                        } else if (((Atomic) moveType).getElement() != null && !repr.matches(((Atomic) moveType).getElement().picture().toString())) {
                            throw new IllegalStateException("Move with identifier '" + ((Atomic) moveType).getElement().name() + "' does not match target '" + identifier.get() + "'");
                        }
                    }
                    return identifier.get();
                }
        ).toList();
        Move move = new Move(moveType, targets);
        procedures.add(move, ctx);
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
        List<Object> args = new ArrayList<>();
        if (ctx.call_function().isEmpty()) {
            if (ctx.using_clause() != null) {
                var options = ctx.using_clause().by_with_as();
                System.err.println(options);
                for (var option : options) {
                    if (option.as_clause() != null) {
                        throw new IllegalStateException("AS clause is not supported in CALL without a function name");
                    }
                    if (option.by_clause() != null) {
                        if (option.by_clause().by_reference() != null && option.by_clause().by_reference().atomic().identifier().IDENTIFIER() == null) {
                            throw new IllegalStateException("An identifier must be used for BY REFERENCE option of CALL");
                        }
                    }
                }
            }
        } else {
            var callFunctions = ctx.call_function().stream().map(
                    t -> {
                        var name = t.getText();
                        return name;
                    }
            );
            var options = ctx.using_clause().by_with_as();
            if (ctx.using_clause().isEmpty() || options.isEmpty()) {
                throw new IllegalStateException("Call to function '" + filename + "' must have USING clause");
            }
            args.addAll(options);

        }
//        Call call = new Call(filename, args);
//        procedures.add(call);
//        return call;
        return null;
    }

    @Override
    public Node visitGoto(CoBabyBoL.GotoContext ctx) {
        var label = ctx.procedure_name().IDENTIFIER().getText();
        try {
            String paragraph = symbolTable.resolveParagraph(label).get();
            GoTo goTo = new GoTo(paragraph);
            procedures.add(goTo, ctx);
            return goTo;
        }
        catch (NoSuchElementException e) {
            throw new IllegalStateException("Goto label '" + label + "' is not defined in the program");
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
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

