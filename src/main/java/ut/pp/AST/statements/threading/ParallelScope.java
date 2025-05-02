package ut.pp.AST.statements.threading;

import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.constituents.Variable;

import java.util.ArrayList;

public class ParallelScope extends Scope {
    public ParallelScope(ArrayList<Variable> sharedVariable) {
        super();
        this.variables.addAll(sharedVariable);
    }

    @Override
    public String generateNodeString() {
        return "Parallel Scope";
    }
}
