package se.group5.ast;

public class Identifier implements Node {
    final String value;

    public Identifier(String value) {
        this.value = value;
    }
}
