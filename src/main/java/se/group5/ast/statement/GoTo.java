package se.group5.ast.statement;

import se.group5.ast.Function;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.ast.data.DataElement;
import se.group5.ast.procedure.Procedure;

import java.util.Optional;

public record GoTo(String identifier, SymbolTable symbolTable) implements Procedure {
    @Override
    public void execute(Program program) {
        Optional<Function> function = symbolTable.resolveFunc(identifier);
        if (function.isEmpty()) {
            var dataDefinition = symbolTable.resolve(identifier);
            if (dataDefinition.isEmpty()) throw new IllegalStateException("Function '" + identifier + "' does not exist");
            var dataElement = (DataElement) dataDefinition.get();
            function = symbolTable.resolveFunc((String) dataElement.getValue());
            if (function.isEmpty()) throw new IllegalStateException("Function '" + dataElement.getValue() + "' does not exist");
        }

        function.get().execute(program);
    }
}
