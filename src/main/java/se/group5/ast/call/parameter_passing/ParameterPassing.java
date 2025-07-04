package se.group5.ast.call.parameter_passing;

import se.group5.ast.Node;

public interface ParameterPassing extends Node {
    default Object pass(Object value) {
        return value;
    }
}
