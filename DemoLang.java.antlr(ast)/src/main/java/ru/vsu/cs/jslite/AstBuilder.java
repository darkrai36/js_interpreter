package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.antlr.JSLiteBaseVisitor;
import ru.vsu.cs.jslite.antlr.JSLiteParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AstBuilder extends JSLiteBaseVisitor<AstNodes.AstNode> {

    @Override
    public AstNodes.AstNode visitProgram(JSLiteParser.ProgramContext ctx) {
        List<AstNodes.StmtNode> stmts = new ArrayList<>();
        if (ctx.stmtList() != null) {
            for (JSLiteParser.StmtContext stmtCtx : ctx.stmtList().stmt()) {
                // БЕРЕМ ТОЛЬКО ПЕРВОГО РЕБЕНКА (индекс 0), игнорируя точку с запятой ';'
                AstNodes.AstNode node = visit(stmtCtx.getChild(0));
                // Если ребенок - это просто текст "break" или "continue"
                if (node == null && (stmtCtx.getText().startsWith("break") || stmtCtx.getText().startsWith("continue"))) {
                    stmts.add(new AstNodes.ControlNode(stmtCtx.getText().replace(";", "")));
                    continue;
                }
                if (node instanceof AstNodes.StmtNode) {
                    stmts.add((AstNodes.StmtNode) node);
                }
            }
        }
        return new AstNodes.ProgramNode(stmts);
    }

    @Override
    public AstNodes.AstNode visitExprStmt(JSLiteParser.ExprStmtContext ctx) {
        // Оборачиваем выражение в Statement, чтобы оно могло лежать в списке команд программы
        AstNodes.ExprNode expr = (AstNodes.ExprNode) visit(ctx.expr());
        return new AstNodes.ExprStmtNode(expr);
    }

    @Override
    public AstNodes.AstNode visitVarDecl(JSLiteParser.VarDeclContext ctx) {
        String modifier = ctx.getChild(0).getText(); // let, var или const
        String name = ctx.IDENTIFIER().getText();
        AstNodes.ExprNode value = null;
        if (ctx.expr() != null) {
            value = (AstNodes.ExprNode) visit(ctx.expr());
        }
        return new AstNodes.VarDeclNode(modifier, name, value);
    }

    @Override
    public AstNodes.AstNode visitAddSubExpr(JSLiteParser.AddSubExprContext ctx) {
        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right);
    }

    @Override
    public AstNodes.AstNode visitMulDivExpr(JSLiteParser.MulDivExprContext ctx) {
        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right);
    }

    @Override
    public AstNodes.AstNode visitNumLiteral(JSLiteParser.NumLiteralContext ctx) {
        return new AstNodes.NumNode(Double.parseDouble(ctx.getText()));
    }

    @Override
    public AstNodes.AstNode visitIdentLiteral(JSLiteParser.IdentLiteralContext ctx) {
        return new AstNodes.IdentNode(ctx.getText());
    }

    @Override
    public AstNodes.AstNode visitAssignExpr(JSLiteParser.AssignExprContext ctx) {
        AstNodes.ExprNode target = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode value = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.AssignNode(target, value);
    }

    // Обработка блоков кода { ... }
    @Override
    public AstNodes.AstNode visitBlock(JSLiteParser.BlockContext ctx) {
        List<AstNodes.StmtNode> stmts = new ArrayList<>();
        if (ctx.stmtList() != null) {
            for (JSLiteParser.StmtContext stmtCtx : ctx.stmtList().stmt()) {
                AstNodes.AstNode node = visit(stmtCtx.getChild(0));
                // Если ребенок - это просто текст "break" или "continue"
                if (node == null && (stmtCtx.getText().startsWith("break") || stmtCtx.getText().startsWith("continue"))) {
                    stmts.add(new AstNodes.ControlNode(stmtCtx.getText().replace(";", "")));
                    continue;
                }
                if (node instanceof AstNodes.StmtNode) {
                    stmts.add((AstNodes.StmtNode) node);
                }
            }
        }
        return new AstNodes.BlockNode(stmts);
    }

    // Обработка If
    @Override
    public AstNodes.AstNode visitIfStmt(JSLiteParser.IfStmtContext ctx) {
        AstNodes.ExprNode condition = (AstNodes.ExprNode) visit(ctx.expr());
        AstNodes.StmtNode thenBranch = (AstNodes.StmtNode) visit(ctx.stmt(0));
        AstNodes.StmtNode elseBranch = null;
        if (ctx.stmt().size() > 1) { // Если есть блок else
            elseBranch = (AstNodes.StmtNode) visit(ctx.stmt(1));
        }
        return new AstNodes.IfNode(condition, thenBranch, elseBranch);
    }

    // Обработка While
    @Override
    public AstNodes.AstNode visitWhileStmt(JSLiteParser.WhileStmtContext ctx) {
        AstNodes.ExprNode condition = (AstNodes.ExprNode) visit(ctx.expr());
        AstNodes.StmtNode body = (AstNodes.StmtNode) visit(ctx.stmt());
        return new AstNodes.WhileNode(condition, body);
    }

    // Обработка логических операций (> < >= <=)
    @Override
    public AstNodes.AstNode visitRelExpr(JSLiteParser.RelExprContext ctx) {
        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right); // Переиспользуем узел бинарных операций
    }

    // Обработка операций равенства (== !=)
    @Override
    public AstNodes.AstNode visitEqExpr(JSLiteParser.EqExprContext ctx) {
        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right);
    }

    // --- ТИПЫ ДАННЫХ ---
    @Override
    public AstNodes.AstNode visitStringLiteral(JSLiteParser.StringLiteralContext ctx) {
        String text = ctx.getText();
        return new AstNodes.StringNode(text.substring(1, text.length() - 1)); // Убираем кавычки
    }

    @Override public AstNodes.AstNode visitNullLiteral(JSLiteParser.NullLiteralContext ctx) { return new AstNodes.NullNode(); }
    @Override public AstNodes.AstNode visitUndefLiteral(JSLiteParser.UndefLiteralContext ctx) { return new AstNodes.UndefNode(); }

    @Override
    public AstNodes.AstNode visitArrayLiteral(JSLiteParser.ArrayLiteralContext ctx) {
        List<AstNodes.ExprNode> elements = new ArrayList<>();
        if (ctx.exprList() != null) {
            for (JSLiteParser.ExprContext exprCtx : ctx.exprList().expr()) {
                elements.add((AstNodes.ExprNode) visit(exprCtx));
            }
        }
        return new AstNodes.ArrayNode(elements);
    }

    @Override
    public AstNodes.AstNode visitHashLiteral(JSLiteParser.HashLiteralContext ctx) {
        Map<String, AstNodes.ExprNode> map = new LinkedHashMap<>();
        if (ctx.hashList() != null) {
            for (JSLiteParser.HashElementContext hCtx : ctx.hashList().hashElement()) {
                String key = hCtx.IDENTIFIER() != null ? hCtx.IDENTIFIER().getText() : hCtx.STRING().getText().replace("\"", "").replace("'", "");
                map.put(key, (AstNodes.ExprNode) visit(hCtx.expr()));
            }
        }
        return new AstNodes.HashNode(map);
    }

    // --- ДОСТУП И ФУНКЦИИ ---
    @Override
    public AstNodes.AstNode visitIndexExpr(JSLiteParser.IndexExprContext ctx) {
        return new AstNodes.IndexNode((AstNodes.ExprNode) visit(ctx.left), (AstNodes.ExprNode) visit(ctx.right));
    }

    @Override
    public AstNodes.AstNode visitDotExpr(JSLiteParser.DotExprContext ctx) {
        return new AstNodes.DotNode((AstNodes.ExprNode) visit(ctx.left), ctx.IDENTIFIER().getText());
    }

    @Override
    public AstNodes.AstNode visitCallFunc(JSLiteParser.CallFuncContext ctx) {
        String funcName = ctx.IDENTIFIER().getText();
        List<AstNodes.ExprNode> args = new ArrayList<>();
        if (ctx.argList() != null) {
            for (JSLiteParser.ExprContext exprCtx : ctx.argList().expr()) {
                args.add((AstNodes.ExprNode) visit(exprCtx));
            }
        }
        return new AstNodes.CallNode(funcName, args);
    }

    @Override
    public AstNodes.AstNode visitFuncDecl(JSLiteParser.FuncDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        List<String> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (org.antlr.v4.runtime.tree.TerminalNode id : ctx.paramList().IDENTIFIER()) {
                params.add(id.getText());
            }
        }
        AstNodes.BlockNode body = (AstNodes.BlockNode) visit(ctx.block());
        return new AstNodes.FuncDeclNode(name, params, body);
    }

    @Override
    public AstNodes.AstNode visitReturnStmt(JSLiteParser.ReturnStmtContext ctx) {
        AstNodes.ExprNode value = ctx.expr() != null ? (AstNodes.ExprNode) visit(ctx.expr()) : null;
        return new AstNodes.ReturnNode(value);
    }

    @Override
    public AstNodes.AstNode visitForStmt(JSLiteParser.ForStmtContext ctx) {
        AstNodes.AstNode init = null;
        if (ctx.varDecl() != null) init = visit(ctx.varDecl());
        else if (ctx.exprStmt() != null) init = visit(ctx.exprStmt());

        AstNodes.ExprNode cond = ctx.expr().size() > 0 ? (AstNodes.ExprNode) visit(ctx.expr(0)) : null;
        AstNodes.ExprNode step = ctx.expr().size() > 1 ? (AstNodes.ExprNode) visit(ctx.expr(1)) : null;
        AstNodes.StmtNode body = (AstNodes.StmtNode) visit(ctx.stmt());

        return new AstNodes.ForNode(init, cond, step, body);
    }
}