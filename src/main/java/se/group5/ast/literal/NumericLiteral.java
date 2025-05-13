package se.group5.ast.literal;

public class NumericLiteral implements Literal {
    private final double value;

    public NumericLiteral(String value) {
        this.value = Double.parseDouble(value);
    }
}
