package ut.pp.AST.statements.constituents.expressions.arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class Concatenation extends BinaryExpression {


    public Concatenation(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError {
        super(ctx, right.getType(), left, right);
        this.isArray = true;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        right.generateCode(code);
        left.generateCode(code);
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Concatenation";
    }

    /**
     * An abstract function which must be declared for all extensions of the abstract class {@link Expression}
     *
     * @return an {@link Integer} representing the size of the {@link Expression} extensions
     */
    @Override
    public int getSize() {
        return this.left.getSize() + this.right.getSize();
    }
}
