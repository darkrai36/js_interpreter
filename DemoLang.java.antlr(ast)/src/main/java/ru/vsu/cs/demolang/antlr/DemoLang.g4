grammar DemoLang;

NUMBER: [0-9]+ ('.' [0-9]+)?;
CNAME: [a-zA-Z_][a-zA-Z0-9_]*;

COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

WS: [ \t\r\n]+ -> skip;

num: NUMBER;
ident: CNAME;

lvalue: ident;

inc_dec: '--' lvalue
    | '++' lvalue
    | lvalue '--'
    | lvalue '++'
;

primary: num
    | lvalue
    | inc_dec
    | '(' expr ')'
    | call
;

unary: ('+' | '-' | '!') unary
    | primary
;

binary: binary ('*' | '/' | '%') binary
    | binary ('+' | '-') binary
    | binary ('>' | '>=' | '<' | '<=') binary
    | binary ('==' | '!=') binary
    | binary '&&' binary
    | binary '||' binary
    | unary
;

expr: binary;

call: ident '(' (expr (',' expr)*)? ')';

ident_assign: ident '=' expr;
assign: lvalue '=' expr;

ident_or_ident_assign: ident | ident_assign;
vars_decl: type ident_or_ident_assign (',' ident_or_ident_assign)*;

type: 'int' | 'string' | 'void' | ident;

return_stmt: 'return' expr?;

simple_stmt: call
    | assign
    | vars_decl
    | return_stmt
    | 'break'
    | 'continue'
    | inc_dec
    | // empty
;

expr_or_empty: expr
    | // empty
;

if_stmt: 'if' '(' expr ')' stmt ('else' stmt)?;

while_stmt: 'while' '(' expr ')' stmt;

for_stmt: 'for' '(' simple_stmt ';' expr_or_empty ';' simple_stmt ')' stmt;

func_param: type ident;
func: type ident '(' (func_param (',' func_param)*)? ')' '{' stmt_list '}';

stmt: simple_stmt ';'
    | '{' stmt_list '}'
    | if_stmt
    | while_stmt
    | for_stmt
    | func
;

stmt_list: (stmt ';'*)*;

prog: stmt_list;

start: prog EOF;
