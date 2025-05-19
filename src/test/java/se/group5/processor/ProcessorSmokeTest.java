package se.group5.processor;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import se.group5.build.AstSmokeTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class ProcessorSmokeTest {

    private static final int MAX_TESTS = 100;
    private static Processor processor;

    @Parameterized.Parameter(0)
    public String resourcePath;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> parameters() throws IOException {
        try (Stream<Path> paths = Files.walk(
                Paths.get(Objects.requireNonNull(
                        AstSmokeTest.class.getResource("/programs")).toURI())
        )) {
            List<Object[]> tests = paths
                    .filter(p -> p.toString().endsWith(".baby"))
                    .sorted(Comparator.comparingInt(ProcessorSmokeTest::extractIndex))
                    .limit(MAX_TESTS)
                    .map(p -> new Object[]{"/programs/" + p.getFileName()})
                    .toList();

            if (tests.isEmpty()) {
                throw new IOException("No .baby test files found in /programs");
            }

            return tests;
        } catch (URISyntaxException e) {
            throw new IOException("Unable to locate test programs in /programs", e);
        }
    }

    private static int extractIndex(Path p) {
        String name = p.getFileName().toString();
        int underscore = name.indexOf('_');
        int dot = name.lastIndexOf('.');
        if (underscore >= 0 && dot > underscore) {
            try {
                return Integer.parseInt(name.substring(underscore + 1, dot));
            } catch (NumberFormatException ignored) {}
        }
        return Integer.MAX_VALUE;
    }

    @Test
    public void parsesWithoutErrors() throws Exception {
        ParseResult result = processor.parseFile(resourcePath);
        Assert.assertNotNull(result.rootNode());

    }
}
