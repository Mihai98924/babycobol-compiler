package ut.pp.exceptions;

import ut.pp.exceptions.CodeError;

public class ReDeclaredSymbolError extends CodeError
{
    public ReDeclaredSymbolError(int line, int column, String id,
                                 int prevDeclaredLine, int prevDeclaredColumn) {
        super(line, column, buildErrorMsg(id, prevDeclaredLine, prevDeclaredColumn));
    }

    private static String buildErrorMsg(String id, int prevDeclaredLine, int prevDeclaredColumn) {
        return String.format("Redeclaration of '%s'\n\tPrevious declaration on line %d:%d",
                             id, prevDeclaredLine, prevDeclaredColumn);
    }
}
