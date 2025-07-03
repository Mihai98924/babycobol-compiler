package se.group5.ast.data;

import se.group5.ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsed PICTURE-string held in canonical form so that runtime
 * checks (ACCEPT/DISPLAY) can validate values easily.
 */
public final class Representation implements Node, Typeable {
    private final List<PictureSymbol> pattern;

    public Representation(List<PictureSymbol> pattern, int occurs) {
        this.pattern = new ArrayList<>(pattern.size() * occurs);
        for (int i = 0; i < occurs; i++) {
            this.pattern.addAll(pattern);
        }
    }

    public List<PictureSymbol> getPattern() {
        return pattern;
    }

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

    public boolean containsSymbol(PictureSymbol symbol) {
        return pattern.stream().anyMatch(pic -> pic.glyph == symbol.glyph);
    }

    public boolean isPatternNumeric() {
        return pattern.stream().allMatch(pic -> pic.glyph != PictureSymbol.ALPHANUM.glyph &&
                pic.glyph != PictureSymbol.ALPHA.glyph);
    }

    /**
     * Quick helper that tells whether supplied text already matches the picture
     */
    public boolean matches(String text) {

        // We don't match S, we store it internally
        List<PictureSymbol> patternToMatch = pattern.stream().filter(symbol -> symbol.glyph !=
                PictureSymbol.SIGN.glyph).toList();

        if (text.length() != patternToMatch.size()) return false;
        boolean leading = true;
        for (int i = 0; i < text.length(); i++) {
            PictureSymbol pic = patternToMatch.get(i);
            char ch = text.charAt(i);
            switch (pic) {
                case DIGIT -> {
                    leading = false;
                    if (!Character.isDigit(ch)) return false;
                }
                case ALPHA -> {
                    leading = false;
                    if (!Character.isLetter(ch)) return false;
                }
                case ALPHANUM -> {
                    leading = false;
                    /* any printable char */ }
                case SIGN -> {
                    /* sign char is implementation defined – accept +‑CR/DB etc. */
                    // A sign has to be the first character in the string
                    if(!(ch == '-' || ch == '+' || Character.isSpaceChar(ch)) && leading) return false;
                    leading = false;
                }
                case DECIMAL -> {
                    leading = false;
                    /* nothing stored, logical point */
                    if(!(ch == ',' || ch == '.' )) return false;
                }
                case LEADING_DIGIT -> {
                    if(Character.isDigit(ch)) {
                        leading = false;
                    } else if (!Character.isSpaceChar(ch) && leading) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean matches(List<PictureSymbol> pictureSymbols) {
        if(pictureSymbols.size() != pattern.size()) return false;

        for (int i = 0; i < pattern.size(); i++) {
            if(pattern.get(i).glyph != pictureSymbols.get(i).glyph) return false;
        }

        return true;
    }

    public String maxValueStringForRepresentation() {
        StringBuilder result = new StringBuilder();
        for (PictureSymbol pictureSymbol : pattern) {
            switch (pictureSymbol) {
                case DIGIT, LEADING_DIGIT -> result.append('9');
                case ALPHA -> result.append('z');
                case ALPHANUM -> result.append((char)255);
                case SIGN -> result.append('+');
                case DECIMAL -> result.append('.');
            }
        }
        return result.toString();
    }

    public String minValueStringForRepresentation() {
        StringBuilder result = new StringBuilder();
        for (PictureSymbol pictureSymbol : pattern) {
            switch (pictureSymbol) {
                case DIGIT -> result.append('0');
                case ALPHA, LEADING_DIGIT -> result.append(' ');
                case ALPHANUM -> result.append((char)0);
                case SIGN -> result.append('-');
                case DECIMAL -> result.append('.');
            }
        }
        return result.toString();
    }

    public String spacesValueStringForRepresentation() {
        StringBuilder result = new StringBuilder();
        for (PictureSymbol pictureSymbol : pattern) {
            switch (pictureSymbol) {
                case DIGIT-> result.append('0');
                case ALPHA, LEADING_DIGIT, ALPHANUM, SIGN -> result.append(' ');
                case DECIMAL -> result.append('.');
            }
        }
        return result.toString();
    }

    public String convert(String text) {
        List<PictureSymbol> patternToMatch = pattern.stream().filter(symbol -> symbol.glyph !=
                PictureSymbol.SIGN.glyph).toList();

        String patternText = text;
        List<PictureSymbol> patternBeforeDot = patternToMatch.stream()
                .takeWhile(symbol -> symbol.glyph != PictureSymbol.DECIMAL.glyph)
                .toList();

        List<PictureSymbol> patternAfterDot = new ArrayList<>();
        if(patternToMatch.contains(PictureSymbol.DECIMAL)) {
             patternAfterDot = patternToMatch.stream().toList().subList(patternBeforeDot.size() + 1, patternToMatch.size());
        }

        int indexOfDot = text.indexOf('.');
        String textBeforeDot = "";
        String textAfterDot = "";
        if(indexOfDot != -1) {
            textBeforeDot = text.substring(0, indexOfDot);
            textAfterDot = text.substring(indexOfDot + 1);
        }
        else
            textBeforeDot = text;
        if (text.length() < patternToMatch.size())
            if (isPatternNumeric())
                patternText = ("0".repeat(patternBeforeDot.size() - textBeforeDot.length())) +
                        (!textAfterDot.isEmpty() && patternAfterDot.isEmpty() ? textBeforeDot : patternText) +
                        (!patternAfterDot.isEmpty() ? "0".repeat(patternAfterDot.size() - textAfterDot.length()) : "");
            else {
                patternText = (" ".repeat(patternToMatch.size() - text.length())) + patternText;
            }
        else if(text.length() > patternToMatch.size())
            if(isPatternNumeric())
                // When there is no pattern after the dot, but the value checked is a float
                if(!textAfterDot.isEmpty() && patternAfterDot.isEmpty())
                        if(textBeforeDot.length() > patternToMatch.size())
                            patternText = textBeforeDot.substring(textBeforeDot.length() - patternToMatch.size());
                        else
                            patternText = textBeforeDot;
                else
                    patternText = text.substring(text.length() - patternToMatch.size());
            else
                patternText = text.substring(0, patternToMatch.size());
        else if(textAfterDot.length() > patternAfterDot.size() && isPatternNumeric()) {
            if(textBeforeDot.length() < patternBeforeDot.size())
                textBeforeDot = ("0".repeat(patternBeforeDot.size() - textBeforeDot.length())) + textBeforeDot;

            patternText = textBeforeDot + "." + textAfterDot.substring(0, patternAfterDot.size());
        }


        if(matches(patternText))
            if(isPatternNumeric()) {
                // Check if the pattern is not just zeroes
                if(patternText.matches("^0*$"))
                    return "0";
                else if(patternText.matches("^0+\\.[0-9]+$"))
                    return "0" + patternText.replaceFirst("^0+", "");
                else
                    return patternText.replaceFirst("^0+", "");
            }
            else
                return patternText;
        else
            throw new IllegalArgumentException("Text does not match PICTURE: " + text + " does not match " + this);
    }

    @Override
    public String toString() {
        return pattern.stream().map(s -> String.valueOf(s.glyph)).reduce("", String::concat);
    }

    @Override
    public Type getType() {
        if(pattern.contains(PictureSymbol.ALPHANUM) || pattern.contains(PictureSymbol.ALPHA))
            return Type.ALPHANUMERIC;
        else
            return Type.NUMERIC;
    }

    public double maxValue() {
        String maxValueString = maxValueStringForRepresentation();
        return Double.parseDouble(maxValueString);
    }
}