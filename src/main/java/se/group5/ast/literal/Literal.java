package se.group5.ast.literal;

import se.group5.ast.Node;

public interface Literal extends Node {
    int length();
    String raw();
}
