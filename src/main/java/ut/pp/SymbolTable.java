package ut.pp;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.threading.ParallelScope;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.Statement;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.expressions.variable_access.ArrayElementAccess;
import ut.pp.AST.statements.constituents.expressions.variable_access.VariableAccess;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.EmptyStackException;
import ut.pp.exceptions.ReDeclaredSymbolError;
import ut.pp.exceptions.TypeMismatchError;
import ut.pp.exceptions.UnknownSymbolError;

import java.util.*;
import java.util.stream.Collectors;

public class SymbolTable {
    private final Deque<Scope> scopes;
    private int localMemoryPointer;
    private int sharedMemoryPointer;


    public SymbolTable() {
        this.scopes = new ArrayDeque<>();
        this.scopes.push(new Scope());
        this.localMemoryPointer = 0;
        this.sharedMemoryPointer = Code.NUM_RESERVED_SHARED_ADDRESSES;
    }

    public void openScope() {
        Scope prevScope = scopes.peek();
        this.scopes.push(new Scope(prevScope));
    }

    public void openParallelScope() {
        ArrayList<Variable> sharedVariable = getSharedVariables();
        this.scopes.push(new ParallelScope(sharedVariable));
    }

    public void closeScope() {
        assert this.scopes.peek() != null;
        try {
            Scope topScope = this.scopes.pop();
            for (Variable variable : topScope.getVariables()) {
                if (variable.isShared())
                    sharedMemoryPointer -= variable.getSize();
                else
                    localMemoryPointer -= variable.getSize();
            }
        } catch (NoSuchElementException e) {
            throw new EmptyStackException("Cannot close global scope.");
        }
    }

    public void put(Variable variable) throws ReDeclaredSymbolError {
        Scope topScope = this.scopes.peek();
        assert topScope != null;
        int memAddress;
        if (variable.isShared()) {
            memAddress = sharedMemoryPointer;
            sharedMemoryPointer += variable.getSize();
        } else {
            memAddress = localMemoryPointer;
            localMemoryPointer += variable.getSize();
        }
        variable.setMemAddress(memAddress);
        topScope.addVariable(variable);
    }

    public Scope getScope() {
        return scopes.peek();
    }

    public VariableAccess getVariableAccess(String id, ParserRuleContext ctx) throws UnknownSymbolError {
        assert scopes.peek() != null;
        Scope thisScope = scopes.peek();
        Variable variable = thisScope.getVariable(id, ctx);
        return new VariableAccess(thisScope, ctx, variable);
    }

    public ArrayElementAccess getArrayVariableAccess(String id, ParserRuleContext ctx, Expression index) throws UnknownSymbolError, TypeMismatchError {
        assert scopes.peek() != null;
        Scope thisScope = scopes.peek();
        Variable variable = thisScope.getVariable(id, ctx);
        return new ArrayElementAccess(thisScope, ctx, variable, index);
    }

    public void addStatements(ArrayList<Statement> statements) {
        assert scopes.peek() != null;
        Scope thisScope = scopes.peek();
        thisScope.addStatements(statements);
    }

    public Variable getVariable(String id, ParserRuleContext ctx) throws UnknownSymbolError {
        assert scopes.peek() != null;
        Scope thisScope = scopes.peek();
        return thisScope.getVariable(id, ctx);
    }

    public ArrayList<Variable> getSharedVariables() {
        assert scopes.peek() != null;
        Scope thisScope = scopes.peek();
        ArrayList<Variable> allVariables = thisScope.getAllVariables();
        ArrayList<String> knownIdentifiers = new ArrayList<>();
        return (ArrayList<Variable>) allVariables.stream().filter(Variable::isShared).distinct().filter(
                variable -> {
                    if (!knownIdentifiers.contains(variable.getIdentifier())) {
                        knownIdentifiers.add(variable.getIdentifier());
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
    }
}
