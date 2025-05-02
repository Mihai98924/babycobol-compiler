package ut.pp.AST.statements.constituents.expressions.logical;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.GraphBuilder;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.expressions.UnaryExpression;
import ut.pp.codeGen.Code;
import ut.pp.exceptions.TypeMismatchError;

public class LogicalNot extends UnaryExpression
{
    public LogicalNot(ParserRuleContext ctx, Expression operand) throws TypeMismatchError {
        super(ctx, Type.BOOLEAN, operand);
    }

    @Override
    public void generateCode(Code code) {
        operand.generateCode(code);
        code.appendLogicalNot();
    }

    @Override
    public String generateNodeString() {
        return null;
    }

    @Override
    public void buildGraph(GraphBuilder graphBuilder) {

    }
}
