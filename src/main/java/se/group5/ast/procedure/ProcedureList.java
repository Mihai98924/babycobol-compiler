package se.group5.ast.procedure;

import org.antlr.v4.runtime.ParserRuleContext;
import se.group5.ast.Node;
import se.group5.ast.Program;
import se.group5.ast.statement.LoopVariable;
import se.group5.parser.CoBabyBoL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProcedureList implements Node {
    private final List<Procedure> procedures = new ArrayList<>();

    public boolean breakIteration = false;

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

    public int size() {
        return procedures.size();
    }

    public void clear() {
        this.procedures.clear();
    }

    @Override
    public String toString() {
        return procedures.toString();
    }

    public void execute(Program program) {
        breakIteration = false;
        for (var procedure : procedures) {
            if(breakIteration) break;
            procedure.execute(program);
        }
    }

    public <T> List<T> getAllLoopProcedures(Class<T> type) {
        List<T> loopProcedures = new ArrayList<>();
        for (Procedure procedure : procedures) {
            if (procedure.getClass() == type) {
                loopProcedures.add((T) procedure);
            }
        }
        return loopProcedures;
    }
}