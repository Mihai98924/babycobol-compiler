package se.group5.ast.statement;

import lombok.Getter;
import se.group5.ast.Atomic;
import se.group5.ast.literal.Literal;
import se.group5.ast.procedure.Procedure;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

@Getter
public class Display implements Procedure {
    private final Boolean noAdvancing;
    private final HashMap<Atomic, UnaryOperator<String>> atomics;


    public Display(Boolean noAdvancing, HashMap<Atomic, UnaryOperator<String>> atomics) {
        this.noAdvancing = noAdvancing;
        this.atomics = atomics;
    }

    /**
     * trim both sides
     */
    public static UnaryOperator<String> delimitedBySpace() {
        return s -> s == null ? "" : s.trim();
    }

    /**
     * fixed‐width, padding or truncating to atomic.length()
     */
    public static UnaryOperator<String> delimitedBySize(Atomic atomic) {
        return s -> {
            if (s == null) {
                return " ".repeat(atomic.length());
            }
            String t = s.trim();
            if (t.length() > atomic.length()) {
                return t.substring(0, atomic.length());
            } else {
                // left‐justify, pad with spaces
                return String.format("%-" + atomic.length() + "s", t);
            }
        };
    }

    /**
     * cut off at first occurrence of literal.raw()
     */
    public static UnaryOperator<String> delimitedByLiteral(Literal literal) {
        return s -> {
            if (s == null) return "";
            int idx = s.indexOf(literal.raw());
            return idx >= 0 ? s.substring(0, idx) : s;
        };
    }

    /**
     * Apply *all* delimiters in insertion order, then
     * append a newline unless NO ADVANCING was specified.
     *
     * @param values a map from each Atomic to its raw string
     */
    public String format(Map<Atomic, String> values) {
        StringBuilder sb = new StringBuilder();
        for (var entry : atomics.entrySet()) {
            Atomic atomic = entry.getKey();
            UnaryOperator<String> fn = entry.getValue();
            String raw = values.get(atomic);
            sb.append(fn.apply(raw));
        }
        if (Boolean.FALSE.equals(noAdvancing)) {
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
