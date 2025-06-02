package se.group5.build;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.group5.ast.Program;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.statement.Arithmetic;
import se.group5.parser.CoBabyBoLLexer;
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

        System.out.println(procedures);
    }

    /**
     * Smoke‑test for the new look‑ahead helper in the lexer.
     * It makes sure that a lower‑case "to" is classified as an IDENTIFIER
     * whenever **another** real keyword TO will still occur before the end
     * of the logical line, and stays a keyword otherwise.
     */
    @Test
    public void AddModeLookaheadTo() {
        String src = """
                      ADD X to Y.
                      ADD X to Y TO Z.
                      ADD X TO Y.
            """;

        CoBabyBoLLexer lexer = new CoBabyBoLLexer(CharStreams.fromString(src));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        int idToCount = 0;
        int kwToCount = 0;

        for (Token t : tokens.getTokens()) {
            if ("to".equals(t.getText()) && t.getType() == CoBabyBoLLexer.IDENTIFIER) {
                idToCount++;
            }
            if ("TO".equals(t.getText()) && t.getType() == CoBabyBoLLexer.TO) {
                kwToCount++;
            }
        }

        Assert.assertEquals("Expected two lowercase 'to' identifiers", 2, idToCount);
        Assert.assertEquals("Expected two keyword 'TO's", 2, kwToCount);
    }
}