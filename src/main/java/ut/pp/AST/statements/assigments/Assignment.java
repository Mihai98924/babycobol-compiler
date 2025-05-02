package ut.pp.AST.statements.assigments;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.Statement;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.Main;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class Assignment extends Statement {
    protected Variable variable;
    protected Expression toAssign;

    /**
     *
     * @param ctx        the {@link ParserRuleContext} of the variable, it is required mainly for the purpose of error reporting
     * @param parentScope The {@link Scope} in which this array is being declared
     * @param variable
     * @param expression
     * @throws TypeMismatchError
     */
    public Assignment(ParserRuleContext ctx, Scope parentScope, Variable variable, Expression expression) throws TypeMismatchError, NullPointerException {
        super(parentScope);
        this.variable = variable;
        this.toAssign = expression;
        variable.matchingType(expression, ctx);
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        int memAddress = variable.getMemAddress();
        int assignSize = toAssign.getSize();
        boolean isShared = variable.isShared();

        toAssign.generateCode(code);
        if (Main.UNROLL_ARRAY_ACCESS() || assignSize < 5) {
            for (int i = 0; i < assignSize; i++) {
                code.appendVariableSet(memAddress + i, isShared);
            }
        } else {
            code.appendArraySet(memAddress, assignSize, isShared);
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
        variable.buildGraph(graphBuilder);
        toAssign.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Assignment";
    }


}

