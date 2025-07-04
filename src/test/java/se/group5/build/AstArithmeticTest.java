package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.Arithmetic;
import se.group5.processor.Processor;

/**
 * Smoke-tests that the parser creates an {@link Arithmetic} node
 * for each COBOL arithmetic statement (ADD, SUBTRACT, MULTIPLY, DIVIDE).
 */
public class AstArithmeticTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    /* ------------------------------------------------------------- *
     *  ADD
     * ------------------------------------------------------------- */
    @Test
    public void AddOperation() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. ADDTEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 A PICTURE IS 99.
                   PROCEDURE DIVISION.
                       ADD 06 70 TO A.
            """;
        assertSingleArithmetic(source);
    }

    /* ------------------------------------------------------------- *
     *  SUBTRACT
     * ------------------------------------------------------------- */
    @Test
    public void SubtractOperation() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. SUBTRACTTEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 A PICTURE IS 99.
                   PROCEDURE DIVISION.
                       SUBTRACT 05 20 FROM A.
            """;
        assertSingleArithmetic(source);
    }

    /* ------------------------------------------------------------- *
     *  MULTIPLY
     * ------------------------------------------------------------- */
    @Test
    public void MultiplyOperation() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. MULTIPLYTEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 A PICTURE IS 99.
                   PROCEDURE DIVISION.
                       MULTIPLY 02 BY A.
            """;
        assertSingleArithmetic(source);
    }

    /* ------------------------------------------------------------- *
     *  DIVIDE
     * ------------------------------------------------------------- */
    @Test
    public void DivideOperation() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. DIVIDETEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 A PICTURE IS 99.
                   PROCEDURE DIVISION.
                       DIVIDE 02 INTO A.
            """;
        assertSingleArithmetic(source);
    }

    @Test
    public void DivideOperationWithOf() throws Exception {
        String source = """
                   IDENTIFICATION DIVISION.
                       PROGRAM-ID. DIVIDETEST.
                       AUTHOR. SUSPICIOUSLAWNMOWERS.
                       DATE-WRITTEN. 2022-04-22.
                   DATA DIVISION.
                     01 GROUP.
                       03 A PICTURE IS 99.
                   PROCEDURE DIVISION.
                       DIVIDE 02 INTO A OF GROUP.
            """;
        assertSingleArithmetic(source);
    }

    /* ------------------------------------------------------------- *
     *  Shared assertion helper
     * ------------------------------------------------------------- */
    private static void assertSingleArithmetic(String cobolSource) throws Exception {
        Program program = processor.parse(cobolSource);
        Assert.assertNotNull("Program should not be null", program);

        ProcedureList procedures = program.procedures;
        Assert.assertNotNull("Procedure list should not be null", procedures);
        Assert.assertEquals("Procedure list should contain exactly 1 procedure",
                1, procedures.size());

        Assert.assertTrue("Procedure should be an Arithmetic operation",
                procedures.get(0).get() instanceof Arithmetic);
    }
}
