package se.group5.ast;

import se.group5.ast.data_division.DataDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Flat lookup table for fully‑qualified or simple identifiers
 */
public final class SymbolTable implements Node {
    public final Map<String, DataDefinition> table = new HashMap<>();

    /**
     * Register a definition under its own simple name and the fully‑qualified path
     */
    public void register(List<Identifier> qualification, DataDefinition def) {
        String fq = qualification.stream().map(Identifier::value).reduce("", (a, b) -> a + "." + b);
        table.put(def.name().value(), def);     // short
        table.put(fq.substring(1), def);        // fully qualified (skip leading dot)
    }

    public Optional<DataDefinition> resolve(String name) {
        return Optional.ofNullable(table.get(name));
    }
}