package se.group5.ast.call;

import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.call.parameter_passing.ByReference;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.data.DataDefinition;

/**
 * RETURNING <target> [AS …]
 *
 * Handles the copy-out phase for RETURNING items.
 */
public record CallReturn(Identifier target,
                         ParameterPassing passing,
                         se.group5.ast.call.representation.AsRepresentation asRepresentation)
        implements se.group5.ast.Node {

    /**
     * Copies or re-binds the RETURN value from callee back to caller.
     */
    public void execute(Program caller, Program callee) {

        DataDefinition calleeDef = callee.symbolTable.resolve(target.value())
                .orElseThrow(() ->
                        new IllegalStateException("Return source '" + target + "' not found"));
        DataDefinition callerDef = caller.symbolTable.resolve(target.value())
                .orElseThrow(() ->
                        new IllegalStateException("Return target '" + target + "' not found"));

        if (passing instanceof ByReference) {
            // Share the reference from now on
            caller.symbolTable.table.put(target.value(), calleeDef);
        } else {
            // BY VALUE or BY CONTENT  →  deep value copy
            CallUsing.copyValues(calleeDef, callerDef);
        }
    }

    @Override
    public String toString() {
        return target + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }
}
