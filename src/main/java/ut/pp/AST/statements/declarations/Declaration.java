package ut.pp.AST.statements.declarations;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.assigments.Assignment;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.exceptions.TypeMismatchError;

/**
 * This is the abstract class for declarations, A declaration is when a variable is assigned to an expression
 * and is represented by objects of this class. It is extended by the two kinds of declarations:
 * ArrayDeclaration and SingleDeclaration
 */
public abstract class Declaration extends Assignment {
    boolean isArray;
    ParserRuleContext variableContext;


    /**
     * The constructor for a declaration, it is called only as the super of its extensions "ArrayDeclaration"
     * and "SingleDeclaration".
     *
     * @param ctx        the {@link ParserRuleContext} of the variable, it is required mainly for the purpose of error reporting
     * @param variable   the {@link Variable} this declaration is declaring
     * @param expression the {@link Expression} to which the new variable is being assigned
     * @throws TypeMismatchError this error is thrown when the type of the variable is not equal to the type of the
     *                           expression which is being assigned to it
     */
    public Declaration(Scope parentScope, ParserRuleContext ctx, Variable variable, Expression expression) throws TypeMismatchError {
        super(ctx, parentScope, variable, expression);
        this.variableContext = ctx;
    }

    /**
     * A simple getter method that retrieves the column number at which this declaration begins
     *
     * @return the column number at which this declaration begins
     */
    public int getColumn() {
        return variableContext.getStart().getCharPositionInLine();
    }

    /**
     * A simple getter method that retrieves the line number at which this declaration begins
     *
     * @return the line number at which this declaration begins
     */
    public int getLine() {
        return variableContext.getStart().getLine();
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        variable.buildGraph(graphBuilder);
        toAssign.buildGraph(graphBuilder);
        graphBuilder.pop();
    }


}

