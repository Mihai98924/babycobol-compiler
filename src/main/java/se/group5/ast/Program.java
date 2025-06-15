package se.group5.ast;

import lombok.Getter;
import lombok.NonNull;
import se.group5.ast.identity.IdentityTable;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;
import se.group5.ast.signal.OffSignal;
import se.group5.ast.signal.ProcedureSignal;

public class Program implements Node {

    public final IdentityTable identityTable;
    public final SymbolTable symbolTable;
    public final ProcedureList procedures;

    @Getter
    private ProgramInputStrategy inputStrategy;

    @Getter
    private ProgramDisplayStrategy displayStrategy;

    public Program(IdentityTable identityTable, SymbolTable symbolTable,
                   ProcedureList procedures) {
        this.identityTable = identityTable;
        this.symbolTable = symbolTable;
        this.procedures = procedures;
    }

    @Override
    @NonNull
    public String toString() {
        return "Program {\n" +
                "  IdentityTable: " + identityTable.toString() + "\n" +
                "  SymbolTable:   " + symbolTable.toString() + "\n" +
                "  Procedures:   " + procedures.toString() + "\n" +
                "}";
    }

    public void run() {
        run(null, null);
    }

    public void run(ProgramInputStrategy inputStrategy) {
        run(inputStrategy, null);
    }

    public void run(ProgramInputStrategy inputStrategy, ProgramDisplayStrategy displayStrategy) {
        this.inputStrategy = inputStrategy;
        this.displayStrategy = displayStrategy;

        ProcedureSignal errorHandler = null;

        int index = 0;
        while (procedures.get(index).isPresent()) {
            Procedure procedure = procedures.get(index).get();
            try {
                if (procedure instanceof ProcedureSignal) {
                    errorHandler = (ProcedureSignal) procedure;
                } else if (procedure instanceof OffSignal) {
                    errorHandler = null;
                } else {
                    procedure.execute(this);
                }
            } catch (RuntimeException e) {
                if (errorHandler != null) {
                    errorHandler.execute(this);
                } else {
                    throw e;
                }
            }

            index++;
        }
    }
}
