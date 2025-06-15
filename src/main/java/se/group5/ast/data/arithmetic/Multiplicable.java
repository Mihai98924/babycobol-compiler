package se.group5.ast.data.arithmetic;

import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

public interface Multiplicable<T> {
    T multiply(DataElement other);
    T multiply(DataGroup other);
    T multiply(Literal other);
    T multiply(Atomic other);
}
