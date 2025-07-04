parser grammar CoBabyBoL;

options {
    tokenVocab = CoBabyBoLLexer;
}

// ── PROGRAM STRUCTURE ─────────────────────────────────────────
program
    : identification_division
      data_division?
      procedure_division?
      function*
      EOF
    ;

// ── DIVISIONS ─────────────────────────────────────────────────
identification_division
    : DEFAULT_LINE IDENTIFICATION_DIVISION EOL identification_clause*
    ;

data_division
    : DEFAULT_LINE DATA_DIVISION EOL data_item*
    ;

procedure_division
    : DEFAULT_LINE PROCEDURE_DIVISION EOL sentence*
    ;

// ── TOP-LEVEL FUNCTION ────────────────────────────────────────
function
    : DEFAULT_LINE IDENTIFIER EOL sentence*
    ;

// === IDENTIFICATION DIVISION ==================================
identification_clause
    : DEFAULT_LINE clause_name ID_END clause_value EOL
    ;

clause_name   : ID_NAME ;
clause_value  : ID_VALUE ;

// === DATA DIVISION ============================================
data_item
    : (DEFAULT_LINE copy EOL)
      | (
        DEFAULT_LINE level IDENTIFIER
        (picture_clause | like_clause)*
        occurs_clause*
        EOL
        )
    ;

picture_clause : PICTURE_IS REPRESENTATION ;
like_clause    : LIKE WS* identifier;
occurs_clause  : OCCURS INTEGERLITERAL TIMES ;

// === PROCEDURE DIVISION =======================================
sentence
    : (DEFAULT_LINE statement EOL)
    ;

statement
    : accept
    | alter
    | goto
    | if
    | perform
    | signal
    | copy
    | display
    | call
    | add
    | divide
    | move
    | multiply
    | subtract
    | loop
    | evaluate
    | next_sentence
    | stop
    ;

// ── STATEMENTS ────────────────────────────────────────────────
accept      : ACCEPT identifier+ ;
alter       : ALTER procedure_name TO PROCEED TO procedure_name ;
goto        : GOTO atomic ;

if
    : IF boolean_expression
      DEFAULT_LINE? THEN DEFAULT_LINE? statement+
      (DEFAULT_LINE? ELSE statement+)?
      DEFAULT_LINE? END?
    ;

perform
    : PERFORM procedure_name
      (THROUGH procedure_name)?
      (atomic TIMES)?
    ;

signal      : SIGNAL ((atomic ON_ERROR) | OFF) ;

copy
    : COPY file_name
      (REPLACING (argument_literal BY argument_literal)+)?
    ;

// ── DISPLAY ───────────────────────────────────────────────────
display
    : DISPLAY display_atomic_clause+ WITH_NO_ADVANCING?
    ;

display_atomic_clause
    : atomic
      (DELIMITED_BY (SIZE | SPACE | literal))?
    ;

// ── CALL ───────────────────────────────────────────────
call
    : CALL (function_name OF)? file_name using_clause? returning_clause?
    ;


by_clause
    : BY_REFERENCE
    | BY_CONTENT
    | BY_VALUE
    ;

as_clause
    : (AS_PRIMITIVE | AS_STRUCT)
    ;

using_clause
    : USING by_atomic_as+
    ;

by_atomic_as
    : by_clause? atomic as_clause?
    ;

returning_clause
    : RETURNING by_identifier_as+
    ;

by_identifier_as
    : by_clause? identifier as_clause?
    ;

// ── MOVE ───────────────────────────────────────────────
move
    : MOVE move_arg TO identifier+
    ;

move_arg
    : atomic | HIGH_VALUES | LOW_VALUES | SPACES
    ;

// ── ARITHMETIC ────────────────────────────────────────────────
add
    : add_atomic to_atomic giving_identifier_list? add?
    ;

add_atomic  : ADD atomic+ ;
to_atomic   : TO  atomic   ;

divide
    : DIVIDE atomic into_atomic (giving_identifier_list remainder?)?
    ;

into_atomic : INTO atomic+ ;
remainder   : REMAINDER IDENTIFIER ;

multiply
    : MULTIPLY atomic by_atomic giving_identifier?
    ;

by_atomic   : BY   atomic+ ;

subtract
    : sub_atomic from_atomic giving_identifier_list?
    ;

sub_atomic  : SUBTRACT atomic+ ;
from_atomic : FROM     atomic+ ;

giving_identifier      : GIVING IDENTIFIER ;
giving_identifier_list : GIVING IDENTIFIER* ;

// ── LOOP / EVALUATE ───────────────────────────────────────────
loop
    : LOOP (
          DEFAULT_LINE? (
            loop_statements
          )
      )+ DEFAULT_LINE? END
    ;

loop_statements: loop_while
                | loop_until
                | statement
                | loop_variables;

loop_variables: VARYING IDENTIFIER?
                loop_from?
                loop_to?
                loop_by?
    ;
loop_from: (FROM atomic);
loop_to: (TO atomic);
loop_by: (BY atomic);

loop_while: WHILE boolean_expression;
loop_until: UNTIL boolean_expression;

evaluate
    : EVALUATE any_expression
      (DEFAULT_LINE? ALSO any_expression)*
      (DEFAULT_LINE? when_clause DEFAULT_LINE? statement+)+
      DEFAULT_LINE? END
    ;

any_expression
    : boolean_expression
    | math_expr
    | string_expr
    ;

when_clause
    : WHEN (atomic_through | OTHER)
    ;

// ── PARTS & TERMINATORS ───────────────────────────────────────
next_sentence : NEXT_SENTENCE ;
stop          : STOP ;

// ── EXPRESSIONS & ATOMS ───────────────────────────────────────
atomic_through
    : atomic (THROUGH atomic)? (ALSO atomic_through)?
    ;

argument_literal : ARG_LIT ARG_LIT_ATOMIC;

math_expr
    : (LPAR math_expr RPAR | numeric_literal | IDENTIFIER)
      (MATH_OP math_expr)?
    ;

string_expr
    : (literal | IDENTIFIER)
      (MATH_OP string_expr)?
    ;

boolean_expression
    : DEFAULT_LINE? (
          boolean_negation
        | boolean_parenthesis
        | boolean_expression_main
        | boolean_parenthesis boolean_eq_expression?
      )
    ;

boolean_negation: NOT boolean_expression;
boolean_parenthesis: LPAR boolean_expression RPAR;
boolean_expression_main: atomic (EQ_OP atomic)? boolean_eq_expression?;

boolean_eq_expression
    : DEFAULT_LINE? (OR | AND | XOR) DEFAULT_LINE?
      ( IDENTIFIER (EQ_OP atomic)?
      | EQ_OP? atomic
      | LPAR boolean_expression RPAR
      )
      boolean_eq_expression?
    ;

atomic     : identifier | literal ;
identifier : IDENTIFIER (OF identifier)? ;

literal
    : numeric_literal
    | alphanumeric_literal
    ;

numeric_literal        : NUMERICLITERAL | ZERO | INTEGERLITERAL ;
alphanumeric_literal   : STRINGLITERAL ;

file_name      : alphanumeric_literal ;
procedure_name : IDENTIFIER ;
function_name  : alphanumeric_literal ;
program_name   : IDENTIFIER ;
level          : LEVEL ;