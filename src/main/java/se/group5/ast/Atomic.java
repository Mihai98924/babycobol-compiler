package se.group5.ast;

import lombok.Getter;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

@Getter
public class Atomic implements Node {
    Literal literal;
    DataElement element;
    DataGroup group;

    public Atomic(Literal literal) {
        this.literal = literal;
    }

    public Atomic(DataElement element) {
        this.element = element;
    }

    public Atomic(DataGroup group) {
        this.group = group;
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

    public boolean isLiteral() {
        return literal != null;
    }

    public boolean isComposite() {
        return group != null;
    }

    public boolean isElement() {
        return element != null;
    }
}
