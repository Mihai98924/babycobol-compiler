// Generated from /home/extratoast/IdeaProjects/PP-Project3/src/antlr4/ut/pp/grammar/Potato.g4 by ANTLR 4.10.1
package ut.pp.parser.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PotatoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, SHARED=24, 
		IF=25, ELSE=26, WHILE=27, PRINT=28, PRINT_NL=29, INPUT=30, FORK=31, JOIN=32, 
		NEW=33, LOCK=34, SYNC=35, LENGTH=36, DEC_INT_LITERAL=37, BIN_INT_LITERAL=38, 
		HEX_INT_LITERAL=39, BOOL_LITERAL=40, CHAR_LITERAL=41, STRING_LITERAL=42, 
		TYPE_INT=43, TYPE_BOOL=44, TYPE_CHAR=45, TYPE_STRING=46, IDENTIFIER=47, 
		COMMENT=48, WHITESPACE=49;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_declaration = 2, RULE_synchronizedBlock = 3, 
		RULE_assignment = 4, RULE_if = 5, RULE_while = 6, RULE_scope = 7, RULE_fork = 8, 
		RULE_parallelScope = 9, RULE_print = 10, RULE_expression = 11, RULE_expression_l1 = 12, 
		RULE_expression_l2 = 13, RULE_expression_l3 = 14, RULE_expression_l4 = 15, 
		RULE_expression_l5 = 16, RULE_input = 17, RULE_type = 18, RULE_variable = 19, 
		RULE_literal = 20, RULE_array_literal = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "declaration", "synchronizedBlock", "assignment", 
			"if", "while", "scope", "fork", "parallelScope", "print", "expression", 
			"expression_l1", "expression_l2", "expression_l3", "expression_l4", "expression_l5", 
			"input", "type", "variable", "literal", "array_literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'[]'", "'='", "'['", "']'", "'('", "')'", "'{'", "'}'", 
			"'&&'", "'||'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", 
			"'-'", "'*'", "'++'", "'!'", "','", "'shared'", "'if'", "'else'", "'while'", 
			"'print'", "'printNL'", "'userInput'", "'fork'", "'join'", "'new'", "'lock'", 
			"'sync'", "'length'", null, null, null, null, null, null, "'int'", "'bool'", 
			"'char'", "'string'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"SHARED", "IF", "ELSE", "WHILE", "PRINT", "PRINT_NL", "INPUT", "FORK", 
			"JOIN", "NEW", "LOCK", "SYNC", "LENGTH", "DEC_INT_LITERAL", "BIN_INT_LITERAL", 
			"HEX_INT_LITERAL", "BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", 
			"TYPE_INT", "TYPE_BOOL", "TYPE_CHAR", "TYPE_STRING", "IDENTIFIER", "COMMENT", 
			"WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Potato.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PotatoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PotatoParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << SHARED) | (1L << IF) | (1L << WHILE) | (1L << PRINT) | (1L << PRINT_NL) | (1L << FORK) | (1L << LOCK) | (1L << SYNC) | (1L << TYPE_INT) | (1L << TYPE_BOOL) | (1L << TYPE_CHAR) | (1L << TYPE_STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(44);
				statement();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public WhileContext while_() {
			return getRuleContext(WhileContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public ForkContext fork() {
			return getRuleContext(ForkContext.class,0);
		}
		public SynchronizedBlockContext synchronizedBlock() {
			return getRuleContext(SynchronizedBlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SHARED:
			case LOCK:
			case TYPE_INT:
			case TYPE_BOOL:
			case TYPE_CHAR:
			case TYPE_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				declaration();
				setState(53);
				match(T__0);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				assignment();
				setState(56);
				match(T__0);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				if_();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				while_();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				scope();
				}
				break;
			case PRINT:
			case PRINT_NL:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				print();
				setState(62);
				match(T__0);
				}
				break;
			case FORK:
				enterOuterAlt(_localctx, 7);
				{
				setState(64);
				fork();
				setState(65);
				match(T__0);
				}
				break;
			case SYNC:
				enterOuterAlt(_localctx, 8);
				{
				setState(67);
				synchronizedBlock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringDeclarationContext extends DeclarationContext {
		public TerminalNode TYPE_STRING() { return getToken(PotatoParser.TYPE_STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SHARED() { return getToken(PotatoParser.SHARED, 0); }
		public StringDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterStringDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitStringDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitStringDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockDeclarationContext extends DeclarationContext {
		public TerminalNode LOCK() { return getToken(PotatoParser.LOCK, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public LockDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLockDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLockDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLockDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveDeclarationContext extends DeclarationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SHARED() { return getToken(PotatoParser.SHARED, 0); }
		public PrimitiveDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterPrimitiveDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitPrimitiveDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitPrimitiveDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayDeclarationContext extends DeclarationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_literalContext array_literal() {
			return getRuleContext(Array_literalContext.class,0);
		}
		public TerminalNode SHARED() { return getToken(PotatoParser.SHARED, 0); }
		public ArrayDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterArrayDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitArrayDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitArrayDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		int _la;
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new ArrayDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARED) {
					{
					setState(70);
					match(SHARED);
					}
				}

				setState(73);
				type();
				setState(74);
				match(T__1);
				setState(75);
				match(IDENTIFIER);
				setState(76);
				match(T__2);
				setState(79);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
				case T__18:
				case T__21:
				case INPUT:
				case LENGTH:
				case DEC_INT_LITERAL:
				case BIN_INT_LITERAL:
				case HEX_INT_LITERAL:
				case BOOL_LITERAL:
				case CHAR_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
					{
					setState(77);
					expression(0);
					}
					break;
				case T__3:
				case NEW:
					{
					setState(78);
					array_literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				_localctx = new PrimitiveDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARED) {
					{
					setState(81);
					match(SHARED);
					}
				}

				setState(84);
				type();
				setState(85);
				match(IDENTIFIER);
				setState(86);
				match(T__2);
				setState(87);
				expression(0);
				}
				break;
			case 3:
				_localctx = new StringDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARED) {
					{
					setState(89);
					match(SHARED);
					}
				}

				setState(92);
				match(TYPE_STRING);
				setState(93);
				match(IDENTIFIER);
				setState(94);
				match(T__2);
				setState(95);
				expression(0);
				}
				break;
			case 4:
				_localctx = new LockDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				match(LOCK);
				setState(97);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SynchronizedBlockContext extends ParserRuleContext {
		public TerminalNode SYNC() { return getToken(PotatoParser.SYNC, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public SynchronizedBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synchronizedBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterSynchronizedBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitSynchronizedBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitSynchronizedBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynchronizedBlockContext synchronizedBlock() throws RecognitionException {
		SynchronizedBlockContext _localctx = new SynchronizedBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_synchronizedBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(SYNC);
			setState(101);
			match(IDENTIFIER);
			setState(102);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	 
		public AssignmentContext() { }
		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignArrayElementContext extends AssignmentContext {
		public ExpressionContext index;
		public ExpressionContext expr;
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignArrayElementContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterAssignArrayElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitAssignArrayElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitAssignArrayElement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignVariableContext extends AssignmentContext {
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_literalContext array_literal() {
			return getRuleContext(Array_literalContext.class,0);
		}
		public AssignVariableContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterAssignVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitAssignVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitAssignVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new AssignVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(IDENTIFIER);
				setState(105);
				match(T__2);
				setState(108);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
				case T__18:
				case T__21:
				case INPUT:
				case LENGTH:
				case DEC_INT_LITERAL:
				case BIN_INT_LITERAL:
				case HEX_INT_LITERAL:
				case BOOL_LITERAL:
				case CHAR_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
					{
					setState(106);
					expression(0);
					}
					break;
				case T__3:
				case NEW:
					{
					setState(107);
					array_literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				_localctx = new AssignArrayElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(IDENTIFIER);
				setState(111);
				match(T__3);
				setState(112);
				((AssignArrayElementContext)_localctx).index = expression(0);
				setState(113);
				match(T__4);
				setState(114);
				match(T__2);
				setState(115);
				((AssignArrayElementContext)_localctx).expr = expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfContext extends ParserRuleContext {
		public ScopeContext trueScope;
		public ScopeContext elseScope;
		public TerminalNode IF() { return getToken(PotatoParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ScopeContext> scope() {
			return getRuleContexts(ScopeContext.class);
		}
		public ScopeContext scope(int i) {
			return getRuleContext(ScopeContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(PotatoParser.ELSE, 0); }
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(IF);
			setState(120);
			match(T__5);
			setState(121);
			expression(0);
			setState(122);
			match(T__6);
			setState(123);
			((IfContext)_localctx).trueScope = scope();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(124);
				match(ELSE);
				setState(125);
				((IfContext)_localctx).elseScope = scope();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(PotatoParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public WhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileContext while_() throws RecognitionException {
		WhileContext _localctx = new WhileContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_while);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(WHILE);
			setState(129);
			match(T__5);
			setState(130);
			expression(0);
			setState(131);
			match(T__6);
			setState(132);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__7);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << SHARED) | (1L << IF) | (1L << WHILE) | (1L << PRINT) | (1L << PRINT_NL) | (1L << FORK) | (1L << LOCK) | (1L << SYNC) | (1L << TYPE_INT) | (1L << TYPE_BOOL) | (1L << TYPE_CHAR) | (1L << TYPE_STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(135);
				statement();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForkContext extends ParserRuleContext {
		public TerminalNode FORK() { return getToken(PotatoParser.FORK, 0); }
		public ParallelScopeContext parallelScope() {
			return getRuleContext(ParallelScopeContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(PotatoParser.JOIN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ForkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fork; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterFork(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitFork(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitFork(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForkContext fork() throws RecognitionException {
		ForkContext _localctx = new ForkContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fork);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(FORK);
			setState(144);
			parallelScope();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << SHARED) | (1L << IF) | (1L << WHILE) | (1L << PRINT) | (1L << PRINT_NL) | (1L << FORK) | (1L << LOCK) | (1L << SYNC) | (1L << TYPE_INT) | (1L << TYPE_BOOL) | (1L << TYPE_CHAR) | (1L << TYPE_STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(145);
				statement();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			match(JOIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParallelScopeContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ParallelScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parallelScope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterParallelScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitParallelScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitParallelScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParallelScopeContext parallelScope() throws RecognitionException {
		ParallelScopeContext _localctx = new ParallelScopeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parallelScope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__7);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << SHARED) | (1L << IF) | (1L << WHILE) | (1L << PRINT) | (1L << PRINT_NL) | (1L << FORK) | (1L << LOCK) | (1L << SYNC) | (1L << TYPE_INT) | (1L << TYPE_BOOL) | (1L << TYPE_CHAR) | (1L << TYPE_STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(154);
				statement();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(160);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(PotatoParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PRINT_NL() { return getToken(PotatoParser.PRINT_NL, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_print);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(PRINT);
				setState(163);
				expression(0);
				}
				break;
			case PRINT_NL:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(PRINT_NL);
				setState(165);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LogicalAndContext extends ExpressionContext {
		public ExpressionContext left;
		public Expression_l1Context right;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public LogicalAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLevel1Context extends ExpressionContext {
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public ExprLevel1Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterExprLevel1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitExprLevel1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitExprLevel1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalOrContext extends ExpressionContext {
		public ExpressionContext left;
		public Expression_l1Context right;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public LogicalOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprLevel1Context(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(169);
			expression_l1(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(177);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new LogicalAndContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalAndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(172);
						match(T__9);
						setState(173);
						((LogicalAndContext)_localctx).right = expression_l1(0);
						}
						break;
					case 2:
						{
						_localctx = new LogicalOrContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalOrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(175);
						match(T__10);
						setState(176);
						((LogicalOrContext)_localctx).right = expression_l1(0);
						}
						break;
					}
					} 
				}
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression_l1Context extends ParserRuleContext {
		public Expression_l1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_l1; }
	 
		public Expression_l1Context() { }
		public void copyFrom(Expression_l1Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LessThanEqualsContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public LessThanEqualsContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLessThanEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLessThanEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLessThanEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualsContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public EqualsContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThanContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public LessThanContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLessThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLessThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLessThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotEqualsContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public NotEqualsContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterNotEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitNotEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitNotEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLevel2Context extends Expression_l1Context {
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public ExprLevel2Context(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterExprLevel2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitExprLevel2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitExprLevel2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThanEqualsContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public GreaterThanEqualsContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterGreaterThanEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitGreaterThanEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitGreaterThanEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThanContext extends Expression_l1Context {
		public Expression_l1Context left;
		public Expression_l2Context right;
		public Expression_l1Context expression_l1() {
			return getRuleContext(Expression_l1Context.class,0);
		}
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public GreaterThanContext(Expression_l1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterGreaterThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitGreaterThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitGreaterThan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_l1Context expression_l1() throws RecognitionException {
		return expression_l1(0);
	}

	private Expression_l1Context expression_l1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_l1Context _localctx = new Expression_l1Context(_ctx, _parentState);
		Expression_l1Context _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression_l1, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprLevel2Context(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(183);
			expression_l2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(203);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new EqualsContext(new Expression_l1Context(_parentctx, _parentState));
						((EqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(185);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(186);
						match(T__11);
						setState(187);
						((EqualsContext)_localctx).right = expression_l2(0);
						}
						break;
					case 2:
						{
						_localctx = new NotEqualsContext(new Expression_l1Context(_parentctx, _parentState));
						((NotEqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(188);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(189);
						match(T__12);
						setState(190);
						((NotEqualsContext)_localctx).right = expression_l2(0);
						}
						break;
					case 3:
						{
						_localctx = new LessThanContext(new Expression_l1Context(_parentctx, _parentState));
						((LessThanContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(191);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(192);
						match(T__13);
						setState(193);
						((LessThanContext)_localctx).right = expression_l2(0);
						}
						break;
					case 4:
						{
						_localctx = new LessThanEqualsContext(new Expression_l1Context(_parentctx, _parentState));
						((LessThanEqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(194);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(195);
						match(T__14);
						setState(196);
						((LessThanEqualsContext)_localctx).right = expression_l2(0);
						}
						break;
					case 5:
						{
						_localctx = new GreaterThanContext(new Expression_l1Context(_parentctx, _parentState));
						((GreaterThanContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(197);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(198);
						match(T__15);
						setState(199);
						((GreaterThanContext)_localctx).right = expression_l2(0);
						}
						break;
					case 6:
						{
						_localctx = new GreaterThanEqualsContext(new Expression_l1Context(_parentctx, _parentState));
						((GreaterThanEqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l1);
						setState(200);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(201);
						match(T__16);
						setState(202);
						((GreaterThanEqualsContext)_localctx).right = expression_l2(0);
						}
						break;
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression_l2Context extends ParserRuleContext {
		public Expression_l2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_l2; }
	 
		public Expression_l2Context() { }
		public void copyFrom(Expression_l2Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SubtractionContext extends Expression_l2Context {
		public Expression_l2Context left;
		public Expression_l3Context right;
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public SubtractionContext(Expression_l2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitSubtraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLevel3Context extends Expression_l2Context {
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public ExprLevel3Context(Expression_l2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterExprLevel3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitExprLevel3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitExprLevel3(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditionContext extends Expression_l2Context {
		public Expression_l2Context left;
		public Expression_l3Context right;
		public Expression_l2Context expression_l2() {
			return getRuleContext(Expression_l2Context.class,0);
		}
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public AdditionContext(Expression_l2Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitAddition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_l2Context expression_l2() throws RecognitionException {
		return expression_l2(0);
	}

	private Expression_l2Context expression_l2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_l2Context _localctx = new Expression_l2Context(_ctx, _parentState);
		Expression_l2Context _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expression_l2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprLevel3Context(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(209);
			expression_l3(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(217);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new AdditionContext(new Expression_l2Context(_parentctx, _parentState));
						((AdditionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l2);
						setState(211);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(212);
						match(T__17);
						setState(213);
						((AdditionContext)_localctx).right = expression_l3(0);
						}
						break;
					case 2:
						{
						_localctx = new SubtractionContext(new Expression_l2Context(_parentctx, _parentState));
						((SubtractionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_l2);
						setState(214);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(215);
						match(T__18);
						setState(216);
						((SubtractionContext)_localctx).right = expression_l3(0);
						}
						break;
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression_l3Context extends ParserRuleContext {
		public Expression_l3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_l3; }
	 
		public Expression_l3Context() { }
		public void copyFrom(Expression_l3Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConcatenationContext extends Expression_l3Context {
		public Expression_l4Context left;
		public Expression_l3Context right;
		public Expression_l4Context expression_l4() {
			return getRuleContext(Expression_l4Context.class,0);
		}
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public ConcatenationContext(Expression_l3Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterConcatenation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitConcatenation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitConcatenation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLevel4Context extends Expression_l3Context {
		public Expression_l4Context expression_l4() {
			return getRuleContext(Expression_l4Context.class,0);
		}
		public ExprLevel4Context(Expression_l3Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterExprLevel4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitExprLevel4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitExprLevel4(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicationContext extends Expression_l3Context {
		public Expression_l3Context left;
		public Expression_l4Context right;
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public Expression_l4Context expression_l4() {
			return getRuleContext(Expression_l4Context.class,0);
		}
		public MultiplicationContext(Expression_l3Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitMultiplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitMultiplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_l3Context expression_l3() throws RecognitionException {
		return expression_l3(0);
	}

	private Expression_l3Context expression_l3(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_l3Context _localctx = new Expression_l3Context(_ctx, _parentState);
		Expression_l3Context _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expression_l3, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLevel4Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(223);
				expression_l4();
				}
				break;
			case 2:
				{
				_localctx = new ConcatenationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				((ConcatenationContext)_localctx).left = expression_l4();
				setState(225);
				match(T__20);
				setState(226);
				((ConcatenationContext)_localctx).right = expression_l3(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiplicationContext(new Expression_l3Context(_parentctx, _parentState));
					((MultiplicationContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expression_l3);
					setState(230);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(231);
					match(T__19);
					setState(232);
					((MultiplicationContext)_localctx).right = expression_l4();
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expression_l4Context extends ParserRuleContext {
		public Expression_l4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_l4; }
	 
		public Expression_l4Context() { }
		public void copyFrom(Expression_l4Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NegationContext extends Expression_l4Context {
		public Expression_l5Context expression_l5() {
			return getRuleContext(Expression_l5Context.class,0);
		}
		public NegationContext(Expression_l4Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLevel5Context extends Expression_l4Context {
		public Expression_l5Context expression_l5() {
			return getRuleContext(Expression_l5Context.class,0);
		}
		public ExprLevel5Context(Expression_l4Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterExprLevel5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitExprLevel5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitExprLevel5(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicNotContext extends Expression_l4Context {
		public Expression_l5Context expression_l5() {
			return getRuleContext(Expression_l5Context.class,0);
		}
		public LogicNotContext(Expression_l4Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLogicNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLogicNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLogicNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_l4Context expression_l4() throws RecognitionException {
		Expression_l4Context _localctx = new Expression_l4Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression_l4);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case INPUT:
			case LENGTH:
			case DEC_INT_LITERAL:
			case BIN_INT_LITERAL:
			case HEX_INT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
				_localctx = new ExprLevel5Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				expression_l5();
				}
				break;
			case T__18:
				_localctx = new NegationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__18);
				setState(240);
				expression_l5();
				}
				break;
			case T__21:
				_localctx = new LogicNotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(T__21);
				setState(242);
				expression_l5();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_l5Context extends ParserRuleContext {
		public Expression_l5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_l5; }
	 
		public Expression_l5Context() { }
		public void copyFrom(Expression_l5Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableCallContext extends Expression_l5Context {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableCallContext(Expression_l5Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterVariableCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitVariableCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitVariableCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LengthContext extends Expression_l5Context {
		public Expression_l3Context arrayExpression;
		public TerminalNode LENGTH() { return getToken(PotatoParser.LENGTH, 0); }
		public Expression_l3Context expression_l3() {
			return getRuleContext(Expression_l3Context.class,0);
		}
		public LengthContext(Expression_l5Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLength(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLength(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UserInputContext extends Expression_l5Context {
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public UserInputContext(Expression_l5Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterUserInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitUserInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitUserInput(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisContext extends Expression_l5Context {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisContext(Expression_l5Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralValueContext extends Expression_l5Context {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralValueContext(Expression_l5Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterLiteralValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitLiteralValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitLiteralValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_l5Context expression_l5() throws RecognitionException {
		Expression_l5Context _localctx = new Expression_l5Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression_l5);
		try {
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEC_INT_LITERAL:
			case BIN_INT_LITERAL:
			case HEX_INT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
				_localctx = new LiteralValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				literal();
				}
				break;
			case IDENTIFIER:
				_localctx = new VariableCallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				variable();
				}
				break;
			case INPUT:
				_localctx = new UserInputContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
				input();
				}
				break;
			case T__5:
				_localctx = new ParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(248);
				match(T__5);
				setState(249);
				expression(0);
				setState(250);
				match(T__6);
				}
				break;
			case LENGTH:
				_localctx = new LengthContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(252);
				match(LENGTH);
				setState(253);
				((LengthContext)_localctx).arrayExpression = expression_l3(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public Token request;
		public TerminalNode INPUT() { return getToken(PotatoParser.INPUT, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(PotatoParser.STRING_LITERAL, 0); }
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_input);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(INPUT);
			setState(257);
			((InputContext)_localctx).request = match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharTypeContext extends TypeContext {
		public TerminalNode TYPE_CHAR() { return getToken(PotatoParser.TYPE_CHAR, 0); }
		public CharTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterCharType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitCharType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitCharType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public TerminalNode TYPE_INT() { return getToken(PotatoParser.TYPE_INT, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitIntType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolTypeContext extends TypeContext {
		public TerminalNode TYPE_BOOL() { return getToken(PotatoParser.TYPE_BOOL, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitBoolType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type);
		try {
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_INT:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(TYPE_INT);
				}
				break;
			case TYPE_BOOL:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(TYPE_BOOL);
				}
				break;
			case TYPE_CHAR:
				_localctx = new CharTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(261);
				match(TYPE_CHAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	 
		public VariableContext() { }
		public void copyFrom(VariableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableAccessContext extends VariableContext {
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public VariableAccessContext(VariableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterVariableAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitVariableAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitVariableAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayAccessContext extends VariableContext {
		public TerminalNode IDENTIFIER() { return getToken(PotatoParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayAccessContext(VariableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variable);
		try {
			setState(270);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				_localctx = new ArrayAccessContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				match(IDENTIFIER);
				setState(265);
				match(T__3);
				setState(266);
				expression(0);
				setState(267);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new VariableAccessContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinIntLiteralContext extends LiteralContext {
		public TerminalNode BIN_INT_LITERAL() { return getToken(PotatoParser.BIN_INT_LITERAL, 0); }
		public BinIntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterBinIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitBinIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitBinIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HexIntLiteralContext extends LiteralContext {
		public TerminalNode HEX_INT_LITERAL() { return getToken(PotatoParser.HEX_INT_LITERAL, 0); }
		public HexIntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterHexIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitHexIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitHexIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING_LITERAL() { return getToken(PotatoParser.STRING_LITERAL, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLiteralContext extends LiteralContext {
		public TerminalNode CHAR_LITERAL() { return getToken(PotatoParser.CHAR_LITERAL, 0); }
		public CharLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterCharLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitCharLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitCharLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DecIntLiteralContext extends LiteralContext {
		public TerminalNode DEC_INT_LITERAL() { return getToken(PotatoParser.DEC_INT_LITERAL, 0); }
		public DecIntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterDecIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitDecIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitDecIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLiteralContext extends LiteralContext {
		public TerminalNode BOOL_LITERAL() { return getToken(PotatoParser.BOOL_LITERAL, 0); }
		public BoolLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		try {
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEC_INT_LITERAL:
				_localctx = new DecIntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				match(DEC_INT_LITERAL);
				}
				break;
			case BIN_INT_LITERAL:
				_localctx = new BinIntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(BIN_INT_LITERAL);
				}
				break;
			case HEX_INT_LITERAL:
				_localctx = new HexIntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				match(HEX_INT_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				match(BOOL_LITERAL);
				}
				break;
			case CHAR_LITERAL:
				_localctx = new CharLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				match(CHAR_LITERAL);
				}
				break;
			case STRING_LITERAL:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(277);
				match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_literalContext extends ParserRuleContext {
		public Array_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_literal; }
	 
		public Array_literalContext() { }
		public void copyFrom(Array_literalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayLiteralContext extends Array_literalContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayLiteralContext(Array_literalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewArrayContext extends Array_literalContext {
		public TerminalNode NEW() { return getToken(PotatoParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NewArrayContext(Array_literalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).enterNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PotatoListener ) ((PotatoListener)listener).exitNewArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PotatoVisitor ) return ((PotatoVisitor<? extends T>)visitor).visitNewArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_literalContext array_literal() throws RecognitionException {
		Array_literalContext _localctx = new Array_literalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_array_literal);
		int _la;
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new ArrayLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				match(T__3);
				setState(281);
				expression(0);
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__22) {
					{
					{
					setState(282);
					match(T__22);
					setState(283);
					expression(0);
					}
					}
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(289);
				match(T__4);
				}
				break;
			case NEW:
				_localctx = new NewArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				match(NEW);
				setState(292);
				type();
				setState(293);
				match(T__5);
				setState(294);
				expression(0);
				setState(295);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 12:
			return expression_l1_sempred((Expression_l1Context)_localctx, predIndex);
		case 13:
			return expression_l2_sempred((Expression_l2Context)_localctx, predIndex);
		case 14:
			return expression_l3_sempred((Expression_l3Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_l1_sempred(Expression_l1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_l2_sempred(Expression_l2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_l3_sempred(Expression_l3Context _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00011\u012c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0005\u0000.\b\u0000\n\u0000\f\u00001\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"E\b\u0001\u0001\u0002\u0003\u0002H\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002P\b\u0002\u0001"+
		"\u0002\u0003\u0002S\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002[\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002c\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004m\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004v\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u007f\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u0089\b\u0007\n\u0007\f\u0007\u008c\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u0093\b\b\n\b\f\b\u0096\t"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005\t\u009c\b\t\n\t\f\t\u009f\t\t"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00a7\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00b2\b\u000b\n\u000b\f\u000b"+
		"\u00b5\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00cc\b\f\n\f\f\f\u00cf"+
		"\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u00da\b\r\n\r\f\r\u00dd\t\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00e5\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u00ea\b\u000e\n\u000e\f\u000e\u00ed"+
		"\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00f4\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00ff"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0107\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u010f\b\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0117"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u011d"+
		"\b\u0015\n\u0015\f\u0015\u0120\t\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u012a\b\u0015\u0001\u0015\u0000\u0004\u0016\u0018\u001a\u001c\u0016\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*\u0000\u0000\u0147\u0000/\u0001\u0000\u0000\u0000\u0002D"+
		"\u0001\u0000\u0000\u0000\u0004b\u0001\u0000\u0000\u0000\u0006d\u0001\u0000"+
		"\u0000\u0000\bu\u0001\u0000\u0000\u0000\nw\u0001\u0000\u0000\u0000\f\u0080"+
		"\u0001\u0000\u0000\u0000\u000e\u0086\u0001\u0000\u0000\u0000\u0010\u008f"+
		"\u0001\u0000\u0000\u0000\u0012\u0099\u0001\u0000\u0000\u0000\u0014\u00a6"+
		"\u0001\u0000\u0000\u0000\u0016\u00a8\u0001\u0000\u0000\u0000\u0018\u00b6"+
		"\u0001\u0000\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000\u001c\u00e4"+
		"\u0001\u0000\u0000\u0000\u001e\u00f3\u0001\u0000\u0000\u0000 \u00fe\u0001"+
		"\u0000\u0000\u0000\"\u0100\u0001\u0000\u0000\u0000$\u0106\u0001\u0000"+
		"\u0000\u0000&\u010e\u0001\u0000\u0000\u0000(\u0116\u0001\u0000\u0000\u0000"+
		"*\u0129\u0001\u0000\u0000\u0000,.\u0003\u0002\u0001\u0000-,\u0001\u0000"+
		"\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000002\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u0000"+
		"23\u0005\u0000\u0000\u00013\u0001\u0001\u0000\u0000\u000045\u0003\u0004"+
		"\u0002\u000056\u0005\u0001\u0000\u00006E\u0001\u0000\u0000\u000078\u0003"+
		"\b\u0004\u000089\u0005\u0001\u0000\u00009E\u0001\u0000\u0000\u0000:E\u0003"+
		"\n\u0005\u0000;E\u0003\f\u0006\u0000<E\u0003\u000e\u0007\u0000=>\u0003"+
		"\u0014\n\u0000>?\u0005\u0001\u0000\u0000?E\u0001\u0000\u0000\u0000@A\u0003"+
		"\u0010\b\u0000AB\u0005\u0001\u0000\u0000BE\u0001\u0000\u0000\u0000CE\u0003"+
		"\u0006\u0003\u0000D4\u0001\u0000\u0000\u0000D7\u0001\u0000\u0000\u0000"+
		"D:\u0001\u0000\u0000\u0000D;\u0001\u0000\u0000\u0000D<\u0001\u0000\u0000"+
		"\u0000D=\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000\u0000DC\u0001\u0000"+
		"\u0000\u0000E\u0003\u0001\u0000\u0000\u0000FH\u0005\u0018\u0000\u0000"+
		"GF\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000"+
		"\u0000IJ\u0003$\u0012\u0000JK\u0005\u0002\u0000\u0000KL\u0005/\u0000\u0000"+
		"LO\u0005\u0003\u0000\u0000MP\u0003\u0016\u000b\u0000NP\u0003*\u0015\u0000"+
		"OM\u0001\u0000\u0000\u0000ON\u0001\u0000\u0000\u0000Pc\u0001\u0000\u0000"+
		"\u0000QS\u0005\u0018\u0000\u0000RQ\u0001\u0000\u0000\u0000RS\u0001\u0000"+
		"\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0003$\u0012\u0000UV\u0005/\u0000"+
		"\u0000VW\u0005\u0003\u0000\u0000WX\u0003\u0016\u000b\u0000Xc\u0001\u0000"+
		"\u0000\u0000Y[\u0005\u0018\u0000\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0005.\u0000\u0000]^"+
		"\u0005/\u0000\u0000^_\u0005\u0003\u0000\u0000_c\u0003\u0016\u000b\u0000"+
		"`a\u0005\"\u0000\u0000ac\u0005/\u0000\u0000bG\u0001\u0000\u0000\u0000"+
		"bR\u0001\u0000\u0000\u0000bZ\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000"+
		"\u0000c\u0005\u0001\u0000\u0000\u0000de\u0005#\u0000\u0000ef\u0005/\u0000"+
		"\u0000fg\u0003\u000e\u0007\u0000g\u0007\u0001\u0000\u0000\u0000hi\u0005"+
		"/\u0000\u0000il\u0005\u0003\u0000\u0000jm\u0003\u0016\u000b\u0000km\u0003"+
		"*\u0015\u0000lj\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000mv\u0001"+
		"\u0000\u0000\u0000no\u0005/\u0000\u0000op\u0005\u0004\u0000\u0000pq\u0003"+
		"\u0016\u000b\u0000qr\u0005\u0005\u0000\u0000rs\u0005\u0003\u0000\u0000"+
		"st\u0003\u0016\u000b\u0000tv\u0001\u0000\u0000\u0000uh\u0001\u0000\u0000"+
		"\u0000un\u0001\u0000\u0000\u0000v\t\u0001\u0000\u0000\u0000wx\u0005\u0019"+
		"\u0000\u0000xy\u0005\u0006\u0000\u0000yz\u0003\u0016\u000b\u0000z{\u0005"+
		"\u0007\u0000\u0000{~\u0003\u000e\u0007\u0000|}\u0005\u001a\u0000\u0000"+
		"}\u007f\u0003\u000e\u0007\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001"+
		"\u0000\u0000\u0000\u007f\u000b\u0001\u0000\u0000\u0000\u0080\u0081\u0005"+
		"\u001b\u0000\u0000\u0081\u0082\u0005\u0006\u0000\u0000\u0082\u0083\u0003"+
		"\u0016\u000b\u0000\u0083\u0084\u0005\u0007\u0000\u0000\u0084\u0085\u0003"+
		"\u000e\u0007\u0000\u0085\r\u0001\u0000\u0000\u0000\u0086\u008a\u0005\b"+
		"\u0000\u0000\u0087\u0089\u0003\u0002\u0001\u0000\u0088\u0087\u0001\u0000"+
		"\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000"+
		"\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008d\u0001\u0000"+
		"\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u008e\u0005\t\u0000"+
		"\u0000\u008e\u000f\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u001f\u0000"+
		"\u0000\u0090\u0094\u0003\u0012\t\u0000\u0091\u0093\u0003\u0002\u0001\u0000"+
		"\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000"+
		"\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000"+
		"\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0005 \u0000\u0000\u0098\u0011\u0001\u0000\u0000\u0000\u0099"+
		"\u009d\u0005\b\u0000\u0000\u009a\u009c\u0003\u0002\u0001\u0000\u009b\u009a"+
		"\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u00a0"+
		"\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0005\t\u0000\u0000\u00a1\u0013\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"\u001c\u0000\u0000\u00a3\u00a7\u0003\u0016\u000b\u0000\u00a4\u00a5\u0005"+
		"\u001d\u0000\u0000\u00a5\u00a7\u0003\u0016\u000b\u0000\u00a6\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u0015\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0006\u000b\uffff\uffff\u0000\u00a9\u00aa"+
		"\u0003\u0018\f\u0000\u00aa\u00b3\u0001\u0000\u0000\u0000\u00ab\u00ac\n"+
		"\u0002\u0000\u0000\u00ac\u00ad\u0005\n\u0000\u0000\u00ad\u00b2\u0003\u0018"+
		"\f\u0000\u00ae\u00af\n\u0001\u0000\u0000\u00af\u00b0\u0005\u000b\u0000"+
		"\u0000\u00b0\u00b2\u0003\u0018\f\u0000\u00b1\u00ab\u0001\u0000\u0000\u0000"+
		"\u00b1\u00ae\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b4\u0017\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0006\f\uffff\uffff\u0000\u00b7\u00b8\u0003\u001a\r\u0000"+
		"\u00b8\u00cd\u0001\u0000\u0000\u0000\u00b9\u00ba\n\u0006\u0000\u0000\u00ba"+
		"\u00bb\u0005\f\u0000\u0000\u00bb\u00cc\u0003\u001a\r\u0000\u00bc\u00bd"+
		"\n\u0005\u0000\u0000\u00bd\u00be\u0005\r\u0000\u0000\u00be\u00cc\u0003"+
		"\u001a\r\u0000\u00bf\u00c0\n\u0004\u0000\u0000\u00c0\u00c1\u0005\u000e"+
		"\u0000\u0000\u00c1\u00cc\u0003\u001a\r\u0000\u00c2\u00c3\n\u0003\u0000"+
		"\u0000\u00c3\u00c4\u0005\u000f\u0000\u0000\u00c4\u00cc\u0003\u001a\r\u0000"+
		"\u00c5\u00c6\n\u0002\u0000\u0000\u00c6\u00c7\u0005\u0010\u0000\u0000\u00c7"+
		"\u00cc\u0003\u001a\r\u0000\u00c8\u00c9\n\u0001\u0000\u0000\u00c9\u00ca"+
		"\u0005\u0011\u0000\u0000\u00ca\u00cc\u0003\u001a\r\u0000\u00cb\u00b9\u0001"+
		"\u0000\u0000\u0000\u00cb\u00bc\u0001\u0000\u0000\u0000\u00cb\u00bf\u0001"+
		"\u0000\u0000\u0000\u00cb\u00c2\u0001\u0000\u0000\u0000\u00cb\u00c5\u0001"+
		"\u0000\u0000\u0000\u00cb\u00c8\u0001\u0000\u0000\u0000\u00cc\u00cf\u0001"+
		"\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001"+
		"\u0000\u0000\u0000\u00ce\u0019\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d1\u0006\r\uffff\uffff\u0000\u00d1\u00d2\u0003"+
		"\u001c\u000e\u0000\u00d2\u00db\u0001\u0000\u0000\u0000\u00d3\u00d4\n\u0002"+
		"\u0000\u0000\u00d4\u00d5\u0005\u0012\u0000\u0000\u00d5\u00da\u0003\u001c"+
		"\u000e\u0000\u00d6\u00d7\n\u0001\u0000\u0000\u00d7\u00d8\u0005\u0013\u0000"+
		"\u0000\u00d8\u00da\u0003\u001c\u000e\u0000\u00d9\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d9\u00d6\u0001\u0000\u0000\u0000\u00da\u00dd\u0001\u0000\u0000"+
		"\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000"+
		"\u0000\u00dc\u001b\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0006\u000e\uffff\uffff\u0000\u00df\u00e5\u0003\u001e"+
		"\u000f\u0000\u00e0\u00e1\u0003\u001e\u000f\u0000\u00e1\u00e2\u0005\u0015"+
		"\u0000\u0000\u00e2\u00e3\u0003\u001c\u000e\u0001\u00e3\u00e5\u0001\u0000"+
		"\u0000\u0000\u00e4\u00de\u0001\u0000\u0000\u0000\u00e4\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e5\u00eb\u0001\u0000\u0000\u0000\u00e6\u00e7\n\u0002\u0000"+
		"\u0000\u00e7\u00e8\u0005\u0014\u0000\u0000\u00e8\u00ea\u0003\u001e\u000f"+
		"\u0000\u00e9\u00e6\u0001\u0000\u0000\u0000\u00ea\u00ed\u0001\u0000\u0000"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ec\u001d\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000"+
		"\u0000\u00ee\u00f4\u0003 \u0010\u0000\u00ef\u00f0\u0005\u0013\u0000\u0000"+
		"\u00f0\u00f4\u0003 \u0010\u0000\u00f1\u00f2\u0005\u0016\u0000\u0000\u00f2"+
		"\u00f4\u0003 \u0010\u0000\u00f3\u00ee\u0001\u0000\u0000\u0000\u00f3\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f4\u001f"+
		"\u0001\u0000\u0000\u0000\u00f5\u00ff\u0003(\u0014\u0000\u00f6\u00ff\u0003"+
		"&\u0013\u0000\u00f7\u00ff\u0003\"\u0011\u0000\u00f8\u00f9\u0005\u0006"+
		"\u0000\u0000\u00f9\u00fa\u0003\u0016\u000b\u0000\u00fa\u00fb\u0005\u0007"+
		"\u0000\u0000\u00fb\u00ff\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005$\u0000"+
		"\u0000\u00fd\u00ff\u0003\u001c\u000e\u0000\u00fe\u00f5\u0001\u0000\u0000"+
		"\u0000\u00fe\u00f6\u0001\u0000\u0000\u0000\u00fe\u00f7\u0001\u0000\u0000"+
		"\u0000\u00fe\u00f8\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000"+
		"\u0000\u00ff!\u0001\u0000\u0000\u0000\u0100\u0101\u0005\u001e\u0000\u0000"+
		"\u0101\u0102\u0005*\u0000\u0000\u0102#\u0001\u0000\u0000\u0000\u0103\u0107"+
		"\u0005+\u0000\u0000\u0104\u0107\u0005,\u0000\u0000\u0105\u0107\u0005-"+
		"\u0000\u0000\u0106\u0103\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000"+
		"\u0000\u0000\u0106\u0105\u0001\u0000\u0000\u0000\u0107%\u0001\u0000\u0000"+
		"\u0000\u0108\u0109\u0005/\u0000\u0000\u0109\u010a\u0005\u0004\u0000\u0000"+
		"\u010a\u010b\u0003\u0016\u000b\u0000\u010b\u010c\u0005\u0005\u0000\u0000"+
		"\u010c\u010f\u0001\u0000\u0000\u0000\u010d\u010f\u0005/\u0000\u0000\u010e"+
		"\u0108\u0001\u0000\u0000\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010f"+
		"\'\u0001\u0000\u0000\u0000\u0110\u0117\u0005%\u0000\u0000\u0111\u0117"+
		"\u0005&\u0000\u0000\u0112\u0117\u0005\'\u0000\u0000\u0113\u0117\u0005"+
		"(\u0000\u0000\u0114\u0117\u0005)\u0000\u0000\u0115\u0117\u0005*\u0000"+
		"\u0000\u0116\u0110\u0001\u0000\u0000\u0000\u0116\u0111\u0001\u0000\u0000"+
		"\u0000\u0116\u0112\u0001\u0000\u0000\u0000\u0116\u0113\u0001\u0000\u0000"+
		"\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0116\u0115\u0001\u0000\u0000"+
		"\u0000\u0117)\u0001\u0000\u0000\u0000\u0118\u0119\u0005\u0004\u0000\u0000"+
		"\u0119\u011e\u0003\u0016\u000b\u0000\u011a\u011b\u0005\u0017\u0000\u0000"+
		"\u011b\u011d\u0003\u0016\u000b\u0000\u011c\u011a\u0001\u0000\u0000\u0000"+
		"\u011d\u0120\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000"+
		"\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0121\u0001\u0000\u0000\u0000"+
		"\u0120\u011e\u0001\u0000\u0000\u0000\u0121\u0122\u0005\u0005\u0000\u0000"+
		"\u0122\u012a\u0001\u0000\u0000\u0000\u0123\u0124\u0005!\u0000\u0000\u0124"+
		"\u0125\u0003$\u0012\u0000\u0125\u0126\u0005\u0006\u0000\u0000\u0126\u0127"+
		"\u0003\u0016\u000b\u0000\u0127\u0128\u0005\u0007\u0000\u0000\u0128\u012a"+
		"\u0001\u0000\u0000\u0000\u0129\u0118\u0001\u0000\u0000\u0000\u0129\u0123"+
		"\u0001\u0000\u0000\u0000\u012a+\u0001\u0000\u0000\u0000\u001d/DGORZbl"+
		"u~\u008a\u0094\u009d\u00a6\u00b1\u00b3\u00cb\u00cd\u00d9\u00db\u00e4\u00eb"+
		"\u00f3\u00fe\u0106\u010e\u0116\u011e\u0129";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}