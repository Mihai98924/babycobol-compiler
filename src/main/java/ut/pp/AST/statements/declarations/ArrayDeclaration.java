package ut.pp.AST.statements.declarations;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.exceptions.TypeMismatchError;

/**
 * This object represents an array declaration in our parse tree
 */
public class ArrayDeclaration extends Declaration {


    /**
     * The constructor for an ArrayDeclaration its arguments consist of all the different child nodes in our ast
     *
     * @param parentScope The {@link Scope} in which this array is being declared
     * @param ctx         the {@link ParserRuleContext} of the ArrayDeclaration, for error handling purposes
     * @param variable    The {@link Variable} to which to assign this array
     * @param expression  The {@link Expression} being assigned to this array, a TypeMismatch will occur if the expression
     *                    is not an array.
     * @throws TypeMismatchError this error is thrown when the expression being assigned to this array is not an
     *                           ArrayExpression, or when the type of the variable doesn't match the type of the
     *                           expression.
     */
    public ArrayDeclaration(Scope parentScope, ParserRuleContext ctx, Variable variable, Expression expression) throws TypeMismatchError {
        super(parentScope, ctx, variable, expression);
        this.isArray = true;
    }


    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Array Declaration";
    }
}
