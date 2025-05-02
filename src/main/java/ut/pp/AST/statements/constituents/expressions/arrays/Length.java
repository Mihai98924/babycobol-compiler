package ut.pp.AST.statements.constituents.expressions.arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.expressions.LiteralExpression;
import ut.pp.exceptions.NotAnArrayError;
import ut.pp.parser.antlr4.PotatoParser;

public class Length extends LiteralExpression {

    public Length(PotatoParser.LengthContext ctx, Expression expression) throws NotAnArrayError {
        super(getArrayLength(ctx, expression));
    }

    public static int getArrayLength(PotatoParser.LengthContext ctx, Expression expression) throws NotAnArrayError {
        if (expression.isArray()) {
            return expression.getSize();
        } else {
            throw new NotAnArrayError(ctx);
        }
    }


}
