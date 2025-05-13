package se.group5;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import se.group5.processor.Processor;


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
/*  src/test/java/se/group5/ParserSmokeTest.java  */
@RunWith(Parameterized.class)
public class ParserSmokeTest {

    @Parameterized.Parameter(0)
    public String resourcePath;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        try (java.util.stream.Stream<java.nio.file.Path> paths =
                     java.nio.file.Files.walk(java.nio.file.Paths.get(
                             Objects.requireNonNull(ParserSmokeTest.class
                                     .getResource("/programs")).toURI()))) {

            return paths
                    .filter(p -> p.toString().endsWith(".baby"))
                    // ── sort by the integer between “_” and “.baby” ──────────────
                    .sorted(java.util.Comparator.comparingInt(ParserSmokeTest::extractIndex))
                    .map(p -> new Object[]{"/programs/" + p.getFileName()})
                    .toList();          // Java 16+; replace with .collect(Collectors.toList()) if needed
        } catch (Exception e) {
            throw new IOException("Unable to locate test programs in /programs", e);
        }
    }

    /**
     * Helper that pulls the numeric part out of a filename such as “test_17.baby”.
     * If no number is found the file is pushed to the end of the list.
     */
    private static int extractIndex(java.nio.file.Path p) {
        String name = p.getFileName().toString();   // e.g. “test_17.baby”
        int underscore = name.indexOf('_');
        int dot        = name.lastIndexOf('.');
        if (underscore >= 0 && dot > underscore) {
            try {
                return Integer.parseInt(name.substring(underscore + 1, dot));
            } catch (NumberFormatException ignored) { /* fall through */ }
        }
        return Integer.MAX_VALUE;   // non-conforming names go to the end
    }


    @Test
    public void parsesWithoutErrors() throws Exception {
        Processor processor = new Processor(resourcePath);
        ParseTree tree = processor.parse();
        Assert.assertNotNull(tree);
    }
}
