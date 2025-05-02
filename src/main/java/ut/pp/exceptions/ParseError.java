package ut.pp.exceptions;

import ut.pp.exceptions.CodeError;

import java.util.List;

public class ParseError extends CodeError
{
    public ParseError(int line, int column, String unexpected, List<String> expected) {
        super(line, column, buildErrorMsg(unexpected, expected));
    }

    private static String buildErrorMsg(String unexpected, List<String> expected)
    {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("Unexpected token: '").append(unexpected);
        errorMsg.append("'\n\tPossible alternatives: ");
        for (String token : expected)
            errorMsg.append(token).append(' ');

        return errorMsg.toString();
    }
}
