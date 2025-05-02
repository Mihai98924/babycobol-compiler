package ut.pp.AST.statements.constituents.expressions.arrays;

import com.kitfox.svg.A;
import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;
import ut.pp.parser.antlr4.PotatoParser;

import java.util.ArrayList;

public class ArrayExpression extends Expression {

    ArrayList<Expression> expressions;

    public ArrayExpression(Type type, ArrayList<Expression> expressions) {
        super(type, true);
        this.expressions = expressions;
    }

    public int getSize() {
        return expressions.size();
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        for (int i = getSize() - 1; i >= 0; i--) {
            this.expressions.get(i).generateCode(code);
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
        for (Expression expression : expressions) {
            expression.buildGraph(graphBuilder);
        }
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        switch (this.type) {
            case CHARACTER -> {
                return "Char Array<br/>stores: '"+ expressions.size() + "' Chars";
            }
            case INTEGER -> {
                return "Int Array<br/>stores: '"+ expressions.size() + "' Ints";
            }
            case BOOLEAN -> {
                return "Bool Array<br/>stores: '"+ expressions.size() + "' Bools";
            }
            case STRING -> {
                return "String <br/>stores: '"+ expressions.size() + "' Chars";
            }
            default -> {
                return "Error";
            }
        }
    }
}


