package se.group5.ast.data.arithmetic;

import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

public interface Additable<T> {
    T add(DataElement other);
    T add(DataGroup other);
    T add(Literal other);
    T add(Atomic other);
}
