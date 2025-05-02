package ut.pp.AST.statements.threading;

import org.antlr.v4.runtime.ParserRuleContext;

import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.*;
import ut.pp.AST.statements.constituents.*;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class SynchronizedBlock extends Statement
{
    Lock lock;
    Scope synchronizedBody;

    public SynchronizedBlock(Scope parentScope, Variable lock, Scope syncBody, ParserRuleContext ctx) throws TypeMismatchError {
        super(parentScope);
        lock.matchingType(Type.LOCK, ctx);
        this.lock = (Lock) lock;
        this.synchronizedBody = syncBody;
    }

    @Override
    public void generateCode(Code code) {
        code.appendSetlock(lock.getMemAddress());
        synchronizedBody.generateCode(code);
        code.appendUnlock(lock.getMemAddress());
    }

    @Override
    public void buildGraph(GraphBuilder graphBuilder) {
        graphBuilder.push(generateNodeString());
        lock.buildGraph(graphBuilder);
        synchronizedBody.buildGraph(graphBuilder);
        graphBuilder.pop();
    }

    @Override
    public String generateNodeString() {
        return "Synchronized Block";
    }
}
