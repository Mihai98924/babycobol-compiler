package se.group5.build;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;
import se.group5.ast.statement.Arithmetic;
import se.group5.ast.statement.Display;
import se.group5.ast.statement.Loop;
import se.group5.processor.Processor;

import java.io.IOException;

@Slf4j
public class AstLoopTest {
    private Processor processor;
    private ProgramInputStrategy strategy;

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
    }

    /// Process list should be stored in the loop, not in the program.
    @Test
    public void processesParseTest() throws IOException {
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
                      ADD X TO X
                      SUBTRACT 1 FROM X
                      UNTIL "HELLO"
                      END.
                      DISPLAY X WITH NO ADVANCING.
           """;

        // Act
        Program program = processor.parse(source);
        program.run(strategy);

        // Assert
        // Assert program structure
        Assert.assertEquals(2, program.procedures.size());
        Assert.assertEquals(Loop.class,
                program.procedures.get(0).get().getClass());
        Assert.assertEquals(Display.class,
                program.procedures.get(1).get().getClass());

        // Assert loop structure
        Assert.assertEquals(3, ((Loop)program.procedures.get(0).get()).procedures.size());
        Assert.assertEquals(Display.class,
                ((Loop)program.procedures.get(0).get()).procedures.get(0).get().getClass());
        Assert.assertEquals(Arithmetic.class,
                ((Loop)program.procedures.get(0).get()).procedures.get(1).get().getClass());
        Assert.assertEquals(Arithmetic.class,
                ((Loop)program.procedures.get(0).get()).procedures.get(2).get().getClass());
    }
}
