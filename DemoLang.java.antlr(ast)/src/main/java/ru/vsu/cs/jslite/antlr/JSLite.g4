grammar JSLite;

// Точка входа
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
    | ';' // пустой оператор
    ;

block: '{' stmtList '}';

// В JS переменные объявляются через let, var или const
varDecl: ('let' | 'var' | 'const') IDENTIFIER ('=' expr)?;

exprStmt: expr;

ifStmt: 'if' '(' expr ')' stmt ('else' stmt)?;

whileStmt: 'while' '(' expr ')' stmt;

forStmt: 'for' '(' (varDecl | exprStmt)? ';' expr? ';' expr? ')' stmt;

funcDecl: 'function' IDENTIFIER '(' paramList? ')' block;

paramList: IDENTIFIER (',' IDENTIFIER)*;

returnStmt: 'return' expr?;

// Выражения (с учетом приоритета операций)
expr: left=expr op=('*' | '/' | 'div' | 'mod' | '%') right=expr  # MulDivExpr
    | left=expr op=('+' | '-') right=expr                        # AddSubExpr
    | left=expr op=('<' | '<=' | '>' | '>=') right=expr          # RelExpr
    | left=expr op=('==' | '!=') right=expr                      # EqExpr
    | left=expr '=' right=expr                                   # AssignExpr // Присваивание - тоже выражение
    | primary                                                    # PrimaryExpr
    ;

primary: NUMBER                   # NumLiteral
       | STRING                   # StringLiteral
       | 'null'                   # NullLiteral
       | 'undefined'              # UndefLiteral
       | IDENTIFIER               # IdentLiteral
       | IDENTIFIER '(' argList? ')' # CallFunc
       | '(' expr ')'             # ParenExpr
       ;

argList: expr (',' expr)*;

// ЛЕКСЕР (Токены)
NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '"' (~["\\] | '\\' .)* '"' | '\'' (~['\\] | '\\' .)* '\'';
IDENTIFIER: [a-zA-Z_$] [a-zA-Z0-9_$]*;

COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
WS: [ \t\r\n]+ -> skip;