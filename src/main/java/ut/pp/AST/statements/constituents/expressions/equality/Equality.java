package ut.pp.AST.statements.constituents.expressions.equality;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

public abstract class Equality extends BinaryExpression {

    public Equality(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError {
        super(ctx, Type.BOOLEAN, left, right);
        this.type = Type.BOOLEAN;
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