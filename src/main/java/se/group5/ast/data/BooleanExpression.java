package se.group5.ast.data;

import se.group5.ast.Atomic;
import se.group5.ast.Node;

public class BooleanExpression implements Node {
    public Atomic lValue, pValue;
    public EqOp eqOp;

    public BooleanExpression(Atomic lValue, Atomic pValue, EqOp eqOp) {
        this.lValue = lValue;
        this.pValue = pValue;
        this.eqOp = eqOp;
    }

    public boolean result() {
        if(lValue.isElement() && pValue.isElement()) {
            return eqOp.apply((double)lValue.getElement().getValue(), (double)pValue.getElement().getValue());
        } else if (lValue.isLiteral() && pValue.isLiteral()) {
            return eqOp.apply(Double.parseDouble(lValue.getLiteral().raw()), Double.parseDouble(pValue.getLiteral().raw()));
        } else if (lValue.isLiteral() && pValue.isElement()) {
            return eqOp.apply(Double.parseDouble(lValue.getLiteral().raw()), (double)pValue.getElement().getValue());
        } else if (lValue.isElement() && pValue.isLiteral()) {
            return eqOp.apply((double)lValue.getElement().getValue(), Double.parseDouble(pValue.getLiteral().raw()));
        } else {
            throw new IllegalArgumentException("Invalid Atomic types for comparison");
        }
    }
}
