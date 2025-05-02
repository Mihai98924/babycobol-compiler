package se.group5;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import se.group5.CoBabyBoLParser;
import se.group5.CoBabyBoLLexer;

/**
 * Simple parser smoke-test:
 *   – parameterised: one test invocation per .baby file
 *   – fails on the first syntax error
 *
 * Generated classes used here:
 *   CoBabyBoLLexer   – from CoBabyBoLLexer.g4
 *   CoBabyBoLParser  – from CoBabyBoLParser.g4
 *
 * Adjust the start rule name (`program` below) if your grammar’s
 * top-level rule is called differently.
 */
@RunWith(Parameterized.class)
public class ParserSmokeTest {

    @Parameterized.Parameter(0)
    public String resourcePath;

    // ---------------------------------------------------------------------
    //  Test data provider
    // ---------------------------------------------------------------------
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        List<Object[]> files = new ArrayList<>();
        try (java.util.stream.Stream<java.nio.file.Path> paths =
                     java.nio.file.Files.walk(java.nio.file.Paths.get(
                             Objects.requireNonNull(ParserSmokeTest.class.getResource("/programs")).toURI()))) {
            paths.filter(p -> p.toString().endsWith(".baby"))
                    .forEach(p -> files.add(new Object[]{"/programs/" + p.getFileName()}));
        } catch (Exception e) {
            throw new IOException("Unable to locate test programs in /programs", e);
        }
        return files;
    }

    // ---------------------------------------------------------------------
    //  The actual test
    // ---------------------------------------------------------------------
    @Test
    public void parsesWithoutErrors() throws Exception {

        // 1. Load input
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            Assert.assertNotNull("Test resource not found: " + resourcePath, in);

            CharStream chars = CharStreams.fromStream(in, StandardCharsets.UTF_8);

            // 2. Run lexer
            CoBabyBoLLexer lexer = new CoBabyBoLLexer(chars);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 3. Run parser
            CoBabyBoLParser parser = new CoBabyBoLParser(tokens);

            // Report errors through JUnit
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?,?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    Assert.fail(String.format("%s:%d:%d %s",
                            resourcePath, line, charPositionInLine, msg));
                }
            });

            // !!! Replace “program” with your grammar’s start rule if needed
            ParseTree tree = parser.program();

            // 4. Sanity assertion
            Assert.assertEquals("Parser reported syntax errors",
                    0, parser.getNumberOfSyntaxErrors());
            Assert.assertNotNull(tree);
        }
    }
}
