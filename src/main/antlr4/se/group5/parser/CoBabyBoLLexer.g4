lexer grammar CoBabyBoLLexer;

@members {
    /**
     * Return {@code true} **iff** the given {@code tokenType} occurs somewhere
     * between the current input position and the next {@code EOL} (or end-of-file).
     *
     * It does this by lexing the **tail of the current line** with a *fresh*
     * instance of this lexer, started in the **same mode and mode-stack**.
     * That means we respect comments, hidden-channel tokens, embedded
     * whitespace inside keywords, etc., while leaving the real lexer totally
     * untouched.
     */
    private boolean hasNextTokenInLine(int tokenType) {
        // Capture all text that is still unconsumed
        int start = _input.index();
        String tail = _input.getText(org.antlr.v4.runtime.misc.Interval.of(start, _input.size()));

        // Tokenise that tail in a new lexer that mirrors the current mode situation
        org.antlr.v4.runtime.CharStream cs = org.antlr.v4.runtime.CharStreams.fromString(tail);
        CoBabyBoLLexer lookAhead = new CoBabyBoLLexer(cs);
        lookAhead._mode = this._mode;
        lookAhead._modeStack.addAll(this._modeStack);

        // Walk tokens only until EOL or EOF
        for (org.antlr.v4.runtime.Token t = lookAhead.nextToken();
             t.getType() != org.antlr.v4.runtime.Token.EOF;
             t = lookAhead.nextToken()) {

            if (t.getType() == EOL)          // reached logical end of line
                return false;

            if (t.getType() == tokenType)    // found what we are looking for
                return true;
        }
        return false;                        // nothing before EOL/EOF
    }
}

// ── CHARACTER FRAGMENTS ───────────────────────────────────────
fragment DOT         : '.' ;
fragment LINE_BREAK  : '\r'? '\n' ;
fragment ANY         : ~[\r\n] ;
fragment PLUS        : '+' ;
fragment HYPHEN      : '-' ;
fragment STAR        : '*' ;
fragment SLASH       : '/' ;

// ── LETTER FRAGMENTS ──────────────────────────────────────────
fragment A : [aA]; fragment B : [bB]; fragment C : [cC]; fragment D : [dD];
fragment E : [eE]; fragment F : [fF]; fragment G : [gG]; fragment H : [hH];
fragment I : [iI]; fragment J : [jJ]; fragment K : [kK]; fragment L : [lL];
fragment M : [mM]; fragment N : [nN]; fragment O : [oO]; fragment P : [pP];
fragment Q : [qQ]; fragment R : [rR]; fragment S : [sS]; fragment T : [tT];
fragment U : [uU]; fragment V : [vV]; fragment W : [wW]; fragment X : [xX];
fragment Y : [yY]; fragment Z : [zZ];

// Code line begins at column 7
DEFAULT_LINE : SOL [ ] -> pushMode(CONTEXT);

mode SOL_MODE;
// === DEFAULT MODE (start-of-line handling) ====================
// Match start-of-line (columns 1-6); handle comments or advance.
SOL
    : ( ANY ANY ANY ANY ANY ANY COMMENT
      | ANY ANY ANY ANY ANY ANY
      ) -> skip
    ;

// Entire COBOL comment line (column-7 '*')
COMMENT : STAR ANY* LINE_BREAK SOL;

mode CONTEXT;

IDENTIFICATION_DIVISION
    : I WS* D WS* E WS* N WS* T WS* I WS* F WS* I WS* C WS* A WS* T WS* I WS* O WS* N WS+
      D WS* I WS* V WS* I WS* S WS* I WS* O WS* N
      -> pushMode(ID)
    ;

DATA_DIVISION
    : D WS* A WS* T WS* A WS+ D
      WS* I WS* V WS* I WS* S WS* I WS* O WS* N   -> pushMode(DD)
    ;

PROCEDURE_DIVISION
    : P WS* R WS* O WS* C WS* E WS* D WS* U WS* R WS* E WS+
      D WS* I WS* V WS* I WS* S WS* I WS* O WS* N -> pushMode(CODE) // Go to parsing of CODE (procedure division)
    ;

IDENTIFIER
    : [a-zA-Z0-9]+ ([-_]+ [a-zA-Z0-9]+)* -> pushMode(CODE) // Go to parsing of CODE (function)
    ;

