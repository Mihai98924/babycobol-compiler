package se.group5.ast.literal;

import se.group5.ast.Atomic;
import se.group5.ast.Identifier;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.data.Representation;
import se.group5.ast.data.Type;

public class Aggregator extends Atomic {
    private Type aggregateType = Type.UNKNOWN;

    public Aggregator(Type type) {
        element = new DataElement(0, new Identifier(""), new Representation(""), 0);
        literal = type == Type.ALPHANUMERIC
                ? new AlphanumericLiteral("")
                : new NumericLiteral("0");
        group = new DataGroup(0, new Identifier(""));
        aggregateType = type;
    }

    public Object getValue() {
        if(aggregateType == Type.ALPHANUMERIC) {
            return literal.raw() ;
        }
    }
}
