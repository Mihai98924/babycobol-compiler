package ut.pp.AST.statements.threading;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.Statement;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.ThreadError;
import ut.pp.parser.antlr4.PotatoParser;

import java.util.ArrayList;

public class Fork extends Statement {

    Scope parallelScope;
    ArrayList<Statement> beforeJoin;

    /**
     * @param parallelScope The {@link Scope} for the thread to run in parallel to the thread that called the fork
     */
    public Fork(PotatoParser.ForkContext ctx, Scope parentScope, Scope parallelScope, ArrayList<Statement> beforeJoin) throws ThreadError {
        super(parentScope);
        this.parallelScope = parallelScope;
        this.beforeJoin = beforeJoin;

        if (ctx.JOIN() == null)
            throw new ThreadError(ctx);
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        int threadID = code.startFork();
        parallelScope.generateCode(code);
        code.endFork(threadID);
        for (Statement statement : beforeJoin) {
            statement.generateCode(code);
        }
        code.join();
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        parallelScope.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Fork";
    }
}

