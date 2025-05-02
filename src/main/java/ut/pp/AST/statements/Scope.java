package ut.pp.AST.statements;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Variable;
import ut.pp.AST.statements.threading.Fork;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.ReDeclaredSymbolError;
import ut.pp.exceptions.UnknownSymbolError;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Scope extends Statement {

    protected boolean isRootScope;
    protected ArrayList<Statement> statements;
    protected ArrayList<Variable> variables;

    /**
     * The constructor for a Scope in our AST which stores a set of {@link Statement}'s and a set of {@link Variable}'s
     * these {@link Variable}'s are used by {@link ut.pp.AST.statements.declarations.Declaration},
     * {@link ut.pp.AST.statements.assigments.Assignment} and called on by
     * {@link ut.pp.AST.statements.constituents.expressions.variable_access.VariableAccess} and {@link ut.pp.AST.statements.constituents.expressions.variable_access.ArrayElementAccess}
     *
     * @param parentScope the scope in which this {@link Statement} is located, an instance of {@link ut.pp.AST.Program}
     *                    or {@link Scope}
     */
    public Scope(Scope parentScope) {
        super(parentScope);
        statements = new ArrayList<>();
        variables = new ArrayList<>();
        this.isRootScope = false;
    }
    /**
     * The constructor for the root scope of our AST which stores a set of {@link Statement}'s and a set of {@link Variable}'s
     * these {@link Variable}'s are used by {@link ut.pp.AST.statements.declarations.Declaration},
     * {@link ut.pp.AST.statements.assigments.Assignment} and called on by
     * {@link ut.pp.AST.statements.constituents.expressions.variable_access.VariableAccess} and {@link ut.pp.AST.statements.constituents.expressions.variable_access.ArrayElementAccess}
     */
    public Scope() {
        super(null);
        statements = new ArrayList<>();
        variables = new ArrayList<>();
        this.isRootScope = true;
    }


    /**
     * This method will attempt to add a new variable to the current scope and will throw an error if the symbol
     * is already in the current scope.
     *
     * @param variable a variable which to add to the current scope
     * @throws ReDeclaredSymbolError an error which is thrown when a variable identifier is already in the scope
     */
    public void addVariable(Variable variable) throws ReDeclaredSymbolError {
        String newIdentifier = variable.getIdentifier();
        Stream<Variable> variableStream = getDeclaredVars();
        Optional<Variable> optVar = variableStream.filter(var -> Objects.equals(var.getIdentifier(), newIdentifier)).findAny();
        if (optVar.isEmpty()) {
            variables.add(variable);
        } else {
            throw variable.getRedeclaredSymbolError(optVar.get());
        }
    }

    public void addStatements(ArrayList<Statement> statements) {
        this.statements.addAll(statements);
    }

    public ArrayList<Statement> getStatements() {
        return this.statements;
    }

    /**
     * This method returns a stream of all the declarations that are in the current scope
     *
     * @return a stream of all the Statements which are also Declarations
     */
    public Stream<Variable> getDeclaredVars() {
        return variables.stream();
    }

    /**
     * This method goes through the current and upper scopes (if one exists) and will return the Declaration of this
     * variable. It will throw an error if the variable identifier is not known to our program
     *
     * @param identifier the identifier for which to find the matching declaration
     * @param ctx        the context of the variable retrieval, this is used for error building
     * @return the previous declaration of the variable that needs to be retrieved
     * @throws UnknownSymbolError this error is thrown when the identifier cannot be found in the current context
     */
    public Variable getVariable(String identifier, ParserRuleContext ctx) throws UnknownSymbolError {
        Stream<Variable> variableStram = getVariables().stream();
        Optional<Variable> matches = variableStram.filter(var -> var.getIdentifier().equals(identifier)).findFirst();
        if (matches.isEmpty()) {
            if (!isRootScope) {
                return parentScope.getVariable(identifier, ctx);
            } else {
                throw new UnknownSymbolError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), identifier);
            }
        } else {
            return matches.get();
        }
    }

    /**
     * A function which retrieves the declared variables in this scope as a ArrayList
     *
     * @return an arraylist containing the declared variable objects
     */
    public ArrayList<Variable> getVariables() {
        return variables;
    }

    /**
     * A method which is implemented for every {@link ut.pp.AST.AST_Node} in our program, it walks these nodes in our
     * AST and generates the sprockell code for these nodes and their children
     *
     * @param code The {@link Code} object which is used to build the sprockell code
     */
    @Override
    public void generateCode(Code code) {
        for (Statement statement : statements) {
            statement.generateCode(code);
        }
    }

    /**
     * This method is used by all declarations in order to generate our a (.dot) tree graph from our AST
     *
     * @param graphBuilder A {@link GraphBuilder} instance to which to add the different nodes of our AST
     */
    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        for (Statement statement : statements) {
            statement.buildGraph(graphBuilder);
        }
        graphBuilder.pop();
    }

    /**
     * This function is used in order to generate our tree graph.
     *
     * @return A string that represents a specific node
     */
    @Override
    public String generateNodeString() {
        return "Scope";
    }

    public ArrayList<Variable> getAllVariables() {
        ArrayList<Variable> variablesInScope = getVariables();
        if (!isRootScope) {
            variablesInScope.addAll(parentScope.getAllVariables());
        }
        return variablesInScope;
    }

    public boolean hasThreadable() {
        return statements.stream().anyMatch(statement -> (statement instanceof Fork));
    }
}
