package se.group5.ast;

import lombok.Getter;
import org.junit.runner.manipulation.Alphanumeric;
import se.group5.ast.data.*;
import se.group5.ast.data.arithmetic.ArithmeticBase;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.Literal;
import se.group5.ast.literal.NumericLiteral;

@Getter
public class Atomic extends ArithmeticBase implements Node, Typeable, Pictureable {
    protected Literal literal;
    protected DataElement element;
    protected DataGroup group;

    public Atomic() {}

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

    @Override
    public Atomic add(Atomic other) {
        if(isElement()) {
            element.add(other);
        } else if (isLiteral()) {
            if(other.isLiteral()) {
                if(literal.getType() != other.getLiteral().getType()) {
                    throw new UnsupportedOperationException("Cannot add literals of different types: "
                            + literal.getType() + " and " + other.getLiteral().getType());
                }

                if(literal.getType() == Type.ALPHANUMERIC) {
                    AlphanumericLiteral al = (AlphanumericLiteral) literal;
                    AlphanumericLiteral otherAl = (AlphanumericLiteral) other.getLiteral();
                    this.literal = new AlphanumericLiteral("\"" + al.getText() + otherAl.getText() + "\"");
                } else if (literal.getType() == Type.NUMERIC) {
                    double thisValue = Double.parseDouble(literal.raw());
                    double otherValue = Double.parseDouble(other.getLiteral().raw());
                    this.literal = new NumericLiteral(String.valueOf(thisValue + otherValue));
                } else {
                    throw new UnsupportedOperationException("Addition not supported for type: " + literal.getType());
                }

            } else if (other.isElement()) {
                element.add(literal);
            } else if (other.isComposite()) {
                group.add(literal);
            }
        } else if (isComposite()) {
            group.add(other);
        }
        return this;
    }
}
