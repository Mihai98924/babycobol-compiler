package se.group5.build;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
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

        ParseResult result = processor.parse(validInput);

        Assert.assertNotNull("Symbol table should not be null", result.symbolTable());
        Assert.assertTrue("Symbol table should be empty", result.symbolTable().table.isEmpty());
    }
}


