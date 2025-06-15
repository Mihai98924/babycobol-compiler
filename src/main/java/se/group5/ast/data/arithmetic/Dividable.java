package se.group5.ast.data.arithmetic;

import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

public interface Dividable<T> {

    T divide(DataElement other);
    T divide(DataGroup other);
    T divide(Literal other);
    T divide(Atomic other);
}
