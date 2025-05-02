package ut.pp.exceptions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.exceptions.CodeError;

public class NotAnArrayError extends CodeError {
    public NotAnArrayError(ParserRuleContext ctx, String id, String context) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(id, context));
    }

    public NotAnArrayError(ParserRuleContext ctx) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(ctx));
    }

    private static String buildErrorMsg(String id, String context) {
        return String.format(
                "Invalid array access! '%s' is not an array\n\tin the following context: %s",
                id, context);
    }

    private static String buildErrorMsg(ParserRuleContext ctx) {
        return String.format(
                "Invalid call of 'length', 'length' can only be called on arrays\n\tin the following context: %s",
                ctx.getText());
    }
}
