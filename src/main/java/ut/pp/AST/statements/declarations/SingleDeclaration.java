package ut.pp.AST.statements.declarations;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.exceptions.TypeMismatchError;

public class SingleDeclaration extends Declaration {


    /**
     * @param parentScope the {@link Scope} in which this declaration occurs
     * @param ctx         the {@link ParserRuleContext} of the variable, it is required mainly for the purpose of error reporting
     * @param variable    the {@link Variable} this declaration is declaring
     * @param expression  the {@link Expression} to which the new variable is being assigned
     * @throws TypeMismatchError an error which is thrown for the different types of type mismatches that can occur during
     *                           declaration
     */
    public SingleDeclaration(Scope parentScope, ParserRuleContext ctx, Variable variable, Expression expression) throws TypeMismatchError {
        super(parentScope, ctx, variable, expression);
        this.isArray = false;
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Declaration";
    }
}
