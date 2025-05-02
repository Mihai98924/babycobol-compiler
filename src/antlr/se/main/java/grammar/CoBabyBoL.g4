grammar CoBabyBoL;

@header{package antlr.se.grammar;}

import CoBabyBoLTokens;

program
    : identification_division
      statement* EOF
    ;

identification_division: IDENTIFICATION_DIVISION EOL clause+;

statement: (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract | loop | data_division | evaluate | next_sentence | stop) EOL;

accept: ACCEPT WS+ IDENTIFIER;
alter: ALTER WS+ PROCEDURE_NAME WS+ TO WS+ PROCEED WS+ TO WS+ PROCEDURE_NAME;
goto: GOTO WS+ PROCEDURE_NAME;
if: IF WS+ boolean_expression WS+ THEN WS+ statement+ (WS+ ELSE WS+ statement+)? (WS+ END)+;
perform: PERFORM WS+ PROCEDURE_NAME (WS+ THROUGH WS+ PROCEDURE_NAME)? (WS+ ATOMIC WS+ TIMES)?;
signal: SIGNAL WS+ (PROCEDURE_NAME | OFF) WS+ ON_ERROR;
copy: COPY WS+ FILE_NAME (WS+ REPLACING (WS+ argument_literal WS+ BY WS+ argument_literal)+)?;
display: DISPLAY (WS+ ATOMIC (WS+ DELIMITED_BY WS+ (SIZE | SPACE | LITERAL))?)+ (WS+ WITH_NO_ADVANCING)?;
add: ADD WS+ ATOMIC+ WS+ TO WS+ ATOMIC (WS+ GIVING WS+ IDENTIFIER)*;
call: CALL WS+ FILE_NAME (WS+ USING (BY_REFERENCE IDENTIFIER | BY_CONTENT ATOMIC | BY_VALUE ATOMIC)+)*
      CALL WS+ (FUNCTION_NAME WS+ OF + WS+)* PROGRAM_NAME (WS+ USING WS+ ((BY_REFERENCE | BY_CONTENT | BY_VALUE) WS+ ATOMIC WS+ (AS_PRIMITIVE | AS_STRUCT))+ WS+)* (RETURNING WS+ ((BY_REFERENCE | BY_CONTENT | BY_VALUE) WS+ ATOMIC WS+ (AS_PRIMITIVE | AS_STRUCT)))*;
divide: DIVIDE WS+ ATOMIC WS+ INTO WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER+ (WS+ REMAINDER IDENTIFIER)?)?;
move: MOVE WS+ (ATOMIC | HIGH_VALUES | LOW_VALUES | SPACES) WS+ TO IDENTIFIER+;
multiply: MULTIPLY WS+ ATOMIC WS+ BY WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER)?;
subtract: SUBTRACT WS+ ATOMIC+ WS+ FROM WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER)*;
loop: LOOP WS+ ((WHILE WS+ boolean_expression | UNTIL WS+ boolean_expression | statement) WS+ VARYING WS+ IDENTIFIER* (FROM WS+ ATOMIC)* (TO WS+ ATOMIC)* (BY WS+ ATOMIC)*)+ WS+ END;
data_division: DATA_DIVISION WS* DOT (WS+ LEVEL WS+ IDENTIFIER (WS+ ((PICTURE WS+ IS WS+ REPRESENTATION) | (LIKE IDENTIFIER)))? (OCCURS INTEGER_LITERAL TIMES)? DOT)+;
evaluate: EVALUATE WS+ any_expression (WS+ ALSO WS+ any_expression)* (WS+ when_clause WS+ statement+)+ WS+ END;
next_sentence: NEXT_SENTENCE;
stop: STOP;

// Parts
atomic_through: ATOMIC (WS+ THROUGH WS+ ATOMIC)? (WS+ ALSO WS+ atomic_through)?;
when_clause: WHEN ((WS+ atomic_through | (WS+ OTHER)));
clause
    : clause_name  EOL
      clause_value EOL
    ;
clause_name: PROGRAM_ID | AUTHOR | INSTALLATION | DATE_WRITTEN | DATE_COMPILED | SECURITY | TEXT_UNTIL_DOT;
clause_value: TEXT_UNTIL_DOT;
argument_literal: '≡≡≡' LITERAL '≡≡≡';
any_expression: boolean_expression;
boolean_expression
    : (NOT WS+)* ( LITERAL | IDENTIFIER ) WS+ EQ_OP WS+ ( LITERAL | IDENTIFIER ) ((AND | OR) boolean_expression)*
    ;
