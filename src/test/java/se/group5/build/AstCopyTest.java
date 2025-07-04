package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.StringAccumulatingDisplayStrategy;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstCopyTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void CopyOperation() throws Exception {
        String source = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. ADDTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                           COPY "src/test/resources/test/data_division.baby".
                       PROCEDURE DIVISION.
                           COPY "src/test/resources/test/sentence.baby".
                """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);

        Assert.assertNotNull("Program should not be null", program);
        Assert.assertEquals("There should be 4 procedures", 4, program.procedures.size());
        Assert.assertEquals("The Output should be correct",
                """
                ITS ALIVE
                ITS ALIVE
                ITS ALIVE
                """,
                output.getOutput());
    }
    @Test
    public void CopyOperationReplacing() throws Exception {
        String source = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. ADDTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                           01 A PICTURE IS X(9).
                           COPY "src/test/resources/test/data_division.baby"
                      - REPLACING ===A=== BY ===D=== ===B=== BY ===E=== ===C=== BY ===F===.
                           01 B PICTURE IS X(9).
                       PROCEDURE DIVISION.
                           COPY "src/test/resources/test/sentence.baby"
                      - REPLACING ===A=== BY ===D=== ===B=== BY ===E=== ===C=== BY ===F===.
                """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertEquals("There should be 4 procedures", 4, program.procedures.size());
        Assert.assertEquals("The Output should be correct",
                """
                ITS ALIVE
                ITS ALIVE
                ITS ALIVE
                """,
                output.getOutput());
        Assert.assertTrue("The replacing statement does not replace identifiers defined earlier", program.symbolTable.resolve("A").isPresent());
        Assert.assertTrue("The replacing statement does not replace identifiers defined later", program.symbolTable.resolve("B").isPresent());
    }
    @Test
    public void CopyOperationParagraphAndNested() throws Exception {
        String source = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. ADDTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                           COPY "src/test/resources/test/data_division.baby"
                      - REPLACING ===A=== BY ===D=== ===B=== BY ===E=== ===C=== BY ===F===.
                       PROCEDURE DIVISION.
                           COPY "src/test/resources/test/paragraph_1.baby".
                """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertEquals("There should be 2 procedures", 2, program.procedures.size());
        Assert.assertEquals("The Output should be correct",
                """
                ITS ALIVE
                ITS ALIVE
                ITS ALIVE
                """,
                output.getOutput());
    }
}
