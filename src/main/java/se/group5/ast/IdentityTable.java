package se.group5.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IdentityTable implements Node {
    public final Map<String, String> table = new HashMap<>();

    /**
     * Register a Identity entry
     */
    public void register(String key, String value) {
        table.put(key, value);
    }

    public Optional<String> resolve(String name) {
        return Optional.ofNullable(table.get(name));
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public String toString() {
        return table.toString();
    }
}
