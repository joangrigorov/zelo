grammar Zelo;

module
    : 'модул' qualifiedName exports? use* function*
    ;

exports
    : 'разкрива' names
    ;

names
    : nameList+=NAME (',' nameList+=NAME)*
    ;

use
    : 'ползва' qualifiedName
    ;

qualifiedName
    : (parts+=NAME '::')* finalName=NAME
    ;

function
    : type name=NAME ':' declarations+=declaration (',' declarations+=declaration)* '.'
    ;

declaration
    : signature=patterns '->' expression
    ;

patterns
    : patternList+=pattern (',' patternList+=pattern)*
    ;

pattern
    : type NAME     #typedArgumentPattern
    | literal       #literalPattern
    ;

literal
    : TRUE      #booleanTrueLiteral
    | FALSE     #booleanFalseLiteral
    | INTEGER   #integerLiteral
    | FLOAT     #floatLiteral
    | STRING    #stringLiteral
    ;

expression
    : literal                        #literalExpression
    | NAME                           #identifier
    | expression expression+         #call
    | expression '.' expression      #composition
    | '(' expression ')'             #group
    | '!' expression                 #negation
    | '+' expression expression      #addition
    | '-' expression expression      #subtraction
    | '++' expression                #increment
    | '--' expression                #decrement
    | '*' expression expression      #multiplication
    | '/' expression expression      #division
    | '>' expression expression      #greaterThan
    | '<' expression expression      #lowerThan
    | '==' expression expression     #equals
    | '!=' expression expression     #notEqual
    | '<=' expression expression     #lowerThanOrEqual
    | '>=' expression expression     #greaterThanOrEqual
    | '&&' expression expression     #logicalAnd
    | '||' expression expression     #logicalOr
    ;

type
    : TYPE_STRING   #typeString
    | TYPE_INTEGER  #typeInteger
    | TYPE_REAL     #typeReal
    | TYPE_BOOLEAN  #typeBoolean
    ;

TYPE_STRING: 'низ';
TYPE_INTEGER: 'цял' | 'цяло' | 'цяла' | 'цели';
TYPE_REAL: 'реален' | 'реално' | 'реална' | 'реални';
TYPE_BOOLEAN: 'булев' | 'булево' | 'булева' | 'булеви';

INTEGER: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+;
STRING : '"' (ESCAPE_QUOTE | ~ ["\\])* '"';
TRUE: 'вярно' | 'да';
FALSE: 'не' | 'невярно';
NAME: [а-яА-Я]+;

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
