package se.group5.ast;
import lombok.NonNull;
import se.group5.ast.identity.IdentityTable;
import se.group5.ast.procedure.ProcedureList;

public record Program(IdentityTable identityTable, SymbolTable symbolTable,
                      ProcedureList procedures) implements Node {

    @Override
    @NonNull
    public String toString() {
        return "Program {\n" +
                "  IdentityTable: " + identityTable.toString() + "\n" +
                "  SymbolTable:   " + symbolTable.toString() + "\n" +
                "  Procedures:   " + procedures.toString() + "\n" +
                "}";
    }
}
