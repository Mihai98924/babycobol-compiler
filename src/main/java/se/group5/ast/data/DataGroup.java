package se.group5.ast.data;

import se.group5.ast.Identifier;

import java.util.*;

/**
 * 01 – 49 group entries that contain children but no direct representation.
 */
public final class DataGroup implements DataDefinition {
    private final int level;
    private final Identifier name;
    public Map<String, DataDefinition> children = new HashMap<>();
    public boolean readOnly;

    public DataGroup(int level, Identifier name) {
        this.level = level;
        this.name = name;
    }

    public void register(String name, DataDefinition d) {
        children.put(name, d);
    }

    public Optional<DataDefinition> resolve(String name) {
        return Optional.ofNullable(children.get(name));
    }

    public List<DataDefinition> children() {
        return new ArrayList<>(children.values());
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
        return null;
    }

    @Override
    public void setValue(Object value) {
        if (this.readOnly) throw new IllegalArgumentException("Cannot set value on readonly data element!");
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(Object value, boolean applyPictureToValue) {
        if (this.readOnly) throw new IllegalArgumentException("Cannot set value on readonly data group!");
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "GROUP(" + level + " " + name + ")";
    }

    @Override
    public Type getType() {
        return Type.COMPOSITE;
    }

    @Override
    public boolean doesPictureContainAnySymbols(PictureSymbol... symbols) {
        for (DataDefinition child : children.values()) {
            // Analyze only direct data elements, not nested groups
            if (child instanceof DataElement element) {
                if(element.doesPictureContainAnySymbols(symbols))
                    return true;
            }
        }
        return false;
    }

    @Override
    public DataDefinition copy() {
        DataGroup c = new DataGroup(level, name);
        for (Map.Entry<String, DataDefinition> e : this.children.entrySet()) {
            c.children.put(e.getKey(), e.getValue().copy());
        }
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
