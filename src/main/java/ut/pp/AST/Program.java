package ut.pp.AST;

import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.Statement;
import ut.pp.codeGen.Code;
import ut.pp.codeGen.RootCode;

import static guru.nidi.graphviz.model.Factory.*;

import java.util.ArrayList;

/**
 * This AST_Node represents our AST root, it contains only the program scope which is the highest level scope
 * of our program.
 */
public class Program implements AST_Node {
    ArrayList<Statement> statements;

    /**
     * A program is a special cast for the top layer scope which can be seen as a collection of statement tree's
     * @param statements
     */
    public Program(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        for (Statement statement : statements) {
            statement.generateCode(code);
        }
    }

    @Override
    public Code generateCode() {
        Code code = new RootCode();
        generateCode(code);
        return code;
    }

    public void buildGraph(GraphBuilder graphBuilder) {
        for (Statement statement : statements) {
            statement.buildGraph(graphBuilder);
        }
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    public String generateNodeString() {
        return "Program";
    }

    public GraphBuilder graphBuilder() {
        GraphBuilder graphBuilder = new GraphBuilder(generateNodeString());
        buildGraph(graphBuilder);
        return graphBuilder;
    }

}
