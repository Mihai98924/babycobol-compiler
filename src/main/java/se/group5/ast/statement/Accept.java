package se.group5.ast.statement;

import se.group5.ast.Identifier;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

public record Accept(List<Identifier> targets) implements Procedure {
    @Override
    public String toString() {
        // ACCEPT(VAR1, VAR3)
        return "ACCEPT(" +
                targets.stream()
                        .map(Identifier::toString)          // Identifier::value if you prefer
                        .collect(Collectors.joining(", ")) +
                ")";
    }
}
