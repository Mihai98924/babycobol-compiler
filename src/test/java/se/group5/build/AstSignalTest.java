package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.StringAccumulatingDisplayStrategy;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstSignalTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void SignalTriggersFunction() throws Exception {
        String source = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. ADDTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                           01 A PICTURE IS 99.
                           01 B PICTURE IS AA.
                       PROCEDURE DIVISION.
                           MOVE "EE" TO B.
                           SIGNAL OOPS ON ERROR.
                           GO TO B.
                           SIGNAL OFF.
                       OOPS.
                         DISPLAY "AN ERROR HAS OCCURRED".
                """;
        Program program = processor.parse(source);
        var output = new StringAccumulatingDisplayStrategy();
        program.run(null, output);

        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("AN ERROR HAS OCCURRED", output.getOutput().trim());
    }

    @Test
    public void SignalTurnsOffCorrectly() throws Exception {
        String source = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. ADDTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                           01 A PICTURE IS 99.
                           01 B PICTURE IS AA.
                       PROCEDURE DIVISION.
                           MOVE "EE" TO B.
                           SIGNAL OOPS ON ERROR.
                           GO TO B.
                           SIGNAL OFF.
                           GO TO B.
                       OOPS.
                         DISPLAY "AN ERROR HAS OCCURRED".
                """;
        Program program = processor.parse(source);
        Assert.assertThrows(IllegalStateException.class, program::run);
    }
}
