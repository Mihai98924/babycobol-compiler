package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstGoToTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    // CALL HELLO USING BY VALUE 5 BY REFERENCE A BY CONTENT 6.
    @Test
    public void GoToOperation() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      DISPLAY "Hello World!".
                      GO TO B.
                  ABC.
                      DISPLAY "This is ABC.".
                  B.
                      DISPLAY "This is B.".
           """;
        Program program = processor.parse(source);
        System.out.println(program);

        ProcedureList procedures = program.procedures();
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
    }
}
