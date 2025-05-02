package ut.pp.AST.statements;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;
import ut.pp.parser.antlr4.PotatoParser;
import ut.pp.exceptions.TypeMismatchError;

/**
 * A AST_Node object which represent a while-statement from within our grammar
 */
public class While extends Statement {

    private final Expression condition;
    private final Scope loopBody;

    /**
     * The constructor for a while statement in our ast it stores the different child nodes of a while statement
     * namely its {@link java.util.concurrent.locks.Condition} and {@link Scope}
     *
     * @param ctx         the {@link ut.pp.parser.antlr4.PotatoParser.WhileContext} of this while loop, this is mainly used for error handling as we want to print out
     *                    the location in the code where an error occurred
     * @param parentScope the scope in which this {@link Statement} is located
     * @param condition   an {@link Expression} which resolves to a boolean, while this boolean is true the while loop will
     *                    continue repeating
     * @param innerScope  the inner {@link Scope} of the while loop, this is a scope which is run every iteration of the while
     *                    loop. It can manipulate and declare variables.
     * @throws TypeMismatchError this error is thrown when the type of the {@link Expression} "condition" is not a boolean
     */
    public While(PotatoParser.WhileContext ctx, Scope parentScope, Expression condition, Scope innerScope) throws TypeMismatchError, NullPointerException {
        super(parentScope);
        this.condition = condition;
        this.loopBody = innerScope;
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
        Code loopCode = this.loopBody.generateCode();
        code.appendWhileStatement(conditionCode, loopCode);
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
        loopBody.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "While";
    }

//
}

