package se.group5.ast.data;

import se.group5.ast.Identifier;

import java.util.*;

/**
 * 01 – 49 group entries that contain children but no direct representation.
 */
public final class DataGroup implements DataDefinition {
    private final int level;
    private final Identifier name;
    public final Map<String, DataDefinition> children = new HashMap<>();


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
}
