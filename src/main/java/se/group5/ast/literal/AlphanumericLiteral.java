package se.group5.ast.literal;

import se.group5.ast.data.Type;

public record AlphanumericLiteral(String value) implements Literal {
    @Override
    public int length() {
        return value.length();
    }

    @Override
    public String raw() {
        return value;
    }

    public String getText() {
        return value.substring(1, value.length() - 1);
    }

    @Override
    public Type getType() {
        return Type.ALPHANUMERIC;
    }
}
