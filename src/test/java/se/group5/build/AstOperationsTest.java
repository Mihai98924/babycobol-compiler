package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

import java.io.IOException;

public class AstOperationsTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void AddOperation() throws Exception {
        String validInput = """
                       IDENTIFICATION DIVISION.
                           PROGRAM-ID. MULTIPLICATIONTEST.
                           AUTHOR. SUSPICIOUSLAWNMOWERS.
                           DATE-WRITTEN. 2022-04-22.
                       DATA DIVISION.
                            01 A PICTURE IS 99.
                       PROCEDURE DIVISION.
                          ADD 06 70 TO A.
                """;
        Program program = processor.parse(validInput);
        Assert.assertNotNull("Program should not be null", program);
        ProcedureList procedureList = program.procedures();
        Assert.assertNotNull("Procedure list should not be null", procedureList);
        System.out.println(program);
    }
}
