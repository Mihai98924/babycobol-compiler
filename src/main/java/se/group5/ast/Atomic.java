package se.group5.ast;

import lombok.Getter;
import se.group5.ast.data.*;
import se.group5.ast.literal.Literal;

@Getter
public class Atomic implements Node, Typeable, Pictureable, Cloneable {
    protected Literal literal;
    protected DataElement element;
    protected DataGroup group;

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

    public DataDefinition dataDefinition() {
        if (this.isElement()) return this.getElement();
        if (this.isComposite()) return this.getGroup();
        throw new IllegalArgumentException("This atomic is a literal it has no data definition ");
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

    @Override
    public Type getType() {
        if(isElement()) {
            return element.getType();
        } else if (isLiteral()) {
            return literal.getType();
        } else if(isComposite()) {
            return group.getType();
        } else {
            return Type.UNKNOWN;
        }
    }

    @Override
    public boolean doesPictureContainAnySymbols(PictureSymbol... symbols) {
        if(isElement()) {
            return element.doesPictureContainAnySymbols(symbols);
        } else if (isComposite()) {
            return group.doesPictureContainAnySymbols(symbols);
        } else {
            return false;
        }
    }

    public Atomic copy() {
        if (isLiteral()) {
            return new Atomic(literal);                   // Literals are immutable
        }
        if (isElement()) {
            return new Atomic((DataElement) element.copy());
        }
        if (isComposite()) {
            return new Atomic((DataGroup)  group.copy());
        }
        throw new IllegalStateException("Empty Atomic cannot be copied");
    }
}
