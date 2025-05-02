package ut.pp.exceptions;

import ut.pp.AST.statements.constituents.Type;

public class TypeMismatchError extends CodeError {
    public TypeMismatchError(int line, int column, Type expected, Type actual, String context) {
        super(line, column, buildErrorMsg(expected, actual, context));
    }

    public TypeMismatchError(int line, int column, Type expected1, Type expected2, Type actual, String context) {
        super(line, column, buildErrorMsg(expected1, expected2, actual, context));
    }

    private static String buildErrorMsg(Type expected, Type actual, String context) {
        return String.format(
                "Type mismatch! Expected '%s' but got '%s'\n\tin the following context: %s",
                expected.toString(), actual.toString(), context);
    }

    private static String buildErrorMsg(Type expected1, Type expected2, Type actual, String context) {
        return String.format(
                "Type mismatch! Expected '%s' or '%s' but got '%s'\n\tin the following context: %s",
                expected1.toString(), expected2.toString(), actual.toString(), context);
    }
}

