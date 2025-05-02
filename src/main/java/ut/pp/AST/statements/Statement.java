package ut.pp.AST.statements;

import ut.pp.AST.AST_Node;

/**
 * The implementation of a Statement as part of our set of AST_Nodes, statements consist of the different constituents
 * of a statement in our grammar
 */
public abstract class Statement implements AST_Node {
    Scope parentScope;

    /**
     * A statement in our AST, all extensions of a Statement are listed in this directory or in the
     * AST/statements/Assignments, AST/statements/declarations or AST/statements/threading depending on their grouping.
     * The constituents that make up these different statements are stored in AST/constituents
     *
     * @param parentScope the scope in which this {@link Statement} is located
     */
    public Statement(Scope parentScope) {
        this.parentScope = parentScope;
    }


}
