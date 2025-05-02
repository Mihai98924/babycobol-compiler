grammar Potato;

@header{package ut.pp.parser.antlr4;}

import PotatoTokens;

program
    : statement* EOF
    ;

// === STATEMENTS =============================================================
statement
    : declaration ';'
    | assignment ';'
    | if
    | while
    | scope
    | print ';'
    | fork ';'
    | synchronizedBlock
    ;

declaration
    : SHARED? type '[]' IDENTIFIER
        '=' (expression | array_literal)                #arrayDeclaration
    | SHARED? type IDENTIFIER '=' expression            #primitiveDeclaration
    | SHARED? TYPE_STRING IDENTIFIER '=' expression     #stringDeclaration
    | LOCK IDENTIFIER                                   #lockDeclaration
    ; // Locks are only used in a threading context so Shared keyword is not needed.

synchronizedBlock
    : SYNC IDENTIFIER scope
    ;

assignment
    : IDENTIFIER '=' (expression|array_literal)         #assignVariable
    | IDENTIFIER '[' index=expression ']'
        '=' expr=expression                             #assignArrayElement
    ;

if
    : IF '(' expression ')' trueScope=scope (ELSE elseScope=scope)?
    ;

while
    : WHILE '(' expression ')' scope
    ;

scope
    : '{' statement* '}'
    ;

fork
    : FORK parallelScope statement* JOIN
    ;

parallelScope
    : '{' statement* '}'
    ;

print
    : PRINT expression
    | PRINT_NL expression
    ;

// === EXPRESSIONS ============================================================
expression
    : expression_l1                                     #exprLevel1
    | left=expression '&&' right=expression_l1          #logicalAnd
    | left=expression '||' right=expression_l1          #logicalOr
    ;

expression_l1
    : expression_l2                                     #exprLevel2
    | left=expression_l1 '==' right=expression_l2       #equals
    | left=expression_l1 '!=' right=expression_l2       #notEquals
    | left=expression_l1 '<'  right=expression_l2       #lessThan
    | left=expression_l1 '<=' right=expression_l2       #lessThanEquals
    | left=expression_l1 '>'  right=expression_l2       #greaterThan
    | left=expression_l1 '>=' right=expression_l2       #greaterThanEquals
    ;

expression_l2
    : expression_l3                                     #exprLevel3
    | left=expression_l2 '+' right=expression_l3        #addition
    | left=expression_l2 '-' right=expression_l3        #subtraction
    ;

expression_l3
    : expression_l4                                     #exprLevel4
    | left=expression_l3 '*' right=expression_l4        #multiplication
    | left=expression_l4 '++' right=expression_l3       #concatenation
    ;

expression_l4
    : expression_l5                                     #exprLevel5
    | '-' expression_l5                                 #negation
    | '!' expression_l5                                 #logicNot
    ;

expression_l5
    : literal                                           #literalValue
    | variable                                          #variableCall
    | input                                             #userInput
    | '(' expression ')'                                #parenthesis
    | LENGTH arrayExpression=expression_l3              #length
    ;

input
    : INPUT request=STRING_LITERAL
    ;

// === TYPES, VARIABLES & LITERALS ============================================
type
    : TYPE_INT                                          #intType
    | TYPE_BOOL                                         #boolType
    | TYPE_CHAR                                         #charType
    ;

variable
    : IDENTIFIER '[' expression ']'                     #arrayAccess
    | IDENTIFIER                                        #variableAccess
    ;

literal
    : DEC_INT_LITERAL                                   #decIntLiteral
    | BIN_INT_LITERAL                                   #binIntLiteral
    | HEX_INT_LITERAL                                   #hexIntLiteral
    | BOOL_LITERAL                                      #boolLiteral
    | CHAR_LITERAL                                      #charLiteral
    | STRING_LITERAL                                    #stringLiteral
    ;

array_literal
    : '[' expression (',' expression)* ']'              #arrayLiteral
    | NEW type '(' expression ')'                       #newArray
    ;
