package se.group5.processor;

import com.kitfox.svg.A;
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
    private ProgramDisplayStrategy strategy;
    private List<String> outputs;

    @Before
    public void setup() {
        processor = new Processor();
        outputs = new ArrayList<>();
        strategy = outputs::add;
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
                      01 X PICTURE IS 9.
                  PROCEDURE DIVISION.
                      LOOP VARYING X
                      DISPLAY X WITH NO ADVANCING
                      UNTIL "HELLO"
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
}
