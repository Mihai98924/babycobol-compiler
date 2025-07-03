package se.group5.processor.picture;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;
import se.group5.processor.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Logic for adapting to PICTURE patterns is the same for every arithmetic operation. Therefore,
 * the tests check addition only.
 */
@Slf4j
public class PictureNumericAddTest {
    private Processor processor;
    private List<String> results;
    private ProgramInputStrategy negativeInputStrategy, oneNegativeOnePositiveInputStrategy,
    zeroInputStrategy, minusZeroInputStrategy;
    private ProgramDisplayStrategy displayStrategy;

    @Before
    public void setup() {
        processor = new Processor();
        negativeInputStrategy = new ProgramInputStrategy() {
            String[] inputs = {"-5.5", "-10", "-12"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        };

        oneNegativeOnePositiveInputStrategy = new ProgramInputStrategy() {
            String[] inputs = {"-5.5", "10", "12", "15.25"};
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

    /// 10 Overflows to the left, so 0.0 is assigned to B.
    @Test
    public void programRunTest_Picture_NumericAdd_OneNegativeOnePositiveNumber_WithOverflow_0() throws IOException {
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

    /// 12 Overflows to the left, so 2.0 is assigned to B.
    @Test
    public void programRunTest_Picture_NumericAdd_OneNegativeOnePositiveNumber_WithOverflow_2() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZZV99.
                      01 C PICTURE IS SZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A TO C GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("-3.50", results.get(0).trim());
    }

    /// -12 Overflows to the left, so -2.0 is assigned to B.
    @Test
    public void programRunTest_Picture_NumericAdd_TwoNegativeNumbers_WithOverflow_Minus2() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZZV99.
                      01 B PICTURE IS SZZV99.
                      01 C PICTURE IS SZV9.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A TO C GIVING A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(negativeInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("-7.50", results.get(0).trim());
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

    /// 5.5 is truncated to 5
    @Test
    public void programRunTest_Picture_PatternDoesNotMatch_TruncateDecimalPart() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9999.
                      01 B PICTURE IS 9999.
                      01 C PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      ADD A TO C.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("7", results.get(0).trim());
    }

    /// Add -5.5 to 10, and truncate 4.5 to 4
    @Test
    public void programRunTest_Picture_PatternDoesNotMatch_TruncateDecimalPartFromResult() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZV9.
                      01 B PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO B.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("4", results.get(0).trim());
    }

    /// Add 15.25 to -5.5, and truncate 9.75 to 9.7
    @Test
    public void programRunTest_Picture_PatternDoesNotMatch_TruncatePartOfDecimalPartFromResult() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZ9V9.
                      01 B PICTURE IS 9999.
                      01 C PICTURE IS 9999.
                      01 D PICTURE IS SZZV99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C D.
                      ADD D TO A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("9.7", results.get(0).trim());
    }

    /// Truncate - from the result
    @Test
    public void programRunTest_Picture_NumericAdd_AddNumberToDouble_TruncateSign() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS SZV9.
                      01 B PICTURE IS ZZV99.
                  PROCEDURE DIVISION.
                      ACCEPT A B.
                      ADD A TO A GIVING B.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(oneNegativeOnePositiveInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals("11.00", results.get(0).trim());
    }
}
