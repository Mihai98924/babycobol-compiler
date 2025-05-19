package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors; /**
 * SUBTRACT atomic+ FROM atomic+ [GIVING identifier*]
 */
public record Subtract(List<Atomic> sources,
                       List<Atomic> from,
                       List<DataElement> giving) implements Procedure {

    @Override
    @NonNull
    public String toString() {
        return "SUBTRACT(" +
                "SOURCES(" + sources.stream()
                .map(Atomic::toString)
                .collect(Collectors.joining(", ")) + ")" +
                ", FROM(" + from.stream()
                .map(Atomic::toString)
                .collect(Collectors.joining(", ")) + ")" +
                (giving != null && !giving.isEmpty()
                        ? ", GIVING(" + giving.stream()
                        .map(DataElement::toString)
                        .collect(Collectors.joining(", ")) + ")"
                        : "") +
                ")";
    }
}
