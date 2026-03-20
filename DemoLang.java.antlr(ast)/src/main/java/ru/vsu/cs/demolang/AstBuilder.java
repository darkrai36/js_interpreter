package ru.vsu.cs.demolang;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.cs.demolang.antlr.*;

public class AstBuilder extends DemoLangBaseVisitor<AstNode> {

    @Override
    public AstNode visitNum(DemoLangParser.NumContext ctx) {
        return new NumNode(ctx.getText());
    }

    @Override
    public AstNode visitIdent(DemoLangParser.IdentContext ctx) {
        return new IdentNode(ctx.getText());
    }

    @Override
    public AstNode visitInc_dec(DemoLangParser.Inc_decContext ctx) {
        IdentNode lvalue = (IdentNode) visit(ctx.lvalue());

        if (ctx.getChild(0).getText().equals("++")) {
            return new IncDecNode(IncDecOp.PREFIX_INC, lvalue);
        } else if (ctx.getChild(0).getText().equals("--")) {
            return new IncDecNode(IncDecOp.PREFIX_DEC, lvalue);
        } else if (ctx.getChild(1).getText().equals("++")) {
            return new IncDecNode(IncDecOp.SUFFIX_INC, lvalue);
        } else if (ctx.getChild(1).getText().equals("--")) {
            return new IncDecNode(IncDecOp.SUFFIX_DEC, lvalue);
        } else {
            return lvalue;
        }
    }

    @Override
    public AstNode visitPrimary(DemoLangParser.PrimaryContext ctx) {
        if (ctx.num() != null) {
            return visit(ctx.num());
        } else if (ctx.lvalue() != null) {
            return visit(ctx.lvalue());
        } else if (ctx.inc_dec() != null) {
            return visit(ctx.inc_dec());
        } else if (ctx.call() != null) {
            return visit(ctx.call());
        } else if (ctx.expr() != null) {
            return visit(ctx.expr());
        }
        return null;
    }

    @Override
    public AstNode visitUnary(DemoLangParser.UnaryContext ctx) {
        if (ctx.getChildCount() == 2) {
            String opText = ctx.getChild(0).getText();
            ExprNode arg = (ExprNode) visit(ctx.unary());
            return new UnOpNode(UnOp.fromString(opText), arg);
        } else {
            return visit(ctx.primary());
        }
    }

    @Override
    public AstNode visitBinary(DemoLangParser.BinaryContext ctx) {
        if (ctx.unary() != null) {
            return visit(ctx.unary());
        } else {
            ExprNode left = (ExprNode) visit(ctx.binary(0));
            String opText = ctx.getChild(1).getText();
            ExprNode right = (ExprNode) visit(ctx.binary(1));
            return new BinOpNode(BinOp.fromString(opText), left, right);
        }
    }

    @Override
    public AstNode visitCall(DemoLangParser.CallContext ctx) {
        IdentNode name = (IdentNode) visit(ctx.ident());
        List<ExprNode> params = new ArrayList<>();
        for (DemoLangParser.ExprContext exprCtx : ctx.expr()) {
            params.add((ExprNode) visit(exprCtx));
        }
        return new CallNode(name, params);
    }

    @Override
    public AstNode visitIdent_assign(DemoLangParser.Ident_assignContext ctx) {
        IdentNode var = (IdentNode) visit(ctx.ident());
        ExprNode val = (ExprNode) visit(ctx.expr());
        return new AssignNode(var, val);
    }

    @Override
    public AstNode visitAssign(DemoLangParser.AssignContext ctx) {
        ExprNode var = (ExprNode) visit(ctx.lvalue());
        ExprNode val = (ExprNode) visit(ctx.expr());
        return new AssignNode(var, val);
    }

    @Override
    public AstNode visitReturn_stmt(DemoLangParser.Return_stmtContext ctx) {
        if (ctx.expr() != null) {
            ExprNode val = (ExprNode) visit(ctx.expr());
            return new ReturnNode(val);
        }
        return new ReturnNode();
    }

    @Override
    public AstNode visitType(DemoLangParser.TypeContext ctx) {
        if (ctx.ident() != null) {
            return visit(ctx.ident());
        }
        return new IdentNode(ctx.getText());
    }

    @Override
    public AstNode visitVars_decl(DemoLangParser.Vars_declContext ctx) {
        IdentNode type = (IdentNode) visit(ctx.type());

        List<ExprNode> vars = new ArrayList<>();
        vars.add((ExprNode) visit(ctx.ident_or_ident_assign(0)));
        for (int i = 1; i < ctx.ident_or_ident_assign().size(); i++) {
            vars.add((ExprNode) visit(ctx.ident_or_ident_assign(i)));
        }

        return new VarsDeclNode(type, vars);
    }

