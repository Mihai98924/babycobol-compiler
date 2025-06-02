package se.group5.ast.statement;

import se.group5.ast.Program;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.procedure.Procedure;

import java.util.List;

public record Call(AlphanumericLiteral fileName, List<Object> callArgs) implements Procedure {
    public Call(AlphanumericLiteral fileName) {
        this(fileName, null);
    }

    public Call(AlphanumericLiteral fileName, List<Object> callArgs) {
        this.fileName = fileName;
        this.callArgs = callArgs;
    }

    @Override
    public String toString() {
        return "CALL(" + fileName + (callArgs != null ? ", " + callArgs : "") + ")";
    }

    @Override
    public void execute(Program state) {
        
    }
}
