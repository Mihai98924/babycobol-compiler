lexer grammar CoBabyBoLTokens;

// === KEYWORDS ===============================================================
ACCEPT: A WS* C WS* C WS* E WS* P WS* T;
ADD: A WS* D WS* D;
ALTER: A WS* L WS* T WS* E WS* R;
BY: B WS* Y;
CALL: C WS* A WS* L WS* L;
COPY: C WS* O WS* P WS* Y;
DATA_DIVISION: D WS* A WS* T WS* A WS+ D WS* I WS* V WS* I WS* S WS* I WS* O WS* N;
DELIMITED_BY: D WS* E WS* L WS* I WS* M WS* I WS* T WS* E WS* D WS+ B WS* Y;
DISPLAY: D WS* I WS* S WS* P WS* L WS* A WS* Y;
DIVIDE: D WS* I WS* V WS* I WS* D WS* E;
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
MULTIPLY: M WS* U WS* L WS* T WS* I WS* P WS* L WS* Y;
NEXT_SENTENCE: N WS* E WS* X WS* T WS+ S WS* E WS* N WS* T WS* E WS* N WS* C WS* E;
OF: O WS* F;
OFF: O WS* F WS* F;
ON_ERROR: O WS* N WS+ E WS* R WS* R WS* O WS* R;
PERFORM: P WS* E WS* R WS* F WS* O WS* R WS* M;
PROCEED : P WS* R WS* O WS* C WS* E WS* E WS* D;
REPLACING: R WS* E WS* P WS* L WS* A WS* C WS* I WS* N WS* G;
SIGNAL: S WS* I WS* G WS* N WS* A WS* L;
SUBTRACT: S WS* U WS* B WS* T WS* R WS* A WS* C WS* T;
THEN: I WS* F WS+ T WS* H WS* E WS* N;
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
REMAINDER: R WS* E WS* M WS* A WS* I WS* N WS* D WS* E WS* R;
INTO: I WS* N WS* T WS* O;
USING: U WS* S WS* I WS* N WS* G;
BY_REFERENCE: B WS* Y WS+ R WS* E WS* F WS* E WS* R WS* E WS* N WS* C WS* E;
BY_CONTENT: B WS* Y WS+ C WS* O WS* N WS* T WS* E WS* N WS* T;
BY_VALUE: B WS* Y WS+ V WS* A WS* L WS* U WS* E;
THROUGH: T WS* H WS* R WS* O WS* U WS* G WS* H;
TIMES: T WS* I WS* M WS* E WS* S;
RETURNING: R WS* E WS* T WS* U WS* R WS* N WS* I WS* N WS* G;
AS_PRIMITIVE: A WS* S WS+ P WS* R WS* I WS* M WS* I WS* T WS* I WS* V WS* E;
AS_STRUCT: A WS* S WS+ S WS* T WS* R WS* U WS* C WS* T;
PICTURE: P WS* I WS* C WS* T WS* U WS* R WS* E;
IS: I WS* S;
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

// === LITERALS ===============================================================
fragment LETTER     : [a-zA-Z];
fragment DIGIT      : [0-9];
fragment CHARACTER  : ~['"\r\n];
fragment ALPHANUMERIC : [a-zA-Z0-9];

//LEVEL: SOL DIGIT{2} SOL;

IDENTIFIER: LETTER ( ALPHANUMERIC | '-' ALPHANUMERIC )*;

LITERAL: INTEGER_LITERAL | NUMERIC_LITERAL | ALPHANUMERIC_LITERAL | ALPHABETIC_LITERAL;

INTEGER_LITERAL: SIGN? DIGIT+;
NUMERIC_LITERAL: SIGN? DIGIT* (DOT | COMMA) DIGIT+ ( E SIGN? DIGIT+ )?;
ALPHANUMERIC_LITERAL: ALPHANUMERIC+;
ALPHABETIC_LITERAL: LETTER+;

// === LOGICAL OPERATORS ===============================================================
AND: A WS* N WS* D;
OR: O WS* R;
NOT: N WS* O WS* T;


// === EQUALITY OPERATORS ===============================================================
EQ_OP : EQ | NEQ | LEQ | GEQ | GT | LT;

EQ: '=';
NEQ: '<>';
LEQ: '<=';
GEQ: '>=';
GT: '>';
LT: '<';


// === MISCELLANEOUS ===============================================================

WS: [ \n\t\r] ('-')* -> skip;
fragment DOT: '.';
fragment COMMA: ',';
SOL : WS+ ('*' ALPHANUMERIC | '-' WS*)?;
EOL : DOT WS*;
SIGN : '+' | '-';
//LQ: (SOL '-' WS*) | WS+;



A : 'A' | 'a';
B : 'B' | 'b';
C : 'C' | 'c';
D : 'D' | 'd';
E : 'E' | 'e';
F : 'F' | 'f';
G : 'G' | 'g';
H : 'H' | 'h';
I : 'I' | 'i';
J : 'J' | 'j';
K : 'K' | 'k';
L : 'L' | 'l';
M : 'M' | 'm';
N : 'N' | 'n';
O : 'O' | 'o';
P : 'P' | 'p';
Q : 'Q' | 'q';
R : 'R' | 'r';
S : 'S' | 's';
T : 'T' | 't';
U : 'U' | 'u';
V : 'V' | 'v';
W : 'W' | 'w';
X : 'X' | 'x';
Y : 'Y' | 'y';
Z : 'Z' | 'z';