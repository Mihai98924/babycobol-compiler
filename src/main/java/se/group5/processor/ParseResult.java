package se.group5.processor;

import org.antlr.v4.runtime.tree.ParseTree;
import se.group5.ast.Node;
import se.group5.ast.SymbolTable;

/**
 * Immutable triple returned by {@link Processor#parse(String)} and
 * {@link Processor#parseFile(String)}.
 */
public record ParseResult(ParseTree parseTree, Node rootNode, SymbolTable symbolTable) {
}
