package se.group5.ast;

import se.group5.ast.procedure.ProcedureList;

public record Function(ProcedureList procedures) implements Node {
    public void execute(Program program) {
        procedures.execute(program);
    }
}
