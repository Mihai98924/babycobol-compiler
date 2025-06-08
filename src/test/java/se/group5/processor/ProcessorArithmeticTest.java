package se.group5.processor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;


@Slf4j
public class ProcessorArithmeticTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void programRunTest() throws IOException {
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
                      ADD A TO B.
                      DISPLAY B.
           """;

        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
            String[] inputs = {"1", "2", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        });

        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("B").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Subtract_ReturnsMinus1_Passes() throws IOException {
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

        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
            String[] inputs = {"1", "2", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        });

        Assert.assertEquals(-1.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Multiply_Returns6_Passes() throws IOException {
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

        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
            String[] inputs = {"1", "2", "3"};
            int inputIndex = 0;
            @Override
            public String getInput() {
                return inputs[inputIndex++];
            }
        });

        Assert.assertEquals(6.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }

    @Test
    public void programRunTest_Divide_Returns3_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9V9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE C INTO B GIVING A.
                      DISPLAY A.
           """;

        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
                String[] inputs = {"1", "6", "2"};
                int inputIndex = 0;
                @Override
                public String getInput() {
                return inputs[inputIndex++];
            }
            }, System.out::print);

        Assert.assertEquals(3.0, (double)program.symbolTable.table.get("A").getValue(), 0.001);
    }
}
