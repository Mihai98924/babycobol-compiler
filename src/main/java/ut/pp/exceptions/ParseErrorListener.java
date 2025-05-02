package ut.pp.exceptions;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

public class ParseErrorListener extends BaseErrorListener
{
    private final List<CodeError> errorOutput;

    public ParseErrorListener(List<CodeError> errorOutput) {
        this.errorOutput = errorOutput;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int column, String msg, RecognitionException e)
    {
        if (e == null) {
            errorOutput.add(new SyntaxError(line, column, msg));
            return;
        }

        Vocabulary vocab = recognizer.getVocabulary();
        String unexpected = e.getOffendingToken().getText();

        List<Integer> expectedTokeTypes = e.getExpectedTokens().toList();
        List<String> expectedTokens = new ArrayList<>(expectedTokeTypes.size());
        for (int tokenType : expectedTokeTypes)
            expectedTokens.add(vocab.getDisplayName(tokenType));

        ParseError parseError = new ParseError(line, column, unexpected, expectedTokens);
        errorOutput.add(parseError);
    }
}
