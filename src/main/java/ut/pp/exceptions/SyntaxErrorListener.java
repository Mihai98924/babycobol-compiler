package ut.pp.exceptions;

import org.antlr.v4.runtime.*;

import java.util.List;

public class SyntaxErrorListener extends BaseErrorListener
{
    private final List<CodeError> errorOutput;

    public SyntaxErrorListener(List<CodeError> errorOutput) {
        this.errorOutput = errorOutput;
    }

    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int column, String msg, RecognitionException e)
    {
        SyntaxError syntaxError = new SyntaxError(line, column, msg);
        errorOutput.add(syntaxError);
    }
}
