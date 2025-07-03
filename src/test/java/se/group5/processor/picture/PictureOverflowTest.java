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

@Slf4j
public class PictureOverflowTest {
    private Processor processor;
    private List<String> results;
    private ProgramInputStrategy numericOverflowInputStrategy,
        alphanumericOverflowInputStrategy;
    private ProgramDisplayStrategy displayStrategy;

    @Before
    public void setup() {
        processor = new Processor();
        numericOverflowInputStrategy = new ProgramInputStrategy() {
            @Override
            public String getInput() {
                return "10000";
            }
        };

        alphanumericOverflowInputStrategy = () -> "ABCDEFG";

        results = new ArrayList<>();
        displayStrategy = message -> results.add(message);
    }

    @Test
    public void programRunTest_Picture_NumericOverflow_OverflowLeft() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(numericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("0000", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericOverflow_OverflowTwice() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      ADD 20011 TO A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(numericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("0011", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericOverflow_RecoverFromTwiceOverflow() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      ADD 20011 TO A.
                      ADD 1 TO A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(numericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("12", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_NumericOverflow_RecoverFromOverflow() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9999.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      ADD 1 TO A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(numericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("1", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_AlphanumericOverflow_OverflowRight() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS XXXX.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(alphanumericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("ABCD", results.get(0).trim());
    }

    @Test
    public void programRunTest_Picture_AlphanumericOverflow_OverflowTwice() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS XXXX.
                  PROCEDURE DIVISION.
                      ACCEPT A.
                      ADD "EF" TO A.
                      DISPLAY A.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(alphanumericOverflowInputStrategy, displayStrategy);

        // Assert
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("EFAB", results.get(0).trim());
    }
}
