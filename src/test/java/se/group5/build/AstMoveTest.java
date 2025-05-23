package se.group5.build;

import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.processor.Processor;

public class AstMoveTest {
    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void MoveOperation() throws Exception {
        String source = """
                  IDENTIFICATION DIVISION.
                      PROGRAM-ID. ADDTEST.
                      AUTHOR. SUSPICIOUSLAWNMOWERS.
                      DATE-WRITTEN. 2022-04-22.
                  DATA DIVISION.
                       01 A PICTURE IS 99.
                       01 B PICTURE IS 99.
                       01 C PICTURE IS 99.
                  PROCEDURE DIVISION.
                     MOVE C TO B.
                     MOVE 09 TO A.
            """;
        Program program = processor.parse(source);
        System.out.println(program);
    }
}
