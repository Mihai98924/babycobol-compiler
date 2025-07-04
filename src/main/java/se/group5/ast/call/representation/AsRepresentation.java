package se.group5.ast.call.representation;

import se.group5.ast.Node;

/**
 * PRIMITIVE vs STRUCT: how to *interpret* or *unpack* the raw bytes.
 * Each subtype will later know how to perform that transformation.
 */
public interface AsRepresentation extends Node {

    /**
     * Stub: will convert a raw argument/return value into the desired view.
     */
    default Object transform(Object raw) {
        return raw;
    }
}
