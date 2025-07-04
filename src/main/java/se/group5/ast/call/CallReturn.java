package se.group5.ast.call;

import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.call.parameter_passing.ByContent;
import se.group5.ast.call.parameter_passing.ByReference;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.call.representation.AsRepresentation;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;

import java.util.Map;

/**
 * RETURNING <target> [AS …]
 * <p>
 * Handles the copy-out phase for RETURNING items.
 */
public record CallReturn(Identifier target,
                         ParameterPassing passing,
                         AsRepresentation asRepresentation)
        implements se.group5.ast.Node {

    /**
     * Copies or re-binds the RETURN value from callee back to caller.
     */
    public void resolve(Program caller, Program callee) {

        DataDefinition calleeDef = callee.symbolTable.resolve(target.value())
                .orElseThrow(() ->
                        new IllegalStateException("Return source '" + target + "' not found"));
        DataDefinition callerDef = caller.symbolTable.resolve(target.value())
                .orElseThrow(() ->
                        new IllegalStateException("Return target '" + target + "' not found"));

        if (passing instanceof ByReference) {
            caller.symbolTable.table.put(target.value(), calleeDef);
        } else if (passing instanceof ByContent) {
            copyValues(calleeDef, callerDef);
            callerDef.setReadOnly(true);
        } else {
            copyValues(calleeDef, callerDef);
        }
    }

    @Override
    public String toString() {
        return target + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }

    public static void copyValues(DataDefinition from, DataDefinition to) {
        if (from instanceof DataElement fe &&
                to instanceof DataElement te) {
            te.setValue(fe.getValue());
        } else if (from instanceof DataGroup fg &&
                to instanceof DataGroup tg) {
            for (Map.Entry<String, DataDefinition> e : fg.children.entrySet()) {
                copyValues(e.getValue(), tg.children.get(e.getKey()));
            }
        } else {
            throw new IllegalStateException("Mismatching parameter structures");
        }
    }
}
