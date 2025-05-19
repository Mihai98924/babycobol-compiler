package se.group5.ast.literal;

public record NumericLiteral(String raw, double value) implements Literal {
    public NumericLiteral(String raw) {
        this(raw, Double.parseDouble(raw));
    }

    @Override
    public int length() {
        return raw.length();
    }
}
