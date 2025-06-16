package se.group5.processor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessorDisplayTest {

    private ProgramInputStrategy strategy;
    private ProgramInputStrategy alphanumericStrategy;
    private ProgramDisplayStrategy displayStrategy;
    private Processor processor;
    private List<String> results;

    @Before
    public void setup() {
        processor = new Processor();
        results = new ArrayList<>();
        displayStrategy = new ProgramDisplayStrategy() {
            @Override
            public void display(String message) {
                results.add(message);
            }
        };
        strategy = new ProgramInputStrategy() {
            String[] inputs = {"1", "6", "2", "-10", "-1", "-5.5"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        };

        alphanumericStrategy = new ProgramInputStrategy() {
            String[] inputs = {"A", "AB", "ABC", "AB9", "AB10"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        };
    }

    @Test
    public void programRunTest_Display_Numeric_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99V99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                      01 E PICTURE IS S99.
                      01 F PICTURE IS S99V999.
                  PROCEDURE DIVISION.
                      ACCEPT A B C D E F.
                      DISPLAY A B C D E F.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy, displayStrategy);

        // Assert
        Assert.assertEquals("1.00", results.get(0).trim());
        Assert.assertEquals("6", results.get(1).trim());
        Assert.assertEquals("2", results.get(2).trim());
        Assert.assertEquals("10", results.get(3).trim());
        Assert.assertEquals("-1", results.get(4).trim());
        Assert.assertEquals("-5.500", results.get(5).trim());
    }

    @Test
    public void programRunTest_Display_Alphanumeric_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS XX.
                      01 B PICTURE IS XX.
                      01 C PICTURE IS XX.
                      01 D PICTURE IS XXX.
                      01 E PICTURE IS AXX.
                  PROCEDURE DIVISION.
                      ACCEPT A B C D E.
                      DISPLAY A B C D E.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(alphanumericStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("A", results.get(0).trim());
        Assert.assertEquals("AB", results.get(1).trim());
        Assert.assertEquals("AB", results.get(2).trim());
        Assert.assertEquals("AB9", results.get(3).trim());
        Assert.assertEquals("AB1", results.get(4).trim());
    }

    @Test
    public void programRunTest_Divide_DisplaysReducedSizeValue_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE C INTO B GIVING A.
                      DISPLAY A.
           """;

        Program program = processor.parse(source);
        program.run(strategy, displayStrategy);

        Assert.assertEquals("1\n", results.get(0));
    }
}
