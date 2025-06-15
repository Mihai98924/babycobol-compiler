package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.ProgramInputStrategy;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstGoToTest {
    private static Processor processor;
    private static ProgramInputStrategy strategy;

    @BeforeClass
    public static void setup() {
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

    // CALL HELLO USING BY VALUE 5 BY REFERENCE A BY CONTENT 6.
    @Test
    public void GoToOperation() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      DISPLAY "Hello World!".
                      GO TO AB.
                      GO TO A.
                      GO TO ABC.
                  A.
                      DISPLAY "This is 2.".
                  AB.
                      DISPLAY "This is 1.".
                  ABC.
                      DISPLAY "This is 3".
           """;
        Program program = processor.parse(source);
        System.out.println(program);

        ProcedureList procedures = program.procedures;
        program.run(strategy, System.out::println);
        Assert.assertNotNull("Program should not be null", program);
        Assert.assertNotNull("Procedure list should not be null", procedures);
    }
}
