package se.group5.ast.statement;

import se.group5.ast.Program;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;

import java.util.List;

public class Loop implements Procedure {

    public ProcedureList procedures;
    public boolean breakIteration = false;

    public Loop(ProcedureList procedures) {
        this.procedures = procedures;
    }

    @Override
    public void execute(Program state) {
        List<LoopVariable> loopVariables = procedures.getAllLoopProcedures(LoopVariable.class);
        loopVariables.forEach(LoopVariable::reset);
        breakIteration = false;
        while(true) {
            if(breakIteration) break;
            procedures.execute(state);
        }
    }
}
