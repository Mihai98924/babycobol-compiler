package se.group5.ast.data_division;

import se.group5.ast.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 01 – 49 group entries that contain children but no direct representation.
 */
public final class DataGroup implements DataDefinition {
    private final int level;
    private final Identifier name;
    private final List<DataDefinition> children = new ArrayList<>();

    public DataGroup(int level, Identifier name) {
        this.level = level;
        this.name = name;
    }

    public void addChild(DataDefinition d) {
        children.add(d);
    }

    public List<DataDefinition> children() {
        return Collections.unmodifiableList(children);
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
    public String toString() {
        return "GROUP(" + level + " " + name + ")";
    }
}
