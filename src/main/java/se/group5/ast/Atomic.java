package se.group5.ast;

import se.group5.ast.data.DataElement;
import se.group5.ast.literal.Literal;

public class Atomic implements Node {
    Literal literal;
    DataElement element;

    public Atomic(Literal literal) {
        this.literal = literal;
    }

    public Atomic(DataElement element) {
        this.element = element;
    }

    public int length() {
        if (literal != null) {
            return literal.length();
        }
        return element.picture().length();
    }

    @Override
    public String toString() {
        if (literal != null) {
            return "ATOMIC(" + literal + ")";
        }
        return "ATOMIC(" + element + ")";
    }
}
