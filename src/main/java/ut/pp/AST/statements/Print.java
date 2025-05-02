package ut.pp.AST.statements;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;

public class Print extends Statement {
    Expression expression;
    boolean appendNewLine;

    /**
     *
     * @param parentScope the scope in which this {@link Statement} is located
     * @param expression
     * @param appendNewLine
     */
    public Print(Scope parentScope, Expression expression, boolean appendNewLine) {
        super(parentScope);
        this.expression = expression;
        this.appendNewLine = appendNewLine;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        this.expression.generateCode(code);
        switch (expression.getType()) {
            case INTEGER:
                for (int i = 0; i < this.expression.getSize(); i++) {
                    code.appendPrintNumber();
                }
                break;
            case CHARACTER:
                for (int i = 0; i < this.expression.getSize(); i++) {
                    code.appendPrintChar();
                }
                break;
            case BOOLEAN:
                for (int i = 0; i < this.expression.getSize(); i++) {
                    code.appendPrintBool();
                }
                break;
            // No need to print strings as they are parsed as character arrays.
        }
        if (appendNewLine) {
            code.appendPrintNewline();
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
        expression.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Print";
    }
}
