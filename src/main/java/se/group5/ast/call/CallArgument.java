package se.group5.ast.call;

import se.group5.ast.Atomic;
import se.group5.ast.Node;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.call.representation.AsRepresentation;

/**
 * One entry in the USING list.
 */
public record CallArgument(Atomic value,
                           ParameterPassing passing,
                           AsRepresentation asRepresentation) implements Node {
    @Override
    public String toString() {
        return value + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }
}
