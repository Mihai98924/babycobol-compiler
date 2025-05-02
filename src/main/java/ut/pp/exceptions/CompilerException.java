package ut.pp.exceptions;

import java.util.List;

public class CompilerException extends Exception
{
    private final List<CodeError> errors;

    public CompilerException(List<CodeError> errors) {
        super("Some errors occurred during compilation!\nFetch them with getErrors().");
        this.errors = errors;
    }

    public List<CodeError> getErrors() {
        return this.errors;
    }
}
