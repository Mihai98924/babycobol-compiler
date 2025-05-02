package ut.pp.AST.statements.constituents.expressions.literals;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.statements.constituents.expressions.Expression;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

import java.util.ArrayList;

public class StringLiteral extends ArrayLiteral {

    public StringLiteral(ParserRuleContext ctx, ArrayList<Expression> expressions) throws TypeMismatchError {
        super(ctx, expressions, Type.CHARACTER);
    }
}

