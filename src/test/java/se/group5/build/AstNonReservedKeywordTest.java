package se.group5.build;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.processor.Processor;

public class AstNonReservedKeywordTest {

    private static Processor processor;

    @BeforeClass
    public static void setup() {
        processor = new Processor();
    }

    @Test
    public void AddOperation() throws Exception {
        String cobol = """
                   IDENTIFICATION DIVISION.
                   DATA DIVISION.
                     01 A PICTURE IS 9 (9).
                     01 B LIKE A.
                     01 C LIKE A.
                     01 D LIKE A.
                     01 E LIKE A.
                     01 F LIKE A.
                     01 to LIKE A.
                   PROCEDURE DIVISION.
                       ADD A TO B ADD C TO D ADD E TO F.
                       ADD A to B ADD C TO D ADD E TO F.
                       ADD A TO B ADD C to D ADD E TO F.
                       ADD A TO B ADD C TO D ADD E to F.
                       ADD A to B ADD C to D ADD E TO F.
                       ADD A to B ADD C TO D ADD E to F.
            """;

        Program program = processor.parse(cobol);
        ProcedureList procedures = program.procedures();

        Assert.assertEquals("[ADD(SOURCES(ATOMIC(ELEM(1, A, 999999999))), TO(ATOMIC(ELEM(1, B, 999999999)))), ADD(SOURCES(ATOMIC(ELEM(1, A, 999999999)), ATOMIC(ELEM(1, to, 999999999)), ATOMIC(ELEM(1, B, 999999999)), ATOMIC(ELEM(1, C, 999999999))), TO(ATOMIC(ELEM(1, D, 999999999)))), ADD(SOURCES(ATOMIC(ELEM(1, A, 999999999))), TO(ATOMIC(ELEM(1, B, 999999999)))), ADD(SOURCES(ATOMIC(ELEM(1, A, 999999999))), TO(ATOMIC(ELEM(1, B, 999999999)))), ADD(SOURCES(ATOMIC(ELEM(1, A, 999999999)), ATOMIC(ELEM(1, to, 999999999)), ATOMIC(ELEM(1, B, 999999999)), ATOMIC(ELEM(1, C, 999999999))), TO(ATOMIC(ELEM(1, D, 999999999))))]", procedures.toString());
    }
}