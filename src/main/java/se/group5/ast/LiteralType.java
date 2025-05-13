package se.group5.ast;

public enum LiteralType {
    NUMERIC('9'),
    ALPHABETIC('A'),
    ALPHANUMERIC('X'),
    SIGNED('S'),
    ASSUMED_DECIMAL('P');

    public final Character symbol;
    LiteralType(Character symbol) {
        this.symbol = symbol;
    }
}
