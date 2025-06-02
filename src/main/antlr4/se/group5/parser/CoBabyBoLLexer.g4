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

DEFAULT_WS : WS -> skip ;

mode CODE;
// Whitespace (incl. continuation) – skipped
WS : ([ ] | CONTINUE_LINE) -> skip ;
CONTINUE_LINE : LINE_BREAK SOL '-' -> skip ;

// === UNIVERSAL TOKENS ========================================
EOL : DOT LINE_BREAK ;

// === IDENTIFICATION DIVISION TOKENS ===========================
IDENTIFICATION_DIVISION
    : I D E N T I F I C A T I O N WS+
      D I V I S I O N
      -> pushMode(ID)
    ;

mode ID;
ID_WS         : WS -> skip;
ID_EOL        : EOL       -> type(EOL);
ID_CODE_LINE  : DEFAULT_LINE -> type(DEFAULT_LINE);
ID_LINE       : DEFAULT_LINE [ ] [ ] [ ] [ ]           -> pushMode(ID_NAME);
ID_START_DD   : DATA_DIVISION                       -> type(DATA_DIVISION), pushMode(DD);
ID_START_PD   : DEFAULT_LINE PROCEDURE_DIVISION                  -> type(PROCEDURE_DIVISION), pushMode(CODE);

mode ID_NAME;
ID_NAME_VAL       : ~[.\r\n]+ ;
ID_NAME_VAL_END   : DOT -> pushMode(ID_VALUE);

mode ID_VALUE;
ID_VALUE_VAL      : ~[.\r\n]+ ;
ID_VALUE_VAL_END  : EOL -> pushMode(ID);

// Placeholder for future CODE mode
mode CODE;

DATA_DIVISION
    : D A T A WS+ D I V I S I O N
      -> pushMode(DD)
    ;

// === DATA DIVISION TOKENS =====================================
mode DD;
DD_EOL        : EOL        -> type(EOL);
DD_WS         : WS         -> skip;
PICTURE_IS    : P I C T U R E I S
                -> pushMode(PIC_REP);
LIKE          : L I K E                         -> pushMode(ID_REP);
OCCURS        : O C C U R S             -> pushMode(DD_INT);
TIMES         : T I M E S;
DD_LINE       : DEFAULT_LINE [ ] [ ]                    -> pushMode(DD_LVL);
DD_START_PD   : DEFAULT_LINE PROCEDURE_DIVISION -> type(PROCEDURE_DIVISION), pushMode(CODE);

mode DD_LVL;
DD_REP_WS : WS    -> skip;
DD_LEVEL  : LEVEL -> type(LEVEL), pushMode(ID_REP);
LEVEL : [0-9] [0-9];

mode DD_INT;
DD_INT_WS : WS -> skip;
DD_INT : INTEGERLITERAL -> type(INTEGERLITERAL), popMode;

mode ID_REP;
ID_REP_WS : WS         -> skip;
IR_ID     : IDENTIFIER -> type(IDENTIFIER), pushMode(DD);

// === PICTURE REPRESENTATION ===================================
mode PIC_REP;
PIC_WS : WS -> skip;
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

CODE_WS : WS -> skip;
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
ADD                 : A D D      -> pushMode(UNTIL_TO);
SUBTRACT            : S U B T R A C T -> pushMode(UNTIL_FROM);
MULTIPLY            : M U L T I P L Y -> pushMode(UNTIL_BY);
DIVIDE              : D I V I D E             -> pushMode(UNTIL_INTO);

REMAINDER           : R E M A I N D E R;

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
PROCEDURE_DIVISION  : P R O C E D U R E WS+ D I V I S I O N;
ZERO                : Z E R O;
XOR                 : X O R;

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

IDENTIFIER    : [a-zA-Z0-9]+ ([-_]+ [a-zA-Z0-9]+)* ;
LETTER        : [a-zA-Z] ;
DIGIT         : [0-9] ;
ALPHANUMERIC  : [a-zA-Z0-9] ;
ARG_LIT       : '≡≡≡' ;

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
UT_TO       : { noMatchInLine(CoBabyBoLLexer.TO) }? TO  -> type(TO), popMode;
UT_ID       : IDENTIFIER -> type(IDENTIFIER);

mode UNTIL_FROM;
UF_WS       : WS  -> channel(HIDDEN);
UF_SUB      : SUBTRACT          -> type(SUBTRACT);
UF_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UF_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UF_ZERO     : ZERO              -> type(ZERO);
UF_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UF_FROM     : { noMatchInLine(CoBabyBoLLexer.FROM) }? FROM -> type(FROM), popMode;
UF_ID       : IDENTIFIER -> type(IDENTIFIER);

mode UNTIL_BY;
UB_WS       : WS  -> channel(HIDDEN);
UB_MULT     : MULTIPLY          -> type(MULTIPLY);
UB_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UB_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UB_ZERO     : ZERO              -> type(ZERO);
UB_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UB_BY       : { noMatchInLine(CoBabyBoLLexer.BY) }? BY -> type(BY), popMode;
UB_ID       : IDENTIFIER  -> type(IDENTIFIER);

mode UNTIL_INTO;
UI_WS       : WS  -> channel(HIDDEN);
UI_DIV      : DIVIDE            -> type(DIVIDE);
UI_NUMERIC  : NUMERICLITERAL    -> type(NUMERICLITERAL);
UI_INTEGER  : INTEGERLITERAL    -> type(INTEGERLITERAL);
UI_ZERO     : ZERO              -> type(ZERO);
UI_STRING   : STRINGLITERAL     -> type(STRINGLITERAL);
UI_INTO     : { noMatchInLine(CoBabyBoLLexer.INTO) }? INTO -> type(INTO), popMode;
UI_ID       : IDENTIFIER        -> type(IDENTIFIER);