package ut.pp.AST.statements.assigments;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class ArrayElementAssignment extends Assignment {
    Expression index;

    /**
     *
     * @param ctx        the {@link ParserRuleContext} of the variable, it is required mainly for the purpose of error reporting
     * @param parentScope The {@link Scope} in which this array is being declared
     * @param variable
     * @param expression
     * @param index
     * @throws TypeMismatchError
     */
    public ArrayElementAssignment(ParserRuleContext ctx, Scope parentScope, Variable variable, Expression expression, Expression index) throws TypeMismatchError, NullPointerException {
        super(ctx, parentScope, variable, expression);
        index.matchingType(Type.INTEGER, ctx);
        this.index = index;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        toAssign.generateCode(code);
        index.generateCode(code);
        code.appendArrayElementSet(variable.getMemAddress(), variable.isShared());
    }
}
