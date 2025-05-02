package ut.pp.exceptions;

import ut.pp.exceptions.CodeError;

public class UnknownSymbolError extends CodeError
{
    public UnknownSymbolError(int line, int column, String id) {
        super(line, column, buildErrorMsg(id));
    }

    private static String buildErrorMsg(String id) {
        return String.format("Use of unknown identifier '%s'", id);
    }
}
