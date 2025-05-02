package ut.pp.AST.statements.constituents.expressions.literals;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.arrays.ArrayExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

import java.util.ArrayList;

public class ArrayLiteral extends ArrayExpression {

    ArrayList<Expression> expressions;

    public ArrayLiteral(ParserRuleContext ctx, ArrayList<Expression> expressions) throws TypeMismatchError, NullPointerException {
        super(expressions.get(0).getType(), expressions);
        Type type = expressions.get(0).getType();
        this.expressions = expressions;
        for (Expression expression : expressions) {
            expression.matchingType(type, ctx);
        }
        this.isLiteral = true;
    }


    public ArrayLiteral(ParserRuleContext ctx, ArrayList<Expression> chars, Type type) throws TypeMismatchError, NullPointerException {
        super(type, chars);
        this.expressions = chars;
        for (Expression expression : chars) {
            expression.matchingType(Type.CHARACTER, ctx);
        }
        this.isLiteral = true;

    }

    public int getSize() {
        return this.expressions.size();
    }
}
