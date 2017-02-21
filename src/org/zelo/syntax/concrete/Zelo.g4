grammar Zelo;

module
    : 'модул' qualifiedName use* declaration*
    ;

use
    : 'ползва' qualifiedName ';'
    ;

qualifiedName
    : NAME ('::' NAME)*
    ;

declaration
    : type NAME '(' arguments? ')' '=' expression ';'
    | visibility type NAME '(' arguments? ')' '=' expression ';'
    ;

arguments
    : argument (',' argument)*
    ;

argument
    : type NAME
    | literal_pattern
    ;

literal_pattern
    : BOOLEAN
    | INTEGER
    | FLOAT
    | STRING
    ;

expression
    : BOOLEAN
    | INTEGER
    | FLOAT
    | STRING
    ;

visibility
    : PUBLIC
    | PRIVATE
    ;

type
    : TYPE_STRING
    | TYPE_INTEGER
    | TYPE_REAL
    | TYPE_BOOLEAN
    | type '[]'
    | '{' type ',' type '}'
    ;

TYPE_STRING: 'низ';
TYPE_INTEGER: 'цял' | 'цяло' | 'цяла' | 'цели';
TYPE_REAL: 'реален' | 'реално' | 'реална' | 'реални';
TYPE_BOOLEAN: 'булев' | 'булево' | 'булева' | 'булеви';

PUBLIC: 'публичен' | 'публично' | 'публична' | 'публични';
PRIVATE: 'личен' | 'лично' | 'лична' | 'лични';

INTEGER: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+;
STRING : '"' (ESCAPE_QUOTE | ~ ["\\])* '"';
NAME: [А-Яа-я]+;
BOOLEAN: 'вярно' | 'невярно';

WS:  [ \t\r\n\u000C]+ -> skip;

fragment ESCAPE_QUOTE
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;
