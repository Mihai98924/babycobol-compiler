package se.group5.ast.statement;

import se.group5.ast.Program;
import se.group5.ast.data.BooleanExpression;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;

public class While implements Procedure {
    private BooleanExpression ex;

    // To break loops
    public Loop loop;
    public ProcedureList procedureList;

    public While(BooleanExpression ex) {
        this.ex = ex;
    }

    @Override
    public void execute(Program state) {
        if(!ex.result()) {
            loop.breakIteration = true;
            procedureList.breakIteration = true;
        }
    }
}
