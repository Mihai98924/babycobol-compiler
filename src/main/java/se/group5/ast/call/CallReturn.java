package se.group5.ast.call;

import se.group5.ast.Atomic;
import se.group5.ast.Identifier;
import se.group5.ast.Node;
import se.group5.ast.call.parameter_passing.ParameterPassing;
import se.group5.ast.call.representation.AsRepresentation;

/**
 * RETURNING <target> [AS …]
 */
public record CallReturn(Identifier target,
                         ParameterPassing passing,
                         AsRepresentation asRepresentation) implements Node {
    @Override
    public String toString() {
        return target + " " + passing +
                (asRepresentation == null ? "" : " " + asRepresentation);
    }
}
