// Generated from /home/extratoast/IdeaProjects/PP-Project3/src/antlr4/ut/pp/grammar/Potato.g4 by ANTLR 4.10.1
package ut.pp.parser.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PotatoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PotatoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PotatoParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PotatoParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PotatoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(PotatoParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveDeclaration(PotatoParser.PrimitiveDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDeclaration(PotatoParser.StringDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lockDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockDeclaration(PotatoParser.LockDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#synchronizedBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynchronizedBlock(PotatoParser.SynchronizedBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignVariable}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignVariable(PotatoParser.AssignVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignArrayElement}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignArrayElement(PotatoParser.AssignArrayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(PotatoParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(PotatoParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(PotatoParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#fork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFork(PotatoParser.ForkContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#parallelScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParallelScope(PotatoParser.ParallelScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(PotatoParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAnd(PotatoParser.LogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLevel1}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLevel1(PotatoParser.ExprLevel1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOr(PotatoParser.LogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanEquals(PotatoParser.LessThanEqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(PotatoParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThan(PotatoParser.LessThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEquals(PotatoParser.NotEqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLevel2}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLevel2(PotatoParser.ExprLevel2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThanEquals(PotatoParser.GreaterThanEqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThan(PotatoParser.GreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtraction(PotatoParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLevel3}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLevel3(PotatoParser.ExprLevel3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(PotatoParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation(PotatoParser.ConcatenationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLevel4}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLevel4(PotatoParser.ExprLevel4Context ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(PotatoParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLevel5}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLevel5(PotatoParser.ExprLevel5Context ctx);
	/**
	 * Visit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(PotatoParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicNot}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicNot(PotatoParser.LogicNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalValue}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralValue(PotatoParser.LiteralValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableCall}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableCall(PotatoParser.VariableCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userInput}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserInput(PotatoParser.UserInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(PotatoParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code length}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLength(PotatoParser.LengthContext ctx);
	/**
	 * Visit a parse tree produced by {@link PotatoParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(PotatoParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(PotatoParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(PotatoParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharType(PotatoParser.CharTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(PotatoParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAccess(PotatoParser.VariableAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecIntLiteral(PotatoParser.DecIntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinIntLiteral(PotatoParser.BinIntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hexIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexIntLiteral(PotatoParser.HexIntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(PotatoParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteral(PotatoParser.CharLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(PotatoParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLiteral}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(PotatoParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newArray}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArray(PotatoParser.NewArrayContext ctx);
}