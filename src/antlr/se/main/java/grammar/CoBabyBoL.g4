grammar CoBabyBoL;

@header{package antlr.se.grammar;}

import CoBabyBoLTokens;

program
    : identification_division
      statement* EOF
    ;

statement: (accept | alter | goto | if | perform | signal | copy | display | call | add | divide | move | multiply | subtract) EOL;

argument_literal: '≡≡≡' LITERAL '≡≡≡';

boolean_expression
    : (NOT WS+)* ( LITERAL | IDENTIFIER ) WS+ EQ_OP WS+ ( LITERAL | IDENTIFIER ) ((AND | OR) boolean_expression)*
    ;

accept: ACCEPT WS+ IDENTIFIER;
alter: ALTER WS+ PROCEDURE_NAME WS+ TO WS+ PROCEED WS+ TO WS+ PROCEDURE_NAME;
goto: GOTO WS+ PROCEDURE_NAME;
if: IF WS+ boolean_expression WS+ THEN WS+ statement+ (WS+ ELSE WS+ statement+)? (WS+ END)+;
perform: PERFORM WS+ PROCEDURE_NAME (WS+ THROUGH WS+ PROCEDURE_NAME)? (WS+ ATOMIC WS+ TIMES)?;
signal: SIGNAL WS+ (PROCEDURE_NAME | OFF) WS+ ON_ERROR;
copy: COPY WS+ FILE_NAME (WS+ REPLACING (WS+ argument_literal WS+ BY WS+ argument_literal)+)?;
display: DISPLAY (WS+ ATOMIC (WS+ DELIMITED_BY WS+ (SIZE | SPACE | LITERAL))?)+ (WS+ WITH_NO_ADVANCING)?;
identification_division: IDENTIFICATION_DIVISION EOL clause+;
add: ADD WS+ ATOMIC+ WS+ TO WS+ ATOMIC (WS+ GIVING WS+ IDENTIFIER)*;
call: CALL WS+ FILE_NAME WS+ USING (BY_REFERENCE IDENTIFIER | BY_CONTENT ATOMIC | BY_VALUE ATOMIC)
    | CALL WS+ FUNCTION_NAME WS+ OF WS+ PROGRAM_NAME WS+ USING WS+ ((BY_REFERENCE | BY_CONTENT | BY_VALUE) WS+ ATOMIC WS+ (AS_PRIMITIVE | AS_STRUCT)) WS+ RETURNING WS+ ((BY_REFERENCE | BY_CONTENT | BY_VALUE) WS+ ATOMIC WS+ (AS_PRIMITIVE | AS_STRUCT));
divide: DIVIDE WS+ ATOMIC WS+ INTO WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER+ (WS+ REMAINDER IDENTIFIER)?)?;
move: MOVE WS+ (ATOMIC | HIGH_VALUES | LOW_VALUES | SPACES) WS+ TO IDENTIFIER+;
multiply: MULTIPLY WS+ ATOMIC WS+ BY WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER)?;
subtract: SUBTRACT WS+ ATOMIC+ WS+ FROM WS+ ATOMIC+ (WS+ GIVING WS+ IDENTIFIER)*;

// Identification division parts
clause
    : clause_name  EOL
      clause_value EOL
    ;
clause_name: PROGRAM_ID | AUTHOR | INSTALLATION | DATE_WRITTEN | DATE_COMPILED | SECURITY | TEXT_UNTIL_DOT;
clause_value: TEXT_UNTIL_DOT;
