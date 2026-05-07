grammar JSLite;

program: stmtList EOF;

stmtList: stmt*;

stmt: block
    | varDecl ';'
    | exprStmt ';'
    | ifStmt
    | whileStmt
    | forStmt
    | funcDecl
    | returnStmt ';'
    | 'break' ';'
    | 'continue' ';'
    | ';'
    ;

block: '{' stmtList '}';

varDecl: ('let' | 'var' | 'const') IDENTIFIER ('=' expr)?;

exprStmt: expr;

ifStmt: 'if' '(' expr ')' stmt ('else' stmt)?;

whileStmt: 'while' '(' expr ')' stmt;

forStmt: 'for' '(' (varDecl | exprStmt)? ';' expr? ';' expr? ')' stmt;

funcDecl: 'function' IDENTIFIER '(' paramList? ')' block;

paramList: IDENTIFIER (',' IDENTIFIER)*;

returnStmt: 'return' expr?;

expr: left=expr '[' right=expr ']'                               # IndexExpr
    | left=expr '.' IDENTIFIER                                   # DotExpr
    | left=expr '(' argList? ')'                                 # CallExpr
    | left=expr op=('*' | '/' | 'div' | 'mod' | '%') right=expr  # MulDivExpr
    | left=expr op=('+' | '-') right=expr                        # AddSubExpr
    | left=expr op=('<' | '<=' | '>' | '>=') right=expr          # RelExpr
    | left=expr op=('==' | '!=') right=expr                      # EqExpr
    | left=expr '=' right=expr                                   # AssignExpr
    | primary                                                    # PrimaryExpr
    ;

primary: NUMBER                   # NumLiteral
       | STRING                   # StringLiteral
       | 'null'                   # NullLiteral
       | 'undefined'              # UndefLiteral
       | THIS                     # thisLiteral
       | IDENTIFIER               # IdentLiteral
       | '[' exprList? ']'        # ArrayLiteral
       | '{' hashList? '}'        # HashLiteral
       | '(' expr ')'             # ParenExpr
       | 'function' '(' paramList? ')' block # AnonFuncExpr
       ;

argList: expr (',' expr)*;
exprList: expr (',' expr)*;
hashList: hashElement (',' hashElement)*;
hashElement: (IDENTIFIER | STRING) ':' expr;

NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'';
THIS : 'this' ;
IDENTIFIER: [a-zA-Z_$] [a-zA-Z0-9_$]*;

COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
WS: [ \t\r\n]+ -> skip;