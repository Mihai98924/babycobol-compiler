package ut.pp.AST.statements.constituents.expressions.arrays;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.expressions.LiteralExpression;
import ut.pp.AST.statements.constituents.expressions.arrays.ArrayExpression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.CodeError;
import ut.pp.exceptions.NonLiteralInNewArrayError;
import ut.pp.exceptions.TypeMismatchError;

import java.util.ArrayList;

public class NewArray extends ArrayExpression {

    public NewArray(ParserRuleContext ctx, Type type, Expression expression) throws TypeMismatchError, NonLiteralInNewArrayError {
        super(type, buildNullArray(expression, ctx,type));
    }

    public static ArrayList<Expression> buildNullArray(Expression expression, ParserRuleContext ctx, Type type) throws TypeMismatchError, NullPointerException, NonLiteralInNewArrayError {
        expression.matchingType(expression.getType(), ctx);
        ArrayList<Expression> expressions = new ArrayList<>();
        if (!expression.isLiteral()) {
            throw new NonLiteralInNewArrayError(ctx);
        } else {
            LiteralExpression literalExpression = (LiteralExpression) expression;
            for (int i = 0; i < literalExpression.getValue(); i++){
                switch (type) {
                    case BOOLEAN -> expressions.add(new LiteralExpression(false));
                    case INTEGER -> expressions.add(new LiteralExpression(0));
                    case CHARACTER, STRING -> expressions.add(new LiteralExpression(' '));
                    default -> throw new Error();
                }
            }
            return expressions;
        }
    }
}

