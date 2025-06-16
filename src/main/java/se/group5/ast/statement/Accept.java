package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

public record Accept(List<Identifier> targets) implements Procedure {
    @Override
    @NonNull
    public String toString() {
        return "ACCEPT(" +
                targets.stream()
                        .map(Identifier::toString)          // Identifier::value if you prefer
                        .collect(Collectors.joining(", ")) +
                ")";
    }

    @Override
    public void execute(Program state) {
        for (Identifier target : targets)
        {
            String input = state.getInputStrategy().getInput();

            try {
                double value = Double.parseDouble(input);
                state.symbolTable.table.get(target.value()).setValue(value, true);
            } catch (NumberFormatException e) {
                state.symbolTable.table.get(target.value()).setValue(input, true);
            }
        }
    }
}
