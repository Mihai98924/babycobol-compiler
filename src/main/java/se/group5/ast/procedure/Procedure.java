package se.group5.ast.procedure;

import se.group5.ast.Node;
import se.group5.ast.Program;

public interface Procedure extends Node {
    void execute(Program state);
}
