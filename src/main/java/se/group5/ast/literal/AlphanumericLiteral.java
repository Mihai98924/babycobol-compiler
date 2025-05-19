package se.group5.ast.literal;

public record AlphanumericLiteral(String value) implements Literal {
    @Override
    public int length() {
        return value.length();
    }

    @Override
    public String raw() {
        return value;
    }
}
