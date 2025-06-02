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
    : IDENTIFIER EOL sentence*
    ;

// === IDENTIFICATION DIVISION ==================================
identification_clause
    : ID_LINE clause_name ID_NAME_VAL_END clause_value ID_VALUE_VAL_END
    ;

clause_name   : ID_NAME_VAL ;
clause_value  : ID_VALUE_VAL ;

// === DATA DIVISION ============================================
data_item
    : DD_LINE level WS* IDENTIFIER WS*
      (picture_clause | like_clause)* WS*
      occurs_clause*
      EOL
    ;

picture_clause : PICTURE_IS REPRESENTATION ;
like_clause    : LIKE WS* identifier;
occurs_clause  : OCCURS INTEGERLITERAL TIMES ;

// === PROCEDURE DIVISION =======================================
sentence
    : statement* EOL
    ;

statement
    : CODE_LINE WS* (
          accept
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
      )
    ;

// ── STATEMENTS ────────────────────────────────────────────────
accept      : ACCEPT IDENTIFIER+ ;
alter       : ALTER procedure_name TO PROCEED TO procedure_name ;
goto        : GOTO procedure_name ;

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

signal      : SIGNAL (procedure_name | OFF) ON_ERROR ;

copy
    : COPY file_name
      (REPLACING (argument_literal BY argument_literal)+)?
    ;

// ── DISPLAY ───────────────────────────────────────────────────
display
    : DISPLAY WS* display_atomic_clause+ WITH_NO_ADVANCING?
    ;

display_atomic_clause
    : atomic WS*
      (DELIMITED_BY (SIZE | SPACE | literal))?
      WS*
    ;

// ── CALL & MOVE ───────────────────────────────────────────────
call
    : CALL call_function* file_name using_clause? returning_clause?
    ;

call_function
    : function_name OF
    ;

by_reference
    : BY_REFERENCE atomic
    ;

by_content
    : BY_CONTENT atomic
    ;

by_value
    : BY_VALUE atomic
    ;

by_clause
    : by_reference
    | by_content
    | by_value
    ;

by_with_as
    : by_clause as_clause*
    ;

using_clause
    : USING by_with_as+
    ;

returning_clause
    : RETURNING by_with_as+
    ;

as_clause
    : AS_PRIMITIVE
    | AS_STRUCT
    ;

move
    : MOVE move_arg TO identifier+
    ;

move_arg
    : atomic | HIGH_VALUES | LOW_VALUES | SPACES
    ;


// ── ARITHMETIC ────────────────────────────────────────────────
add
    : add_atomic to_atomic giving_identifier_list?
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
              WHILE   boolean_expression
            | UNTIL   boolean_expression
            | statement
            | VARYING IDENTIFIER?
                (FROM atomic)?
                (TO   atomic)?
                (BY   atomic)?
          )
      )+ DEFAULT_LINE? END
    ;

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
          NOT boolean_expression
        | LPAR boolean_expression RPAR
        | atomic (EQ_OP atomic)? boolean_eq_expression?
        | LPAR boolean_expression RPAR boolean_eq_expression?
      )
    ;

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

file_name      : atomic ;
procedure_name : IDENTIFIER ;
function_name  : alphanumeric_literal ;
program_name   : IDENTIFIER ;
level          : LEVEL ;