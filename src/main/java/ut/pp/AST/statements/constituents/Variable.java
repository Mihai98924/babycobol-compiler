package ut.pp.AST.statements.constituents;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.ReDeclaredSymbolError;

/**
 * An object which represents a variable as a {@link ut.pp.AST.AST_Node} within our AST.
 */
public class Variable extends Expression {

    String identifier;
    int size;
    int memAddress;
    boolean isShared;
    ParserRuleContext ctx;

    /**
     * The constructor for our variables, a variable is any parse tree node which has a location in memory and some
     * value attached to it.
     *
     * @param ctx        The {@link ParserRuleContext} for this expression, this is mainly used for error handling and reporting.
     * @param type       The {@link Type} the variable was defined with.
     * @param identifier The identifier for the variable, i.e. what the Variable has been labelled as
     * @param isShared   A simple boolean which indicates whether this {@link Variable} is placed in a shared memory location.
     * @param size       The size this {@link Variable} will take up in the memory space
     */
    public Variable(ParserRuleContext ctx, Type type, String identifier, boolean isShared, int size) {
        super(type, size > 1);
        this.type = type;
        this.identifier = identifier;
        this.isShared = isShared;
        this.size = size;
        this.ctx = ctx;
    }

    /**
     * This is a simple method which builds a ReDeclaredSymbolError based on the context of the current declaration
     *
     * @param previousDeclaration the previous time within the current scope where a {@link #Variable} with the same
     *                            identifier was declared
     * @return a new {@link #/symbols/ReDeclaredSymbolError} object containing the specifications required for compiler
     * error reporting and handling
     */
    public ReDeclaredSymbolError getRedeclaredSymbolError(Variable previousDeclaration) {
        int line = getLine();
        int column = getColumn();
        int prevLine = previousDeclaration.getLine();
        int prevColumn = previousDeclaration.getColumn();
        return new ReDeclaredSymbolError(line, column, identifier, prevLine, prevColumn);
    }

    /**
     * A simple getter for the type of this variable
     *
     * @return returns the {@link Type} for this {@link Variable}
     */
    public Type getType() {
        return this.type;
    }


    @Override
    public int getSize() {
        return size;
    }

    public String getIdentifier() {
        return identifier;
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
        String toReturn = isShared ? "Shared " : "Local ";
        toReturn += isArray ? "Array " : "";
        toReturn += "Variable <br/>";
        toReturn += "ID: '" + identifier + "'<br/> " +
                    "Size: '" + size + "'";
        return toReturn;
    }

    public int getLine() {
        return ctx.getStart().getLine();
    }

    public int getColumn() {
        return ctx.getStart().getCharPositionInLine();
    }

    public boolean isShared() {
        return isShared;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setMemAddress(int memAddress) {
        this.memAddress = memAddress;
    }


    public int getMemAddress() {
        return memAddress;
    }
}
