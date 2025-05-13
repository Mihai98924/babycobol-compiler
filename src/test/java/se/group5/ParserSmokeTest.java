package se.group5;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /** Maximum number of test programs to run through. */
    private static final int MAX_TESTS = 100;

    @Parameterized.Parameter(0)
    public String resourcePath;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> parameters() throws IOException {
        try (Stream<Path> paths = Files.walk(
                Paths.get(Objects.requireNonNull(
                        AstSmokeTest.class.getResource("/programs")).toURI())
        )) {
            List<Object[]> tests = paths
                    .filter(p -> p.toString().endsWith(".baby"))
                    .sorted(Comparator.comparingInt(ParserSmokeTest::extractIndex))
                    .limit(MAX_TESTS)                                 // ← cap at MAX_TESTS
                    .map(p -> new Object[]{ "/programs/" + p.getFileName() })
                    .collect(Collectors.toList());

            if (tests.isEmpty()) {
                throw new IOException("No .baby test files found in /programs");
            }

            return tests;
        } catch (URISyntaxException e) {
            throw new IOException("Unable to locate test programs in /programs", e);
        }
    }

    private static int extractIndex(Path p) {
        String name = p.getFileName().toString();   // e.g. “test_17.baby”
        int underscore = name.indexOf('_');
        int dot        = name.lastIndexOf('.');
        if (underscore >= 0 && dot > underscore) {
            try {
                return Integer.parseInt(name.substring(underscore + 1, dot));
            } catch (NumberFormatException ignored) { /* fall through */ }
        }
        return Integer.MAX_VALUE;
    }


    @Test
    public void parsesWithoutErrors() throws Exception {
        Processor processor = new Processor(resourcePath);
        ParseTree tree = processor.parse();
        Assert.assertNotNull(tree);
    }
}
