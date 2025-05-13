grammar CoBabyBoL;

import CoBabyBoLTokens;

//options { caseInsensitive = true; }

program: identification_division data_division? procedure_division function* EOF;

// Divisions
identification_division: SOL IDENTIFICATION_DIVISION EOL clause*;
procedure_division: PROCEDURE_DIVISION EOL sentence*;
data_division: DATA_DIVISION EOL data_item*;
data_item: level IDENTIFIER (picture_clause | like_clause)* (occurs_clause)* EOL;
picture_clause: PICTURE IS representation;
like_clause: LIKE IDENTIFIER;
occurs_clause: OCCURS integer_literal TIMES;
function: IDENTIFIER EOL sentence*;

sentence: statement* EOL;
statement: SOL? (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract | loop | evaluate | next_sentence | stop);

accept: ACCEPT IDENTIFIER+;
alter: ALTER procedure_name TO PROCEED TO procedure_name;
goto: GOTO procedure_name;
if: IF boolean_expression THEN statement+ (ELSE statement+)? SOL? END+;
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
clause: clause_name EOL clause_value EOL;
clause_name: PROGRAM_ID | AUTHOR | INSTALLATION | DATE_WRITTEN | DATE_COMPILED | SECURITY;
clause_value: atomic;
argument_literal: '≡≡≡' literal '≡≡≡';
any_expression: boolean_expression;
boolean_expression: (NOT)* (literal | IDENTIFIER) (EQ_OP (literal | IDENTIFIER) ((AND | OR) boolean_expression)*)?;
atomic: IDENTIFIER | literal;

file_name: NONNUMERICLITERAL;
procedure_name: IDENTIFIER;
function_name: IDENTIFIER;
program_name: IDENTIFIER;
representation: atomic;
level: integer_literal;

numeric_literal: NUMERICLITERAL | ZERO | integer_literal;
integer_literal: INTEGERLITERAL;

literal: (numeric_literal | NONNUMERICLITERAL) (LPAREN (numeric_literal | NONNUMERICLITERAL) RPAREN)?;