CONTINUE_CODE_LINE
    : [ ]+ -> channel(HIDDEN), popMode, popMode; // Return to earlier parsing mode.

mode CODE;
// Whitespace (incl. continuation) – skipped
WS : ([ ] | CONTINUE_LINE) -> skip ;
CONTINUE_LINE : LINE_BREAK SOL '-' -> skip ;

// === UNIVERSAL TOKENS ========================================
EOL : DOT LINE_BREAK ;


mode ID;
ID_EOL        : EOL       -> type(EOL), pushMode(DEFAULT_MODE);
ID_NAME       : ~[.\r\n]+ ;
ID_END        : DOT -> pushMode(ID_VALUE_MODE); // Start parsing

mode ID_VALUE_MODE;
ID_VALUE      : ~[.\r\n]+ ;
IV_END  : EOL -> type(EOL), popMode, pushMode(DEFAULT_MODE);


// === DATA DIVISION TOKENS =====================================
mode DD;
DD_EOL        : EOL        -> type(EOL), pushMode(DEFAULT_MODE);
DD_WS         : WS         -> channel(HIDDEN);
DD_LINE       : DEFAULT_LINE [ ] [ ]                  -> pushMode(DD_LVL);
PICTURE_IS    : P I C T U R E WS+ I S
                -> pushMode(PIC_REP);
LIKE          : L I K E                    -> pushMode(ID_REP);
OCCURS        : O C C U R S;
TIMES         : T I M E S;
DD_CODE_LINE  : DEFAULT_LINE        -> type(DEFAULT_LINE);
DD_START_PD   : PROCEDURE_DIVISION -> type(PROCEDURE_DIVISION), pushMode(CODE);

mode DD_LVL;
DD_REP_WS : WS    -> skip;
DD_LEVEL  : LEVEL -> type(LEVEL), pushMode(ID_REP);

mode ID_REP;
ID_REP_WS : WS         -> skip;
IR_ID     : IDENTIFIER -> type(IDENTIFIER), pushMode(DD);

// === PICTURE REPRESENTATION ===================================
mode PIC_REP;
REPRESENTATION
    : PR_SIGN? CHUNK (PR_DECSEP CHUNK)? PRECISION? -> popMode
    ;
CHUNK        : ('9' | 'A' | 'X' | 'Z')+ ;
PRECISION    : '(' [0-9]+ ')' ;
PR_WS        : [ \t\r\n]+ -> skip ;
PR_SIGN      : 'S' ;               // operational sign
PR_DECSEP    : 'V' ;               // decimal separator

// === BACK TO DEFAULT MODE =====================================
mode CODE;

CODE_EOL : EOL -> type(EOL);
CODE_LINE : DEFAULT_LINE [ ] [ ] [ ] [ ];
DISPLAY : D I S P L A Y ;

// === KEYWORDS & RESERVED WORDS ================================
ACCEPT              : A C C E P T;
ALTER               : A L T E R;
BY                  : B Y;
CALL                : C A L L;
COPY                : C O P Y;

DELIMITED_BY        : D E L I M I T E D WS+ B Y;

// ── Arithmetic prefixes that switch modes for “until …” parsing
ADD                 : A D D      -> pushMode(ADD_MODE);
SUBTRACT            : S U B T R A C T -> pushMode(SUBTRACT_MODE);
MULTIPLY            : M U L T I P L Y -> pushMode(MULTIPLY_MODE);
DIVIDE              : D I V I D E            -> pushMode(DIVIDE_MODE);

REMAINDER           : R E M A I N D E R;

