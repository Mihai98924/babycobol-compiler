package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.StringAccumulatingDisplayStrategy;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstCallTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    // CALL HELLO USING BY VALUE 5 BY REFERENCE A BY CONTENT 6.
    @Test
    public void CallOperation() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      CALL "src/test/resources/programs/test_0.baby".
           """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);

        System.out.println("Program: " + program);
        System.out.println("Procedure: " + program.procedures);
        System.out.println("Out: " + output.getOutput());
        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("42100054", output.getOutput());
    }

    @Test
    public void CallOperationByReference() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS X(9).
                      01 B PICTURE IS X(9).
                      01 C PICTURE IS X(9).
                  PROCEDURE DIVISION.
                      CALL "MOVING" OF "src/test/resources/test/target_function_program.baby"
                - USING BY REFERENCE A
                - USING BY REFERENCE B
                - USING BY REFERENCE C.
           """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);

        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("42100054", output.getOutput());
    }
}
