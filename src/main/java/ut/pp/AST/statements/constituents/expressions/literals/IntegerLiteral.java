package ut.pp.AST.statements.constituents.expressions.literals;

import ut.pp.AST.statements.constituents.expressions.LiteralExpression;

public class IntegerLiteral extends LiteralExpression {

    public IntegerLiteral(int radix, String integerString) {
        super(Integer.parseInt(integerString,radix));
    }
}
