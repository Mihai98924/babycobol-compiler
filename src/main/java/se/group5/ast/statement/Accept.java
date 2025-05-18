package se.group5.ast.statement;

import se.group5.ast.Identifier;
import se.group5.ast.Node;

import java.util.List;

public record Accept(List<Identifier> targets) implements Node {
}
