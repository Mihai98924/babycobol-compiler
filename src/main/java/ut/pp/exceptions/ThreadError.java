package ut.pp.exceptions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.parser.antlr4.PotatoParser;

public class ThreadError extends CodeError {


    public ThreadError(PotatoParser.WhileContext ctx) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(ctx));
    }

    public ThreadError(PotatoParser.IfContext ctx) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(ctx));
    }

    public ThreadError(PotatoParser.ForkContext ctx) {
        super(getLine(ctx), getColumn(ctx), buildErrorMsg(ctx));
    }

    private static String buildErrorMsg(PotatoParser.WhileContext ctx) {
        return String.format(
                "Disallowed threading action. Fork not allowed in while loop \n\tin the following context: %s",
                ctx.getText());
    }

    private static String buildErrorMsg(PotatoParser.IfContext ctx) {
        return String.format(
                "Disallowed threading action. Fork not allowed in If statement \n\tin the following context: %s",
                ctx.getText());
    }

    private static String buildErrorMsg(PotatoParser.ForkContext ctx) {
        return String.format(
                "Disallowed threading action. Fork without join \n\tin the following context: %s",
                ctx.getText());
    }
}
