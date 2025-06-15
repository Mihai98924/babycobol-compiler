package se.group5.ast.signal;

import se.group5.ast.Atomic;
import se.group5.ast.Function;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;

import java.util.Optional;

public record ProcedureSignal(Atomic procedureName, SymbolTable symbolTable) implements Signal {
    @Override
    public void execute(Program program) {
        Optional<Function> function;
        if (procedureName.isLiteral()) {
            String identifier = procedureName.getLiteral().raw();
            function = symbolTable.resolveFunc(identifier);
            if (function.isEmpty()) throw new IllegalStateException("Function '" + identifier + "' does not exist");
            function.get().execute(program);
        }

        if (procedureName.isElement()) {
            var dataElement = procedureName.getElement();
            function = symbolTable.resolveFunc((String) dataElement.getValue());
            if (function.isEmpty()) throw new IllegalStateException("Function '" + dataElement.getValue() + "' does not exist");
            function.get().execute(program);
        }
    }
}
