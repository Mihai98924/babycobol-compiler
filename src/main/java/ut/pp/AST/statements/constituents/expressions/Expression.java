package ut.pp.AST.statements.constituents.expressions;

import org.antlr.v4.runtime.ParserRuleContext;
import ut.pp.AST.AST_Node;
import ut.pp.AST.statements.constituents.Type;
import ut.pp.exceptions.TypeMismatchError;

public abstract class Expression implements AST_Node {

    protected Type type;
    protected boolean isArray;
    protected boolean isLiteral;

    /**
     * The constructor for our abstract class {@link Expression} this is extended on by all the types of expressions
     * that can exist within our grammar
     *
     * @param type    the {@link Type} of this expression, for use with error reporting and handling within our {@link ut.pp.Compiler}
     * @param isArray a simple boolean representing whether this {@link Expression} instance is an array
     */
    public Expression(Type type, boolean isArray) {
        this.type = type;
        this.isArray = isArray;
    }


    /**
     * A simple function which is used to check for {@link TypeMismatchError}'s it will attempt to match the type of
     * this {@link Expression} object with the type of the argument expression
     *
     * @param expression the {@link Expression} against which to check the type of this {@link Expression} object
     * @param ctx        the {@link ParserRuleContext} which we are doing type checking for, it is used to generate error
     *                   objects
     * @throws TypeMismatchError this error is thrown if the types of the current {@link Expression} object does not
     *                           match the type of the {@link Expression} passed as an argument
     */
    public void matchingType(Expression expression, ParserRuleContext ctx) throws TypeMismatchError, NullPointerException {
        expression.matchingType(type, ctx);
    }

    /**
     * A simple function which is used to check for {@link TypeMismatchError}'s it will attempt to match the type of
     * this {@link Expression} object with the type of the argument type
     *
     * @param type the {@link Type} to match the current {@link Expression}'s type against
     * @param ctx  the {@link ParserRuleContext} which we are doing type checking for, it is used to generate error
     *             objects
     * @throws TypeMismatchError this error is thrown if the types of the current {@link Expression} object does not
     *                           match the type of the {@link Expression} passed as an argument
     */
    public void matchingType(Type type, ParserRuleContext ctx) throws TypeMismatchError, NullPointerException {
        if (this.type != type) {
            int line = ctx.getStart().getLine();
            int column = ctx.getStart().getCharPositionInLine();
            throw new TypeMismatchError(line, column, type, this.type, ctx.getText());

        }
    }

    /**
     * A simple function which is used to check for {@link TypeMismatchError}'s it will attempt to match the type of
     * this {@link Expression} object with the type of the arguments type1 and type2
     *
     * @param type1 one of the {@link Type} objects to match the current type against
     * @param type2 one of the {@link Type} objects to match the current type against
     * @param ctx   the {@link ParserRuleContext} which we are doing type checking for, it is used to generate error
     *              objects
     * @throws TypeMismatchError this error is thrown if the types of the current {@link Expression} object does not
     *                           match the type of the {@link Expression} passed as an argument
     */
    public void matchingType(Type type1, Type type2, ParserRuleContext ctx) throws TypeMismatchError, NullPointerException {
        if (this.type != type1 && this.type != type2) {
            int line = ctx.getStart().getLine();
            int column = ctx.getStart().getCharPositionInLine();
            throw new TypeMismatchError(line, column, type1, type2, this.type, ctx.getText());
        }
    }


    /**
     * A simple getter which returns the {@link Type} of this {@link Expression}
     *
     * @return the {@link Type} of this {@link Expression} object
     */
    public Type getType() {
        return type;
    }

    /**
     * An abstract function which must be declared for all extensions of the abstract class {@link Expression}
     *
     * @return an {@link Integer} representing the size of the {@link Expression} extensions
     */
    public abstract int getSize();

    public boolean isArray(){
        return isArray;
    }

    public boolean isLiteral() {
        return isLiteral;
    }
}

