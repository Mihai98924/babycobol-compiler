package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstContractedExpressionsTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void ContractedExpressionTest() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B LIKE A.
                      01 C LIKE A.
                  PROCEDURE DIVISION.
                      MOVE 10 TO A.
                      MOVE 10 TO B.
                      MOVE 10 TO C.
                      IF A < B AND < C
                      THEN DISPLAY A
                      ELSE DISPLAY B
                      END.
           """;
        Program program = processor.parse(source);
        System.out.println(program);

        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
    }
}
