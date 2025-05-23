package se.group5.ast.statement;

import se.group5.ast.Identifier;
import se.group5.ast.procedure.Procedure;

import java.util.List;

public record Move(Object type, List<Identifier> targets) implements Procedure {

    public enum MoveType {
        HIGH_VALUES,
        LOW_VALUES,
        SPACES;
    }

    @Override
    public String toString() {
        return "MOVE(" + type + targets + ")";
    }
}
