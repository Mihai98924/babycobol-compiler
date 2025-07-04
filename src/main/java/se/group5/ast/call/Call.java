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
    private final String functionName;

    public Call(Program externalProgram,
                String functionName,
                List<CallArgument> arguments,
                List<CallReturn> returns) {
        this.externalProgram = externalProgram;
        this.arguments = arguments;
        this.returns = returns;
        this.functionName = functionName;
    }

    @Override
    public void execute(Program caller) {

        /* 1.  Bind USING arguments (copy-in phase) */
        CallUsing using = new CallUsing(arguments);
        List<CallUsing.Binding> byValuePairs = using.execute(caller, externalProgram);

        /* 2.  Execute the requested procedure */
        if (functionName == null) {
            externalProgram.run(caller.getInputStrategy(), caller.getDisplayStrategy());
        } else {
            externalProgram.symbolTable.resolveFunc(functionName)
                    .orElseThrow(() -> new IllegalStateException(
                            "Function '" + functionName + "' not found"))
                    .execute(externalProgram);
        }

        /* 3.  Copy-out for BY VALUE */
        for (CallUsing.Binding b : byValuePairs) {
            CallUsing.copyValues(b.calleeDef(), b.callerDef());
        }

        /* 4.  RETURNING … */
        for (CallReturn ret : returns) {
            ret.execute(caller, externalProgram);
        }
    }

    @Override
    public String toString() {
        if (functionName == null) {
            return "CALL " + externalProgram.toString() +
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
            return "CALL FUNCTION(" + functionName + ") OF " + externalProgram.toString() +
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
