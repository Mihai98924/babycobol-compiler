package se.group5.ast;

import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;

public class Function implements Node {
    public final SymbolTable symbolTable;
    public final ProcedureList procedures;

    public Function(SymbolTable symbolTable, ProcedureList procedures) {
        this.symbolTable = symbolTable;
        this.procedures = procedures;
    }

    public void execute(Program program) {
        int index = 0;
        while (this.procedures.get(index).isPresent()) {
            Procedure procedure = procedures.get(index).get();
            procedure.execute(program);
            index++;
        }
    }
}
