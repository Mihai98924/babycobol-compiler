package ut.pp.AST.statements.constituents.expressions.equality;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class Equal extends Equality {

    public Equal(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError {
        super(ctx, left, right);
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        generateCode(code, "Equal");
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Equal";
    }

}
