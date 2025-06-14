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
public class SubtractionTest {
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
    public void programRunTest_Subtract_ReturnsMinus1_Passes() throws IOException {
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
                      SUBTRACT B FROM A.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractWithGIVING_ReturnsMinus1_Passes() throws IOException {
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
                      SUBTRACT B FROM A GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractLiteral_Returns1_Passes() throws IOException {
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
                      SUBTRACT 10 FROM 11 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiterals_ReturnsMinus69_Passes() throws IOException {
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
                      SUBTRACT 10 10 60 FROM 11 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-69.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-69.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiteralsWithIdentifiers_Returns2_Passes() throws IOException {
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
                      SUBTRACT 10 B 6 FROM 20 GIVING C D.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("D").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleLiteralsWithAndFromIdentifiers_NoGiving_ReturnsMinus5_Passes() throws IOException {
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
                      SUBTRACT 4 B 2 FROM C.
                      DISPLAY B.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(-5.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractComposites_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B.
                      SUBTRACT X FROM Y.
                      DISPLAY D.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_SubtractMultipleCompositeElements_Returns_Passes() throws IOException {
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
                                  07 B PICTURE IS 99.
                          03 UB.
                              05 Y.
                                  07 A PICTURE IS 99.
                                  07 B PICTURE IS 99.
                                  07 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X A OF Y B OF UA B OF UB.
                      SUBTRACT X FROM Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.A").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.UB.Y.B").getValue(), 0.001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_SubtractIdentifiers_HasPictureClauseX_Fail() throws IOException {
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
                      SUBTRACT A OF X FROM B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_SubtractComposites_HasPictureClauseX_Fail() throws IOException {
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
                      SUBTRACT X FROM Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_SubtractIdentifiers_HasPictureClauseA_Fail() throws IOException {
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
                      SUBTRACT B OF X FROM B OF Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void programRunTest_SubtractComposites_HasPictureClauseA_Fail() throws IOException {
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
                      SUBTRACT X FROM Y.
                      DISPLAY C.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);
    }
}
