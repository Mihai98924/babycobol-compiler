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

mode ID;
ID_EOL        : EOL       -> type(EOL), pushMode(DEFAULT_MODE);
ID_NAME       : ~[.\r\n]+ ;
ID_END        : DOT [ ]* -> pushMode(ID_VALUE_MODE); // Start parsing

mode ID_VALUE_MODE;
ID_VALUE      : ~[.\r\n]+ ;
IV_END  : EOL -> type(EOL), popMode, pushMode(DEFAULT_MODE);


// === DATA DIVISION TOKENS =====================================
mode DD;
DD_EOL          : EOL                           -> type(EOL), pushMode(DEFAULT_MODE);
DD_WS           : WS                            -> channel(HIDDEN);
DD_LEVEL        : LEVEL                         -> type(LEVEL), pushMode(ID_REP);
PICTURE_IS      : P I C T U R E WS+ I S         -> pushMode(PIC_REP);
LIKE            : L I K E                       -> pushMode(ID_REP);
OCCURS          : O C C U R S;
DD_INT_LIT      : INTEGERLITERAL                -> type(INTEGERLITERAL);
TIMES           : T I M E S;

mode ID_REP;
ID_REP_WS : WS                                  -> skip;
IR_ID     : IDENTIFIER                          -> type(IDENTIFIER), popMode;

// === PICTURE REPRESENTATION ===================================
mode PIC_REP;
PR_WS        : WS                               -> channel(HIDDEN) ;
REPRESENTATION
    : PR_SIGN? CHUNK (PR_DECSEP CHUNK)? WS* PRECISION? -> popMode
    ;
CHUNK        : ('9' | 'A' | 'X' | 'Z')+ ;
PRECISION    : '(' [0-9]+ ')' ;
PR_SIGN      : 'S' ;               // operational sign
PR_DECSEP    : 'V' ;               // decimal separator

mode CODE;
// Whitespace (incl. continuation) – skipped
WS
    : ([ ] | CONTINUE_LINE)         -> skip
    ;
CONTINUE_LINE
    : LINE_BREAK SOL '-'                -> skip
    ;

// === UNIVERSAL TOKENS ========================================
EOL : DOT LINE_BREAK -> pushMode(DEFAULT_MODE);


// === KEYWORDS & RESERVED WORDS ================================
ACCEPT              : A C C E P T;
ALTER               : A L T E R;
CALL                : C A L L;
COPY                : C O P Y;
DISPLAY             : D I S P L A Y ;
DELIMITED_BY        : D E L I M I T E D WS+ B Y;

