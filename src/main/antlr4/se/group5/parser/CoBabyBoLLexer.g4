lexer grammar CoBabyBoLLexer;

@members {
    private boolean noMatchInLine(int want) {
        int k = 1;
        for (;;) {
            int ttype = _input.LA(k);
            if (ttype == EOL || ttype == Token.EOF) return true;
            if (ttype == want)                     return false;
            k++;
        }
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

// === DEFAULT MODE (start-of-line handling) ====================
// Match start-of-line (columns 1-6); handle comments or advance.
SOL
    : ( ANY ANY ANY ANY ANY ANY COMMENT
      | ANY ANY ANY ANY ANY ANY
      ) -> skip
    ;

// Code line begins at column 7
DEFAULT_LINE : SOL [ ] -> pushMode(CODE);

// Entire COBOL comment line (column-7 '*')
COMMENT : STAR ANY* LINE_BREAK SOL;

mode CODE;
// Whitespace (incl. continuation) – skipped
WS : ([ ] | CONTINUE_LINE) -> skip ;
CONTINUE_LINE : LINE_BREAK SOL '-' -> skip ;

// === UNIVERSAL TOKENS ========================================
EOL : DOT LINE_BREAK ;

// === IDENTIFICATION DIVISION TOKENS ===========================
IDENTIFICATION_DIVISION
    : I WS* D WS* E WS* N WS* T WS* I WS* F WS* I WS* C WS* A WS* T WS* I WS* O WS* N WS+
      D WS* I WS* V WS* I WS* S WS* I WS* O WS* N
      -> pushMode(ID)
    ;

mode ID;
ID_EOL        : EOL       -> type(EOL);
ID_CODE_LINE  : DEFAULT_LINE -> type(DEFAULT_LINE);
ID_LINE       : DEFAULT_LINE [ ] [ ] [ ] [ ]           -> pushMode(ID_NAME);
ID_START_DD   : DATA_DIVISION                       -> type(DATA_DIVISION), pushMode(DD);

mode ID_NAME;
ID_NAME_VAL       : ~[.\r\n]+ ;
ID_NAME_VAL_END   : DOT WS* -> pushMode(ID_VALUE);

mode ID_VALUE;
ID_VALUE_VAL      : ~[.\r\n]+ ;
ID_VALUE_VAL_END  : EOL -> pushMode(ID);

// Placeholder for future CODE mode
mode CODE;

DATA_DIVISION
    : D WS* A WS* T WS* A WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N
      -> pushMode(DD)
    ;

// === DATA DIVISION TOKENS =====================================
mode DD;
DD_EOL        : EOL        -> type(EOL);
DD_WS         : WS         -> type(WS);
DD_LINE       : DEFAULT_LINE [ ] [ ] WS*                  -> pushMode(DD_LVL);
PICTURE_IS    : P WS* I WS* C WS* T WS* U WS* R WS* E WS* I WS* S
                -> pushMode(PIC_REP);
LIKE          : L WS* I WS* K WS* E                    -> pushMode(ID_REP);
OCCURS        : O WS* C WS* C WS* U WS* R WS* S;
TIMES         : T WS* I WS* M WS* E WS* S;
DD_CODE_LINE  : DEFAULT_LINE        -> type(DEFAULT_LINE);
DD_START_PD   : PROCEDURE_DIVISION -> type(PROCEDURE_DIVISION), pushMode(CODE);

mode DD_LVL;
DD_REP_WS : WS    -> type(WS);
LEVEL  : [0-9] [0-9] -> pushMode(ID_REP);

mode ID_REP;
ID_REP_WS : WS         -> type(WS);
IR_ID     : IDENTIFIER -> type(IDENTIFIER), pushMode(DD);

// === PICTURE REPRESENTATION ===================================
mode PIC_REP;
REPRESENTATION
    : PR_SIGN? WS* CHUNK WS* (PR_DECSEP CHUNK)? WS* PRECISION? -> popMode
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
DISPLAY : D WS* I WS* S WS* P WS* L WS* A WS* Y ;

// === KEYWORDS & RESERVED WORDS ================================
ACCEPT              : A WS* C WS* C WS* E WS* P WS* T;
ALTER               : A WS* L WS* T WS* E WS* R;
BY                  : B WS* Y;
CALL                : C WS* A WS* L WS* L;
COPY                : C WS* O WS* P WS* Y;

DELIMITED_BY        : D WS* E WS* L WS* I WS* M WS* I WS* T WS* E WS* D WS+ B WS* Y;

// ── Arithmetic prefixes that switch modes for “until …” parsing
ADD                 : A WS* D WS* D      -> pushMode(UNTIL_TO);
SUBTRACT            : S WS* U WS* B WS* T WS* R WS* A WS* C WS* T -> pushMode(UNTIL_FROM);
MULTIPLY            : M WS* U WS* L WS* T WS* I WS* P WS* L WS* Y -> pushMode(UNTIL_BY);
DIVIDE              : D WS* I WS* V WS* I WS* D WS* E            -> pushMode(UNTIL_INTO);

REMAINDER           : R WS* E WS* M WS* A WS* I WS* N WS* D WS* E WS* R;

ELSE                : E WS* L WS* S WS* E;
END                 : E WS* N WS* D;
EVALUATE            : E WS* V WS* A WS* L WS* U WS* A WS* T WS* E;
GIVING              : G WS* I WS* V WS* I WS* N WS* G;
GO                  : G WS* O;
GOTO                : G WS* O WS+ T WS* O;
IF                  : I WS* F;
LOOP                : L WS* O WS* O WS* P;
MOVE                : M WS* O WS* V WS* E;
NEXT_SENTENCE       : N WS* E WS* X WS* T WS+ S WS* E WS* N WS* T WS* E WS* N WS* C WS* E;
OF                  : O WS* F;
OFF                 : O WS* F WS* F;
ON_ERROR            : O WS* N WS+ E WS* R WS* R WS* O WS* R;
PERFORM             : P WS* E WS* R WS* F WS* O WS* R WS* M;
PROCEED             : P WS* R WS* O WS* C WS* E WS* E WS* D;
REPLACING           : R WS* E WS* P WS* L WS* A WS* C WS* I WS* N WS* G;
SIGNAL              : S WS* I WS* G WS* N WS* A WS* L;
THEN                : T WS* H WS* E WS* N;
TO                  : T WS* O;
WITH_NO_ADVANCING   : W WS* I WS* T WS* H WS+ N WS* O WS+ A WS* D WS* V WS* A WS* N WS* C WS* I WS* N WS* G;
SIZE                : S WS* I WS* Z WS* E;
SPACE               : S WS* P WS* A WS* C WS* E;
SPACES              : S WS* P WS* A WS* C WS* E WS* S;

FROM                : F WS* R WS* O WS* M;
HIGH_VALUES         : H WS* I WS* G WS* H WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
LOW_VALUES          : L WS* O WS* W WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
INTO                : I WS* N WS* T WS* O;
USING               : U WS* S WS* I WS* N WS* G;
BY_REFERENCE        : B WS* Y WS+ R WS* E WS* F WS* E WS* R WS* E WS* N WS* C WS* E;
BY_CONTENT          : B SOL* Y SOL+ C SOL* O SOL* N SOL* T SOL* E SOL* N SOL* T;
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
PROCEDURE_DIVISION  : P WS* R WS* O WS* C WS* E WS* D WS* U WS* R WS* E WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N;
ZERO                : Z WS* E WS* R WS* O;
XOR                 : X WS* O WS* R;

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
    : '"'  (WS* ~["\n\r] | '""' | '\'')* '"'
    | '\'' (~['\n\r]     | '\'\'' | '"')* '\''
    ;

INTEGERLITERAL : (SIGN)? [0-9]+ ;
NUMERICLITERAL
    : (SIGN)? [0-9]* (DOT | COMMA) [0-9]+
      (('e' | 'E') (SIGN)? [0-9]+)?
    ;

IDENTIFIER    : [a-zA-Z0-9]+ ([-_]+ [a-zA-Z0-9]+)* ;
LETTER        : [a-zA-Z] ;
DIGIT         : [0-9] ;
ALPHANUMERIC  : [a-zA-Z0-9] ;
//ARG_LIT : '≡≡≡';
ARG_LIT: '===' -> pushMode(ARG_LIT_MODE);
// === MISCELLANEOUS ===============================================================

// === MISCELLANEOUS ===========================================
COMMA : ',' ;
SIGN  : '+' | '-' ;

// ── UNTIL … helper modes (ADD/SUB/MULT/DIV) ───────────────────
mode UNTIL_TO;
UT_WS       : WS  -> channel(HIDDEN);
UT_ADD      : ADD -> type(ADD);
UT_NUMERIC  : NUMERICLITERAL   -> type(NUMERICLITERAL);
UT_INTEGER  : INTEGERLITERAL   -> type(INTEGERLITERAL);
UT_ZERO     : ZERO             -> type(ZERO);
UT_STRING   : STRINGLITERAL    -> type(STRINGLITERAL);
UT_TO       : TO  -> type(TO), popMode;
UT_ID       : ({ noMatchInLine(CoBabyBoLLexer.TO) }? IDENTIFIER) -> type(IDENTIFIER);

mode UNTIL_FROM;
UF_WS       : WS  -> channel(HIDDEN);
UF_SUB      : SUBTRACT          -> type(SUBTRACT);
UF_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UF_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UF_ZERO     : ZERO              -> type(ZERO);
UF_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UF_FROM     : FROM -> type(FROM), popMode;
UF_ID       : ({ noMatchInLine(CoBabyBoLLexer.FROM) }? IDENTIFIER) -> type(IDENTIFIER);

mode UNTIL_BY;
UB_WS       : WS  -> channel(HIDDEN);
UB_MULT     : MULTIPLY          -> type(MULTIPLY);
UB_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UB_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UB_ZERO     : ZERO              -> type(ZERO);
UB_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UB_BY       : BY  -> type(BY), popMode;
UB_ID       : ({ noMatchInLine(CoBabyBoLLexer.BY) }? IDENTIFIER) -> type(IDENTIFIER);

mode UNTIL_INTO;
UI_WS       : WS  -> channel(HIDDEN);
UI_DIV      : DIVIDE            -> type(DIVIDE);
UI_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UI_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UI_ZERO     : ZERO              -> type(ZERO);
UI_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UI_ID       : IDENTIFIER        -> type(IDENTIFIER);
UI_INTO     : { noMatchInLine(CoBabyBoLLexer.INTO) }? INTO -> type(INTO), popMode;

mode ARG_LIT_MODE;

T_ARG_LIT: '===';
ARG_LIT_ATOMIC: ((IDENTIFIER | NUMERICLITERAL | ZERO | INTEGERLITERAL | STRINGLITERAL) DOT? WS*)+ T_ARG_LIT -> popMode;