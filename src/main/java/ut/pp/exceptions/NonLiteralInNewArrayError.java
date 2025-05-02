package ut.pp.exceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class NonLiteralInNewArrayError extends CodeError {

    public NonLiteralInNewArrayError(ParserRuleContext ctx) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(ctx));

    }

    private static String buildErrorMsg(ParserRuleContext ctx) {
        return String.format("Only Literals are allowed within a new Array at line %d:%d\n\t%s", getLine(ctx), getColumn(ctx), ctx.getText());
    }
}
