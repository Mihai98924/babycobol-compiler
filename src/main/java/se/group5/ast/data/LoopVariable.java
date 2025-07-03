package se.group5.ast.data;

import lombok.Getter;
import se.group5.ast.Atomic;

@Getter
public class LoopVariable {
    private Atomic from;
    private Atomic to;
    private Atomic step;
    private double currentValue;
    public final DataElement identifier;

    public LoopVariable(DataElement identifier, Atomic from, Atomic to, Atomic step) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.step = step;

        if(from != null && from.isElement())
            this.currentValue = (double)from.getElement().getValue();
        else if(from != null && from.isLiteral())
            this.currentValue = Double.parseDouble(from.getLiteral().raw());
        else
            this.currentValue = 1;

        identifier.setValue(currentValue);
    }

    public void increment() {
        double stepVal = 1.0;
        if(step != null && step.isElement())
            stepVal = (double)step.getElement().getValue();
        else if(step != null && step.isLiteral())
            stepVal = Double.parseDouble(step.getLiteral().raw());

        this.currentValue += stepVal;
        identifier.setValue(currentValue);
    }

    public boolean isInRange() {
        double toVal = identifier.picture().maxValue();
        if(to != null && to.isElement())
            toVal = (double)to.getElement().getValue();
        else if(to != null &&  to.isLiteral())
            toVal = Double.parseDouble(to.getLiteral().raw());

        return currentValue <= toVal;
    }
}
