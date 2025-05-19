package se.group5.ast.procedure;

import se.group5.ast.Node;
import se.group5.ast.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcedureList implements Node {
    private final List<Procedure> procedures = new ArrayList<>();

    /**
     * Add a Procedure to the list
     */
    public void add(Procedure procedure) {
        procedures.add(procedure);
    }

    /**
     * Get a Procedure by index, safely wrapped in Optional
     */
    public Optional<Procedure> get(int index) {
        if (index >= 0 && index < procedures.size()) {
            return Optional.of(procedures.get(index));
        } else {
            return Optional.empty();
        }
    }

    public boolean isEmpty() {
        return procedures.isEmpty();
    }

    @Override
    public String toString() {
        return procedures.toString();
    }
}