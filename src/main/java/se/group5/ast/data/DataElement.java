package se.group5.ast.data;

import lombok.Getter;
import lombok.Setter;
import se.group5.ast.Identifier;

/**
 * Elementary item, optionally OCCURS‑array
 */
public final class DataElement implements DataDefinition {
    private final int level;
    private final Identifier name;
    private final Representation picture;
    private final int occurs; // 0 == not an array
    public boolean readOnly;

    // If value was truncated during conversion (to fit the picture),
    // this is set to true.
    private boolean truncated = false;
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
        if (this.readOnly) throw new IllegalArgumentException("Cannot set value on readonly data element!");
        truncated = false;
        this.value = value;
    }

    @Override
    public void setValue(Object value, boolean applyPictureToValue) {
        if (this.readOnly) throw new IllegalArgumentException("Cannot set value on readonly data element!");
        setValue(value);
        if(applyPictureToValue)
            applyPictureToValue();
    }

    public String convert() {

        if(getType() == Type.NUMERIC) {
            double unsignedDouble = (double) value;
            boolean valueIsNegative = false;
            if (unsignedDouble < 0) {
                valueIsNegative = true;
                unsignedDouble = Math.abs((double) value);
            }

            String usignedDoubleString = Double.toString(unsignedDouble);
            // remove .0
            if (usignedDoubleString.endsWith(".0") && !picture.containsSymbol(PictureSymbol.DECIMAL)) {
                usignedDoubleString = usignedDoubleString.substring(0, usignedDoubleString.length() - 2);
            }

            String convertedRepresentation = picture.convert(usignedDoubleString);
            if(truncated) {
                int numberOfZeros = picture.length() - convertedRepresentation.length();
                if (numberOfZeros > 0) {
                    convertedRepresentation = "0".repeat(numberOfZeros) + convertedRepresentation;
                }
            }

            if (picture.containsSymbol(PictureSymbol.SIGN)) {
                if (valueIsNegative) {
                    convertedRepresentation = "-" + convertedRepresentation;
                } else {
                    convertedRepresentation = " " + convertedRepresentation;
                }
            }

            return convertedRepresentation;
        } else if (getType() == Type.ALPHANUMERIC) {
                String convertedRepresentation = picture.convert(value.toString());
                return convertedRepresentation.stripLeading();
            } else {
                throw new IllegalArgumentException("Unsupported type for picture conversion: " + getType());
            }
    }

    public void applyPictureToValue() {
        if (picture != null && value != null) {
            if(getType() == Type.NUMERIC) {
                // When double is positive add space
                // When double is negative replace the first '-' with a space
                double unsignedDouble;
                boolean valueIsNegative;
                try {
                    unsignedDouble = (double) value;
                    valueIsNegative = false;
                    if (unsignedDouble < 0) {
                        valueIsNegative = true;
                        unsignedDouble = Math.abs((double)value);
                    } else if(unsignedDouble == 0 && value.toString().contains("-")) {
                        valueIsNegative = true;
                        unsignedDouble = Math.abs((double)value);
                    }
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Value must be a number for numeric picture: " + picture);
                }

                String unsignedDoubleString = Double.toString(unsignedDouble);
                // remove .0
                if (unsignedDoubleString.endsWith(".0") && !picture.containsSymbol(PictureSymbol.DECIMAL)) {
                    unsignedDoubleString = unsignedDoubleString.substring(0, unsignedDoubleString.length() - 2);
                }

                String convertedString = picture.convert(unsignedDoubleString);
                if(unsignedDoubleString.length() > picture.symbols().stream()
                        .filter(s -> s.glyph != PictureSymbol.SIGN.glyph).toList().size())
                    truncated = true;

                this.value = Double.parseDouble(convertedString);
                if(valueIsNegative) {
                    this.value = -((double)this.value);
                }
            } else if(getType() == Type.ALPHANUMERIC) {
                this.value = picture.convert(value.toString()).stripLeading();
            } else {
                throw new IllegalArgumentException("Unsupported type for picture conversion: " + getType());
            }
        }
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
    public DataDefinition copy() {
        DataElement c = new DataElement(level, name, picture, occurs);
        c.truncated = this.truncated;
        c.value     = this.value;
        return c;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public boolean getReadOnly() {
        return this.readOnly;
    }
}
