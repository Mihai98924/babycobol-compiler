package se.group5.processor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PictureNumericArithmeticTest {
    private Processor processor;
    private List<String> results;
    private ProgramInputStrategy negativeInputStrategy, oneNegativeOnePositiveInputStrategy,
    zeroInputStrategy, minusZeroInputStrategy;
    private ProgramDisplayStrategy displayStrategy;

    @Before
    public void setup() {
        processor = new Processor();
        negativeInputStrategy = new ProgramInputStrategy() {
            String[] inputs = {"-5.5", "-10"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        };

        oneNegativeOnePositiveInputStrategy = new ProgramInputStrategy() {
            String[] inputs = {"-5.5", "10"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        };

        zeroInputStrategy = () -> "0";
        minusZeroInputStrategy = () -> "-0";

        results = new ArrayList<>();
        displayStrategy = message -> results.add(message);
    }

    // Addition

    @Test
    public void programRunTest_Picture_NumericAdd_TwoNegativeNumbers() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(negativeInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("-15.50", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericAdd_OneNegativeOnePositiveNumber() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("4.50", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericAdd_OneNegativeOnePositiveNumber_WithOverflow() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("-5.50", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericAdd_AddTwoZeros() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(zeroInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("0.00", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericAdd_AddTwoNegativeZeros() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(minusZeroInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("0.00", results.get(0).trim());
    }
}
