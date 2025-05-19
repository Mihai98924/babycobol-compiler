package se.group5.ast.data;

public enum PictureSymbol {
    /**
     * any numerical digit
     */
    DIGIT('9'),
    /**
     * any alphabetic character or whitespace
     */
    ALPHA('A'),
    /**
     * any single character
     */
    ALPHANUM('X'),
    /**
     * a leading digit, disappearing into space if zero
     */
    LEADING_DIGIT('Z'),
    /**
     * a sign, positive or negative, space is treated as a plus
     */
    SIGN('S'),
    /**
     * a decimal separator (usually '.' or ',')
     */
    DECIMAL('V');

    public final char glyph;

    PictureSymbol(char glyph) {
        this.glyph = glyph;
    }

    public static PictureSymbol fromGlyph(char c) {
        for (PictureSymbol s : values()) if (s.glyph == c) return s;
        throw new IllegalArgumentException("Illegal picture glyph: " + c);
    }
}
