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
import se.group5.processor.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AstBuilderTest {

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
        System.out.println(program);
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
}