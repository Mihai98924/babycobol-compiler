package ut.pp.AST.statements.constituents.expressions.math;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

public abstract class Math extends BinaryExpression {

    public Math(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError, NullPointerException {
        super(ctx, Type.INTEGER, left, right);
        left.matchingType(right, ctx);
        left.matchingType(Type.INTEGER, ctx);
    }

    @Override
    public int getSize() {
        return 1;
    }
}

