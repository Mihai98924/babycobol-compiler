package se.group5.ast.statement;

import se.group5.ast.Function;
import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.procedure.Procedure;

public record GoTo(Function function) implements Procedure {
    @Override
    public void execute(Program program) {
        function.execute(program);
    }
}
