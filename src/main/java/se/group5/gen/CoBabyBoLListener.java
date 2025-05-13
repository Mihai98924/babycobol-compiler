// Generated from /home/extratoast/IdeaProjects/babycobol-compiler/src/main/antlr/se/group5/CoBabyBoL.g4 by ANTLR 4.13.2
package se.group5.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoBabyBoLParser}.
 */
public interface CoBabyBoLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoBabyBoLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoBabyBoLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#identification_division}.
	 * @param ctx the parse tree
	 */
	void enterIdentification_division(CoBabyBoLParser.Identification_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#identification_division}.
	 * @param ctx the parse tree
	 */
	void exitIdentification_division(CoBabyBoLParser.Identification_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#identification_clause}.
	 * @param ctx the parse tree
	 */
	void enterIdentification_clause(CoBabyBoLParser.Identification_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#identification_clause}.
	 * @param ctx the parse tree
	 */
	void exitIdentification_clause(CoBabyBoLParser.Identification_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#clause_name}.
	 * @param ctx the parse tree
	 */
	void enterClause_name(CoBabyBoLParser.Clause_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#clause_name}.
	 * @param ctx the parse tree
	 */
	void exitClause_name(CoBabyBoLParser.Clause_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#clause_value}.
	 * @param ctx the parse tree
	 */
	void enterClause_value(CoBabyBoLParser.Clause_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#clause_value}.
	 * @param ctx the parse tree
	 */
	void exitClause_value(CoBabyBoLParser.Clause_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#procedure_division}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_division(CoBabyBoLParser.Procedure_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#procedure_division}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_division(CoBabyBoLParser.Procedure_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(CoBabyBoLParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(CoBabyBoLParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CoBabyBoLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CoBabyBoLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#data_division}.
	 * @param ctx the parse tree
	 */
	void enterData_division(CoBabyBoLParser.Data_divisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#data_division}.
	 * @param ctx the parse tree
	 */
	void exitData_division(CoBabyBoLParser.Data_divisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#data_item}.
	 * @param ctx the parse tree
	 */
	void enterData_item(CoBabyBoLParser.Data_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#data_item}.
	 * @param ctx the parse tree
	 */
	void exitData_item(CoBabyBoLParser.Data_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#picture_clause}.
	 * @param ctx the parse tree
	 */
	void enterPicture_clause(CoBabyBoLParser.Picture_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#picture_clause}.
	 * @param ctx the parse tree
	 */
	void exitPicture_clause(CoBabyBoLParser.Picture_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#like_clause}.
	 * @param ctx the parse tree
	 */
	void enterLike_clause(CoBabyBoLParser.Like_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#like_clause}.
	 * @param ctx the parse tree
	 */
	void exitLike_clause(CoBabyBoLParser.Like_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#occurs_clause}.
	 * @param ctx the parse tree
	 */
	void enterOccurs_clause(CoBabyBoLParser.Occurs_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#occurs_clause}.
	 * @param ctx the parse tree
	 */
	void exitOccurs_clause(CoBabyBoLParser.Occurs_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(CoBabyBoLParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(CoBabyBoLParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#accept}.
	 * @param ctx the parse tree
	 */
	void enterAccept(CoBabyBoLParser.AcceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#accept}.
	 * @param ctx the parse tree
	 */
	void exitAccept(CoBabyBoLParser.AcceptContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlter(CoBabyBoLParser.AlterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlter(CoBabyBoLParser.AlterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#goto}.
	 * @param ctx the parse tree
	 */
	void enterGoto(CoBabyBoLParser.GotoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#goto}.
	 * @param ctx the parse tree
	 */
	void exitGoto(CoBabyBoLParser.GotoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(CoBabyBoLParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(CoBabyBoLParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#perform}.
	 * @param ctx the parse tree
	 */
	void enterPerform(CoBabyBoLParser.PerformContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#perform}.
	 * @param ctx the parse tree
	 */
	void exitPerform(CoBabyBoLParser.PerformContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#signal}.
	 * @param ctx the parse tree
	 */
	void enterSignal(CoBabyBoLParser.SignalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#signal}.
	 * @param ctx the parse tree
	 */
	void exitSignal(CoBabyBoLParser.SignalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#copy}.
	 * @param ctx the parse tree
	 */
	void enterCopy(CoBabyBoLParser.CopyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#copy}.
	 * @param ctx the parse tree
	 */
	void exitCopy(CoBabyBoLParser.CopyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#display}.
	 * @param ctx the parse tree
	 */
	void enterDisplay(CoBabyBoLParser.DisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#display}.
	 * @param ctx the parse tree
	 */
	void exitDisplay(CoBabyBoLParser.DisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(CoBabyBoLParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(CoBabyBoLParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(CoBabyBoLParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(CoBabyBoLParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#divide}.
	 * @param ctx the parse tree
	 */
	void enterDivide(CoBabyBoLParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#divide}.
	 * @param ctx the parse tree
	 */
	void exitDivide(CoBabyBoLParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(CoBabyBoLParser.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(CoBabyBoLParser.MoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(CoBabyBoLParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(CoBabyBoLParser.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#subtract}.
	 * @param ctx the parse tree
	 */
	void enterSubtract(CoBabyBoLParser.SubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#subtract}.
	 * @param ctx the parse tree
	 */
	void exitSubtract(CoBabyBoLParser.SubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(CoBabyBoLParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(CoBabyBoLParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#evaluate}.
	 * @param ctx the parse tree
	 */
	void enterEvaluate(CoBabyBoLParser.EvaluateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#evaluate}.
	 * @param ctx the parse tree
	 */
	void exitEvaluate(CoBabyBoLParser.EvaluateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#next_sentence}.
	 * @param ctx the parse tree
	 */
	void enterNext_sentence(CoBabyBoLParser.Next_sentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#next_sentence}.
	 * @param ctx the parse tree
	 */
	void exitNext_sentence(CoBabyBoLParser.Next_sentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#stop}.
	 * @param ctx the parse tree
	 */
	void enterStop(CoBabyBoLParser.StopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#stop}.
	 * @param ctx the parse tree
	 */
	void exitStop(CoBabyBoLParser.StopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#atomic_through}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_through(CoBabyBoLParser.Atomic_throughContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#atomic_through}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_through(CoBabyBoLParser.Atomic_throughContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#when_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhen_clause(CoBabyBoLParser.When_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#when_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhen_clause(CoBabyBoLParser.When_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#argument_literal}.
	 * @param ctx the parse tree
	 */
	void enterArgument_literal(CoBabyBoLParser.Argument_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#argument_literal}.
	 * @param ctx the parse tree
	 */
	void exitArgument_literal(CoBabyBoLParser.Argument_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#any_expression}.
	 * @param ctx the parse tree
	 */
	void enterAny_expression(CoBabyBoLParser.Any_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#any_expression}.
	 * @param ctx the parse tree
	 */
	void exitAny_expression(CoBabyBoLParser.Any_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#math_expr}.
	 * @param ctx the parse tree
	 */
	void enterMath_expr(CoBabyBoLParser.Math_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#math_expr}.
	 * @param ctx the parse tree
	 */
	void exitMath_expr(CoBabyBoLParser.Math_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(CoBabyBoLParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(CoBabyBoLParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(CoBabyBoLParser.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(CoBabyBoLParser.AtomicContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#file_name}.
	 * @param ctx the parse tree
	 */
	void enterFile_name(CoBabyBoLParser.File_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#file_name}.
	 * @param ctx the parse tree
	 */
	void exitFile_name(CoBabyBoLParser.File_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#procedure_name}.
	 * @param ctx the parse tree
	 */
	void enterProcedure_name(CoBabyBoLParser.Procedure_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#procedure_name}.
	 * @param ctx the parse tree
	 */
	void exitProcedure_name(CoBabyBoLParser.Procedure_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(CoBabyBoLParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(CoBabyBoLParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#program_name}.
	 * @param ctx the parse tree
	 */
	void enterProgram_name(CoBabyBoLParser.Program_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#program_name}.
	 * @param ctx the parse tree
	 */
	void exitProgram_name(CoBabyBoLParser.Program_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#representation}.
	 * @param ctx the parse tree
	 */
	void enterRepresentation(CoBabyBoLParser.RepresentationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#representation}.
	 * @param ctx the parse tree
	 */
	void exitRepresentation(CoBabyBoLParser.RepresentationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#level}.
	 * @param ctx the parse tree
	 */
	void enterLevel(CoBabyBoLParser.LevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#level}.
	 * @param ctx the parse tree
	 */
	void exitLevel(CoBabyBoLParser.LevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_literal(CoBabyBoLParser.Numeric_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_literal(CoBabyBoLParser.Numeric_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void enterInteger_literal(CoBabyBoLParser.Integer_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void exitInteger_literal(CoBabyBoLParser.Integer_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoBabyBoLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CoBabyBoLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoBabyBoLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CoBabyBoLParser.LiteralContext ctx);
}