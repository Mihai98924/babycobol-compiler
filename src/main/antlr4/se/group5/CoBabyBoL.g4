grammar CoBabyBoL;

import CoBabyBoLTokens;

program: identification_division data_division? procedure_division EOF;


// Divisions
identification_division: SOL IDENTIFICATION_DIVISION EOL clause*;
procedure_division: PROCEDURE_DIVISION EOL statement*;
data_division: SOL DATA_DIVISION (LEVEL IDENTIFIER (((PICTURE IS REPRESENTATION) | (LIKE IDENTIFIER)))? (OCCURS INTEGER_LITERAL TIMES)? EOL)* EOL;

statement: (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract | loop | evaluate | next_sentence | stop) EOL;

accept: ACCEPT IDENTIFIER;
alter: ALTER procedure_name TO PROCEED TO procedure_name;
goto: GOTO procedure_name;
if: IF boolean_expression THEN statement+ (ELSE statement+)? END+;
perform: PERFORM procedure_name (THROUGH procedure_name)? (atomic TIMES)?;
signal: SIGNAL (procedure_name | OFF) ON_ERROR;
copy: COPY file_name (REPLACING (argument_literal BY argument_literal)+)?;


display: DISPLAY (atomic (DELIMITED_BY (SIZE | SPACE | LITERAL))?)+ WITH_NO_ADVANCING?;


add: ADD atomic+ TO atomic (GIVING IDENTIFIER)*;
call: CALL file_name (USING (BY_REFERENCE IDENTIFIER | BY_CONTENT atomic | BY_VALUE atomic)+)*
      CALL (function_name OF)* program_name ( USING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT))+)* (RETURNING ((BY_REFERENCE | BY_CONTENT | BY_VALUE) atomic (AS_PRIMITIVE | AS_STRUCT)))*;
divide: DIVIDE atomic INTO atomic+ (GIVING IDENTIFIER+ (REMAINDER IDENTIFIER)?)?;
move: MOVE (atomic | HIGH_VALUES | LOW_VALUES | SPACES) TO IDENTIFIER+;
multiply: MULTIPLY atomic BY atomic+ (GIVING IDENTIFIER)?;
subtract: SUBTRACT atomic+ FROM atomic+ (GIVING IDENTIFIER)*;
loop: LOOP ((WHILE  boolean_expression | UNTIL  boolean_expression | statement) VARYING IDENTIFIER* (FROM atomic)* (TO atomic)* (BY atomic)*)+ END;
evaluate: EVALUATE any_expression (ALSO any_expression)* (when_clause statement+)+  END;
next_sentence: NEXT_SENTENCE;
stop: STOP;

// Parts
atomic_through: atomic (THROUGH  atomic)? (ALSO atomic_through)?;
when_clause: WHEN (( atomic_through | ( OTHER)));
clause: clause_name EOL clause_value EOL;
clause_name: PROGRAM_ID | AUTHOR | INSTALLATION | DATE_WRITTEN | DATE_COMPILED | SECURITY;
clause_value: atomic;
argument_literal: '≡≡≡' LITERAL '≡≡≡';
any_expression: boolean_expression;
boolean_expression: (NOT)* (LITERAL | IDENTIFIER) EQ_OP (LITERAL | IDENTIFIER) ((AND | OR) boolean_expression)*;
atomic: IDENTIFIER | LITERAL;

file_name: ALPHANUMERIC_LITERAL;
procedure_name: IDENTIFIER;
function_name: IDENTIFIER;
program_name: IDENTIFIER;