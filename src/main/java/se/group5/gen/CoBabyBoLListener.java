// Generated from /home/extratoast/IdeaProjects/babycobol-compiler/src/main/antlr/se/group5/CoBabyBoL.g4 by ANTLR 4.13.2
package se.group5.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoBabyBoL}.
 */
public interface CoBabyBoLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoBabyBoL.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoBabyBoL.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(CoBabyBoL.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(CoBabyBoL.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#identification_division}.
	 * @param ctx the parse tree
	 */
	void enterIdentification_division(CoBabyBoL.Identification_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#identification_division}.
	 * @param ctx the parse tree
	 */
	void exitIdentification_division(CoBabyBoL.Identification_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#identification_clause}.
	 * @param ctx the parse tree
	 */
	void enterIdentification_clause(CoBabyBoL.Identification_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#identification_clause}.
	 * @param ctx the parse tree
	 */
	void exitIdentification_clause(CoBabyBoL.Identification_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#clause_name}.
	 * @param ctx the parse tree
	 */
	void enterClause_name(CoBabyBoL.Clause_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#clause_name}.
	 * @param ctx the parse tree
	 */
	void exitClause_name(CoBabyBoL.Clause_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#clause_value}.
	 * @param ctx the parse tree
	 */
	void enterClause_value(CoBabyBoL.Clause_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#clause_value}.
	 * @param ctx the parse tree
	 */
	void exitClause_value(CoBabyBoL.Clause_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#procedure_division}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_division(CoBabyBoL.Procedure_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#procedure_division}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_division(CoBabyBoL.Procedure_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(CoBabyBoL.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(CoBabyBoL.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CoBabyBoL.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CoBabyBoL.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#data_division}.
	 * @param ctx the parse tree
	 */
	void enterData_division(CoBabyBoL.Data_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#data_division}.
	 * @param ctx the parse tree
	 */
	void exitData_division(CoBabyBoL.Data_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#data_item}.
	 * @param ctx the parse tree
	 */
	void enterData_item(CoBabyBoL.Data_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#data_item}.
	 * @param ctx the parse tree
	 */
	void exitData_item(CoBabyBoL.Data_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#picture_clause}.
	 * @param ctx the parse tree
	 */
	void enterPicture_clause(CoBabyBoL.Picture_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#picture_clause}.
	 * @param ctx the parse tree
	 */
	void exitPicture_clause(CoBabyBoL.Picture_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#like_clause}.
	 * @param ctx the parse tree
	 */
	void enterLike_clause(CoBabyBoL.Like_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#like_clause}.
	 * @param ctx the parse tree
	 */
	void exitLike_clause(CoBabyBoL.Like_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#occurs_clause}.
	 * @param ctx the parse tree
	 */
	void enterOccurs_clause(CoBabyBoL.Occurs_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#occurs_clause}.
	 * @param ctx the parse tree
	 */
	void exitOccurs_clause(CoBabyBoL.Occurs_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#accept}.
	 * @param ctx the parse tree
	 */
	void enterAccept(CoBabyBoL.AcceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#accept}.
	 * @param ctx the parse tree
	 */
	void exitAccept(CoBabyBoL.AcceptContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlter(CoBabyBoL.AlterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlter(CoBabyBoL.AlterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#goto}.
	 * @param ctx the parse tree
	 */
	void enterGoto(CoBabyBoL.GotoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#goto}.
	 * @param ctx the parse tree
	 */
	void exitGoto(CoBabyBoL.GotoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(CoBabyBoL.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(CoBabyBoL.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#perform}.
	 * @param ctx the parse tree
	 */
	void enterPerform(CoBabyBoL.PerformContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#perform}.
	 * @param ctx the parse tree
	 */
	void exitPerform(CoBabyBoL.PerformContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#signal}.
	 * @param ctx the parse tree
	 */
	void enterSignal(CoBabyBoL.SignalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#signal}.
	 * @param ctx the parse tree
	 */
	void exitSignal(CoBabyBoL.SignalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#copy}.
	 * @param ctx the parse tree
	 */
	void enterCopy(CoBabyBoL.CopyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#copy}.
	 * @param ctx the parse tree
	 */
	void exitCopy(CoBabyBoL.CopyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#display}.
	 * @param ctx the parse tree
	 */
	void enterDisplay(CoBabyBoL.DisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#display}.
	 * @param ctx the parse tree
	 */
	void exitDisplay(CoBabyBoL.DisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(CoBabyBoL.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(CoBabyBoL.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(CoBabyBoL.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(CoBabyBoL.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#divide}.
	 * @param ctx the parse tree
	 */
	void enterDivide(CoBabyBoL.DivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#divide}.
	 * @param ctx the parse tree
	 */
	void exitDivide(CoBabyBoL.DivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(CoBabyBoL.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(CoBabyBoL.MoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(CoBabyBoL.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(CoBabyBoL.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#subtract}.
	 * @param ctx the parse tree
	 */
	void enterSubtract(CoBabyBoL.SubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#subtract}.
	 * @param ctx the parse tree
	 */
	void exitSubtract(CoBabyBoL.SubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(CoBabyBoL.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(CoBabyBoL.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#evaluate}.
	 * @param ctx the parse tree
	 */
	void enterEvaluate(CoBabyBoL.EvaluateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#evaluate}.
	 * @param ctx the parse tree
	 */
	void exitEvaluate(CoBabyBoL.EvaluateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#next_sentence}.
	 * @param ctx the parse tree
	 */
	void enterNext_sentence(CoBabyBoL.Next_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#next_sentence}.
	 * @param ctx the parse tree
	 */
	void exitNext_sentence(CoBabyBoL.Next_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#stop}.
	 * @param ctx the parse tree
	 */
	void enterStop(CoBabyBoL.StopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#stop}.
	 * @param ctx the parse tree
	 */
	void exitStop(CoBabyBoL.StopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#atomic_through}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_through(CoBabyBoL.Atomic_throughContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#atomic_through}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_through(CoBabyBoL.Atomic_throughContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#when_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhen_clause(CoBabyBoL.When_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#when_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhen_clause(CoBabyBoL.When_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#argument_literal}.
	 * @param ctx the parse tree
	 */
	void enterArgument_literal(CoBabyBoL.Argument_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#argument_literal}.
	 * @param ctx the parse tree
	 */
	void exitArgument_literal(CoBabyBoL.Argument_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#any_expression}.
	 * @param ctx the parse tree
	 */
	void enterAny_expression(CoBabyBoL.Any_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#any_expression}.
	 * @param ctx the parse tree
	 */
	void exitAny_expression(CoBabyBoL.Any_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#math_expr}.
	 * @param ctx the parse tree
	 */
	void enterMath_expr(CoBabyBoL.Math_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#math_expr}.
	 * @param ctx the parse tree
	 */
	void exitMath_expr(CoBabyBoL.Math_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(CoBabyBoL.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(CoBabyBoL.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#atomic}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(CoBabyBoL.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#atomic}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(CoBabyBoL.AtomicContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(CoBabyBoL.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(CoBabyBoL.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#file_name}.
	 * @param ctx the parse tree
	 */
	void enterFile_name(CoBabyBoL.File_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#file_name}.
	 * @param ctx the parse tree
	 */
	void exitFile_name(CoBabyBoL.File_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#procedure_name}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_name(CoBabyBoL.Procedure_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#procedure_name}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_name(CoBabyBoL.Procedure_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(CoBabyBoL.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(CoBabyBoL.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#program_name}.
	 * @param ctx the parse tree
	 */
	void enterProgram_name(CoBabyBoL.Program_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#program_name}.
	 * @param ctx the parse tree
	 */
	void exitProgram_name(CoBabyBoL.Program_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#level}.
	 * @param ctx the parse tree
	 */
	void enterLevel(CoBabyBoL.LevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#level}.
	 * @param ctx the parse tree
	 */
	void exitLevel(CoBabyBoL.LevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CoBabyBoL.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CoBabyBoL.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_literal(CoBabyBoL.Numeric_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_literal(CoBabyBoL.Numeric_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoL#alphanumeric_literal}.
	 * @param ctx the parse tree
	 */
	void enterAlphanumeric_literal(CoBabyBoL.Alphanumeric_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoL#alphanumeric_literal}.
	 * @param ctx the parse tree
	 */
	void exitAlphanumeric_literal(CoBabyBoL.Alphanumeric_literalContext ctx);
}