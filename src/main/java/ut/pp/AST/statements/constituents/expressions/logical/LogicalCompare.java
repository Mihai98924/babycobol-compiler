package ut.pp.AST.statements.constituents.expressions.logical;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.BinaryExpression;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public abstract class LogicalCompare extends BinaryExpression {

    public LogicalCompare(ParserRuleContext ctx, Expression left, Expression right) throws TypeMismatchError {
        super(ctx, Type.BOOLEAN, left, right);
        left.matchingType(Type.BOOLEAN, ctx);
        left.matchingType(right, ctx);
    }

}


