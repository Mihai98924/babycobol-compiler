package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.Move;
import se.group5.processor.Processor;

public class AstMoveTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void MoveOperation() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. ADDTEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 A PICTURE IS 99.
                     01 B PICTURE IS 99.
                     01 C PICTURE IS 99.
                   PROCEDURE DIVISION.
                       MOVE C TO B.
                       MOVE B TO C.
                       MOVE SPACES TO A.
            """;
        Program program = processor.parse(source);
        System.out.println(program);

        Assert.assertNotNull("Program should not be null", program);

        ProcedureList procedures = program.procedures();
        System.out.println(procedures);
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("Procedure list should contain exactly 3 procedures",
                3, procedures.size());
        for(int i = 0; i < procedures.size(); i++) {
            Assert.assertTrue("Procedure should be a MOVE operation",
                    procedures.get(i).get() instanceof Move);
        }

    }
}