// ── Arithmetic prefixes that switch modes for “until …” parsing
ADD                 : A WS* D WS* D                 -> pushMode(ADD_MODE);
SUBTRACT            : S WS* U WS* B WS* T WS* R WS* A WS* C WS* T       -> pushMode(SUBTRACT_MODE);
MULTIPLY            : M WS* U WS* L WS* T WS* I WS* P WS* L WS* Y       -> pushMode(MULTIPLY_MODE);
DIVIDE              : D WS* I WS* V WS* I WS* D WS* E           -> pushMode(DIVIDE_MODE);
REMAINDER           : R WS* E WS* M WS* A WS* I WS* N WS* D WS* E WS* R;
ELSE                : E WS* L WS* S WS* E;
END                 : E WS* N WS* D;
EVALUATE            : E WS* V WS* A WS* L WS* U WS* A WS* T WS* E;
GIVING              : G WS* I WS* V WS* I WS* N WS* G;
GO                  : G WS* O;
GOTO                : G WS* O WS* WS+ T WS* O;
IF                  : I WS* F;
LOOP                : L WS* O WS* O WS* P;
MOVE                : M WS* O WS* V WS* E;
TO                  : T WS* O;
NEXT_SENTENCE       : N WS* E WS* X WS* T WS+ S WS* E WS* N WS* T WS* E WS* N WS* C WS* E;
OF                  : O WS* F;
OFF                 : O WS* F WS* F;
ON_ERROR            : O WS* N WS+ E WS* R WS* R WS* O WS* R;
PERFORM             : P WS* E WS* R WS* F WS* O WS* R WS* M;
PROCEED             : P WS* R WS* O WS* C WS* E WS* E WS* D;
REPLACING           : R WS* E WS* P WS* L WS* A WS* C WS* I WS* N WS* G;
SIGNAL              : S WS* I WS* G WS* N WS* A WS* L;
THEN                : T WS* H WS* E WS* N;
WITH_NO_ADVANCING   : W WS* I WS* T WS* H WS+ N WS* O WS+ A WS* D WS* V WS* A WS* N WS* C WS* I WS* N WS* G;
SIZE                : S WS* I WS* Z WS* E;
SPACE               : S WS* P WS* A WS* C WS* E;
SPACES              : S WS* P WS* A WS* C WS* E WS* S;
HIGH_VALUES         : H WS* I WS* G WS* H WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
LOW_VALUES          : L WS* O WS* W WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
USING               : U WS* S WS* I WS* N WS* G;
BY_REFERENCE        : B WS* Y WS+ R WS* E WS* F WS* E WS* R WS* E WS* N WS* C WS* E;
BY_CONTENT          : B WS* Y WS+ C WS* O WS* N WS* T WS* E WS* N WS* T;
BY_VALUE            : B WS* Y WS+ V WS* A WS* L WS* U WS* E;
THROUGH             : T WS* H WS* R WS* O WS* U WS* G WS* H;
RETURNING           : R WS* E WS* T WS* U WS* R WS* N WS* I WS* N WS* G;
AS_PRIMITIVE        : A WS* S WS+ P WS* R WS* I WS* M WS* I WS* T WS* I WS* V WS* E;
AS_STRUCT           : A WS* S WS+ S WS* T WS* R WS* U WS* C WS* T;
ALSO                : A WS* L WS* S WS* O;
VARYING             : V WS* A WS* R WS* Y WS* I WS* N WS* G;
WHILE               : W WS* H WS* I WS* L WS* E;
UNTIL               : U WS* N WS* T WS* I WS* L;
WHEN                : W WS* H WS* E WS* N;
OTHER               : O WS* T WS* H WS* E WS* R;
STOP                : S WS* T WS* O WS* P;
ZERO                : Z WS* E WS* R WS* O;
XOR                 : X WS* O WS* R;
CODE_IDENTIFIER
    : IDENTIFIER        -> type(IDENTIFIER)
    ;

LEVEL : [0-9] [0-9];

// === OPERATORS & PUNCTUATION ================================
AND : A WS* N WS* D;
OR  : O WS* R;
NOT : N WS* O WS* T;

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

ARG_LIT: '===' -> pushMode(ARG_LIT_MODE);

// === MISCELLANEOUS ===========================================
COMMA : ',' ;
SIGN  : '+' | '-' ;


mode ADD_MODE;
AM_WS       : WS               -> skip;
AM_ADD      : ADD               -> type(ADD);
AM_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
AM_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
AM_ZERO     : ZERO              -> type(ZERO);
AM_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
AM_TO_ID    : 'to' { hasNextTokenInLine(CoBabyBoLLexer.TO) }? -> type(IDENTIFIER);
AM_TO       : TO               -> type(TO), popMode;
AM_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode SUBTRACT_MODE;
UF_WS       : WS                -> skip;
UF_SUB      : SUBTRACT          -> type(SUBTRACT);
UF_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UF_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UF_ZERO     : ZERO              -> type(ZERO);
UF_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UF_FROM_ID  : 'from' { hasNextTokenInLine(CoBabyBoLLexer.FROM) }? -> type(IDENTIFIER);
FROM        : F R O M           -> popMode;
UF_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode MULTIPLY_MODE;
UB_WS       : WS                -> skip;
UB_MULT     : MULTIPLY          -> type(MULTIPLY);
UB_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UB_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UB_ZERO     : ZERO              -> type(ZERO);
UB_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UB_BY_ID    : 'by' { hasNextTokenInLine(CoBabyBoLLexer.BY) }? -> type(IDENTIFIER);
BY          : B Y               -> popMode;
UB_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode DIVIDE_MODE;
UI_WS       : WS                -> skip;
UI_DIV      : DIVIDE            -> type(DIVIDE);
UI_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UI_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UI_ZERO     : ZERO              -> type(ZERO);
UI_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UI_INT_ID   : 'into' { hasNextTokenInLine(CoBabyBoLLexer.INTO) }? -> type(IDENTIFIER);
INTO        : I N T O           -> popMode;
UI_ID       : IDENTIFIER        -> type(IDENTIFIER);

mode ARG_LIT_MODE;

T_ARG_LIT: '===';
ARG_LIT_ATOMIC: ((IDENTIFIER | NUMERICLITERAL | ZERO | INTEGERLITERAL | STRINGLITERAL) DOT? WS*)+ T_ARG_LIT -> popMode;