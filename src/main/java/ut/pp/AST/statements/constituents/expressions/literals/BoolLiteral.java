package ut.pp.AST.statements.constituents.expressions.literals;

import ut.pp.AST.statements.constituents.expressions.LiteralExpression;
import ut.pp.parser.antlr4.PotatoParser;

public class BoolLiteral extends LiteralExpression {

    public BoolLiteral(PotatoParser.BoolLiteralContext ctx) {
        super(getValue(ctx));
    }


    private static boolean getValue(PotatoParser.BoolLiteralContext ctx){
        String booleanString = ctx.BOOL_LITERAL().getText();
        return booleanString.equals("true");
    }
}
