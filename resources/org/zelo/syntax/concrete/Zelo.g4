grammar Zelo;

module
    : 'модул' qualifiedName use* function*         #moduleWithoutExports
    | 'модул' qualifiedName exports use* function* #moduleWithExports
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
    : type name=NAME ':' declarations+=declaration (',' declarations+=declaration)* '.' #typedFunction
    | name=NAME ':' declarations+=declaration (',' declarations+=declaration)* '.'      #untypedFunction
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
    : literal                                             #literalExpression
    | NAME                                                #symbol
    | '(' nativeFunction args+=expression+ ')'            #callNative
    | '(' caller=expression args+=expression+ ')'         #callDefined
    | lhs=expression '.' rhs=expression                   #composition
    ;

nativeFunction
    : '!='  #notEqual
    | '!'   #negation
    | '+'   #addition
    | '-'   #subtraction
    | '++'  #increment
    | '--'  #decrement
    | '*'   #multiplication
    | '/'   #division
    | '>'   #greaterThan
    | '<'   #lowerThan
    | '=='  #equal
    | '<='  #lowerThanOrEqual
    | '>='  #greaterThanOrEqual
    | '&&'  #logicalAnd
    | '||'  #logicalOr
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
TRUE: 'да' | 'вярно';
FALSE: 'не' | 'невярно';
NAME: [а-яА-Я][а-яА-Я0-9]*;

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
