package se.group5.ast.call;


import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The CALL statement itself
 */
public final class Call implements Procedure {

    private final Program externalProgram;
    private final List<CallArgument> arguments;
    private final List<CallReturn> returns;
    private final SymbolTable symbolTable;
    private final String functionName;

    public Call(Program externalProgram,
                String functionName,
                List<CallArgument> arguments,
                List<CallReturn> returns,
                SymbolTable symbols) {
        this.externalProgram = externalProgram;
        this.arguments = arguments;
        this.returns = returns;
        this.symbolTable = symbols;
        this.functionName = functionName;
    }

    @Override
    public void execute(Program program) {

    }

    @Override
    public String toString() {
        if (functionName == null) {
            return "CALL PROGRAM(" + externalProgram.identityTable.toString() +
                    (arguments.isEmpty() ? "" :
                            ", ARGS(" + arguments.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", ")) + ")") +
                    (returns.isEmpty() ? "" :
                            ", RETURNS(" + returns.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", ")) + ")") +
                    ")";
        } else {
            return "CALL FUNCTION(" + functionName + ") OF PROGRAM(" + externalProgram.identityTable.toString() +
                    (arguments.isEmpty() ? "" :
                            ", ARGS(" + arguments.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", ")) + ")") +
                    (returns.isEmpty() ? "" :
                            ", RETURNS(" + returns.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", ")) + ")") +
                    ")";
        }

    }
}

