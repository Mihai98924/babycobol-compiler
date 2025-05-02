package ut.pp.AST.statements.constituents.expressions.variable_access;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.Main;
import ut.pp.codeGen.Code;

public class VariableAccess extends Expression {
    Scope scope;
    Variable varToAccess;

    public VariableAccess(Scope scope, ParserRuleContext ctx, Variable variable) {
        super(variable.getType(), variable.isArray());
        this.scope = scope;
        this.varToAccess = variable;
        this.isLiteral = variable.isLiteral();
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        int memAddress = varToAccess.getMemAddress();
        int accessSize = varToAccess.getSize();
        boolean isShared = varToAccess.isShared();

        if (Main.UNROLL_ARRAY_ACCESS() || accessSize < 5) {
            for (int i = accessSize - 1; i >= 0; i--) {
                code.appendVariableGet(memAddress + i, isShared);
            }
        } else {
            code.appendArrayGet(memAddress, accessSize, isShared);
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
        varToAccess.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    @Override
    public String generateNodeString() {
        return "Variable Access";
    }

    @Override
    public int getSize() {
        return this.varToAccess.getSize();
    }

}

