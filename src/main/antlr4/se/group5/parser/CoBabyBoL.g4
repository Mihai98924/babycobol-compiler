parser grammar CoBabyBoL;

options {
    tokenVocab=CoBabyBoLLexer;
}

program: identification_division data_division? procedure_division? function* EOF;
function: IDENTIFIER EOL sentence*;

// Identification division
identification_division: SOL IDENTIFICATION_DIVISION EOL identification_clause*;
identification_clause: WS* clause_name EOL clause_value EOL;
clause_name: PROGRAM_ID | AUTHOR | INSTALLATION | DATE_WRITTEN | DATE_COMPILED | SECURITY | IDENTIFIER;
clause_value: atomic;

// Procedure division
procedure_division: PROCEDURE_DIVISION EOL sentence*;
sentence: statement* EOL;
statement: SOL? (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract | loop | evaluate | next_sentence | stop);

// Data division
data_division: DATA_DIVISION EOL data_item*;
data_item: level IDENTIFIER (picture_clause | like_clause)* (occurs_clause)* EOL;
picture_clause: PICTURE_IS REPRESENTATION;
like_clause: LIKE IDENTIFIER;
occurs_clause: OCCURS INTEGERLITERAL TIMES;

// Statements
accept: ACCEPT IDENTIFIER+;
alter: ALTER procedure_name TO PROCEED TO procedure_name;
goto: GOTO procedure_name;
if: IF boolean_expression SOL? THEN SOL? statement+ (SOL? ELSE statement+)? SOL? END?;
perform: PERFORM procedure_name (THROUGH procedure_name)? (atomic TIMES)?;
signal: SIGNAL (procedure_name | OFF) ON_ERROR;
copy: COPY file_name (REPLACING (argument_literal BY argument_literal)+)?;
display: DISPLAY ((atomic)+ (DELIMITED_BY (SIZE | SPACE | literal))?)+ WITH_NO_ADVANCING?;
add: ADD ADD_REPRESENTATION atomic (GIVING IDENTIFIER)*;
call: CALL file_name (USING (BY_REFERENCE IDENTIFIER | BY_CONTENT atomic | BY_VALUE atomic)+)* |
      CALL (function_name OF)* program_name ( USING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT))+)* (RETURNING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT)))*;
divide: DIVIDE atomic INTO atomic+ (GIVING IDENTIFIER+ (REMAINDER REM_REPRESENTATION)?)?;
move: MOVE (atomic | HIGH_VALUES | LOW_VALUES | SPACES) TO IDENTIFIER+ (OF IDENTIFIER+)*;
multiply: MULTIPLY atomic BY atomic+ (GIVING IDENTIFIER)?;
subtract: SUBTRACT atomic+ FROM atomic+ (GIVING IDENTIFIER)*;
loop: LOOP (SOL? ( WHILE  boolean_expression | UNTIL  boolean_expression | statement | (VARYING IDENTIFIER? (FROM atomic)? (TO atomic)? (BY atomic)?)))+ SOL? END;
evaluate: EVALUATE any_expression (SOL? ALSO any_expression)* (SOL? when_clause SOL? statement+)+ SOL? END;
next_sentence: NEXT_SENTENCE;
stop: STOP;


// Parts
atomic_through: atomic (THROUGH  atomic)? (ALSO atomic_through)?;
when_clause: WHEN ((atomic_through | ( OTHER)));

argument_literal: ARG_LIT literal ARG_LIT;
any_expression: boolean_expression | math_expr | string_expr;
math_expr: (LPAR math_expr RPAR | numeric_literal | IDENTIFIER) (MATH_OP math_expr)?;
string_expr: (literal | IDENTIFIER) (MATH_OP string_expr)?;

boolean_expression: SOL?
(NOT boolean_expression |
 LPAR boolean_expression RPAR |
  atomic (EQ_OP atomic)? boolean_eq_expression? |
  LPAR boolean_expression RPAR boolean_eq_expression?
);
boolean_eq_expression: SOL? (OR | AND | XOR) SOL? (IDENTIFIER (EQ_OP atomic)? | EQ_OP? atomic | LPAR boolean_expression RPAR) boolean_eq_expression?;

atomic: identifier | literal;
identifier: IDENTIFIER;

file_name: alphanumeric_literal;
procedure_name: IDENTIFIER;
function_name: IDENTIFIER;
program_name: IDENTIFIER;
level: INTEGERLITERAL;

literal: numeric_literal | alphanumeric_literal;
numeric_literal: NUMERICLITERAL | ZERO | INTEGERLITERAL;
alphanumeric_literal: STRINGLITERAL;