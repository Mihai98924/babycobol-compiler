package se.group5.ast.call;


import lombok.Getter;
import se.group5.ast.Program;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The CALL statement itself
 */
public final class Call implements Procedure {

    @Getter
    private final Program callee;
    private final List<CallArgument> arguments;
    private final List<CallReturn> returns;
    private final String functionName;

    public Call(Program callee,
                String functionName,
                List<CallArgument> arguments,
                List<CallReturn> returns) {
        this.callee = callee;
        this.arguments = arguments;
        this.returns = returns;
        this.functionName = functionName;
    }

    @Override
    public void execute(Program caller) {

        callee.setDisplayStrategy(caller.getDisplayStrategy());
        callee.setInputStrategy(caller.getInputStrategy());

        for (CallArgument arg : arguments) {
            arg.register(callee);
        }

        if (functionName == null) {
            callee.run();
        } else {
            callee.symbolTable.resolveFunc(functionName)
                    .orElseThrow(() -> new IllegalStateException(
                            "Function '" + functionName + "' not found"))
                    .execute(callee);
        }

        for (CallReturn ret : returns) {
            ret.resolve(caller, callee);
        }
    }

    @Override
    public String toString() {
        if (functionName == null) {
            return "CALL " + callee.toString() +
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
            return "CALL FUNCTION(" + functionName + ") OF " + callee.toString() +
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
