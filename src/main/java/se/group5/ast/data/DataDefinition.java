package se.group5.ast.data;

import se.group5.ast.Identifier;
import se.group5.ast.Node;

/**
 * Common super‑type for anything that can appear in the DATA DIVISION
 */
public interface DataDefinition extends Node, Typeable, Pictureable, Cloneable {

    int level();

    Identifier name();

    Object getValue();

    void setValue(Object value);
    void setValue(Object value, boolean applyPictureToValue);

    DataDefinition copy();
}
