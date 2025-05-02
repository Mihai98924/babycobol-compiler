package ut.pp.AST.statements.constituents.expressions.variable_access;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class ArrayElementAccess extends VariableAccess {

    Expression index;

    public ArrayElementAccess(Scope scope, ParserRuleContext ctx, Variable varToAccess, Expression index) throws TypeMismatchError, NullPointerException {
        super(scope, ctx, varToAccess);
        this.index = index;
        index.matchingType(Type.INTEGER, ctx);
    }

    @Override
    public int getSize() {
        return 1;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        index.generateCode(code);
        code.appendArrayElementGet(varToAccess.getMemAddress(), varToAccess.isShared());
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        varToAccess.buildGraph(graphBuilder);
        index.buildGraph(graphBuilder);
        graphBuilder.pop();
    }


    @Override
    public String generateNodeString() {
        return "Array Variable Element Access";
    }
}
