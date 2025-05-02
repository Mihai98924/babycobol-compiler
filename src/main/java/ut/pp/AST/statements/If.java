package ut.pp.AST.statements;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;
import ut.pp.parser.antlr4.PotatoParser;
import ut.pp.exceptions.TypeMismatchError;

public class If extends Statement {

    private final Expression condition;
    private final boolean hasElse;
    private final Scope trueScope;
    private Scope elseScope;

    /**
     *
     * @param ctx        the {@link ParserRuleContext} of the variable, it is required mainly for the purpose of error reporting
     * @param condition
     * @param parentScope the scope in which this {@link Statement} is located
     * @param trueScope
     * @param elseScope
     * @throws TypeMismatchError
     */
    public If(PotatoParser.IfContext ctx, Expression condition, Scope parentScope, Scope trueScope, Scope elseScope) throws TypeMismatchError, NullPointerException {
        super(parentScope);
        this.condition = condition;
        this.trueScope = trueScope;
        this.elseScope = elseScope;
        this.hasElse = true;

        condition.matchingType(Type.BOOLEAN, ctx);
    }

    /**
     * The constructor for the extensions of our {@link ut.pp.AST.AST_Node} which implements the If statement in our
     * AST
     * @param ctx the {@link ut.pp.parser.antlr4.PotatoParser.IfContext} of our
     * @param condition   an {@link Expression} which resolves to a boolean, while this boolean is true the while loop will
     *                    continue repeating
     * @param parentScope the scope in which this {@link Statement} is located
     * @param trueScope
     * @throws TypeMismatchError
     */
    public If(PotatoParser.IfContext ctx, Expression condition, Scope parentScope, Scope trueScope) throws TypeMismatchError, NullPointerException {
        super(parentScope);
        this.condition = condition;
        this.trueScope = trueScope;
        this.hasElse = false;
        condition.matchingType(Type.BOOLEAN, ctx);
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        Code conditionCode = this.condition.generateCode();
        Code trueScopeCode = this.trueScope.generateCode();
        if (hasElse) {
            Code elseScopeCode = this.elseScope.generateCode();
            code.appendIfStatement(conditionCode, trueScopeCode, elseScopeCode);
        } else {
            code.appendIfStatement(conditionCode, trueScopeCode);
        }
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        condition.buildGraph(graphBuilder);
        trueScope.buildGraph(graphBuilder);
        if (hasElse) {
            elseScope.buildGraph(graphBuilder);
        }
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "If";
    }
}
