package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MULTIPLY atomic BY atomic+ [GIVING identifier]
 */
public record Multiply(Atomic source,
                       List<Atomic> by,
                       DataElement giving) implements Procedure {

    @Override
    @NonNull
    public String toString() {
        return "MULTIPLY(" +
                "SOURCE(" + source + ")" +
                ", BY(" + by.stream()
                .map(Atomic::toString)
                .collect(Collectors.joining(", ")) + ")" +
                (giving != null ? ", GIVING(" + giving + ")" : "") +
                ")";
    }
}
