package se.group5.build;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.Accept;
import se.group5.ast.statement.Display;
import se.group5.processor.Processor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AstDataTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test(expected = ParseCancellationException.class)
    public void requiresIdentificationDivision() throws Exception {
        processor.parse("PROCEDURE DIVISION. DISPLAY 'Hello'.");
    }

    @Test
    public void parsesIdentificationDivision() throws Exception {
        String validInput = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. MULTIPLICATIONTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                """;

        Program program = processor.parse(validInput);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Identity table should not be null", program.identityTable());

        var identityTable = program.identityTable();

        Assert.assertTrue("MULIPLICATIONTEST is present", identityTable.resolve("PROGRAM-ID").isPresent());
        Assert.assertEquals("MULTIPLICATIONTEST", identityTable.resolve("PROGRAM-ID").get());

        Assert.assertTrue("AUTHOR is present", identityTable.resolve("AUTHOR").isPresent());
        Assert.assertEquals("SUSPICIOUSLAWNMOWERS", identityTable.resolve("AUTHOR").get());

        Assert.assertTrue("DATE-WRITTEN is present", identityTable.resolve("DATE-WRITTEN").isPresent());
        Assert.assertEquals("2022-04-22", identityTable.resolve("DATE-WRITTEN").get());
    }

    @Test
    public void parsesDataDivision() throws Exception {
        String validInput = """
                       IDENTIFICATION DIVISION.
                       DATA DIVISION.
                         01 VAR1 PICTURE IS 9 (04).
                         01 VAR2 PICTURE IS ZZZ9.
                         01 VAR3 LIKE VAR1.
                         01 VAR4 LIKE VAR2 OCCURS 9 TIMES.
                         01 SOME-GROUP.
                            03 VAR5 PICTURE IS 9 (04).
                       PROCEDURE DIVISION.
                         ACCEPT VAR1 VAR3.
                         DISPLAY VAR1 VAR3 DELIMITED BY SPACE "HENK" DELIMITED BY "E".
                """;

        Program program = processor.parse(validInput);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Symbol table should not be null", program.symbolTable());

        SymbolTable symbolTable = program.symbolTable();

        assertDataDefinition(symbolTable.resolve("VAR1"), 1, "VAR1", "9999", 0);
        assertDataDefinition(symbolTable.resolve("VAR2"), 1, "VAR2", "ZZZ9", 0);
        assertDataDefinition(symbolTable.resolve("VAR3"), 1, "VAR3", "9999", 0);
        assertDataDefinition(symbolTable.resolve("VAR4"), 1, "VAR4", "ZZZ9", 9);

        Assert.assertTrue("SOME-GROUP is present", symbolTable.resolve("SOME-GROUP").isPresent());
        DataGroup group = (DataGroup) symbolTable.resolve("SOME-GROUP").get();
        Assert.assertEquals("Name mismatch for group", "SOME-GROUP", group.name().toString());
        assertDataDefinition(group.resolve("VAR5"), 3, "VAR5", "9999", 0);

        ProcedureList procedures = program.procedures();
        Assert.assertNotNull("Procedures aren't present", procedures);
        Assert.assertFalse("Procedures aren't present", procedures.isEmpty());

        Optional<Procedure> acceptProcedure = procedures.get(0);
        Assert.assertTrue("Procedure should be present", acceptProcedure.isPresent());
        Assert.assertTrue("Procedure is not an instance of accept", acceptProcedure.get() instanceof Accept);

        Accept accept = (Accept) acceptProcedure.get();
        List<Identifier> targets = accept.targets();
        Assert.assertEquals("Accept correctly targets VAR1", "VAR1", targets.get(0).toString());
        Assert.assertEquals("Accept correctly targets VAR1", "VAR3", targets.get(1).toString());


    }

    private void assertDataDefinition(
            Optional<DataDefinition> optional,
            int expectedLevel,
            String expectedName,
            String expectedPicture,
            int expectedOccurs
    ) {
        Assert.assertTrue("Data definition should be present for " + expectedName, optional.isPresent());
        DataElement element = (DataElement) optional.get();
        Assert.assertEquals("Level mismatch for " + expectedName, expectedLevel, element.level());
        Assert.assertEquals("Name mismatch for " + expectedName, expectedName, element.name().toString());
        Assert.assertEquals("Picture mismatch for " + expectedName, expectedPicture, element.picture().toString());
        Assert.assertEquals("Occurs mismatch for " + expectedName, expectedOccurs, element.occurs());
    }

    @Test
    public void parsesDisplayStatementWithDelimiters() throws Exception {
        String cobol = """
                       IDENTIFICATION DIVISION.
                       DATA DIVISION.
                         01 VAR1 PICTURE IS 9 (04).
                         01 VAR3 PICTURE IS 9 (04).
                       PROCEDURE DIVISION.
                         DISPLAY VAR1 VAR3 DELIMITED BY SPACE "HENK" DELIMITED BY "E".
                """;

        Program program = processor.parse(cobol);
        ProcedureList procedures = program.procedures();

        Assert.assertEquals("Exactly two procedures should be parsed (DISPLAY)", 1, procedures.size());

        Optional<Procedure> displayOpt = procedures.get(0);
        Assert.assertTrue("Second procedure should be present", displayOpt.isPresent());
        Assert.assertTrue("Second procedure should be an instance of Display", displayOpt.get() instanceof Display);

        Display display = (Display) displayOpt.get();
        Assert.assertFalse("DISPLAY must not use NO ADVANCING in the test source", display.isNoAdvancing());

        // ─── Argument list ──────────────────────────────────────────────────────
        Assert.assertEquals("DISPLAY must contain three arguments", 3, display.getArguments().size());

        Display.Argument arg1 = display.getArguments().get(0);
        Assert.assertEquals("First atomic should be VAR1", "ATOMIC(ELEM(1, VAR1, 9999))", arg1.atomic().toString());
        Assert.assertNull("First argument has no delimiter", arg1.delimiter());

        Display.Argument arg2 = display.getArguments().get(1);
        Assert.assertEquals("Second atomic should be VAR3", "ATOMIC(ELEM(1, VAR3, 9999))", arg2.atomic().toString());
        Assert.assertNotNull("Second argument must have a delimiter", arg2.delimiter());
        Assert.assertEquals("Delimiter type must be SPACE", Display.DelimiterType.SPACE, arg2.delimiter().type());

        Display.Argument arg3 = display.getArguments().get(2);
        Assert.assertEquals("Third atomic should be \"HENK\"", "ATOMIC(AlphanumericLiteral[value=\"HENK\"])", arg3.atomic().toString());
        Assert.assertNotNull("Third argument must have a delimiter", arg3.delimiter());
        Assert.assertEquals("Delimiter type must be LITERAL", Display.DelimiterType.LITERAL, arg3.delimiter().type());
        Assert.assertEquals("Delimiter literal must be \"E\"", "AlphanumericLiteral[value=\"E\"]", arg3.delimiter().literal().toString());

        // ─── toString() – round‑trip serialisation check ───────────────────────
        String expected = "DISPLAY(ATOMIC(ELEM(1, VAR1, 9999)), ATOMIC(ELEM(1, VAR3, 9999)) DELIMITED BY SPACE, ATOMIC(AlphanumericLiteral[value=\"HENK\"]) DELIMITED BY AlphanumericLiteral[value=\"E\"])";
        Assert.assertEquals("DISPLAY.toString() must match exactly", expected, display.toString());
    }

    @Test
    public void sufficientQualificationTest_Ambiguous_Fail() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 A PICTURE IS 99.
                          03 X.
                              05 Y.
                                  07 C PICTURE IS 99.
                      01 J LIKE X.
                          03 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO C OF X Y.
           """;

        Assert.assertThrows(IllegalStateException.class, () -> processor.parse(source));
    }

    @Test
    public void sufficientQualificationTest_NotAmbiguous_Pass() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 A PICTURE IS 99.
                          03 X.
                              05 Y.
                                  07 C PICTURE IS 99.
                      01 J LIKE X.
                          03 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO C OF J Y.
                      MOVE HIGH-VALUES TO C OF Y OF X Y.
           """;

        Assert.assertNotNull(processor.parse(source));
    }
}