package ut.pp;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ut.pp.AST.AST_Node;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.Program;
import ut.pp.AST.statements.*;
import ut.pp.AST.statements.constituents.*;
import ut.pp.AST.statements.assigments.ArrayElementAssignment;
import ut.pp.AST.statements.assigments.Assignment;
import ut.pp.AST.statements.constituents.expressions.arrays.Concatenation;
import ut.pp.AST.statements.constituents.expressions.arrays.Length;
import ut.pp.AST.statements.constituents.expressions.arrays.NewArray;
import ut.pp.AST.statements.constituents.expressions.logical.LogicalAnd;
import ut.pp.AST.statements.constituents.expressions.logical.LogicalNot;
import ut.pp.AST.statements.constituents.expressions.logical.LogicalOr;
import ut.pp.AST.statements.declarations.*;
import ut.pp.AST.statements.constituents.expressions.*;
import ut.pp.AST.statements.constituents.expressions.equality.*;
import ut.pp.AST.statements.constituents.expressions.equality.orderable.*;
import ut.pp.AST.statements.constituents.expressions.literals.*;
import ut.pp.AST.statements.constituents.expressions.math.*;
import ut.pp.AST.statements.constituents.expressions.variable_access.*;
import ut.pp.AST.statements.threading.*;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.*;
import ut.pp.parser.antlr4.*;
import ut.pp.parser.antlr4.PotatoParser.*;

import java.util.*;

public class Compiler extends PotatoBaseListener {
    private final ParseTreeWalker treeWalker;
    private SyntaxErrorListener syntaxErrorListener;
    private ParseErrorListener parseErrorListener;

    public Compiler() {
        this.treeWalker = new ParseTreeWalker();
    }

    private ParseTreeProperty<AST_Node> nodes;
    private Program program;
    private SymbolTable symbolTable;
    private List<CodeError> errors;


    // === PARSING ============================================================

    private void reset() {
        this.nodes = new ParseTreeProperty<>();
        this.symbolTable = new SymbolTable();
        this.errors = new ArrayList<>();
        this.syntaxErrorListener = new SyntaxErrorListener(this.errors);
        this.parseErrorListener = new ParseErrorListener(this.errors);
    }

    public Code compile(CharStream stream) throws CompilerException {
        reset();

        PotatoLexer lexer = new PotatoLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(this.syntaxErrorListener);

        TokenStream tokens = new CommonTokenStream(lexer);
        PotatoParser parser = new PotatoParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(this.parseErrorListener);

        ParseTree parseTree = parser.program();
        treeWalker.walk(this, parseTree);

        if (this.errors.size() > 0)
            throw new CompilerException(this.errors);

        if (Main.DEBUG_MODE()) {
            System.out.println(program.toString());
        }

        return this.program.generateCode();
    }

    @Override
    public void exitProgram(ProgramContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>(ctx.statement().size());
        for (StatementContext context : ctx.statement()) {
            statements.add((Statement) nodes.get(context));
        }
        symbolTable.addStatements(statements);
        program = new Program(symbolTable.getScope().getStatements());

    }

    @Override
    public void exitStatement(StatementContext ctx) {
        nodes.put(ctx, nodes.get(ctx.getChild(0)));
    }

    @Override
    public void exitFork(ForkContext ctx) {
        Scope parentScope = symbolTable.getScope();
        ParallelScope parallelScope = (ParallelScope) nodes.get(ctx.parallelScope());
        ArrayList<Statement> beforeJoin = new ArrayList<>(ctx.statement().size());
        for (int i = 0; i < ctx.statement().size(); i++) {
            beforeJoin.add((Statement) nodes.get(ctx.statement(i)));
        }
        try {
            Fork fork = new Fork(ctx, parentScope, parallelScope, beforeJoin);
            nodes.put(ctx, fork);
        } catch (ThreadError error) {
            this.errors.add(error);
        }
    }

