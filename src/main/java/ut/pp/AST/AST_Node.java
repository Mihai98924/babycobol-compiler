package ut.pp.AST;

import ut.pp.codeGen.Code;

/**
 * This interface represents the different nodes in our AST
 * We make all our nodes in the tree implement the AST_Node so that we can save the different nodes
 * Into the parse tree property nodes in Compiler
 */
public interface AST_Node
{
    void generateCode(Code code);

    default Code generateCode()
    {
        Code code = new Code();
        generateCode(code);
        return code;
    }

    void buildGraph(GraphBuilder graphBuilder);

    String generateNodeString();
//    void generateTreeGraph(Graph graph, GraphCoordinate graphCoordinate);
}
