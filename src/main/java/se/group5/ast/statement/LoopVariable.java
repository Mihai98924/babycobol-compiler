package se.group5.ast.statement;

import lombok.Getter;
import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.data.DataElement;
import se.group5.ast.procedure.Procedure;
import se.group5.ast.procedure.ProcedureList;

@Getter
public class LoopVariable implements Procedure {
    private Atomic from;
    private Atomic to;
    private Atomic step;
    public final DataElement identifier;

    // To break loops
    public Loop loop;
    public ProcedureList procedureList;

    public LoopVariable(DataElement identifier, Atomic from, Atomic to, Atomic step) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.step = step;
    }

    public void reset() {
        double stepVal = 1.0;
        if(step != null && step.isElement())
            stepVal = (double)step.getElement().getValue();
        else if(step != null && step.isLiteral())
            stepVal = Double.parseDouble(step.getLiteral().raw());

        double currentValue;
        if(from != null && from.isElement())
            currentValue = (double)from.getElement().getValue();
        else if(from != null && from.isLiteral())
            currentValue = Double.parseDouble(from.getLiteral().raw());
        else
            currentValue = 1;

        identifier.setValue(currentValue - stepVal);
    }

    public void increment() {
        double stepVal = 1.0;
        if(step != null && step.isElement())
            stepVal = (double)step.getElement().getValue();
        else if(step != null && step.isLiteral())
            stepVal = Double.parseDouble(step.getLiteral().raw());

        identifier.setValue((double)identifier.getValue() + stepVal);
    }

    public boolean isInRange() {
        double toVal = identifier.picture().maxValue();
        if(to != null && to.isElement())
            toVal = (double)to.getElement().getValue();
        else if(to != null &&  to.isLiteral())
            toVal = Double.parseDouble(to.getLiteral().raw());

        return (double)identifier.getValue() <= toVal;
    }

    @Override
    public void execute(Program state) {
        increment();
        if(!isInRange()) {
            loop.breakIteration = true;
            procedureList.breakIteration = true;
        }
    }
}
