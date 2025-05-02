package ut.pp.exceptions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.Expression;

public abstract class CodeError extends Exception
{
    public CodeError(int line, int column, String errorMsg) {
        super(buildErrorMsg(line, column, errorMsg));
    }

    private static String buildErrorMsg(int line, int column, String errorMsg) {
        return String.format("ERROR at line %d:%d\n\t%s", line, column, errorMsg);
    }

    public static int getLine(ParserRuleContext ctx) {
        return ctx.getStart().getLine();
    }

    public static int getColumn(ParserRuleContext ctx) {
        return ctx.getStart().getCharPositionInLine();
    }

}

