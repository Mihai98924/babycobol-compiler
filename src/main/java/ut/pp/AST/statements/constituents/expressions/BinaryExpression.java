package ut.pp.AST.statements.constituents.expressions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public abstract class BinaryExpression extends Expression {
    protected Expression left;
    protected Expression right;

    /**
     * A abstract class for {@link BinaryExpression} expressions, a binary expression is defined as any expression
     * which has two (binary) {@link Expression} objects as its children
     *
     * @param ctx   the {@link ParserRuleContext} which we are doing type checking for, it is used to generate error
     *              objects
     * @param left  the left  child {@link Expression} object
     * @param right the right child {@link Expression} object
     * @throws TypeMismatchError this error is thrown if the types of the current {@link Expression} object does not
     *                           match the type of the {@link Expression} passed as an argument
     */
    public BinaryExpression(ParserRuleContext ctx, Type type, Expression left, Expression right) throws TypeMismatchError, NullPointerException {
        super(type, false);
        this.left = left;
        this.right = right;
        this.left.matchingType(this.right, ctx);
    }

    /**
     * A method which walks through the {@link ut.pp.AST.AST_Node}'s in our AST and generates our
     * sprockell code
     *
     * @param code     the {@link Code} object which is used to build out our sprockell instructions
     * @param operator the sprockell operator string for the different kinds of {@link BinaryExpression}'s
     */
    protected void generateCode(Code code, String operator) {
        this.left.generateCode(code);
        this.right.generateCode(code);
        code.appendBinaryExpression(operator);
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        this.left.buildGraph(graphBuilder);
        this.right.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

}
