package se.group5.ast.literal;

import se.group5.ast.data.Type;

public record NumericLiteral(String raw, double value) implements Literal{
    public NumericLiteral(String raw) {
        this(raw, Double.parseDouble(raw));
    }

    @Override
    public int length() {
        return raw.length();
    }

    @Override
    public Type getType() {
        return Type.NUMERIC;
    }
}
