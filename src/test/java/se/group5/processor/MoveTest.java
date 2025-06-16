package se.group5.processor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;

@Slf4j
public class MoveTest {
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
    public void programRunTest_Move_Returns1_Passes() throws IOException {
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
                      MOVE A TO B.
                      DISPLAY B.
           """;

        // TODO should move atomic, move A to B? OR only composite to composite?
        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MoveComposite_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                      01 Y.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X B OF X A OF Y B OF Y.
                      MOVE X TO Y.
                      DISPLAY B OF Y.
           """;

        // TODO should move atomic, move A to B? OR only composite to composite?
        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("Y.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("Y.B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Move_LowValues_Numeric_Passes() throws IOException {
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
                      ACCEPT A B C D.
                      MOVE LOW-VALUES TO A B.
                      DISPLAY B.
           """;

        // TODO should move atomic, move A to B? OR only composite to composite?
        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(Double.MIN_VALUE, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(Double.MIN_VALUE, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Move_HighValues_Numeric_Passes() throws IOException {
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
                      ACCEPT A B C D.
                      MOVE HIGH-VALUES TO A B.
                      DISPLAY B.
           """;

        // TODO should move atomic, move A to B? OR only composite to composite?
        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(Double.MAX_VALUE, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(Double.MAX_VALUE, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Move_Composite_LowValues_Numeric_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                      01 Y.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X B OF X A OF Y B OF Y.
                      MOVE LOW-VALUES TO Y.
                      DISPLAY B OF Y.
           """;

        // TODO should move atomic, move A to B? OR only composite to composite?
        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(Double.MIN_VALUE, (double)program.symbolTable.table.get("Y.A").getValue(), 0.001);
        Assert.assertEquals(Double.MIN_VALUE, (double)program.symbolTable.table.get("Y.B").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("X.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("X.B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Move_Composite_HighValues_Numeric_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                      01 Y.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X B OF X A OF Y B OF Y.
                      MOVE HIGH-VALUES TO Y.
                      DISPLAY B OF Y.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(Double.MAX_VALUE, (double)program.symbolTable.table.get("Y.A").getValue(), 0.001);
        Assert.assertEquals(Double.MAX_VALUE, (double)program.symbolTable.table.get("Y.B").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("X.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("X.B").getValue(), 0.001);
    }
}
