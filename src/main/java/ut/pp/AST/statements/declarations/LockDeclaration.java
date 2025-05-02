package ut.pp.AST.statements.declarations;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.Scope;
import ut.pp.AST.statements.Statement;
import ut.pp.AST.statements.constituents.Lock;
import ut.pp.codeGen.Code;

public class LockDeclaration extends Statement
{
    Lock lock;

    public LockDeclaration(Scope parentScope, Lock lock) {
        super(parentScope);
        this.lock = lock;
    }

    @Override
    public void generateCode(Code code) {
        code.appendLockInit(lock.getMemAddress());
    }

    @Override
    public String generateNodeString() {
        return null;
    }

    @Override
    public void buildGraph(GraphBuilder graphBuilder) {

    }
}
