package se.group5.build;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.processor.ParseResult;
import se.group5.processor.Processor;

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
    public void allowsMinimalProgramWithIdentificationDivision() throws Exception {
        String validInput = ""
                + "       IDENTIFICATION DIVISION.\n"
                + "           PROGRAM-ID. MULTIPLICATIONTEST.";

        Program program = processor.parse(validInput);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Symbol table should not be null", program.getSymbolTable());
        Assert.assertTrue("Symbol table should be empty", program.getSymbolTable().isEmpty());
        Assert.assertNotNull("Identification division should not be null", program.getIdentityTable());
        System.out.println("Identification division: " + program.getIdentityTable().table);
        Assert.assertFalse("Identification division should not be empty", program.getIdentityTable().isEmpty());
    }


}