package se.group5.processor.arithmetic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;
import se.group5.processor.Processor;

import java.io.IOException;

@Slf4j
public class MultiplicationTest {
    private Processor processor;
    private ProgramInputStrategy strategy, compositesStrategy;

    @Before
    public void setup() {
        processor = new Processor();
        strategy = new ProgramInputStrategy() {
            final String[] inputs = {"1", "2", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };

        compositesStrategy = new ProgramInputStrategy() {
            final String[] inputs = {"1", "2", "3", "4"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };
    }

    @Test
    public void programRunTest_Multiply_Returns6_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY B BY C GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiterals_Returns30_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY 5 GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(30.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyMultipleIdentifiers_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY A B C.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(18.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiteralByIdentifier_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY B GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MultiplyLiteralByMultipleIdentifiers_WithGiving_Fails() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      MULTIPLY 6 BY B C GIVING A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(12.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_MultiplicationIdentifiers_HasPictureClauseX_Fail() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 UA.
                              05 X.
                                  07 A PICTURE IS 9X.
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      MULTIPLY A OF X BY B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_MultiplicationIdentifiers_HasPictureClauseA_Fail() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 UA.
                              05 X.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 9A.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      MULTIPLY B OF X BY B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }
}
