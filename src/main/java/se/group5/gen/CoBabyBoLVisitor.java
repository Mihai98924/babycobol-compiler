// Generated from /home/extratoast/IdeaProjects/babycobol-compiler/src/main/antlr/se/group5/CoBabyBoL.g4 by ANTLR 4.13.2
package se.group5.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoBabyBoL}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoBabyBoLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoBabyBoL.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(CoBabyBoL.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#identification_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentification_division(CoBabyBoL.Identification_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#identification_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentification_clause(CoBabyBoL.Identification_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#clause_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause_name(CoBabyBoL.Clause_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#clause_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause_value(CoBabyBoL.Clause_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#procedure_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_division(CoBabyBoL.Procedure_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(CoBabyBoL.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CoBabyBoL.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#data_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_division(CoBabyBoL.Data_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#data_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_item(CoBabyBoL.Data_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#picture_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPicture_clause(CoBabyBoL.Picture_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#like_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_clause(CoBabyBoL.Like_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#occurs_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOccurs_clause(CoBabyBoL.Occurs_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#accept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccept(CoBabyBoL.AcceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter(CoBabyBoL.AlterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#goto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto(CoBabyBoL.GotoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CoBabyBoL.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#perform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPerform(CoBabyBoL.PerformContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#signal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal(CoBabyBoL.SignalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#copy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopy(CoBabyBoL.CopyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#display}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplay(CoBabyBoL.DisplayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CoBabyBoL.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CoBabyBoL.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#divide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(CoBabyBoL.DivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(CoBabyBoL.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(CoBabyBoL.MultiplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#subtract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract(CoBabyBoL.SubtractContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(CoBabyBoL.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#evaluate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluate(CoBabyBoL.EvaluateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#next_sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNext_sentence(CoBabyBoL.Next_sentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#stop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStop(CoBabyBoL.StopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#atomic_through}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_through(CoBabyBoL.Atomic_throughContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#when_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhen_clause(CoBabyBoL.When_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#argument_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_literal(CoBabyBoL.Argument_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#any_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_expression(CoBabyBoL.Any_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#math_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath_expr(CoBabyBoL.Math_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expression(CoBabyBoL.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic(CoBabyBoL.AtomicContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(CoBabyBoL.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#file_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_name(CoBabyBoL.File_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#procedure_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_name(CoBabyBoL.Procedure_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(CoBabyBoL.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#program_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_name(CoBabyBoL.Program_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevel(CoBabyBoL.LevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CoBabyBoL.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#numeric_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_literal(CoBabyBoL.Numeric_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoL#alphanumeric_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlphanumeric_literal(CoBabyBoL.Alphanumeric_literalContext ctx);
}