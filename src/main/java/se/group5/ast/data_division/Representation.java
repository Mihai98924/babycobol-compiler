package se.group5.ast.data_division;

import se.group5.ast.Node;

import java.util.List;

/**
 * Parsed PICTURE-string held in canonical form so that runtime
 * checks (ACCEPT/DISPLAY) can validate values easily.
 */
public final class Representation implements Node {
    private final List<PictureSymbol> pattern;

    public Representation(String raw) {
        // strip surrounding whitespace and parse symbol by symbol
        raw = raw.trim();
        if (raw.isEmpty()) throw new IllegalArgumentException("Empty PICTURE");
        this.pattern = raw.chars()
                .mapToObj(c -> PictureSymbol.fromGlyph((char) c))
                .toList();
    }

    public int length() {
        return pattern.size();
    }

    public List<PictureSymbol> symbols() {
        return pattern;
    }

    /**
     * Quick helper that tells whether supplied text already matches the picture
     */
    public boolean matches(String text) {
        if (text.length() != pattern.size()) return false;
        for (int i = 0; i < text.length(); i++) {
            PictureSymbol pic = pattern.get(i);
            char ch = text.charAt(i);
            switch (pic) {
                case DIGIT -> {
                    if (!Character.isDigit(ch)) return false;
                }
                case ALPHA -> {
                    if (!Character.isLetter(ch)) return false;
                }
                case ALPHANUM -> { /* any printable char */ }
                case SIGN -> { /* sign char is implementation defined – accept +‑CR/DB etc. */ }
                case DECIMAL -> { /* nothing stored, logical point */ }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return pattern.stream().map(s -> String.valueOf(s.glyph)).reduce("", String::concat);
    }
}