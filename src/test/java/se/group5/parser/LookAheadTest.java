package se.group5.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Assert;
import org.junit.Test;
import se.group5.parser.CoBabyBoLLexer;

/**
 * Pure lexer smoke‑tests for the amended look‑ahead logic around lower‑case
 * "to" inside ADD‑statements.  Each test feeds *one* COBOL fixed‑format line
 * (prefixed with six sequence‑number columns and a blank indicator column)
 * straight into the lexer and checks how many occurrences are treated as the
 * keyword <code>TO</code> versus an identifier.
 */
public class LookAheadTest {

    /** 21 blanks – columns 1‑6 (sequence area) + column 7 (indicator) + some margin. */
    private static final String PREFIX = "                     ";

    private void assertToTokens(String codeLine, int expectedIdentifiers, int expectedKeywords) {
        String input = PREFIX + codeLine + "\n";  // terminate line so EOL token forms
        CoBabyBoLLexer lexer = new CoBabyBoLLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        int idCount = 0;
        int kwCount = 0;
        for (Token t : tokens.getTokens()) {
            if (t.getType() == CoBabyBoLLexer.IDENTIFIER && "to".equals(t.getText())) {
                idCount++;
            } else if (t.getType() == CoBabyBoLLexer.TO) {
                kwCount++;
            }
        }

        Assert.assertEquals("identifier 'to' count", expectedIdentifiers, idCount);
        Assert.assertEquals("keyword TO count", expectedKeywords, kwCount);
        // sanity: every ADD‑statement has exactly three occurrences
        Assert.assertEquals(3, idCount + kwCount);
    }

    /** ADD A to B ADD C TO D ADD E TO F. – first “to” becomes IDENTIFIER */
    @Test
    public void lowerTo_followedBy2RealTOs() {
        assertToTokens("ADD A to B ADD C TO D ADD E TO F.", 1, 2);
    }

    /** ADD A TO B ADD C to D ADD E TO F. – middle “to” becomes IDENTIFIER */
    @Test
    public void lowerTo_inMiddle_followedByRealTO() {
        assertToTokens("ADD A TO B ADD C to D ADD E TO F.", 1, 2);
    }

    /** ADD A TO B ADD C TO D ADD E to F. – trailing “to” seen as keyword */
    @Test
    public void lowerTo_atEnd_noRealTO() {
        assertToTokens("ADD A TO B ADD C TO D ADD E to F.", 0, 3);
    }

    /** ADD A to B ADD C to D ADD E TO F. – two IDENTIFIER "to"s */
    @Test
    public void twoLowerTo_beforeRealTO() {
        assertToTokens("ADD A to B ADD C to D ADD E TO F.", 2, 1);
    }

    /** ADD A to B ADD C TO D ADD E to F. – first IDENTIFIER, last keyword */
    @Test
    public void firstAndLastLowerTo_lastCountsAsKeyword() {
        assertToTokens("ADD A to B ADD C TO D ADD E to F.", 1, 2);
    }
}
