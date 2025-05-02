package ut.pp.exceptions;

import ut.pp.exceptions.CodeError;

public class SyntaxError extends CodeError
{
    public SyntaxError(int line, int column, String unknown) {
        super(line, column, buildErrorMsg(unknown));
    }

    private static String buildErrorMsg(String unknown) {
        return String.format("Invalid syntax: '%s'", unknown);
    }
}
