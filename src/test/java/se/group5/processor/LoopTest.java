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
public class LoopTest {
    private Processor processor;
    private ProgramInputStrategy inputStrategy;
    private ProgramDisplayStrategy strategy;
    private List<String> outputs;

    @Before
    public void setup() {
        processor = new Processor();
        outputs = new ArrayList<>();
        strategy = outputs::add;
        inputStrategy = new ProgramInputStrategy() {
            final String[] inputs = {"0", "15", "5"};
            int inputIndex = 0;

            @Override
            public String getInput() {
                return inputs[(inputIndex++) % inputs.length];
            }
        };
    }

    @Test
    public void programRunTest_Loop_IncrementDefault() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 9.
                  PROCEDURE DIVISION.
                      LOOP VARYING X
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(9, outputs.size());
        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(String.valueOf(i + 1), outputs.get(i));
        }
    }

    @Test
    public void programRunTest_Loop_IncrementCustom() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 9V9.
                  PROCEDURE DIVISION.
                      LOOP VARYING X FROM 0.5 TO 9 BY 0.5
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(18, outputs.size());
        for (double i = 0, y = 0.5; i < 18; i++, y+=0.5) {
            Assert.assertEquals(String.valueOf(y), outputs.get((int)i));
        }
    }

    /// BY by default is 1
    @Test
    public void programRunTest_Loop_IncrementWithoutBY() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 9.
                  PROCEDURE DIVISION.
                      LOOP VARYING X FROM 2 TO 9
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        // Assert
        Assert.assertEquals(8, outputs.size());
        for (int i = 0; i < 8; i++) {
            Assert.assertEquals(String.valueOf(i + 2), outputs.get(i));
        }
    }

    /// TO by default is max picture value (99)
    @Test
    public void programRunTest_Loop_IncrementWithoutTO() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP VARYING X FROM 2
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        // Assert
        Assert.assertEquals(98, outputs.size());
        for (int i = 0; i < 98; i++) {
            Assert.assertEquals(String.valueOf(i + 2), outputs.get(i));
        }
    }

    /// TO by default is max picture value (9.9), so the loop should run 9 times
    @Test
    public void programRunTest_Loop_IncrementWithoutTO_Decimal() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 9V9.
                  PROCEDURE DIVISION.
                      LOOP VARYING X FROM 1
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        Assert.assertEquals(9, outputs.size());
        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(i + 1 + ".0", outputs.get(i));
        }
    }

    @Test
    public void programRunTest_Loop_IncrementWithVariables() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                      01 Y PICTURE IS 99.
                      01 Z PICTURE IS 99.
                      01 K PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT Y Z K.
                      LOOP VARYING X FROM Y TO Z BY K
                      DISPLAY X WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(4, outputs.size());
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals( String.valueOf(i * 5), outputs.get(i));
        }
    }

    @Test
    public void programRunTest_Loop_Increment_TwoCounters() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                      01 Y PICTURE IS 99.
                      01 Z PICTURE IS 99.
                      01 K PICTURE IS 99.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT Y Z K.
                      LOOP VARYING X FROM Y TO Z BY K VARYING A FROM 0 TO 4 BY 2
                      DISPLAY X WITH NO ADVANCING
                      DISPLAY A WITH NO ADVANCING
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(6, outputs.size());
        for (int i = 0, y = 1; i < 6; i+=2, y+=2) {
                Assert.assertEquals( String.valueOf(i/2 * 5), outputs.get(i));
                Assert.assertEquals( String.valueOf((y-1)/2 * 2), outputs.get(y));
        }
    }

    @Test
    public void programRunTest_Loop_Operations() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 9.
                      01 Y PICTURE IS 99.
                      01 Z PICTURE IS 99.
                      01 K PICTURE IS S99.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT Y Z K.
                      LOOP VARYING X
                      ADD 2 TO X GIVING Y
                      SUBTRACT 1 FROM K
                      END.
                      DISPLAY Y K.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(2, outputs.size());
        Assert.assertEquals(String.valueOf(11), outputs.get(0).trim());
        Assert.assertEquals(String.valueOf(-4), outputs.get(1).trim());
    }

    @Test
    public void programRunTest_Loop_Until() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(10, outputs.size());
    }

    @Test
    public void programRunTest_Loop_Until_Multiple() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP UNTIL X = 10
                      VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X > 1
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X < 8
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X >= 10
                      WHILE X < 20
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(2, outputs.size());
    }

    /// UNTIL breaks the loop after X is 5, so it should display 1, 2, 3, 4
    @Test
    public void programRunTest_Loop_Two_Untils_BreaksBeforeDisplay() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP VARYING X
                      UNTIL X = 5
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(4, outputs.size());
    }

    @Test
    public void programRunTest_Loop_Until_AfterLoop() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP UNTIL X = 5
                      VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(5, outputs.size());
    }

    @Test
    public void programRunTest_Loop_Until_NoExecution() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP UNTIL X < 5
                      VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(0, outputs.size());
    }

    @Test
    public void programRunTest_Loop_Until_Greater() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP UNTIL X > 5
                      VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 10
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(6, outputs.size());
    }

    ///  When X is 10 the display will not execute
    @Test
    public void programRunTest_Loop_While() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP VARYING X
                      WHILE X < 10
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 11
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(9, outputs.size());
    }

    @Test
    public void programRunTest_Loop_While_AfterLoop() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP WHILE X < 10
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 12
                      VARYING X
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(10, outputs.size());
    }

    @Test
    public void programRunTest_Loop_While_NoExecution() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP WHILE X > 10
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 12
                      VARYING X
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(0, outputs.size());
    }

    @Test
    public void programRunTest_Loop_While_Multiple() throws IOException {
        // Arrange
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 X PICTURE IS 99.
                  PROCEDURE DIVISION.
                      LOOP WHILE X < 2
                      DISPLAY X WITH NO ADVANCING
                      UNTIL X = 12
                      WHILE X > 5
                      VARYING X
                      WHILE X > 11
                      WHILE X < 4
                      END.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(inputStrategy, strategy);

        // Assert
        Assert.assertEquals(1, outputs.size());
    }
}
