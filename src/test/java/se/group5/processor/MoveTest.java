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
            final String[] inputs = {"1", "2", "3", "4", "5"};
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
                          03 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X B OF X A OF Y B OF Y C.
                      MOVE X TO Y.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("X.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("X.B").getValue(), 0.001);
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("Y.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("Y.B").getValue(), 0.001);
        Assert.assertEquals(5.0, (double)program.symbolTable.table.get("Y.C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_MoveComposite_NoSufficientQualification_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 CONTAINER.
                          03 X.
                              05 A PICTURE IS 99.
                              05 B PICTURE IS 99.
                          03 Y.
                              05 A PICTURE IS 99.
                              05 B PICTURE IS 99.
                              05 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A OF X B OF X A OF Y B OF Y C.
                      MOVE X TO CONTAINER.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(1.0, (double)program.symbolTable.table.get("CONTAINER.X.A").getValue(), 0.001);
        Assert.assertEquals(2.0, (double)program.symbolTable.table.get("CONTAINER.X.B").getValue(), 0.001);
        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("CONTAINER.Y.A").getValue(), 0.001);
        Assert.assertEquals(4.0, (double)program.symbolTable.table.get("CONTAINER.Y.B").getValue(), 0.001);
        Assert.assertEquals(5.0, (double)program.symbolTable.table.get("CONTAINER.Y.C").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Move_HighValues_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 9999.
                      01 C PICTURE IS 99V9.
                      01 D PICTURE IS S99V99.
                      01 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO A B C D E.
           """;

        String expectedStringE = "+9.z" + (char)255 + "9";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(99.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(9999.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(99.9, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(99.99, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }

    @Test
    public void programRunTest_Move_LowValues_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 9999.
                      01 C PICTURE IS 99V9.
                      01 D PICTURE IS S99V99.
                      01 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE LOW-VALUES TO A B C D E.
           """;

        String expectedStringE = "-0. " + (char)0 + " ";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(-00.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-00.00, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }

    @Test
    public void programRunTest_Move_Spaces_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                      01 B PICTURE IS 9999.
                      01 C PICTURE IS 99V9.
                      01 D PICTURE IS S99V99.
                      01 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE SPACES TO A B C D E.
           """;

        String expectedStringE = " 0.   ";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(00.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(00.00, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }

    @Test
    public void programRunTest_Move_Composite_LowValues_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 9999.
                          03 C PICTURE IS 99V9.
                          03 D PICTURE IS S99V99.
                          03 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE LOW-VALUES TO X.
           """;
        String expectedStringE = "-0. " + (char)0 + " ";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(-00.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(-00.00, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }

    @Test
    public void programRunTest_Move_Composite_HighValues_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 9999.
                          03 C PICTURE IS 99V9.
                          03 D PICTURE IS S99V99.
                          03 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE HIGH-VALUES TO X.
           """;

        String expectedStringE = "+9.z" + (char)255 + "9";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(99.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(9999.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(99.9, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(99.99, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }

    @Test
    public void programRunTest_Move_Composite_Spaces_Passes() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X.
                          03 A PICTURE IS 99.
                          03 B PICTURE IS 9999.
                          03 C PICTURE IS 99V9.
                          03 D PICTURE IS S99V99.
                          03 E PICTURE IS S9VAXZ.
                  PROCEDURE DIVISION.
                      MOVE SPACES TO X.
           """;

        String expectedStringE = " 0.   ";

        // Act
        Program program = processor.parse(source);
        program.run(compositesStrategy);

        // Assert
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
        Assert.assertEquals(0.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
        Assert.assertEquals(00.0, (double)program.symbolTable.table.get("C").getValue(), 0.001);
        Assert.assertEquals(00.00, (double)program.symbolTable.table.get("D").getValue(), 0.001);
        Assert.assertEquals(expectedStringE, program.symbolTable.table.get("E").getValue().toString());
    }
}
