package se.group5.ast.data;

public enum PictureSymbol {
    /**
     * numeric 0‑9
     */
    DIGIT('9'),
    /**
     * alphabetic A‑Z
     */
    ALPHA('A'),
    /**
     * alphanumeric
     */
    ALPHANUM('X'),
    /**
     * editing sign (S/CR/DB)
     */
    SIGN('S'),
    /**
     * assumed decimal V
     */
    DECIMAL('V');
    /**
     *
     */

    public final char glyph;

    PictureSymbol(char glyph) {
        this.glyph = glyph;
    }

    public static PictureSymbol fromGlyph(char c) {
        for (PictureSymbol s : values()) if (s.glyph == c) return s;
        throw new IllegalArgumentException("Illegal picture glyph: " + c);
    }
}
