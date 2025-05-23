lexer grammar CoBabyBoLLexer;

// === KEYWORDS ===============================================================
ACCEPT: A WS* C WS* C WS* E WS* P WS* T;
ALTER: A WS* L WS* T WS* E WS* R;
BY: B WS* Y;
CALL: C WS* A WS* L WS* L;
COPY: C WS* O WS* P WS* Y;
DATA_DIVISION: D WS* A WS* T WS* A WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N;
DELIMITED_BY: D WS* E WS* L WS* I WS* M WS* I WS* T WS* E WS* D WS+ B WS* Y;
DISPLAY: D WS* I WS* S WS* P WS* L WS* A WS* Y;

// Picture representation
PICTURE_IS: P WS* I WS* C WS* T WS* U WS* R WS* E WS* I WS* S -> pushMode(PIC_REP);

// Maths
ADD: A WS* D WS* D -> pushMode(UNTIL_TO);
SUBTRACT: S WS* U WS* B WS* T WS* R WS* A WS* C WS* T -> pushMode(UNTIL_FROM);
MULTIPLY: M WS* U WS* L WS* T WS* I WS* P WS* L WS* Y -> pushMode(UNTIL_BY);
DIVIDE: D WS* I WS* V WS* I WS* D WS* E -> pushMode(UNTIL_INTO);


REMAINDER: R WS* E WS* M WS* A WS* I WS* N WS* D WS* E WS* R;


ELSE: E WS* L WS* S WS* E;
END: E WS* N WS* D;
EVALUATE: E WS* V WS* A WS* L WS* U WS* A WS* T WS* E;
GIVING: G WS* I WS* V WS* I WS* N WS* G;
GO: G WS* O;
GOTO: G WS* O WS+ T WS* O;
IDENTIFICATION_DIVISION: I WS* D WS* E WS* N WS* T WS* I WS* F WS* I WS* C WS* A WS* T WS* I WS* O WS* N WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N;
IF: I WS* F;
LOOP: L WS* O WS* O WS* P;
MOVE: M WS* O WS* V WS* E;
NEXT_SENTENCE: N WS* E WS* X WS* T WS+ S WS* E WS* N WS* T WS* E WS* N WS* C WS* E;
OF: O WS* F;
OFF: O WS* F WS* F;
ON_ERROR: O WS* N WS+ E WS* R WS* R WS* O WS* R;
PERFORM: P WS* E WS* R WS* F WS* O WS* R WS* M;
PROCEED : P WS* R WS* O WS* C WS* E WS* E WS* D;
REPLACING: R WS* E WS* P WS* L WS* A WS* C WS* I WS* N WS* G;
SIGNAL: S WS* I WS* G WS* N WS* A WS* L;
THEN: T WS* H WS* E WS* N;
TO: T WS* O;
WITH_NO_ADVANCING: W WS* I WS* T WS* H WS+ N WS* O WS+ A WS* D WS* V WS* A WS* N WS* C WS* I WS* N WS* G;
SIZE: S WS* I WS* Z WS* E;
SPACE: S WS* P WS* A WS* C WS* E;
SPACES: S WS* P WS* A WS* C WS* E WS* S;
PROGRAM_ID     : P WS* R WS* O WS* G WS* R WS* A WS* M WS* '-' WS* I WS* D ;
AUTHOR         : A WS* U WS* T WS* H WS* O WS* R ;
INSTALLATION   : I WS* N WS* S WS* T WS* A WS* L WS* L WS* A WS* T WS* I WS* O WS* N ;
DATE_WRITTEN   : D WS* A WS* T WS* E WS* '-' WS* W WS* R WS* I WS* T WS* T WS* E WS* N ;
DATE_COMPILED  : D WS* A WS* T WS* E WS* '-' WS* C WS* O WS* M WS* P WS* I WS* L WS* E WS* D ;
SECURITY       : S WS* E WS* C WS* U WS* R WS* I WS* T WS* Y ;
FROM: F WS* R WS* O WS* M;
HIGH_VALUES: H WS* I WS* G WS* H WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
LOW_VALUES: L WS* O WS* W WS* '-' WS* V WS* A WS* L WS* U WS* E WS* S;
INTO: I WS* N WS* T WS* O;
USING: U WS* S WS* I WS* N WS* G;
BY_REFERENCE: B WS* Y WS+ R WS* E WS* F WS* E WS* R WS* E WS* N WS* C WS* E;
BY_CONTENT: B SOL* Y SOL+ C SOL* O SOL* N SOL* T SOL* E SOL* N SOL* T;
BY_VALUE: B WS* Y WS+ V WS* A WS* L WS* U WS* E;
THROUGH: T WS* H WS* R WS* O WS* U WS* G WS* H;
TIMES: T WS* I WS* M WS* E WS* S;
RETURNING: R WS* E WS* T WS* U WS* R WS* N WS* I WS* N WS* G;
AS_PRIMITIVE: A WS* S WS+ P WS* R WS* I WS* M WS* I WS* T WS* I WS* V WS* E;
AS_STRUCT: A WS* S WS+ S WS* T WS* R WS* U WS* C WS* T;
LIKE: L WS* I WS* K WS* E;
OCCURS: O WS* C WS* C WS* U WS* R WS* S;
ALSO: A WS* L WS* S WS* O;
VARYING: V WS* A WS* R WS* Y WS* I WS* N WS* G;
WHILE: W WS* H WS* I WS* L WS* E;
UNTIL: U WS* N WS* T WS* I WS* L;
WHEN: W WS* H WS* E WS* N;
OTHER: O WS* T WS* H WS* E WS* R;
STOP: S WS* T WS* O WS* P;
PROCEDURE_DIVISION: P WS* R WS* O WS* C WS* E WS* D WS* U WS* R WS* E WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N;
ZERO: Z WS* E WS* R WS* O;
XOR: X WS* O WS* R;

