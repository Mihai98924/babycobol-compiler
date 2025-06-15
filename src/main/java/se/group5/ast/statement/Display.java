package se.group5.ast.statement;

import lombok.Getter;
import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.literal.Literal;
import se.group5.ast.procedure.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AST node representing the COBOL <code>DISPLAY</code> statement.
 * <p>
 * - Each argument (an {@link Atomic}) is paired with exactly one delimiter, captured
 * by {@link Argument}.
 * - For <code>DELIMITED BY SPACE</code> and <code>DELIMITED BY SIZE</code> the delimiter
 * does not carry a literal value.
 * - For <code>DELIMITED BY "literal"</code> the literal value is retained so it can be
 * emitted later by {@link #toString()} or serialisation.
 */
@Getter
public class Display implements Procedure {

    @Override
    public void execute(Program state) {

        if(state.getDisplayStrategy() == null) {
            return;
        }

        String newline = noAdvancing ? "" : "\n";

        for (Argument argument : arguments) {
            String argumentValue = argument.atomic().getElement().getValue().toString();

            if (argument.delimiter() != null) {
                Delimiter delimiter = argument.delimiter();

                switch (delimiter.type){
                    case SPACE -> {
                        state.getDisplayStrategy().display(argumentValue + newline);
                    }
                    case SIZE -> {
                        String result = argument.atomic.getElement().picture().convert(argumentValue);
                        state.getDisplayStrategy().display(result + newline);
                    }
                    case LITERAL -> {
                        String literal = delimiter.literal().raw();
                        int idx = argumentValue.indexOf(literal);
                        String result = idx != -1 ? argumentValue.substring(0, idx) : null;

                        if (result == null)
                            throw new IllegalArgumentException("Argument " + argument +
                                    " has substring literal: " + literal);

                        state.getDisplayStrategy().display(result + newline);
                    }
                }
            } else {
                state.getDisplayStrategy().display(argumentValue + newline);
            }
        }
    }

    /**
     * Supported COBOL delimiter variants.
     */
    public enum DelimiterType {
        SPACE,
        SIZE,
        LITERAL
    }

    /**
     * Value object that captures the delimiter information.
     * <p>
     * For {@code SPACE} and {@code SIZE} the {@code literal} is {@code null}.
     * For {@code LITERAL} the {@code literal} is required.
     */
    public static final class Delimiter {
        private final DelimiterType type;
        private final Literal literal; // nullable except when type == LITERAL

        private Delimiter(DelimiterType type, Literal literal) {
            this.type = type;
            this.literal = literal;
        }

        /**
         * Creates a delimiter of type {@code SPACE} or {@code SIZE}.
         */
        public static Delimiter of(DelimiterType type) {
            if (type == DelimiterType.LITERAL) {
                throw new IllegalArgumentException("Use Delimiter.of(Literal) for LITERAL delimiters");
            }
            return new Delimiter(type, null);
        }

        /**
         * Creates a delimiter of type {@code LITERAL}.
         */
        public static Delimiter of(Literal literal) {
            Objects.requireNonNull(literal, "literal");
            return new Delimiter(DelimiterType.LITERAL, literal);
        }

        public DelimiterType type() {
            return type;
        }

        public Literal literal() {
            return literal;
        }

        @Override
        public String toString() {
            return switch (type) {
                case SPACE -> "SPACE";
                case SIZE -> "SIZE";
                case LITERAL -> literal.toString();
            };
        }
    }

    /**
     * A single argument supplied to the DISPLAY statement together with the
     * way it is delimited.
     */
    public static final class Argument {
        private final Atomic atomic;
        private final Delimiter delimiter;

        public Argument(Atomic atomic, Delimiter delimiter) {
            this.atomic = Objects.requireNonNull(atomic, "atomic");
            this.delimiter = delimiter;
        }

        public Argument(Atomic atomic) {
            this(atomic, null);
        }

        public Atomic atomic() {
            return atomic;
        }

        public Delimiter delimiter() {
            return delimiter;
        }

        @Override
        public String toString() {
            if (delimiter != null) {
                return atomic + " DELIMITED BY " + delimiter;
            }
            return atomic.toString();
        }
    }

    private final boolean noAdvancing;
    private final List<Argument> arguments = new ArrayList<>();

    public Display(boolean noAdvancing) {
        this.noAdvancing = noAdvancing;
    }

    /**
     * Adds an argument with a non-literal delimiter (SPACE or SIZE).
     */
    public void addAtomic(Atomic atomic, DelimiterType type) {
        arguments.add(new Argument(atomic, Delimiter.of(type)));
    }

    /**
     * Adds an argument with a literal delimiter (DELIMITED BY "literal").
     */
    public void addAtomic(Atomic atomic, Literal literal) {
        arguments.add(new Argument(atomic, Delimiter.of(literal)));
    }

    public void addAtomic(Atomic atomic) {
        arguments.add(new Argument(atomic));
    }

    public List<Argument> getArguments() {
        return List.copyOf(arguments);
    }

    @Override
    public String toString() {
        String prefix = noAdvancing ? "NO ADVANCING, " : "";
        String body = String.join(", ", arguments.stream().map(Object::toString).toList());
        return "DISPLAY(" + prefix + body + ")";
    }
}
