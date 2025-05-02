grammar CoBabyBoL;

@header{package antlr.se.grammar;}

import CoBabyBoLTokens;

program
    : statement* EOF
    ;


statement
    : accept '.'
    ;


accept
    : ACCEPT
    ;
