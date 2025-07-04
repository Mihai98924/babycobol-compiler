package se.group5.ast.data;

import java.util.Objects;

public enum EqOp {
    EQUALS("="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    LEQ_THAN("<="),
    GREATER_THAN(">"),
    GEQ_THAN(">=");

    private final String symbol;

    EqOp(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public boolean apply(double b1, double b2) {
        switch (this) {
            case EQUALS:
                return b1 == b2;
            case NOT_EQUALS:
                return b1 != b2;
            case LESS_THAN:
                return b1 < b2;
            case LEQ_THAN:
                return b1 <= b2;
            case GREATER_THAN:
                return b1 > b2;
            case GEQ_THAN:
                return b1 >= b2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + this);
        }
    }

    public static EqOp fromString(String eq) {
        for (EqOp eqOp : EqOp.values()) {
            if (Objects.equals(eqOp.symbol, eq)) {
                return eqOp;
            }
        }
        throw new IllegalArgumentException("No enum constant with eq " + eq);
    }
}
