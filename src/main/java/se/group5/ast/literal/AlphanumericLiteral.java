package se.group5.ast.literal;

public class AlphanumericLiteral implements Literal {
    private final String value;

    public AlphanumericLiteral(String value) {
        this.value = value;
    }
}
