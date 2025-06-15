package se.group5.ast.data;

import se.group5.ast.Identifier;

/**
 * Elementary item, optionally OCCURS‑array
 */
public final class DataElement implements DataDefinition {
    private final int level;
    private final Identifier name;
    private final Representation picture;
    private final int occurs; // 0 == not an array

    private Object value;

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
    public DataDefinition clone() {
        try {
            return (DataElement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
