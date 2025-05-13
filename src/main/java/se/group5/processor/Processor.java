/*  src/main/java/se/group5/processor/Processor.java  */
package se.group5.processor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import se.group5.gen.CoBabyBoLLexer;
import se.group5.gen.CoBabyBoLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * Small helper that hides all the boiler-plate around
 * “load resource → (optional) preprocess → lex → parse → fail on error”.
 * <p>
 * A {@code Processor} can be reused in any JUnit test; simply hand it the
 * resource path and, optionally, a preprocessing lambda.
 */
public class Processor {

    private final String resourcePath;
    private final Function<String, String> preProcessor;


    /**
     * No preprocessing.
     */
    public Processor(String resourcePath) {
        this(resourcePath, Function.identity());
    }

    /**
     * @param resourcePath class-path resource, e.g. {@code "/programs/demo.baby"}.
     * @param preProcessor transformation applied to the *raw* file text before
     *                     it is fed to the lexer (newline normalisation, macro
     *                     expansion, …). Use {@code Function.identity()} for “none”.
     */
    public Processor(String resourcePath, Function<String, String> preProcessor) {
        this.resourcePath = resourcePath;
        this.preProcessor = preProcessor == null ? Function.identity() : preProcessor;
    }

    /**
     * Build & configure a parser but *do not* invoke the start rule yet.
     */
    public CoBabyBoLParser buildParser() throws IOException {
        CharStream chars = loadCharStream();

        CoBabyBoLLexer lexer = new CoBabyBoLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoBabyBoLParser parser = new CoBabyBoLParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPos,
                                    String msg, RecognitionException e) {
                Assert.fail(String.format("%s:%d:%d %s",
                        resourcePath, line, charPos, msg));
            }
        });
        return parser;
    }

    /**
     * Convenience method that:
     * 1. builds the parser,
     * 2. calls the grammar’s start rule ({@code program} here),
     * 3. asserts that ANTLR reported *zero* syntax errors,
     * 4. returns the produced parse tree.
     */
    public ParseTree parse() throws IOException {
        CoBabyBoLParser parser = buildParser();
        ParseTree tree = parser.program();
        Assert.assertEquals("Parser reported syntax errors",
                0, parser.getNumberOfSyntaxErrors());
        return tree;
    }

    private String removeComments(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder(4096);
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 6 && (line.replace(" ", "").startsWith("*") || line.length() > 80)) {
                    continue;
                }

                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Loads the file, applies some pre-processing to the char stream, returns a {@link CharStream}.
     */
    private CharStream loadCharStream() throws IOException {

        InputStream in = getClass().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new IOException("Test resource not found: " + resourcePath);
        }

        String processedString = removeComments(in);
        return CharStreams.fromString(processedString, resourcePath);
    }
}
