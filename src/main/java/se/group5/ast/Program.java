package se.group5.ast;

import lombok.Getter;

@Getter
public class Program implements Node {
    private final IdentityTable identityTable;
    private final SymbolTable symbolTable;

    public Program(IdentityTable identityTable, SymbolTable symbolTable) {
        this.identityTable = identityTable;
        this.symbolTable = symbolTable;
    }
}