    @Override
    public void enterParallelScope(ParallelScopeContext ctx) {
        symbolTable.openParallelScope();
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitParallelScope(ParallelScopeContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) nodes.get(statementContext));
        }
        symbolTable.addStatements(statements);
        nodes.put(ctx, symbolTable.getScope());
        symbolTable.closeScope();
    }

    // === STRUCTURE ==========================================================

    @Override
    public void exitIf(IfContext ctx) {
        Expression condition = (Expression) nodes.get(ctx.expression());
        Scope scope = symbolTable.getScope();
        Scope trueScope = (Scope) nodes.get(ctx.trueScope);

        try {
            if (trueScope.hasThreadable()) {
                throw new ThreadError(ctx);
            }
            If ifObj;
            if (ctx.elseScope == null) {
                ifObj = new If(ctx, condition, scope, trueScope);
            } else {
                Scope elseScope = (Scope) nodes.get(ctx.elseScope);
                if (elseScope.hasThreadable()) {
                    throw new ThreadError(ctx);
                }
                ifObj = new If(ctx, condition, scope, trueScope, elseScope);
            }
            nodes.put(ctx, ifObj);
        } catch (CodeError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }


    @Override
    public void exitWhile(WhileContext ctx) {
        try {
            Scope parentScope = symbolTable.getScope();
            Expression condition = (Expression) nodes.get(ctx.expression());
            Scope innerScope = (Scope) nodes.get(ctx.scope());
            if (innerScope.hasThreadable()) {
                throw new ThreadError(ctx);
            }
            While whileObj = new While(ctx, parentScope, condition, innerScope);
            nodes.put(ctx, whileObj);
        } catch (CodeError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }


    @Override
    public void enterScope(ScopeContext ctx) {
        symbolTable.openScope();
    }

    @Override
    public void exitScope(ScopeContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) nodes.get(statementContext));
        }
        symbolTable.addStatements(statements);
        nodes.put(ctx, symbolTable.getScope());
        symbolTable.closeScope();
    }

    @Override
    public void exitPrint(PrintContext ctx) {
        Expression expression = (Expression) this.nodes.get(ctx.expression());
        boolean printNewLine = ctx.PRINT_NL() != null;
        Scope parentScope = symbolTable.getScope();
        Print print = new Print(parentScope, expression, printNewLine);
        nodes.put(ctx, print);
    }

    @Override
    public void exitInput(InputContext ctx) {
        String request = ctx.request.getText().replace('"', ' ');
        Input input = new Input(request);
        nodes.put(ctx, input);
    }

    // === DECLARATIONS =======================================================

    @Override
    public void exitPrimitiveDeclaration(PrimitiveDeclarationContext ctx) {
        try {
            String id = ctx.IDENTIFIER().getText();
            Expression expression = (Expression) nodes.get(ctx.expression());
            Type type = ((Expression) nodes.get(ctx.expression())).getType();
            Scope scope = symbolTable.getScope();
            boolean isShared = ctx.SHARED() != null;
            Variable variable = new Variable(ctx, type, id, isShared, 1);
            symbolTable.put(variable);
            SingleDeclaration singleDeclaration = new SingleDeclaration(scope, ctx, variable, expression);
            nodes.put(ctx, singleDeclaration);
        } catch (CodeError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitStringDeclaration(StringDeclarationContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        boolean isShared = ctx.SHARED() != null;
        Scope scope = symbolTable.getScope();
        try {
            Expression expression = (Expression) nodes.get(ctx.expression());
            Variable variable = new Variable(ctx, Type.CHARACTER, id, isShared, expression.getSize());
            symbolTable.put(variable);
            ArrayDeclaration arrayDeclaration = new ArrayDeclaration(scope, ctx, variable, expression);
            nodes.put(ctx, arrayDeclaration);
        } catch (CodeError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitLockDeclaration(LockDeclarationContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Scope parentScope = symbolTable.getScope();
        try {
            Lock lock = new Lock(ctx, id);
            symbolTable.put(lock);
            LockDeclaration lockDeclaration = new LockDeclaration(parentScope, lock);
            nodes.put(ctx, lockDeclaration);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }


    @Override
    public void exitSynchronizedBlock(SynchronizedBlockContext ctx) {
        String lockID = ctx.IDENTIFIER().getText();
        Scope parentScope = symbolTable.getScope();
        try {
            Variable lock = symbolTable.getVariable(lockID, ctx);
            Scope syncBody = (Scope) nodes.get(ctx.scope());
            SynchronizedBlock synchronizedBlock = new SynchronizedBlock(parentScope, lock, syncBody, ctx);
            nodes.put(ctx, synchronizedBlock);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitAssignVariable(AssignVariableContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        try {
            Variable variable = symbolTable.getVariable(id, ctx);
            Expression expression = (Expression) nodes.get(ctx.expression());
            Assignment assignment = new Assignment(ctx, symbolTable.getScope(), variable, expression);
            nodes.put(ctx, assignment);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }

    }

    @Override
    public void exitAssignArrayElement(AssignArrayElementContext ctx) {
        super.exitAssignArrayElement(ctx);
        String id = ctx.IDENTIFIER().getText();
        Expression index = (Expression) nodes.get(ctx.index);
        try {
            Variable variable = symbolTable.getVariable(id, ctx);
            Expression expression = (Expression) nodes.get(ctx.expr);
            ArrayElementAssignment arrayElementAssignment = new ArrayElementAssignment(ctx, symbolTable.getScope(), variable, expression, index);
            nodes.put(ctx, arrayElementAssignment);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }


    @Override
    public void exitArrayDeclaration(ArrayDeclarationContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        boolean isShared = ctx.SHARED() != null;
        Type primitive = (Type) nodes.get(ctx.type());
        Scope scope = symbolTable.getScope();

        Expression expression;
        if (ctx.expression() == null) {
            expression = (Expression) nodes.get(ctx.array_literal());
        } else {
            expression = (Expression) nodes.get(ctx.expression());
        }

        try {
            Variable variable;
            variable = new Variable(ctx, primitive, id, isShared, expression.getSize());
            symbolTable.put(variable);
            ArrayDeclaration arrayDeclaration = new ArrayDeclaration(scope, ctx, variable, expression);
            nodes.put(ctx, arrayDeclaration);
        } catch (ReDeclaredSymbolError | TypeMismatchError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }


    // === TOP LEVEL EXPRESSIONS ==============================================

    @Override
    public void exitExprLevel1(ExprLevel1Context ctx) {
        nodes.put(ctx, nodes.get(ctx.expression_l1()));
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitLogicalAnd(LogicalAndContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            LogicalAnd logicalAnd = new LogicalAnd(ctx, leftOperand, rightOperand);
            nodes.put(ctx, logicalAnd);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitLogicalOr(LogicalOrContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            LogicalOr logicalOr = new LogicalOr(ctx, leftOperand, rightOperand);
            nodes.put(ctx, logicalOr);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    // === LEVEL 1 EXPRESSIONS ================================================

    @Override
    public void exitExprLevel2(ExprLevel2Context ctx) {
        nodes.put(ctx, nodes.get(ctx.expression_l2()));
    }

    @Override
    public void exitEquals(EqualsContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            Equal equal = new Equal(ctx, leftOperand, rightOperand);
            nodes.put(ctx, equal);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitNotEquals(NotEqualsContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            NotEquals notEquals = new NotEquals(ctx, leftOperand, rightOperand);
            nodes.put(ctx, notEquals);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitLessThan(LessThanContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            LessThan lessThan = new LessThan(ctx, leftOperand, rightOperand);
            nodes.put(ctx, lessThan);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitLessThanEquals(LessThanEqualsContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            LessThanEquals lessThanEquals = new LessThanEquals(ctx, leftOperand, rightOperand);
            nodes.put(ctx, lessThanEquals);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitGreaterThan(GreaterThanContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            GreaterThan greaterThan = new GreaterThan(ctx, leftOperand, rightOperand);
            nodes.put(ctx, greaterThan);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitGreaterThanEquals(GreaterThanEqualsContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            GreaterThanEquals greaterThanEquals = new GreaterThanEquals(ctx, leftOperand, rightOperand);
            nodes.put(ctx, greaterThanEquals);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }


    // === LEVEL 2 EXPRESSIONS ================================================

    @Override
    public void exitExprLevel3(ExprLevel3Context ctx) {
        nodes.put(ctx, nodes.get(ctx.expression_l3()));
    }

    @Override
    public void exitAddition(AdditionContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            Addition addition = new Addition(ctx, leftOperand, rightOperand);
            nodes.put(ctx, addition);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitSubtraction(SubtractionContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            Subtraction subtraction = new Subtraction(ctx, leftOperand, rightOperand);
            nodes.put(ctx, subtraction);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    // === LEVEL 3 EXPRESSIONS ================================================

    @Override
    public void exitExprLevel4(ExprLevel4Context ctx) {
        nodes.put(ctx, nodes.get(ctx.expression_l4()));
    }

    @Override
    public void exitMultiplication(MultiplicationContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            Multiplication multiplication = new Multiplication(ctx, leftOperand, rightOperand);
            nodes.put(ctx, multiplication);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitConcatenation(ConcatenationContext ctx) {
        try {
            Expression leftOperand = (Expression) nodes.get(ctx.left);
            Expression rightOperand = (Expression) nodes.get(ctx.right);
            Concatenation concatenation = new Concatenation(ctx, leftOperand, rightOperand);
            nodes.put(ctx, concatenation);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    // === LEVEL 3 EXPRESSIONS ================================================

    @Override
    public void exitExprLevel5(ExprLevel5Context ctx) {
        nodes.put(ctx, nodes.get(ctx.expression_l5()));
    }

    @Override
    public void exitLiteralValue(LiteralValueContext ctx) {
        nodes.put(ctx, nodes.get(ctx.literal()));
    }

    @Override
    public void exitVariableCall(VariableCallContext ctx) {
        nodes.put(ctx, nodes.get(ctx.variable()));
    }

    @Override
    public void exitParenthesis(ParenthesisContext ctx) {
        nodes.put(ctx, nodes.get(ctx.expression()));
    }

    @Override
    public void exitNegation(NegationContext ctx) {
        try {
            Expression operand = (Expression) nodes.get(ctx.expression_l5());
            nodes.put(ctx, new Negation(ctx, operand));
        } catch (CodeError e) {
            this.errors.add(e);
        }
    }

    @Override
    public void exitLogicNot(LogicNotContext ctx) {
        try {
            Expression operand = (Expression) nodes.get(ctx.expression_l5());
            nodes.put(ctx, new LogicalNot(ctx, operand));
        } catch (CodeError e) {
            this.errors.add(e);
        }
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitLength(LengthContext ctx) {
        try {
            Expression arrayExpression = (Expression) nodes.get(ctx.arrayExpression);
            Length length = new Length(ctx, arrayExpression);
            nodes.put(ctx, length);
        } catch (CodeError e) {
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }


    @Override
    public void exitUserInput(UserInputContext ctx) {
        nodes.put(ctx, nodes.get(ctx.input()));
    }

    // === TYPES ==============================================================

    @Override
    public void exitIntType(IntTypeContext ctx) {
        nodes.put(ctx, Type.INTEGER);
    }

    @Override
    public void exitBoolType(BoolTypeContext ctx) {
        nodes.put(ctx, Type.BOOLEAN);
    }

    @Override
    public void exitCharType(CharTypeContext ctx) {
        nodes.put(ctx, Type.CHARACTER);
    }


    // === VARIABLES ==========================================================

    @Override
    public void exitVariableAccess(VariableAccessContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        try {
            VariableAccess variableAccess = symbolTable.getVariableAccess(id, ctx);
            nodes.put(ctx, variableAccess);
        } catch (UnknownSymbolError e) {  // Might be thrown if symbol is null.
            this.errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitArrayAccess(ArrayAccessContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        try {
            Expression index = (Expression) nodes.get(ctx.expression());
            ArrayElementAccess arrayElementAccess = symbolTable.getArrayVariableAccess(id, ctx, index);
            nodes.put(ctx, arrayElementAccess);
        } catch (CodeError error) {
            this.errors.add(error);
        } catch (NullPointerException ignored) {
        }
    }

    // === LITERALS ===========================================================

    @Override
    public void exitDecIntLiteral(DecIntLiteralContext ctx) {
        nodes.put(ctx, new IntegerLiteral(10, ctx.DEC_INT_LITERAL().getText()));
    }

    @Override
    public void exitBinIntLiteral(BinIntLiteralContext ctx) {
        nodes.put(ctx, new IntegerLiteral(2, ctx.BIN_INT_LITERAL().getText().substring(2)));
    }


    @Override
    public void exitHexIntLiteral(HexIntLiteralContext ctx) {
        nodes.put(ctx, new IntegerLiteral(16, ctx.HEX_INT_LITERAL().getText().substring(2)));
    }

    @Override
    public void exitBoolLiteral(BoolLiteralContext ctx) {
        nodes.put(ctx, new BoolLiteral(ctx));
    }

    @Override
    public void exitCharLiteral(CharLiteralContext ctx) {
        this.nodes.put(ctx, new CharLiteral(ctx));
    }

    @Override
    public void exitStringLiteral(StringLiteralContext ctx) {
        String string = ctx.STRING_LITERAL().toString();
        string = string.substring(1, string.length() - 1);
        ArrayList<Expression> elements = new ArrayList<>(string.length());
        for (int i = 0; i < string.length(); i++) {
            elements.add(new CharLiteral(string.charAt(i)));
        }
        try {
            this.nodes.put(ctx, new StringLiteral(ctx, elements));
        } catch (TypeMismatchError e) {
            errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void exitArrayLiteral(ArrayLiteralContext ctx) {
        List<ExpressionContext> expressions = ctx.expression();
        ArrayList<Expression> expressionArrayList = new ArrayList<>();
        for (ExpressionContext expressionContext : expressions) {
            expressionArrayList.add((Expression) nodes.get(expressionContext));
        }
        try {
            nodes.put(ctx, new ArrayLiteral(ctx, expressionArrayList));
        } catch (CodeError e) {
            errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * @param ctx the parse tree
     */
    @Override
    public void exitNewArray(NewArrayContext ctx) {
        Expression arraySize = (Expression) nodes.get(ctx.expression());
        Type type = (Type) nodes.get(ctx.type());
        try {
            NewArray newArray = new NewArray(ctx, type, arraySize);
            nodes.put(ctx, newArray);
        } catch (CodeError e) {
            errors.add(e);
        } catch (NullPointerException ignored) {
        }
    }


    /**
     * Creates an empty {@link GraphBuilder} instance and starts the walk of the AST at the root node
     * in order to procedurally generate the contents of a .dot file
     *
     * @return A {@link StringBuilder} instance containing a .dot file generated from the AST
     */
    public StringBuilder generateGraph() {
        GraphBuilder graphBuilder = this.program.graphBuilder();
        graphBuilder.writeDot();
        return graphBuilder.getDotString();
    }

}
