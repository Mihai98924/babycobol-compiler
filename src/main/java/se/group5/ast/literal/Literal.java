package se.group5.ast.literal;

import se.group5.ast.Node;
import se.group5.ast.data.Typeable;

public interface Literal extends Node, Typeable {
    int length();
    String raw();
}
