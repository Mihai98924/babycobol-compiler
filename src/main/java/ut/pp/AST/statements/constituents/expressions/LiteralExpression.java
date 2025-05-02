package ut.pp.AST.statements.constituents.expressions;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.codeGen.Code;


public class LiteralExpression extends Expression {

    int value;

    public LiteralExpression(char value) {
        super(Type.CHARACTER, false);
        this.value = value;
        this.isLiteral = true;
    }

    public LiteralExpression(int value) {
        super(Type.INTEGER, false);
        this.value = value;
        this.isLiteral = true;
    }

    public LiteralExpression(boolean isTrue) {
        super(Type.BOOLEAN, false);
        this.value = isTrue ? 1 : 0;
        this.isLiteral = true;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        code.appendLiteral(value);
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
        String typeString;
        switch (this.type) {
            case CHARACTER -> {
                typeString = String.valueOf((char) value);
                return "Char: \'" + typeString+"\'";
            }
            case INTEGER -> {
                typeString = String.valueOf(value);
                return "Int: '" + typeString+"\'";
            }
            case BOOLEAN -> {
                typeString = String.valueOf((value == 0));
                return "Bool: \'" + typeString+"\'";
            }
            default -> {
                return "Expression has ERROR";
            }
        }
    }


    @Override
    public int getSize() {
        return 1;
    }


    public int getValue() {
        return this.value;
    }
}