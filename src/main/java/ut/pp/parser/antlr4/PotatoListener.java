// Generated from /home/extratoast/IdeaProjects/PP-Project3/src/antlr4/ut/pp/grammar/Potato.g4 by ANTLR 4.10.1
package ut.pp.parser.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PotatoParser}.
 */
public interface PotatoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PotatoParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PotatoParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PotatoParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PotatoParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PotatoParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(PotatoParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(PotatoParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveDeclaration(PotatoParser.PrimitiveDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveDeclaration(PotatoParser.PrimitiveDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterStringDeclaration(PotatoParser.StringDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitStringDeclaration(PotatoParser.StringDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lockDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterLockDeclaration(PotatoParser.LockDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lockDeclaration}
	 * labeled alternative in {@link PotatoParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitLockDeclaration(PotatoParser.LockDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#synchronizedBlock}.
	 * @param ctx the parse tree
	 */
	void enterSynchronizedBlock(PotatoParser.SynchronizedBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#synchronizedBlock}.
	 * @param ctx the parse tree
	 */
	void exitSynchronizedBlock(PotatoParser.SynchronizedBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignVariable}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignVariable(PotatoParser.AssignVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignVariable}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignVariable(PotatoParser.AssignVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignArrayElement}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignArrayElement(PotatoParser.AssignArrayElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignArrayElement}
	 * labeled alternative in {@link PotatoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignArrayElement(PotatoParser.AssignArrayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(PotatoParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(PotatoParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#while}.
	 * @param ctx the parse tree
	 */
	void enterWhile(PotatoParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#while}.
	 * @param ctx the parse tree
	 */
	void exitWhile(PotatoParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(PotatoParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(PotatoParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#fork}.
	 * @param ctx the parse tree
	 */
	void enterFork(PotatoParser.ForkContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#fork}.
	 * @param ctx the parse tree
	 */
	void exitFork(PotatoParser.ForkContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#parallelScope}.
	 * @param ctx the parse tree
	 */
	void enterParallelScope(PotatoParser.ParallelScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#parallelScope}.
	 * @param ctx the parse tree
	 */
	void exitParallelScope(PotatoParser.ParallelScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(PotatoParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(PotatoParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(PotatoParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(PotatoParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLevel1}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLevel1(PotatoParser.ExprLevel1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLevel1}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLevel1(PotatoParser.ExprLevel1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(PotatoParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link PotatoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(PotatoParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterLessThanEquals(PotatoParser.LessThanEqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitLessThanEquals(PotatoParser.LessThanEqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterEquals(PotatoParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitEquals(PotatoParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterLessThan(PotatoParser.LessThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitLessThan(PotatoParser.LessThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterNotEquals(PotatoParser.NotEqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitNotEquals(PotatoParser.NotEqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLevel2}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterExprLevel2(PotatoParser.ExprLevel2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLevel2}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitExprLevel2(PotatoParser.ExprLevel2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThanEquals(PotatoParser.GreaterThanEqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterThanEquals}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThanEquals(PotatoParser.GreaterThanEqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(PotatoParser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link PotatoParser#expression_l1}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(PotatoParser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void enterSubtraction(PotatoParser.SubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void exitSubtraction(PotatoParser.SubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLevel3}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void enterExprLevel3(PotatoParser.ExprLevel3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLevel3}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void exitExprLevel3(PotatoParser.ExprLevel3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code addition}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void enterAddition(PotatoParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link PotatoParser#expression_l2}.
	 * @param ctx the parse tree
	 */
	void exitAddition(PotatoParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(PotatoParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(PotatoParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLevel4}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void enterExprLevel4(PotatoParser.ExprLevel4Context ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLevel4}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void exitExprLevel4(PotatoParser.ExprLevel4Context ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(PotatoParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link PotatoParser#expression_l3}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(PotatoParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLevel5}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void enterExprLevel5(PotatoParser.ExprLevel5Context ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLevel5}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void exitExprLevel5(PotatoParser.ExprLevel5Context ctx);
	/**
	 * Enter a parse tree produced by the {@code negation}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void enterNegation(PotatoParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void exitNegation(PotatoParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicNot}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void enterLogicNot(PotatoParser.LogicNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicNot}
	 * labeled alternative in {@link PotatoParser#expression_l4}.
	 * @param ctx the parse tree
	 */
	void exitLogicNot(PotatoParser.LogicNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalValue}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void enterLiteralValue(PotatoParser.LiteralValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalValue}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void exitLiteralValue(PotatoParser.LiteralValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableCall}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void enterVariableCall(PotatoParser.VariableCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableCall}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void exitVariableCall(PotatoParser.VariableCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userInput}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void enterUserInput(PotatoParser.UserInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userInput}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void exitUserInput(PotatoParser.UserInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(PotatoParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(PotatoParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code length}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void enterLength(PotatoParser.LengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code length}
	 * labeled alternative in {@link PotatoParser#expression_l5}.
	 * @param ctx the parse tree
	 */
	void exitLength(PotatoParser.LengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(PotatoParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(PotatoParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(PotatoParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(PotatoParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(PotatoParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(PotatoParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterCharType(PotatoParser.CharTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link PotatoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitCharType(PotatoParser.CharTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(PotatoParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(PotatoParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariableAccess(PotatoParser.VariableAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableAccess}
	 * labeled alternative in {@link PotatoParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariableAccess(PotatoParser.VariableAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterDecIntLiteral(PotatoParser.DecIntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitDecIntLiteral(PotatoParser.DecIntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBinIntLiteral(PotatoParser.BinIntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBinIntLiteral(PotatoParser.BinIntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hexIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterHexIntLiteral(PotatoParser.HexIntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hexIntLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitHexIntLiteral(PotatoParser.HexIntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(PotatoParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(PotatoParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteral(PotatoParser.CharLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteral(PotatoParser.CharLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(PotatoParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link PotatoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(PotatoParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLiteral}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(PotatoParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLiteral}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(PotatoParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newArray}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void enterNewArray(PotatoParser.NewArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newArray}
	 * labeled alternative in {@link PotatoParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void exitNewArray(PotatoParser.NewArrayContext ctx);
}