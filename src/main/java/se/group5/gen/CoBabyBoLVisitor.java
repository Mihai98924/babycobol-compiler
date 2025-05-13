// Generated from /home/extratoast/IdeaProjects/babycobol-compiler/src/main/antlr/se/group5/CoBabyBoL.g4 by ANTLR 4.13.2
package se.group5.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoBabyBoLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoBabyBoLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoBabyBoLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#identification_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentification_division(CoBabyBoLParser.Identification_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#identification_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentification_clause(CoBabyBoLParser.Identification_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#clause_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause_name(CoBabyBoLParser.Clause_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#clause_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause_value(CoBabyBoLParser.Clause_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#procedure_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_division(CoBabyBoLParser.Procedure_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(CoBabyBoLParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CoBabyBoLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#data_division}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_division(CoBabyBoLParser.Data_divisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#data_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_item(CoBabyBoLParser.Data_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#picture_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPicture_clause(CoBabyBoLParser.Picture_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#like_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_clause(CoBabyBoLParser.Like_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#occurs_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOccurs_clause(CoBabyBoLParser.Occurs_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(CoBabyBoLParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#accept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccept(CoBabyBoLParser.AcceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter(CoBabyBoLParser.AlterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#goto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto(CoBabyBoLParser.GotoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CoBabyBoLParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#perform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPerform(CoBabyBoLParser.PerformContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#signal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignal(CoBabyBoLParser.SignalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#copy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopy(CoBabyBoLParser.CopyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#display}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplay(CoBabyBoLParser.DisplayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CoBabyBoLParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CoBabyBoLParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#divide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(CoBabyBoLParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(CoBabyBoLParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(CoBabyBoLParser.MultiplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#subtract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract(CoBabyBoLParser.SubtractContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(CoBabyBoLParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#evaluate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluate(CoBabyBoLParser.EvaluateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#next_sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNext_sentence(CoBabyBoLParser.Next_sentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#stop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStop(CoBabyBoLParser.StopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#atomic_through}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_through(CoBabyBoLParser.Atomic_throughContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#when_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhen_clause(CoBabyBoLParser.When_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#argument_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_literal(CoBabyBoLParser.Argument_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#any_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_expression(CoBabyBoLParser.Any_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#math_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath_expr(CoBabyBoLParser.Math_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expression(CoBabyBoLParser.Boolean_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic(CoBabyBoLParser.AtomicContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#file_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_name(CoBabyBoLParser.File_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#procedure_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure_name(CoBabyBoLParser.Procedure_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(CoBabyBoLParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#program_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_name(CoBabyBoLParser.Program_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#representation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepresentation(CoBabyBoLParser.RepresentationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevel(CoBabyBoLParser.LevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#numeric_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_literal(CoBabyBoLParser.Numeric_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#integer_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_literal(CoBabyBoLParser.Integer_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoBabyBoLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CoBabyBoLParser.LiteralContext ctx);
}