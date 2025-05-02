package ut.pp.AST.statements.constituents.expressions.equality.orderable;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

public abstract class OrderableEquality extends BinaryExpression {


    public OrderableEquality(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError, NullPointerException {
        super(ctx, Type.BOOLEAN, left, right);
        left.matchingType(Type.INTEGER, Type.CHARACTER, ctx);
    }

    /**
     * An abstract function which must be declared for all extensions of the abstract class {@link Expression}
     *
     * @return an {@link Integer} representing the size of the {@link Expression} extensions
     */
    @Override
    public int getSize() {
        return 1;
    }
}

