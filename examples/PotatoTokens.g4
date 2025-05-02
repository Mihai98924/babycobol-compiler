lexer grammar PotatoTokens;

// === KEYWORDS ===============================================================
SHARED : 'shared';

IF: 'if';
ELSE: 'else';
WHILE: 'while';

PRINT: 'print';
PRINT_NL: 'printNL';
INPUT: 'userInput';
FORK: 'fork';
JOIN: 'join';
NEW: 'new';
LOCK: 'lock';
SYNC: 'sync';
LENGTH: 'length';

// === LITERALS ===============================================================
fragment LETTER    : [a-zA-Z];
fragment DIGIT     : [0-9];
fragment BIN_DIGIT : [01];
fragment HEX_DIGIT : [0-9a-f];
fragment CHARACTER : (~['"]);

// Integer literals
DEC_INT_LITERAL : DIGIT+;           // Decimal integer
BIN_INT_LITERAL : '0b' BIN_DIGIT+;  // Binary integer
HEX_INT_LITERAL : '0x' HEX_DIGIT+;  // Hexadecimal integer

// Boolean literal
BOOL_LITERAL : 'true' | 'false';

// Character Literal
CHAR_LITERAL : '\'' CHARACTER '\'';

// String Literal
STRING_LITERAL : '"' CHARACTER* '"';

// === DATA TYPES =============================================================
// Integer types
TYPE_INT : 'int';

// Boolean type
TYPE_BOOL : 'bool';

// Character type
TYPE_CHAR : 'char';

// String type
TYPE_STRING : 'string';

// Identifier
IDENTIFIER : LETTER (LETTER | DIGIT)*;

// === IGNORED TOKENS =========================================================
COMMENT    : '//' (~[\n])* -> skip;
WHITESPACE : [ \t\r\n]+ -> skip;