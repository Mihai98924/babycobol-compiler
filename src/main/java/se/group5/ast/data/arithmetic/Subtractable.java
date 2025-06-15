package se.group5.ast.data.arithmetic;

import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

public interface Subtractable<T> {
    T subtract(DataElement other);
    T subtract(DataGroup other);
    T subtract(Literal other);
    T subtract(Atomic other);
}
