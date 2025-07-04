package se.group5.ast.call;

import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.call.parameter_passing.ByContent;
import se.group5.ast.call.parameter_passing.ByReference;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.data.DataDefinition;

import java.util.List;

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
     * @param callee program that will be executed
     */
    public void register(Program callee) {
        DataDefinition callerDef = value.dataDefinition();
        if (passing instanceof ByReference) {
            callee.symbolTable.register(List.of(callerDef.name()), callerDef);
        } else if (passing instanceof ByContent) {
            DataDefinition clone = callerDef.copy();
            clone.setReadOnly(true);
            callee.symbolTable.register(List.of(clone.name()), clone);
        } else {
            DataDefinition clone = callerDef.copy();
            callee.symbolTable.register(List.of(clone.name()), clone);
        }
    }

    @Override
    public String toString() {
        return value + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }
}
