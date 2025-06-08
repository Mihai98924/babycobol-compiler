package se.group5.processor;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramDisplayStrategy;
import se.group5.ast.ProgramInputStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessorDisplayTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void programRunTest_Divide_DisplaysTwoStrings_Passes() throws IOException {
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

        List<String> results = new ArrayList<>();
        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
                        String[] inputs = {"1", "6", "2"};
                        int inputIndex = 0;

                        @Override
                        public String getInput() {
                            return inputs[inputIndex++];
                        }
                    },
                new ProgramDisplayStrategy() {
                    @Override
                    public void display(String message) {
                        results.add(message);
                    }
                });

        Assert.assertEquals("1.0\n", results.get(0));
        Assert.assertEquals("3.0\n", results.get(1));
    }

    @Test
    public void programRunTest_Divide_DisplaysReducedSizeValue_Passes() throws IOException {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 9.
                      01 B PICTURE IS 99.
                      01 C PICTURE IS 99.
                      01 D PICTURE IS 99.
                  PROCEDURE DIVISION.
                      ACCEPT A B C.
                      DISPLAY A DELIMITED BY SIZE.
                      DIVIDE C INTO B GIVING A.
                      DISPLAY A.
           """;

        List<String> results = new ArrayList<>();
        Program program = processor.parse(source);
        program.run(new ProgramInputStrategy() {
                        String[] inputs = {"1", "6", "2"};
                        int inputIndex = 0;

                        @Override
                        public String getInput() {
                            return inputs[inputIndex++];
                        }
                    },
                new ProgramDisplayStrategy() {
                    @Override
                    public void display(String message) {
                        results.add(message);
                    }
                });

        Assert.assertEquals("1\n", results.get(0));
    }
}
