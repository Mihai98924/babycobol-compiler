package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Atomic;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

public record Divide(Atomic source, List<Atomic> targets) implements Procedure {

    @Override
    @NonNull
    public String toString() {
        return "DIVIDE(" +
                "SOURCE(" + source.toString() + ")" +
                ", TARGETS(" + targets.stream()
                .map(Atomic::toString)
                .collect(Collectors.joining(", ")) + ")" +
                ")";
    }
}
