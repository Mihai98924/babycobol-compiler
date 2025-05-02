package ut.pp.AST.statements.constituents.expressions.literals;

import ut.pp.AST.statements.constituents.expressions.LiteralExpression;
import ut.pp.parser.antlr4.PotatoParser;

public class CharLiteral extends LiteralExpression {


    public CharLiteral(PotatoParser.CharLiteralContext ctx) {
        super(ctx.CHAR_LITERAL().getText().charAt(1));
    }

    public CharLiteral(char c) {
        super(c);
    }
}

