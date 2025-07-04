package se.group5.ast.call;

import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.call.parameter_passing.ByReference;
import se.group5.ast.call.parameter_passing.ByValue;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.data.DataDefinition;

/**
 * One entry in the USING list.
 * <p>
 * Executes the *binding* step: it installs the argument inside the
 * callee’s symbol-table according to the chosen parameter-passing mode
 * and – for BY VALUE – remembers the pair for the later copy-out.
 */
public record CallArgument(Atomic value,
                           ParameterPassing passing,
                           se.group5.ast.call.representation.AsRepresentation asRepresentation)
        implements se.group5.ast.Node {

    /**
     * Creates the run-time binding for this argument.
     *
     * @param caller        currently running program
     * @param callee        program that will be executed
     * @param valueBindings list that collects <caller, callee> pairs
     *                      for BY VALUE copy-out (filled by this method)
     */
    public void execute(Program caller,
                        Program callee,
                        java.util.List<CallUsing.Binding> valueBindings) {

        DataDefinition callerDef = CallUsing.defOfAtomic(value);
        ParameterPassing mode = passing;

        if (mode instanceof ByReference) {

            // share the *same* object
            CallUsing.register(callee.symbolTable, callerDef);

        } else {
            DataDefinition clone = callerDef.copy();
            CallUsing.register(callee.symbolTable, clone);

            if (mode instanceof ByValue) {
                // remember for copy-out after the call
                valueBindings.add(new CallUsing.Binding(callerDef, clone));
            }
            /* BY CONTENT: no copy-out, read-only semantics are
               effectively achieved by the clone */
        }
    }

    @Override
    public String toString() {
        return value + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }
}
