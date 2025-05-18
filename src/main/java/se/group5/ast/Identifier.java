package se.group5.ast;

public record Identifier(String value) implements Node {
    @Override
    public String toString() {
        return value;
    }
}
