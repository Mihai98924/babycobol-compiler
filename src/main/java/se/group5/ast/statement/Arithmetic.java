package se.group5.ast.statement;

import lombok.NonNull;
import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.data.*;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.procedure.Procedure;

import java.util.*;

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
            case ADD -> executeAdd(state);
            case SUBTRACT -> executeSubtract(state);
            case MULTIPLY -> executeMultiply();
            case DIVIDE -> executeDivide();
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
            List<DataDefinition> giving) {
        return new Arithmetic(
                Verb.ADD, addends, List.of(target), giving, null
        );
    }

    public static Arithmetic subtract(
            List<@NonNull Atomic> subtrahends,
            List<@NonNull Atomic> minuends,
            List<DataDefinition> giving) {
        return new Arithmetic(
                Verb.SUBTRACT, subtrahends, minuends, giving, null
        );
    }

    public static Arithmetic multiply(
            @NonNull Atomic multiplier,
            List<@NonNull Atomic> multiplicands,
            DataDefinition giving) {
        return new Arithmetic(
                Verb.MULTIPLY, List.of(multiplier), multiplicands,
                giving == null ? Collections.emptyList() : List.of(giving),
                null
        );
    }

    public static Arithmetic divide(
            @NonNull Atomic divisor,
            List<@NonNull Atomic> dividend,
            List<DataDefinition> giving,
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
    public List<DataDefinition> giving() { return giving; }

    /** Optional remainder (DIVIDE only) */
    public DataElement remainder() { return remainder; }

    // ------------------------------------------------------------------
    //  Implementation details
    // ------------------------------------------------------------------

    private final Verb verb;
    private final List<Atomic> sources;
    private final List<Atomic> receivers;
    private final List<DataDefinition> giving;
    private final DataElement remainder;

    private Arithmetic(
            Verb verb,
            List<Atomic> sources,
            List<Atomic> receivers,
            List<DataDefinition> giving,
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
            boolean compositeSource = sources.stream().allMatch(Atomic::isComposite);
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
    private void executeAdd(Program state) {
        // VARIABLES
        Atomic firstSource = sources.get(0);
        Atomic receiver = receivers.get(0);
        // Copied group element
        // If GIVING is present, we will set the value to all giving elements
        Atomic aggregatedGroup = null;

        // VALIDATE
        Type type = firstSource.getType();
        if(type == Type.UNKNOWN) {
            throw new IllegalArgumentException("Unsupported type for source: " + firstSource);
        }
        validatePictureClausesToExcludeAandX();

        // VARIABLES
        StringBuilder sb = new StringBuilder();
        double sum = 0;

        // First is a composite
        if(firstSource.isComposite())
        {
            DataGroup receiverGroup = receiver.getGroup();
            if(!giving.isEmpty()) {
                aggregatedGroup = receiver.copy();
            }
            for (Atomic atomic : sources) {
                DataGroup sourceGroup = atomic.getGroup();
                for (Map.Entry<String, DataDefinition> entry : sourceGroup.children.entrySet()) {
                    if(receiverGroup.children.containsKey(entry.getKey())) {
                        DataDefinition receiverDefinition;
                        if(giving.isEmpty()){
                            receiverDefinition = receiverGroup.children.get(entry.getKey());
                        } else {
                            receiverDefinition = aggregatedGroup.getGroup().children.get(entry.getKey());
                        }

                        if(receiverDefinition == null) {
                            continue;
                        }

                        Object receiverValue = receiverDefinition.getValue();
                        Object entryValue = entry.getValue().getValue();
                        switch (entry.getValue().getType()) {
                            case NUMERIC:
                                if(receiverValue != null || entryValue != null)
                                    receiverDefinition.setValue((receiverValue != null ? (double)receiverValue : 0) +
                                            (entryValue != null ? (double)entryValue : 0));
                                else
                                    receiverDefinition.setValue(null);
                                break;
                            case ALPHANUMERIC:
                                if(receiverValue != null || entryValue != null)
                                    receiverDefinition.setValue((receiverValue != null ? receiverValue.toString() : "") +
                                            (entryValue != null ? (double)entryValue : 0));
                                else
                                    receiverDefinition.setValue(null);
                                receiverDefinition.setValue(entry.getValue().getValue().toString() +
                                        receiverDefinition.getValue().toString());
                                break;
                            default:
                                throw new IllegalArgumentException("Unsupported type for addition: " + type);
                        }
                    }
                }
            }

            if (!giving.isEmpty()) {
                Move move = new Move(aggregatedGroup, giving.stream().map(DataDefinition::name).toList());
                move.execute(state);
            }

            return;
        }

        // Regular
        for (Atomic source : sources) {
            if(type == Type.NUMERIC) {
                if(source.isLiteral()) {
                    sum += Double.parseDouble(source.getLiteral().raw());
                } else {
                    sum += (double) source.getElement().getValue();
                }
            } else {
                if (source.isLiteral()) {
                    sb.append(source.getLiteral().raw());
                } else {
                    sb.append(source.getElement().getValue().toString());
                }
            }
        }

        if (receiver.isLiteral()) {
            if (type == Type.ALPHANUMERIC) {
                sb.append(receiver.getLiteral().raw());
            } else {
                sum += Double.parseDouble(receiver.getLiteral().raw());
            }
        } else {
            DataElement element = receiver.getElement();
            if (type == Type.ALPHANUMERIC) {
                if(element.getType() == Type.NUMERIC) {
                    String elementValue = element.convert();
                    sb.append(elementValue);
                } else {
                    sb.append(element.getValue().toString());
                }
            } else {
                sum += (double) element.getValue();
            }
            if (giving.isEmpty()) {
                if (type == Type.ALPHANUMERIC) {
                    element.setValue(sb.toString(), true);
                } else {
                    element.setValue(sum, true);
                }
            }
        }

        for (DataDefinition giving : giving) {
            if (type == Type.ALPHANUMERIC) {
                giving.setValue(sb.toString(), true);
            } else {
                giving.setValue(sum, true);
            }
        }
    }

    private void executeSubtract(Program state) {
        // VARIABLES
        Atomic firstReceiver = receivers.get(0);
        Atomic firstSource = sources.get(0);
        // Copied group element
        // If GIVING is present, we will set the value to all giving elements
        Atomic aggregatedGroup = null;

        // VALIDATE
        validatePictureClausesToExcludeAandX(true);

        if(!giving.isEmpty()) {
            aggregatedGroup = firstReceiver.copy();
        }
        // First is a composite
        if(firstSource.isComposite())
        {
            DataGroup receiverGroup = firstReceiver.getGroup();
            for (Atomic atomic : sources) {
                DataGroup sourceGroup = atomic.getGroup();
                for (Map.Entry<String, DataDefinition> entry : sourceGroup.children.entrySet()) {
                    if (receiverGroup.children.containsKey(entry.getKey())) {
                        DataDefinition receiverDefinition;
                        if (giving.isEmpty()) {
                            receiverDefinition = receiverGroup.children.get(entry.getKey());
                        } else {
                            receiverDefinition = aggregatedGroup.getGroup().children.get(entry.getKey());
                        }

                        Object receiverValue = receiverDefinition.getValue();
                        Object entryValue = entry.getValue().getValue();
                        if(receiverValue != null || entryValue != null)
                            receiverDefinition.setValue((receiverValue != null ? (double)receiverValue : 0) -
                                    (entryValue != null ? (double)entryValue : 0));
                        else
                            receiverDefinition.setValue(null);
                    }
                }
            }

            if (!giving.isEmpty()) {
                Move move = new Move(aggregatedGroup, giving.stream().map(DataDefinition::name).toList());
                move.execute(state);
            }

            return;
        }

        // If first is not a composite
        double sum = 0;
        for (Atomic source : sources) {
            if(source.isLiteral()) {
                sum += Double.parseDouble(source.getLiteral().raw());
            } else {
                sum += (double)source.getElement().getValue();
            }
        }

        if(giving.isEmpty()) {
            for (Atomic receiver : receivers) {
                double value = (double) receiver.getElement().getValue();
                receiver.getElement().setValue(value - sum);
            }
        } else {
            if(firstReceiver.isLiteral()) {
                sum = Double.parseDouble(firstReceiver.getLiteral().raw()) - sum;
            } else {
                sum = (double)firstReceiver.getElement().getValue() - sum;
            }
        }

        // If GIVING is present, we set the value to all giving elements
        for (DataDefinition giving : giving) {
            giving.setValue(sum);
        }
    }

    private void executeMultiply() {
        // VARIABLES
        Atomic firstReceiver = receivers.get(0);
        double value;

        // VALIDATE
        validatePictureClausesToExcludeAandX(true);

        if(sources.get(0).isLiteral()) {
            value = Double.parseDouble(sources.get(0).getLiteral().raw());
        } else {
            value = (double)sources.get(0).getElement().getValue();
        }

        if (firstReceiver.isLiteral()) {
            value *= Double.parseDouble(firstReceiver.getLiteral().raw());
        } else if(giving.isEmpty()) {
            for (Atomic receiver : receivers) {
                receiver.getElement().setValue((double)receiver.getElement().getValue() * value);
            }
        } else {
            value *= (double)firstReceiver.getElement().getValue();
        }

        for (DataDefinition giving : giving) {
            giving.setValue(value);
        }
    }

    private void executeDivide() {
        // VARIABLES
        Atomic firstReceiver = receivers.get(0);
        double value, remainderValue = -1;

        // VALIDATE
        validatePictureClausesToExcludeAandX(true);

        if(sources.get(0).isLiteral()) {
            value = Double.parseDouble(sources.get(0).getLiteral().raw());
        } else {
            value = (double)sources.get(0).getElement().getValue();
        }

        if (firstReceiver.isLiteral()) {
            if(remainder == null) {
                value = Double.parseDouble(firstReceiver.getLiteral().raw()) / value;
            } else {
                int firstValue = (int)Double.parseDouble(firstReceiver.getLiteral().raw());
                int valueInt = (int)value;
                remainderValue = firstValue % valueInt;
                value = (double) (firstValue / valueInt);
            }
        } else if(giving.isEmpty()) {
            for (Atomic receiver : receivers) {
                receiver.getElement().setValue((double)receiver.getElement().getValue() / value);
            }
        } else {
            if(remainder == null) {
                value = (double)firstReceiver.getElement().getValue() / value;
            } else {
                int firstValue = ((Double)firstReceiver.getElement().getValue()).intValue();
                int valueInt = (int)value;
                remainderValue = firstValue % valueInt;
                value = (double) (firstValue / valueInt);
            }
        }

        if (remainder != null) {
            remainder.setValue(remainderValue);
            giving.get(0).setValue(value);
        } else {
            for (DataDefinition giving : giving) {
                giving.setValue(value);
            }
        }
    }

    private void validatePictureClausesToExcludeAandX() {
        validatePictureClausesToExcludeAandX(false);
    }

    private void validatePictureClausesToExcludeAandX(boolean always) {
        // Check if identifiers are defined with picture clauses without A and X
        List<Atomic> atomics = new ArrayList<>();
        atomics.addAll(sources);
        atomics.addAll(receivers);

        for (Atomic atomic : atomics) {
            if((always || atomic.getType() == Type.NUMERIC || atomic.getType() == Type.COMPOSITE) && atomic.doesPictureContainAnySymbols(
                    PictureSymbol.ALPHA,
                    PictureSymbol.ALPHANUM
            )) {
                throw new IllegalArgumentException(
                        "Addition of identifiers with A and X in picture clauses" +
                                " is not supported: " + atomic);
            }
        }

        for (DataDefinition giving : giving) {
            if((always || giving.getType() == Type.NUMERIC || giving.getType() == Type.COMPOSITE) && giving.doesPictureContainAnySymbols(
                    PictureSymbol.ALPHA,
                    PictureSymbol.ALPHANUM
            )) {
                throw new IllegalArgumentException(
                        "Addition of identifiers with A and X in picture clauses" +
                                " is not supported: " + giving);
            }
        }
    }
}
