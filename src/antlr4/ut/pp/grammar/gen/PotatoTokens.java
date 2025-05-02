// Generated from /home/extratoast/IdeaProjects/PP-Project3/src/antlr4/ut/pp/grammar/PotatoTokens.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PotatoTokens extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SHARED=1, IF=2, ELSE=3, SCOPE=4, WHILE=5, DEC_INT_LITERAL=6, BIN_INT_LITERAL=7, 
		HEX_INT_LITERAL=8, BOOL_LITERAL=9, CHAR_LITERAL=10, STRING_LITERAL=11, 
		TYPE_INT=12, TYPE_BOOL=13, TYPE_CHAR=14, TYPE_STRING=15, IDENTIFIER=16, 
		COMMENT=17, WHITESPACE=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SHARED", "LETTER", "DIGIT", "BIN_DIGIT", "HEX_DIGIT", "CHARACTER", "IF", 
			"ELSE", "SCOPE", "WHILE", "DEC_INT_LITERAL", "BIN_INT_LITERAL", "HEX_INT_LITERAL", 
			"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "TYPE_INT", "TYPE_BOOL", 
			"TYPE_CHAR", "TYPE_STRING", "IDENTIFIER", "COMMENT", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'shared'", "'if'", "'else'", "'scope'", "'while'", null, null, 
			null, null, null, null, "'int'", "'bool'", "'char'", "'string'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SHARED", "IF", "ELSE", "SCOPE", "WHILE", "DEC_INT_LITERAL", "BIN_INT_LITERAL", 
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


	public PotatoTokens(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PotatoTokens.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0012\u00b0\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0004\nV\b\n\u000b\n\f\nW\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0004\u000b^\b\u000b\u000b\u000b\f\u000b_\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0004\ff\b\f\u000b\f\f\fg\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\rs\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0005\u000f{\b"+
		"\u000f\n\u000f\f\u000f~\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u009a"+
		"\b\u0014\n\u0014\f\u0014\u009d\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u00a3\b\u0015\n\u0015\f\u0015\u00a6\t\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0004\u0016\u00ab\b\u0016\u000b\u0016\f"+
		"\u0016\u00ac\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001\u0003"+
		"\u0000\u0005\u0000\u0007\u0000\t\u0000\u000b\u0000\r\u0002\u000f\u0003"+
		"\u0011\u0004\u0013\u0005\u0015\u0006\u0017\u0007\u0019\b\u001b\t\u001d"+
		"\n\u001f\u000b!\f#\r%\u000e\'\u000f)\u0010+\u0011-\u0012\u0001\u0000\u0007"+
		"\u0002\u0000AZaz\u0001\u000009\u0001\u000001\u0002\u000009af\u0002\u0000"+
		"\"\"\'\'\u0001\u0000\n\n\u0003\u0000\t\n\r\r  \u00b3\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001"+
		"\u0000\u0000\u0000\u0001/\u0001\u0000\u0000\u0000\u00036\u0001\u0000\u0000"+
		"\u0000\u00058\u0001\u0000\u0000\u0000\u0007:\u0001\u0000\u0000\u0000\t"+
		"<\u0001\u0000\u0000\u0000\u000b>\u0001\u0000\u0000\u0000\r@\u0001\u0000"+
		"\u0000\u0000\u000fC\u0001\u0000\u0000\u0000\u0011H\u0001\u0000\u0000\u0000"+
		"\u0013N\u0001\u0000\u0000\u0000\u0015U\u0001\u0000\u0000\u0000\u0017Y"+
		"\u0001\u0000\u0000\u0000\u0019a\u0001\u0000\u0000\u0000\u001br\u0001\u0000"+
		"\u0000\u0000\u001dt\u0001\u0000\u0000\u0000\u001fx\u0001\u0000\u0000\u0000"+
		"!\u0081\u0001\u0000\u0000\u0000#\u0085\u0001\u0000\u0000\u0000%\u008a"+
		"\u0001\u0000\u0000\u0000\'\u008f\u0001\u0000\u0000\u0000)\u0096\u0001"+
		"\u0000\u0000\u0000+\u009e\u0001\u0000\u0000\u0000-\u00aa\u0001\u0000\u0000"+
		"\u0000/0\u0005s\u0000\u000001\u0005h\u0000\u000012\u0005a\u0000\u0000"+
		"23\u0005r\u0000\u000034\u0005e\u0000\u000045\u0005d\u0000\u00005\u0002"+
		"\u0001\u0000\u0000\u000067\u0007\u0000\u0000\u00007\u0004\u0001\u0000"+
		"\u0000\u000089\u0007\u0001\u0000\u00009\u0006\u0001\u0000\u0000\u0000"+
		":;\u0007\u0002\u0000\u0000;\b\u0001\u0000\u0000\u0000<=\u0007\u0003\u0000"+
		"\u0000=\n\u0001\u0000\u0000\u0000>?\b\u0004\u0000\u0000?\f\u0001\u0000"+
		"\u0000\u0000@A\u0005i\u0000\u0000AB\u0005f\u0000\u0000B\u000e\u0001\u0000"+
		"\u0000\u0000CD\u0005e\u0000\u0000DE\u0005l\u0000\u0000EF\u0005s\u0000"+
		"\u0000FG\u0005e\u0000\u0000G\u0010\u0001\u0000\u0000\u0000HI\u0005s\u0000"+
		"\u0000IJ\u0005c\u0000\u0000JK\u0005o\u0000\u0000KL\u0005p\u0000\u0000"+
		"LM\u0005e\u0000\u0000M\u0012\u0001\u0000\u0000\u0000NO\u0005w\u0000\u0000"+
		"OP\u0005h\u0000\u0000PQ\u0005i\u0000\u0000QR\u0005l\u0000\u0000RS\u0005"+
		"e\u0000\u0000S\u0014\u0001\u0000\u0000\u0000TV\u0003\u0005\u0002\u0000"+
		"UT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000"+
		"\u0000WX\u0001\u0000\u0000\u0000X\u0016\u0001\u0000\u0000\u0000YZ\u0005"+
		"0\u0000\u0000Z[\u0005b\u0000\u0000[]\u0001\u0000\u0000\u0000\\^\u0003"+
		"\u0007\u0003\u0000]\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000"+
		"_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\u0018\u0001\u0000"+
		"\u0000\u0000ab\u00050\u0000\u0000bc\u0005x\u0000\u0000ce\u0001\u0000\u0000"+
		"\u0000df\u0003\t\u0004\u0000ed\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000h\u001a\u0001"+
		"\u0000\u0000\u0000ij\u0005t\u0000\u0000jk\u0005r\u0000\u0000kl\u0005u"+
		"\u0000\u0000ls\u0005e\u0000\u0000mn\u0005f\u0000\u0000no\u0005a\u0000"+
		"\u0000op\u0005l\u0000\u0000pq\u0005s\u0000\u0000qs\u0005e\u0000\u0000"+
		"ri\u0001\u0000\u0000\u0000rm\u0001\u0000\u0000\u0000s\u001c\u0001\u0000"+
		"\u0000\u0000tu\u0005\'\u0000\u0000uv\u0003\u000b\u0005\u0000vw\u0005\'"+
		"\u0000\u0000w\u001e\u0001\u0000\u0000\u0000x|\u0005\"\u0000\u0000y{\u0003"+
		"\u000b\u0005\u0000zy\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000"+
		"|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u007f\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0005\"\u0000\u0000"+
		"\u0080 \u0001\u0000\u0000\u0000\u0081\u0082\u0005i\u0000\u0000\u0082\u0083"+
		"\u0005n\u0000\u0000\u0083\u0084\u0005t\u0000\u0000\u0084\"\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0005b\u0000\u0000\u0086\u0087\u0005o\u0000\u0000"+
		"\u0087\u0088\u0005o\u0000\u0000\u0088\u0089\u0005l\u0000\u0000\u0089$"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0005c\u0000\u0000\u008b\u008c\u0005"+
		"h\u0000\u0000\u008c\u008d\u0005a\u0000\u0000\u008d\u008e\u0005r\u0000"+
		"\u0000\u008e&\u0001\u0000\u0000\u0000\u008f\u0090\u0005s\u0000\u0000\u0090"+
		"\u0091\u0005t\u0000\u0000\u0091\u0092\u0005r\u0000\u0000\u0092\u0093\u0005"+
		"i\u0000\u0000\u0093\u0094\u0005n\u0000\u0000\u0094\u0095\u0005g\u0000"+
		"\u0000\u0095(\u0001\u0000\u0000\u0000\u0096\u009b\u0003\u0003\u0001\u0000"+
		"\u0097\u009a\u0003\u0003\u0001\u0000\u0098\u009a\u0003\u0005\u0002\u0000"+
		"\u0099\u0097\u0001\u0000\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000"+
		"\u009a\u009d\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\u0001\u0000\u0000\u0000\u009c*\u0001\u0000\u0000\u0000\u009d"+
		"\u009b\u0001\u0000\u0000\u0000\u009e\u009f\u0005/\u0000\u0000\u009f\u00a0"+
		"\u0005/\u0000\u0000\u00a0\u00a4\u0001\u0000\u0000\u0000\u00a1\u00a3\b"+
		"\u0005\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0006\u0015\u0000\u0000\u00a8,\u0001\u0000"+
		"\u0000\u0000\u00a9\u00ab\u0007\u0006\u0000\u0000\u00aa\u00a9\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0006\u0016\u0000\u0000\u00af.\u0001\u0000\u0000"+
		"\u0000\n\u0000W_gr|\u0099\u009b\u00a4\u00ac\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}