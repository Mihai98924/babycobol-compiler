package ut.pp.AST.statements.constituents;

import ut.pp.AST.AST_Node;
import ut.pp.AST.GraphBuilder;
import ut.pp.codeGen.Code;

public enum Type implements AST_Node {
    INTEGER,
    BOOLEAN,
    CHARACTER,
    STRING,
    LOCK,
    ;

    @Override
    public void generateCode(Code code) {
        // Nothing to do.
    }

    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        graphBuilder.pop();
    }

    @Override
    public String generateNodeString() {
        return "Type: " + this;
    }
}
