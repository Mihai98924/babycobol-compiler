package se.group5.ast.data_division;

import se.group5.ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsed PICTURE-string held in canonical form so that runtime
 * checks (ACCEPT/DISPLAY) can validate values easily.
 */
public final class Representation implements Node {
    private final List<PictureSymbol> pattern;

    public Representation(String raw) {
        raw = raw.trim();
        if (raw.isEmpty())
            throw new IllegalArgumentException("Empty PICTURE");

        int repeat = 1;                 // default: no repetition
        String picturePart = raw;       // default: whole string is picture

        if (raw.contains("(")) {
            int open  = raw.indexOf('(');
            int close = raw.indexOf(')', open + 1);
            if (close == -1)
                throw new IllegalArgumentException("Found '(' without ')'");

            picturePart = raw.substring(0, open).trim();
            String digits = raw.substring(open + 1, close).trim();
            if (digits.isEmpty())
                throw new IllegalArgumentException("Empty precision in PICTURE");

            repeat = Integer.parseInt(digits);
            if (repeat < 1)
                throw new IllegalArgumentException("Precision must be ≥ 1");
        }

        List<PictureSymbol> unit = picturePart.chars()
                .mapToObj(c -> PictureSymbol.fromGlyph((char) c))
                .toList();

        List<PictureSymbol> out = new ArrayList<>(unit.size() * repeat);
        for (int i = 0; i < repeat; i++)
            out.addAll(unit);

        this.pattern = List.copyOf(out);
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