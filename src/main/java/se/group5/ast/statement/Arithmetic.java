package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.data.DataElement;
import se.group5.ast.procedure.Procedure;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Generic representation of the four COBOL arithmetic verbs.
 *
 *  VERB   sources    CONN   receivers   [GIVING ids...] [REMAINDER id]
 *  ADD    1 2 3      TO     X
 *  SUBTRACT 1 FROM   X Y
 *  MULTIPLY 2 BY     X Y
 *  DIVIDE  2 INTO    X      GIVING Y REMAINDER Z
 *
 *  The exact semantic and syntactic constraints are enforced
 *  in {@link #validate()} and verb-specific helper methods.
 */
public final class Arithmetic implements Procedure {

    @Override
    public void execute(Program state) {
        switch (verb) {
            case ADD -> {
                double sum = 0;
                for (Atomic source : sources)
                {
                    sum += (double)source.getElement().getValue();
                }

                DataElement receiver = receivers.get(0).getElement();
                sum += (double)receiver.getValue();

                if(!giving.isEmpty())
                {
                    for (DataElement element : giving)
                    {
                        element.setValue(sum);
                    }
                }
                else
                {
                    receiver.setValue(sum);
                }
            }
            case SUBTRACT -> {
                double sum = 0;
                    for (Atomic source : sources)
                {
                    sum += (double)source.getElement().getValue();
                }

                if(!giving.isEmpty())
                {
                    // If GIVING is present, we subtract from the only receiver
                    DataElement receiver = receivers.get(0).getElement();
                    sum = (double)receiver.getValue() - sum;

                    for (DataElement element : giving)
                    {
                        element.setValue(sum);
                    }
                }
                else
                {
                    for (Atomic source : receivers)
                    {
                        double value = (double)source.getElement().getValue();
                        source.getElement().setValue(value - sum);
                    }
                }
            }
            case MULTIPLY -> {
                double sum = (double)sources.get(0).getElement().getValue();

                if(!giving.isEmpty())
                {
                    DataElement give = giving.get(0);
                    give.setValue((double)receivers.get(0).getElement().getValue() * sum);
                }
                else
                {
                    for (Atomic source : receivers)
                    {
                        double value = (double)source.getElement().getValue();
                        source.getElement().setValue(value * sum);
                    }
                }
            }
            case DIVIDE -> {
                double sum = (double)sources.get(0).getElement().getValue();

                if(!giving.isEmpty())
                {
                    for (DataElement element : giving)
                    {
                        element.setValue((double)receivers.get(0).getElement().getValue() / sum);
                    }
                    DataElement give = giving.get(0);
                    give.setValue((double)receivers.get(0).getElement().getValue() * sum);
                }
                else
                {
                    for (Atomic source : receivers)
                    {
                        double value = (double)source.getElement().getValue();
                        source.getElement().setValue(value / sum);
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------
    //  Public API
    // ------------------------------------------------------------------

    public enum Verb {
        ADD("TO"), SUBTRACT("FROM"), MULTIPLY("BY"), DIVIDE("INTO");

        private final String connector;
        Verb(String connector) { this.connector = connector; }
        public String connector() { return connector; }
    }

    public static Arithmetic add(
            List<@NonNull Atomic> addends,
            @NonNull Atomic target,
            List<DataElement> giving) {
        return new Arithmetic(
                Verb.ADD, addends, List.of(target), giving, null
        );
    }

    public static Arithmetic subtract(
            List<@NonNull Atomic> subtrahends,
            List<@NonNull Atomic> minuends,
            List<DataElement> giving) {
        return new Arithmetic(
                Verb.SUBTRACT, subtrahends, minuends, giving, null
        );
    }

    public static Arithmetic multiply(
            @NonNull Atomic multiplier,
            List<@NonNull Atomic> multiplicands,
            DataElement giving) {
        return new Arithmetic(
                Verb.MULTIPLY, List.of(multiplier), multiplicands,
                giving == null ? Collections.emptyList() : List.of(giving),
                null
        );
    }

    public static Arithmetic divide(
            @NonNull Atomic divisor,
            List<@NonNull Atomic> dividend,
            List<DataElement> giving,
            DataElement remainder) {
        return new Arithmetic(
                Verb.DIVIDE, List.of(divisor), dividend,
                giving, remainder
        );
    }

    /** The COBOL verb (ADD/SUBTRACT/…) */
    public Verb verb() { return verb; }

    /** First argument(s) before TO / FROM / … */
    public List<Atomic> sources() { return sources; }

    /** Second argument(s) after TO / FROM / … */
    public List<Atomic> receivers() { return receivers; }

    /** Optional identifiers after GIVING */
    public List<DataElement> giving() { return giving; }

    /** Optional remainder (DIVIDE only) */
    public DataElement remainder() { return remainder; }

    // ------------------------------------------------------------------
    //  Implementation details
    // ------------------------------------------------------------------

    private final Verb verb;
    private final List<Atomic> sources;
    private final List<Atomic> receivers;
    private final List<DataElement> giving;
    private final DataElement remainder;

    private Arithmetic(
            Verb verb,
            List<Atomic> sources,
            List<Atomic> receivers,
            List<DataElement> giving,
            DataElement remainder) {

        this.verb       = Objects.requireNonNull(verb);
        this.sources    = List.copyOf(sources);
        this.receivers  = List.copyOf(receivers);
        this.giving     = giving == null ? List.of() : List.copyOf(giving);
        this.remainder  = remainder;

        validate();     // all common + verb-specific checks in one place
    }

    // ---------- Common validation helpers --------------------------------

    private void validate() {
        validateLiterals();
        validateCardinality();
        validateCompositeCompatibility();
        validateRemainder();
    }

    /** “If any receiver is a literal, GIVING is mandatory.” */
    private void validateLiterals() {
        boolean literalReceiver = receivers.stream().anyMatch(Atomic::isLiteral);
        if (literalReceiver && giving.isEmpty()) {
            throw new IllegalArgumentException(
                    "A literal receiver requires a GIVING clause.");
        }
    }

    /** Each verb has small cardinality quirks collected here. */
    private void validateCardinality() {
        switch (verb) {
            case ADD -> {
                if (sources.isEmpty()) error("ADD needs at least one addend");
                if (receivers.size() != 1) error("ADD takes exactly one target");
            }
            case SUBTRACT -> {
                if (sources.isEmpty()) error("SUBTRACT needs at least one subtrahend");
                if (!giving.isEmpty() && receivers.size() != 1)
                    error("When GIVING is present, SUBTRACT must have exactly one receiver");
            }
            case MULTIPLY -> {
                if (sources.size() != 1) error("MULTIPLY takes exactly one multiplier");
                if (!giving.isEmpty() && giving.size() != 1)
                    error("MULTIPLY can GIVING exactly one identifier");
            }
            case DIVIDE -> {
                if (sources.size() != 1) error("DIVIDE takes exactly one divisor");
                if (!giving.isEmpty() && receivers.size() != 1)
                    error("When GIVING is present, DIVIDE must have exactly one dividend");
            }
        }
    }

    /** Composite-field compatibility for ADD/SUBTRACT (shared rule). */
    private void validateCompositeCompatibility() {
        if (verb == Verb.ADD || verb == Verb.SUBTRACT) {
            boolean compositeSource = sources.size() == 1 && sources.get(0).isComposite();
            boolean compositeReceiver = receivers.size() == 1 && receivers.get(0).isComposite();
            if (compositeSource ^ compositeReceiver) {
                error("Both operands must be composite or both simple.");
            }
        }
    }

    /** Only DIVIDE may carry a remainder. */
    private void validateRemainder() {
        if (remainder != null && verb != Verb.DIVIDE) {
            error("REMAINDER is only valid with DIVIDE.");
        }
    }

    private static void error(String msg) { throw new IllegalArgumentException(msg); }

    // ---------- Common `toString()` --------------------------------------

    @Override
    @NonNull
    public String toString() {
        return verb + "(" +
                "SOURCES("   + String.join(", ", sources.stream().map(Object::toString).toList())   + "), " +
                verb.connector() + "(" + String.join(", ", receivers.stream().map(Object::toString).toList()) + ")" +
                (giving.isEmpty() ? "" :
                        ", GIVING(" + String.join(", ", giving.stream().map(Object::toString).toList()) + ")") +
                (remainder == null ? "" : ", REMAINDER(" + remainder + ")") +
                ")";
    }

    // ---------- Execution --------------------------------------

    public double add(double addend1, double addend2) {
        return addend1 + addend2;
    }

    public double subtract(double subtract1, double subtract2) {
        return subtract1 - subtract2;
    }

    public double multiply(double multiplicand1, double multiplicand2) {
        return multiplicand1 * multiplicand2;
    }

    public double divide(double dividend, double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return dividend / divisor;
    }
}
