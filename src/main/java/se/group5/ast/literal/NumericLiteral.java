package se.group5.ast.literal;

public record NumericLiteral(double value) implements Literal {
    public NumericLiteral(String raw) {
        this(Double.parseDouble(raw));
    }
}
