package ut.pp.AST.statements.constituents.expressions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.exceptions.TypeMismatchError;

public abstract class UnaryExpression extends Expression {
    protected final Expression operand;

    public UnaryExpression(ParserRuleContext ctx, Type type, Expression operand) throws TypeMismatchError
    {
        super(type, false);
        this.operand = operand;
        operand.matchingType(type, ctx);
    }

    @Override
    public String generateNodeString() {
        return null;
    }

    @Override
    public void buildGraph(GraphBuilder graphBuilder) {

    }

    @Override
    public int getSize() {
        return 1;
    }
}
