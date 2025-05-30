package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.processor.Processor;

public class AstCallTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void CallOperation() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                      01 A PICTURE IS 99.
                  PROCEDURE DIVISION.
                      CALL HELLO USING BY VALUE 5 BY REFERENCE A BY CONTENT 6.
                      CALL FUNCTION OF HELLO.
           """;
        Program program = processor.parse(source);
        System.out.println(program);
    }
}