// === LOGICAL OPERATORS ===============================================================
AND: A WS* N WS* D;
OR: O WS* R;
NOT: N WS* O WS* T;

// === ARITHMATIC OPERATORS ===============================================================
MATH_OP: ADD_OP | SUB_OP | MULT_OP | DIV_OP | EXP_OP;

fragment ADD_OP: '+';
fragment SUB_OP: '-';
fragment MULT_OP: '*';
fragment DIV_OP: '/';
fragment EXP_OP: '**';

// === EQUALITY OPERATORS ===============================================================
EQ_OP : EQ | NEQ | LEQ | GEQ | GT | LT;

EQ: '=';
NEQ: '<>';
LEQ: '<=';
GEQ: '>=';
GT: '>';
LT: '<';

// === PUNCTUATION ===============================================================
LPAR: '(';
RPAR: ')';

// === LITERALS ===============================================================
STRINGLITERAL: '"' (WS* ~["\n\r] | '""' | '\'')* '"'
                      | '\'' (~['\n\r] | '\'\'' | '"')* '\'';
INTEGERLITERAL: (SIGN)? [0-9]+;
NUMERICLITERAL
    : (SIGN)? [0-9]* (DOT | COMMA) [0-9]+ (
        ('e' | 'E') (SIGN)? [0-9]+
    )?
    ;
IDENTIFIER: [a-zA-Z0-9]+ ([-_]+ [a-zA-Z0-9]+)*;
LETTER     : [a-zA-Z];
DIGIT      : [0-9];
ALPHANUMERIC : [a-zA-Z0-9];
ARG_LIT : '≡≡≡';
// === MISCELLANEOUS ===============================================================

WS: [ \n\t\r] ('-')* -> skip;
DOT: '.';
COMMA: ',';
SOL : WS+ ('-' WS*)?;
EOL : DOT WS*;
SIGN : '+' | '-';

fragment A:[aA]; fragment B:[bB]; fragment C:[cC]; fragment D:[dD];
fragment E:[eE]; fragment F:[fF]; fragment G:[gG]; fragment H:[hH];
fragment I:[iI]; fragment J:[jJ]; fragment K:[kK]; fragment L:[lL];
fragment M:[mM]; fragment N:[nN]; fragment O:[oO]; fragment P:[pP];
fragment Q:[qQ]; fragment R:[rR]; fragment S:[sS]; fragment T:[tT];
fragment U:[uU]; fragment V:[vV]; fragment W:[wW]; fragment X:[xX];
fragment Y:[yY]; fragment Z:[zZ];

mode PIC_REP;

REPRESENTATION: PR_SIGN? WS* CHUNK WS* ( PR_DECSEP CHUNK )? WS* PRECISION? -> popMode;
CHUNK: ( '9' | 'A' | 'X' | 'Z' )+;

PRECISION           : '(' [0-9]+ ')';

PR_WS               : [ \t\r\n]+ -> skip ;
PR_SIGN             : 'S';   // operational sign
PR_DECSEP           : 'V';   // decimal separator


mode UNTIL_TO;

UT_WS       : WS                         -> channel(HIDDEN) ;
UT_ADD      : ADD                        -> type(ADD) ;
UT_ID
    :   { noMatchAfter(CoBabyBoLLexer.TO) }?
        IDENTIFIER                       -> type(IDENTIFIER)
    ;
UT_TO       : TO                         -> type(TO), popMode ;

UT_NUMERIC  : NUMERICLITERAL             -> type(NUMERICLITERAL) ;
UT_ZERO     : ZERO                       -> type(ZERO) ;
UT_STRING   : STRINGLITERAL              -> type(STRINGLITERAL) ;

mode UNTIL_FROM;

UF_WS       : WS                         -> channel(HIDDEN) ;
UF_SUB      : SUBTRACT                   -> type(SUBTRACT) ;   // (typo fixed)
UF_ID       : { noMatchAfter(CoBabyBoLLexer.FROM) }? IDENTIFIER
                                        -> type(IDENTIFIER) ;
UF_FROM     : FROM                       -> type(FROM), popMode ;
UF_NUMERIC  : NUMERICLITERAL             -> type(NUMERICLITERAL) ;
UF_ZERO     : ZERO                       -> type(ZERO) ;
UF_STRING   : STRINGLITERAL              -> type(STRINGLITERAL) ;

mode UNTIL_BY;

UB_WS       : WS                         -> channel(HIDDEN) ;
UB_MULT     : MULTIPLY                   -> type(MULTIPLY) ;
UB_ID       : { noMatchAfter(CoBabyBoLLexer.BY) }? IDENTIFIER  -> type(IDENTIFIER) ;
UB_BY       : BY                         -> type(BY), popMode ;
UB_NUMERIC  : NUMERICLITERAL             -> type(NUMERICLITERAL) ;
UB_ZERO     : ZERO                       -> type(ZERO) ;
UB_STRING   : STRINGLITERAL              -> type(STRINGLITERAL) ;

mode UNTIL_INTO;

UI_WS       : WS                         -> channel(HIDDEN) ;
UI_DIV      : DIVIDE                     -> type(DIVIDE) ;
UI_ID       : { noMatchAfter(CoBabyBoLLexer.INTO) }? IDENTIFIER -> type(IDENTIFIER) ;
UI_INTO     : INTO                       -> type(INTO), popMode ;
UI_NUMERIC  : NUMERICLITERAL             -> type(NUMERICLITERAL) ;
UI_ZERO     : ZERO                       -> type(ZERO) ;
UI_STRING   : STRINGLITERAL              -> type(STRINGLITERAL) ;