    @Override
    public AstNode visitExpr_or_empty(DemoLangParser.Expr_or_emptyContext ctx) {
        if (ctx.expr() != null) {
            return visit(ctx.expr());
        }
        return null;
    }

    @Override
    public AstNode visitIf_stmt(DemoLangParser.If_stmtContext ctx) {
        ExprNode cond = (ExprNode) visit(ctx.expr());
        StmtNode thenStmt = (StmtNode) visit(ctx.stmt(0));
        StmtNode elseStmt = ctx.stmt().size() > 1 ? (StmtNode) visit(ctx.stmt(1)) : null;
        return new IfNode(cond, thenStmt, elseStmt);
    }

    @Override
    public AstNode visitWhile_stmt(DemoLangParser.While_stmtContext ctx) {
        ExprNode cond = (ExprNode) visit(ctx.expr());
        StmtNode body = (StmtNode) visit(ctx.stmt());
        return new WhileNode(cond, body);
    }

    @Override
    public AstNode visitFor_stmt(DemoLangParser.For_stmtContext ctx) {
        StmtNode init = (StmtNode) visit(ctx.simple_stmt(0));
        ExprNode cond = (ExprNode) visit(ctx.expr_or_empty());
        StmtNode next = (StmtNode) visit(ctx.simple_stmt(1));
        StmtNode body = (StmtNode) visit(ctx.stmt());
        return new ForNode(init, cond, next, body);
    }

    @Override
    public AstNode visitFunc_param(DemoLangParser.Func_paramContext ctx) {
        IdentNode type = (IdentNode) visit(ctx.type());
        IdentNode name = (IdentNode) visit(ctx.ident());
        List<ExprNode> vars = new ArrayList<>();
        vars.add(name);
        return new VarsDeclNode(type, vars);
    }

    @Override
    public AstNode visitFunc(DemoLangParser.FuncContext ctx) {
        IdentNode type = (IdentNode) visit(ctx.type());
        IdentNode name = (IdentNode) visit(ctx.ident());

        List<VarsDeclNode> params = new ArrayList<>();
        for (DemoLangParser.Func_paramContext paramCtx : ctx.func_param()) {
            params.add((VarsDeclNode) visit(paramCtx));
        }

        StmtNode bodyNode = (StmtNode) visit(ctx.stmt_list());
        return new FuncNode(type, name, params, bodyNode);
    }

    @Override
    public AstNode visitSimple_stmt(DemoLangParser.Simple_stmtContext ctx) {
        if (ctx.call() != null) {
            return visit(ctx.call());
        } else if (ctx.assign() != null) {
            return visit(ctx.assign());
        } else if (ctx.vars_decl() != null) {
            return visit(ctx.vars_decl());
        } else if (ctx.return_stmt() != null) {
            return visit(ctx.return_stmt());
        } else if (ctx.getText().equals("break")) {
            return new BreakNode();
        } else if (ctx.getText().equals("continue")) {
            return new ContinueNode();
        } else if (ctx.inc_dec() != null) {
            return visit(ctx.inc_dec());
        }
        return null;
    }

    @Override
    public AstNode visitStmt(DemoLangParser.StmtContext ctx) {
        if (ctx.simple_stmt() != null) {
            return visit(ctx.simple_stmt());
        } else if (ctx.stmt_list() != null) {
            return visit(ctx.stmt_list());
        } else if (ctx.if_stmt() != null) {
            return visit(ctx.if_stmt());
        } else if (ctx.while_stmt() != null) {
            return visit(ctx.while_stmt());
        } else if (ctx.for_stmt() != null) {
            return visit(ctx.for_stmt());
        } else if (ctx.func() != null) {
            return visit(ctx.func());
        }
        return null;
    }

    @Override
    public AstNode visitStmt_list(DemoLangParser.Stmt_listContext ctx) {
        List<StmtNode> stmts = new ArrayList<>();
        for (DemoLangParser.StmtContext stmtCtx : ctx.stmt()) {
            AstNode stmt = visit(stmtCtx);
            if (stmt != null) {
                stmts.add((StmtNode) stmt);
            }
        }
        return new StmtListNode(stmts);
    }

    @Override
    public AstNode visitProg(DemoLangParser.ProgContext ctx) {
        return visit(ctx.stmt_list());
    }

    @Override
    public AstNode visitStart(DemoLangParser.StartContext ctx) {
        return visit(ctx.prog());
    }
}