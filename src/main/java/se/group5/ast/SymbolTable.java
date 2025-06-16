package se.group5.ast;

import se.group5.ast.data.DataDefinition;

import java.util.*;

/**
 * Flat lookup table for fully‑qualified or simple identifiers
 */
public final class SymbolTable implements Node {
    public final Map<String, DataDefinition> table = new HashMap<>();

    // Access table elements based on their short name (CONTAINER.C -> C)
    // Map: Short name -> List of table keys
    public final Map<String, List<String>> symbolTableQuickAccesMap = new HashMap<>();

    public final Map<String, Function> functions = new HashMap<>();

    public void register(Identifier id, DataDefinition def) {
        table.put(id.value(), def);
    }

    /**
     * Register a definition under its own simple name and the fully‑qualified path
     */
    public void register(List<Identifier> qualification, DataDefinition def) {
        String fq = qualification.stream().map(Identifier::value).reduce("", (a, b) -> a + "." + b);
        String shortName = def.name().value();                  // short
        table.put(shortName, def);
        String fqWithoutLeadingDot = fq.substring(1); // fully qualified (skip leading dot)
        table.put(fqWithoutLeadingDot, def);

        List<String> existingKeys = symbolTableQuickAccesMap.get(shortName);
        if (existingKeys == null) {
            symbolTableQuickAccesMap.put(shortName, new ArrayList<>() {{
                add(fqWithoutLeadingDot);
            }});
        } else {
            existingKeys.add(fqWithoutLeadingDot);
            symbolTableQuickAccesMap.put(shortName, existingKeys);
        }
    }

    public Optional<DataDefinition> resolve(String name) {
        if (table.containsKey(name)) return Optional.ofNullable(table.get(name));

        var possibleMatches = table.keySet().stream().filter((key) -> key.endsWith(name)).toList();
        if (possibleMatches.size() >= 2) {
            throw new IllegalStateException("Identifier '" + name + "' is ambiguous in the symbol table");
        }
        if (possibleMatches.size() == 1) {
            return Optional.of(table.get(possibleMatches.iterator().next()));
        }

        return Optional.empty();
    }

    public Optional<Identifier> resolveIdentifier(String name) {
        Optional<DataDefinition> def = resolve(name);
        return def.map(DataDefinition::name);
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public String toString() {
        return table.toString();
    }

    /**
     * Get the fully qualified identifier for a given partially qualified identifier.
     * If the identifier is ambiguous, it returns null.
     *
     * @param partiallyQualifiedIdentifier The identifier to resolve
     * @return The fully qualified identifier or null if ambiguous
     */
    public String getFullyQualifiedIdentifier(String partiallyQualifiedIdentifier) {
        List<String> pathElements = Arrays.stream(partiallyQualifiedIdentifier.split("\\.")).toList();
        String identifier = pathElements.get(pathElements.size() - 1);
        return getFullyQualifiedIdentifier(pathElements, identifier);
    }

    /**
     * Get the fully qualified identifier for a given path and identifier.
     * If the identifier is ambiguous, it returns null.
     *
     * @param path       The path to resolve
     * @param identifier The identifier to resolve
     * @return The fully qualified identifier or null if ambiguous
     */
    public String getFullyQualifiedIdentifier(List<String> path, String identifier) {
        List<String> keys = symbolTableQuickAccesMap.get(identifier);
        Map<String, List<String>> symbolPaths = new HashMap<>();
        Map<String, Boolean> couldPathBeAmbiguous = new HashMap<>();

        if (keys == null)
            return "";

        // Start at the leaves of the path and work upwards
        for (String key : keys) {
            List<String> keyParts = Arrays.stream(key.split("\\.")).toList();
            if (keyParts.size() < path.size()) {
                continue;
            }

            symbolPaths.put(key, keyParts);
            couldPathBeAmbiguous.put(key, true);
        }

        for (String pathElement : path) {
            Map<String, List<String>> tmpPath = new HashMap<>(symbolPaths);
            for (Map.Entry<String, List<String>> symbolPath : symbolPaths.entrySet()) {
                if (!couldPathBeAmbiguous.get(symbolPath.getKey())) {
                    continue;
                }

                List<String> pathParts = symbolPath.getValue();

                // Find the index of the current path element in the fully qualified path
                int pathElementIdInFullyQualifiedPath = pathParts.indexOf(pathElement);
                if (pathElementIdInFullyQualifiedPath == -1) {
                    couldPathBeAmbiguous.put(symbolPath.getKey(), false);
                } else {
                    // Reduce pathParts to the part after the current path element
                    // Update the pathPart for a key
                    List<String> tmpPathValue = pathParts.subList(pathElementIdInFullyQualifiedPath + 1, pathParts.size());
                    tmpPath.put(symbolPath.getKey(), tmpPathValue);
                }
            }
            symbolPaths = tmpPath;
        }

        // If there is only one path left, it is not ambiguous
        List<String> trueKeys = couldPathBeAmbiguous.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .toList();

        return trueKeys.size() == 1 ? trueKeys.get(0) : null;
    }

    public void registerFunc(Identifier identifier, Function function) {
        functions.put(identifier.value(), function);
    }

    public Optional<Function> resolveFunc(Identifier identifier) {
        return resolveFunc(identifier.value());
    }

    public Optional<Function> resolveFunc(String identifier) {
        return Optional.ofNullable(functions.get(identifier));
    }
}