package se.group5.ast.statement;

import se.group5.ast.Program;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.procedure.Procedure;

import java.util.HashMap;
import java.util.List;

public record Call(Program program, HashMap<CallArgs, Object> callArgs) implements Procedure {
    public Call(Program program) {
        this(program, null);
    }

    public Call(Program program, HashMap<CallArgs, Object> callArgs) {
        this.program = program;
        this.callArgs = callArgs;
    }

    public enum CallArgs {
        BY_VALUE,
        BY_REFERENCE,
        BY_CONTENT,
        FUNCTION
    }

    @Override
    public String toString() {
        return "CALL(" + program + (callArgs != null ? ", " + callArgs : "") + ")";
    }

    @Override
    public void execute(Program state) {
        
    }
}
