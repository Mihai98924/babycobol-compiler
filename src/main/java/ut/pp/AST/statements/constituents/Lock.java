package ut.pp.AST.statements.constituents;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.codeGen.Code;

public class Lock extends Variable {

    int memAddress;
    String lockIdentifier;

    /**
     *
     * @param ctx
     * @param lockIdentifier
     */
    public Lock(ParserRuleContext ctx, String lockIdentifier) {
        super(ctx, Type.LOCK, lockIdentifier, true, 1);
        this.lockIdentifier = lockIdentifier;
    }

    public void setMemAddress(int memAddress) {
        this.memAddress = memAddress;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {

    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Lock ID: " + lockIdentifier;
    }
}

