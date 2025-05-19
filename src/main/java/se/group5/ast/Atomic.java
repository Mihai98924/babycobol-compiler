package se.group5.ast;

import se.group5.ast.literal.Literal;

public class Atomic implements Node {
    Literal literal;
    Identifier identifier;

    public Atomic(Literal literal) {
        this.literal = literal;
    }

    public Atomic(Identifier identifier) {
        this.identifier = identifier;
    }
}