mode ADD_MODE;
AM_WS       : WS               -> skip;
AM_ADD      : ADD               -> type(ADD);
AM_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
AM_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
AM_ZERO     : ZERO              -> type(ZERO);
AM_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
AM_TO_ID    : 'to' { hasNextTokenInLine(CoBabyBoLLexer.TO) }? -> type(IDENTIFIER);
AM_TO       : TO                -> type(TO), popMode;
AM_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode SUBTRACT_MODE;
UF_WS       : WS                -> skip;
UF_SUB      : SUBTRACT          -> type(SUBTRACT);
UF_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UF_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UF_ZERO     : ZERO              -> type(ZERO);
UF_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UF_FROM_ID  : 'from' { hasNextTokenInLine(CoBabyBoLLexer.FROM) }? -> type(IDENTIFIER);
UF_FROM     : FROM              -> type(FROM), popMode;
UF_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode MULTIPLY_MODE;
UB_WS       : WS                -> skip;
UB_MULT     : MULTIPLY          -> type(MULTIPLY);
UB_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UB_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UB_ZERO     : ZERO              -> type(ZERO);
UB_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UB_BY_ID    : 'by' { hasNextTokenInLine(CoBabyBoLLexer.BY) }? -> type(IDENTIFIER);
UB_BY       : BY                -> type(BY), popMode;
UB_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode DIVIDE_MODE;
UI_WS       : WS                -> skip;
UI_DIV      : DIVIDE            -> type(DIVIDE);
UI_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UI_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UI_ZERO     : ZERO              -> type(ZERO);
UI_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UI_INT_ID   : 'into' { hasNextTokenInLine(CoBabyBoLLexer.INTO) }? -> type(IDENTIFIER);
UI_INTO     : INTO              -> type(INTO), popMode;
UI_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode CODE;

ELSE                : E L S E;
END                 : E N D;
EVALUATE            : E V A L U A T E;
GIVING              : G I V I N G;
GO                  : G O;
GOTO                : G O WS+ T O;
IF                  : I F;
LOOP                : L O O P;
MOVE                : M O V E;
NEXT_SENTENCE       : N E X T WS+ S E N T E N C E;
OF                  : O F;
OFF                 : O F F;
ON_ERROR            : O N WS+ E R R O R;
PERFORM             : P E R F O R M;
PROCEED             : P R O C E E D;
REPLACING           : R E P L A C I N G;
SIGNAL              : S I G N A L;
THEN                : T H E N;
TO                  : T O;
WITH_NO_ADVANCING   : W I T H WS+ N O WS+ A D V A N C I N G;
SIZE                : S I Z E;
SPACE               : S P A C E;
SPACES              : S P A C E S;

FROM                : F R O M;
HIGH_VALUES         : H I G H '-' V A L U E S;
LOW_VALUES          : L O W '-' V A L U E S;
INTO                : I N T O;
USING               : U S I N G;
BY_REFERENCE        : B Y WS+ R E F E R E N C E;
BY_CONTENT          : B SOL* Y SOL+ C SOL* O SOL* N SOL* T SOL* E SOL* N SOL* T;
BY_VALUE            : B Y WS+ V A L U E;
THROUGH             : T H R O U G H;
RETURNING           : R E T U R N I N G;
AS_PRIMITIVE        : A S WS+ P R I M I T I V E;
AS_STRUCT           : A S WS+ S T R U C T;
ALSO                : A L S O;
VARYING             : V A R Y I N G;
WHILE               : W H I L E;
UNTIL               : U N T I L;
WHEN                : W H E N;
OTHER               : O T H E R;
STOP                : S T O P;
ZERO                : Z E R O;
XOR                 : X O R;

LEVEL : [0-9] [0-9];

// === OPERATORS & PUNCTUATION ================================
AND : A N D;
OR  : O R;
NOT : N O T;

MATH_OP : PLUS | HYPHEN | STAR | SLASH | (STAR STAR);

EQ_OP : EQ | NEQ | LEQ | GEQ | GT | LT;
EQ   : '='  ;
NEQ  : '<>' ;
LEQ  : '<=' ;
GEQ  : '>=' ;
GT   : '>'  ;
LT   : '<'  ;

LPAR : '(' ;
RPAR : ')' ;

// === LITERALS =================================================
STRINGLITERAL
    : '"'  (~["\n\r] | '""' | '\'')* '"'
    | '\'' (~['\n\r]     | '\'\'' | '"')* '\''
    ;

INTEGERLITERAL : (SIGN)? [0-9]+ ;
NUMERICLITERAL
    : (SIGN)? [0-9]* (DOT | COMMA) [0-9]+
      (('e' | 'E') (SIGN)? [0-9]+)?
    ;

LETTER        : [a-zA-Z] ;
DIGIT         : [0-9] ;
ALPHANUMERIC  : [a-zA-Z0-9] ;
ARG_LIT       : '≡≡≡' ;

// === MISCELLANEOUS ===========================================
COMMA : ',' ;
SIGN  : '+' | '-' ;