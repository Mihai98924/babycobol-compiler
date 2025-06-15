package se.group5.ast.data.arithmetic;

import se.group5.ast.Atomic;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.literal.Literal;

public abstract class ArithmeticBase<T> implements Arithmetic<T> {

    @Override
    public T add(DataElement other) {
        throw new UnsupportedOperationException("Addition not supported for this type.");
    }

    @Override
    public T add(DataGroup other) {
        throw new UnsupportedOperationException("Addition not supported for this type.");
    }

    @Override
    public T add(Literal other) {
        throw new UnsupportedOperationException("Addition not supported for this type.");
    }

    @Override
    public T add(Atomic other) {
        throw new UnsupportedOperationException("Addition not supported for this type.");
    }

    @Override
    public T divide(DataElement other) {
        throw new UnsupportedOperationException("Division not supported for this type.");
    }

    @Override
    public T divide(DataGroup other) {
        throw new UnsupportedOperationException("Division not supported for this type.");
    }

    @Override
    public T divide(Literal other) {
        throw new UnsupportedOperationException("Division not supported for this type.");
    }

    @Override
    public T divide(Atomic other) {
        throw new UnsupportedOperationException("Division not supported for this type.");
    }

    @Override
    public T multiply(DataElement other) {
        throw new UnsupportedOperationException("Multiplication not supported for this type.");
    }

    @Override
    public T multiply(DataGroup other) {
        throw new UnsupportedOperationException("Multiplication not supported for this type.");
    }

    @Override
    public T multiply(Literal other) {
        throw new UnsupportedOperationException("Multiplication not supported for this type.");
    }

    @Override
    public T multiply(Atomic other) {
        throw new UnsupportedOperationException("Multiplication not supported for this type.");
    }

    @Override
    public T subtract(DataElement other) {
        throw new UnsupportedOperationException("Subtract not supported for this type.");
    }

    @Override
    public T subtract(DataGroup other) {
        throw new UnsupportedOperationException("Subtract not supported for this type.");
    }

    @Override
    public T subtract(Literal other) {
        throw new UnsupportedOperationException("Subtract not supported for this type.");
    }

    @Override
    public T subtract(Atomic other) {
        throw new UnsupportedOperationException("Subtract not supported for this type.");
    }
}
