parser grammar CoBabyBoL;

options { tokenVocab=CoBabyBoLLexer; }

program: identification_division data_division? procedure_division? function* EOF;

// Identification division mode
identification_division: SOL IDENTIFICATION_DIVISION identification_clause*;
identification_clause: clause_name clause_value;
clause_name: ID_NAME;
clause_value: ID_VALUE;

// Data division mode
data_division: DATA_DIVISION EOL data_item*;
data_item: level IDENTIFIER (picture_clause | like_clause)* (occurs_clause)* EOL;
picture_clause: PICTURE IS representation;
like_clause: LIKE IDENTIFIER;
occurs_clause: OCCURS INTEGERLITERAL TIMES;
representation: IDENTIFIER;

//pic_symbol
//    : sign
//    | digit
//    | scale
//    | decimal
//    | edit_char
//    ;

//sign       : 'S';
//digit      : '9' (LPAREN INTEGERLITERAL RPAREN)?;
//scale      : 'P'+;
//decimal    : 'V';
//edit_char  : 'Z' (LPAREN INTEGERLITERAL RPAREN)?
//           | '*' (LPAREN INTEGERLITERAL RPAREN)?
//           | ',' | '.' | '$'
//           | '+' | '-'
//           ;




// Procedure division mode
procedure_division: PROCEDURE_DIVISION EOL sentence*;
sentence: statement* EOL;
statement: SOL? (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract | loop | evaluate | next_sentence | stop);




// DUNNO YET
function: IDENTIFIER EOL sentence*;

// Statements
accept: ACCEPT IDENTIFIER+;
alter: ALTER procedure_name TO PROCEED TO procedure_name;
goto: GOTO procedure_name;
if: IF boolean_expression SOL? THEN SOL? statement+ (SOL? ELSE statement+)? SOL? END?;
perform: PERFORM procedure_name (THROUGH procedure_name)? (atomic TIMES)?;
signal: SIGNAL (procedure_name | OFF) ON_ERROR;
copy: COPY file_name (REPLACING (argument_literal BY argument_literal)+)?;
display: DISPLAY ((atomic)+ (DELIMITED_BY (SIZE | SPACE | literal))?)+ WITH_NO_ADVANCING?;
add: ADD atomic+ TO atomic (GIVING IDENTIFIER)*;
call: CALL file_name (USING (BY_REFERENCE IDENTIFIER | BY_CONTENT atomic | BY_VALUE atomic)+)*
      CALL (function_name OF)* program_name ( USING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT))+)* (RETURNING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT)))*;
divide: DIVIDE atomic INTO atomic+ (GIVING IDENTIFIER+ (REMAINDER IDENTIFIER)?)?;
move: MOVE (atomic | HIGH_VALUES | LOW_VALUES | SPACES) TO IDENTIFIER+;
multiply: MULTIPLY atomic BY atomic+ (GIVING IDENTIFIER)?;
subtract: SUBTRACT atomic+ FROM atomic+ (GIVING IDENTIFIER)*;
loop: LOOP ((WHILE  boolean_expression | UNTIL  boolean_expression | statement | (VARYING IDENTIFIER? (FROM atomic)? (TO atomic)? (BY atomic)?)))+ SOL? END;
evaluate: EVALUATE any_expression (SOL? ALSO any_expression)* (SOL? when_clause SOL? statement+)+ SOL? END;
next_sentence: NEXT_SENTENCE;
stop: STOP;


// Parts
atomic_through: atomic (THROUGH  atomic)? (ALSO atomic_through)?;
when_clause: WHEN ((atomic_through | ( OTHER)));

argument_literal: ARG literal ARG;
any_expression: boolean_expression | math_expr;
math_expr: (numeric_literal | IDENTIFIER) (MATH_OP math_expr)?;

boolean_expression: NOT boolean_expression | (atomic (EQ_OP| AND | OR) atomic ((OR | AND) boolean_expression)*);

atomic: identifier | literal;
identifier: IDENTIFIER;

file_name: alphanumeric_literal;
procedure_name: IDENTIFIER;
function_name: IDENTIFIER;
program_name: IDENTIFIER;
//representation: ALPHANUMERIC LPAREN INTEGERLITERAL RPAREN;
level: INTEGERLITERAL;

literal: numeric_literal | alphanumeric_literal;
numeric_literal: NUMERICLITERAL | ZERO | INTEGERLITERAL;
alphanumeric_literal: STRINGLITERAL;
