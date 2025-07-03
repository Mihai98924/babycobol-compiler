package se.group5.ast.statement;

import se.group5.ast.Program;
import se.group5.ast.data.LoopVariable;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;

import java.util.List;

public record Loop(ProcedureList procedures, List<LoopVariable> variables) implements Procedure {

    @Override
    public void execute(Program state) {
        while(variables.stream().allMatch(LoopVariable::isInRange)) {
            procedures.execute(state);
            for (LoopVariable loopVariable : variables) {
                loopVariable.increment();
            }
        }
    }
}
