package se.group5.ast;

import java.util.HashMap;

public class IdentificationDivision implements Node {
    public final HashMap<String, String> properties;

    public IdentificationDivision(HashMap<String, String> properties) {
        this.properties = properties;
    }
}
