package se.group5.ast.statement;

import se.group5.ast.Identifier;
import se.group5.ast.procedure.Procedure;

public record GoTo(String identifier) implements Procedure {
}
