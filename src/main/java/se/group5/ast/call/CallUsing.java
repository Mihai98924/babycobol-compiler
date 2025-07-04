package se.group5.ast.call;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Entire USING clause = ordered list of arguments.
 */
public record CallUsing(List<CallArgument> arguments) {
    @Override
    public String toString() {
        return "USING(" +
                arguments.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) +
                ")";
    }
}