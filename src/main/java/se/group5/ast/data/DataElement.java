package se.group5.ast.data;

import se.group5.ast.Atomic;
import se.group5.ast.Identifier;
import se.group5.ast.data.arithmetic.ArithmeticBase;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.Literal;

/**
 * Elementary item, optionally OCCURS‑array
 */
public final class DataElement extends ArithmeticBase<DataDefinition> implements DataDefinition {
    private final int level;
    private final Identifier name;
    private final Representation picture;
    private final int occurs; // 0 == not an array

    private Object value;

    public DataElement(int level, Identifier name, Representation picture) {
        this(level, name, picture, 0);
    }

    public DataElement(int level, Identifier name, Representation picture, int occurs) {
        this.level = level;
        this.name = name;
        this.picture = picture;
        this.occurs = occurs;
    }

    public Representation picture() {
        return picture;
    }

    public boolean isArray() {
        return occurs > 0;
    }

    public int occurs() {
        return occurs;
    }

    @Override
    public int level() {
        return level;
    }

    @Override
    public Identifier name() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (isArray() ? "ARRAY" : "ELEM") + "(" + level + ", " + name + ", " + picture + ")";
    }

    @Override
    public Type getType() {
        if(picture != null) {
            return picture.getType();
        } else {
            return Type.UNKNOWN;
        }
    }

    @Override
    public boolean doesPictureContainAnySymbols(PictureSymbol... symbols) {
        if (picture == null) {
            return false;
        }
        for (PictureSymbol symbol : symbols) {
            if (picture.symbols().contains(symbol)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DataDefinition add(DataElement other) {
        validateType(other);
        Type type = this.getType();

        if(type == Type.NUMERIC) {
            double thisValue = (double)this.value;
            double otherValue = (double)other.getValue();
            this.value = thisValue + otherValue;
        } else if(type == Type.ALPHANUMERIC) {
            String thisValue = this.value.toString();
            String otherValue = this.value.toString();
            this.value = thisValue + otherValue;
        } else {
            throw new UnsupportedOperationException("Addition not supported for type: " + type);
        }
        return this;
    }

    @Override
    public DataDefinition add(Atomic other) {
        if (other.isElement()) {
            return add(other.getElement());
        } else if (other.isLiteral()) {
            return add(other.getLiteral());
        } else if (other.isComposite()) {
            return add(other.getGroup());
        } else {
            throw new UnsupportedOperationException("Cannot add Atomic of type: " + other.getType());
        }
    }

    @Override
    public DataDefinition add(Literal other) {
        validateType(other);
        Type type = this.getType();

        if(type == Type.NUMERIC) {
            double thisValue = (double)this.value;
            double otherValue = Double.parseDouble(other.raw());
            this.value = thisValue + otherValue;
        } else if(type == Type.ALPHANUMERIC) {
            String thisValue = this.value.toString();
            String otherValue = ((AlphanumericLiteral) other).getText();
            this.value = thisValue + otherValue;
        } else {
            throw new UnsupportedOperationException("Addition not supported for type: " + type);
        }

        return this;
    }

    private void validateType(Typeable other) {
        Type type = this.getType();
        Type otherType = other.getType();

        if(type != otherType) {
            throw new IllegalArgumentException("Cannot add fields of different types: " + type + " and " + otherType);
        }
    }
}
