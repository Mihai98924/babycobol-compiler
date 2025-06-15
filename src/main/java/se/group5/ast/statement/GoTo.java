package se.group5.ast.statement;

import se.group5.ast.Atomic;
import se.group5.ast.Function;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.ast.procedure.Procedure;

import java.util.Optional;

public record GoTo(Atomic atomic, SymbolTable symbolTable) implements Procedure {
    @Override
    public void execute(Program program) {
        Optional<Function> function;
        if (atomic.isLiteral()) {
            String identifier = atomic.getLiteral().raw();
            function = symbolTable.resolveFunc(identifier);
            if (function.isEmpty()) throw new IllegalStateException("Function '" + identifier + "' does not exist");
            function.get().execute(program);
        }

        if (atomic.isElement()) {
            var dataElement = atomic.getElement();
            function = symbolTable.resolveFunc((String) dataElement.getValue());
            if (function.isEmpty()) throw new IllegalStateException("Function '" + dataElement.getValue() + "' does not exist");
            function.get().execute(program);
        }
    }
}
