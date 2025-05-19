package se.group5.ast.statement;

import se.group5.ast.Atomic;
import se.group5.ast.procedure.Procedure;

import java.util.List;

public record Add(List<Atomic> targets) implements Procedure {
}
