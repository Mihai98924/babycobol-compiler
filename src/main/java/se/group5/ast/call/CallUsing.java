package se.group5.ast.call;

import se.group5.ast.Atomic;
import se.group5.ast.Program;
import se.group5.ast.SymbolTable;
import se.group5.ast.data.DataDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Entire USING clause = ordered list of arguments.
 * Owns all helper utilities needed by CallArgument / CallReturn.
 */
public record CallUsing(List<CallArgument> arguments) {

    /* ------------------------------------------------------------
       Public run-time API
    ------------------------------------------------------------ */

    /**
     * Performs all CALL-USING bindings and returns the list of BY VALUE
     * pairs that have to be copied back *after* the callee finishes.
     */
    public List<Binding> execute(Program caller, Program callee) {
        List<Binding> valueBindings = new ArrayList<>();
        for (CallArgument arg : arguments) {
            arg.execute(caller, callee, valueBindings);
        }
        return valueBindings;
    }

    /* ------------------------------------------------------------
       Small helper record used for BY VALUE copy-out
    ------------------------------------------------------------ */
    public record Binding(DataDefinition callerDef, DataDefinition calleeDef) {
    }

    /* ------------------------------------------------------------
       Shared static helpers – visible to CallArgument / CallReturn
    ------------------------------------------------------------ */

    /**
     * Resolve a DataDefinition behind an Atomic (identifier only).
     */
    public static DataDefinition defOfAtomic(Atomic a) {
        if (a.isElement()) return a.getElement();
        if (a.isComposite()) return a.getGroup();
        throw new IllegalArgumentException("CALL supports only identifiers, not literals: " + a);
    }

    /**
     * Register (or overwrite) the definition under its simple name.
     */
    public static void register(SymbolTable symbols, DataDefinition def) {
        symbols.table.put(def.name().value(), def);
    }

    /**
     * Recursive deep value copy – used for BY VALUE and RETURNING.
     */
    public static void copyValues(DataDefinition from, DataDefinition to) {
        if (from instanceof se.group5.ast.data.DataElement fe &&
                to instanceof se.group5.ast.data.DataElement te) {
            te.setValue(fe.getValue());
        } else if (from instanceof se.group5.ast.data.DataGroup fg &&
                to instanceof se.group5.ast.data.DataGroup tg) {
            for (Map.Entry<String, DataDefinition> e : fg.children.entrySet()) {
                copyValues(e.getValue(), tg.children.get(e.getKey()));
            }
        } else {
            throw new IllegalStateException("Mismatching parameter structures");
        }
    }

    @Override
    public String toString() {
        return "USING(" +
                arguments.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) +
                ")";
    }
}
