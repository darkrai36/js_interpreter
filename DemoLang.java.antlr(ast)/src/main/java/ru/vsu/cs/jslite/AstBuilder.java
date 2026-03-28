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
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        List<AstNodes.StmtNode> stmts = new ArrayList<>();
        if (ctx.stmtList() != null) {
            for (JSLiteParser.StmtContext stmtCtx : ctx.stmtList().stmt()) {
                // БЕРЕМ ТОЛЬКО ПЕРВОГО РЕБЕНКА (индекс 0), игнорируя точку с запятой ';'
                AstNodes.AstNode node = visit(stmtCtx.getChild(0));
                // Если ребенок - это просто текст "break" или "continue"
                if (node == null && (stmtCtx.getText().startsWith("break") || stmtCtx.getText().startsWith("continue"))) {
                    int ctrlLine = stmtCtx.getStart().getLine();
                    int ctrlColumn = stmtCtx.getStart().getCharPositionInLine();
                    stmts.add(new AstNodes.ControlNode(stmtCtx.getText().replace(";", ""), ctrlLine, ctrlColumn));
                    continue;
                }
                if (node instanceof AstNodes.StmtNode) {
                    stmts.add((AstNodes.StmtNode) node);
                }
            }
        }
        return new AstNodes.ProgramNode(stmts, line, column);
    }

    @Override
    public AstNodes.AstNode visitExprStmt(JSLiteParser.ExprStmtContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        // Оборачиваем выражение в Statement, чтобы оно могло лежать в списке команд программы
        AstNodes.ExprNode expr = (AstNodes.ExprNode) visit(ctx.expr());
        return new AstNodes.ExprStmtNode(expr, line, column);
    }

    @Override
    public AstNodes.AstNode visitVarDecl(JSLiteParser.VarDeclContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String modifier = ctx.getChild(0).getText(); // let, var или const
        String name = ctx.IDENTIFIER().getText();
        AstNodes.ExprNode value = null;
        if (ctx.expr() != null) {
            value = (AstNodes.ExprNode) visit(ctx.expr());
        }
        return new AstNodes.VarDeclNode(modifier, name, value, line, column);
    }

    @Override
    public AstNodes.AstNode visitAddSubExpr(JSLiteParser.AddSubExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right, line, column);
    }

    @Override
    public AstNodes.AstNode visitMulDivExpr(JSLiteParser.MulDivExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right, line, column);
    }

    @Override
    public AstNodes.AstNode visitNumLiteral(JSLiteParser.NumLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new AstNodes.NumNode(Double.parseDouble(ctx.getText()), line, column);
    }

    @Override
    public AstNodes.AstNode visitIdentLiteral(JSLiteParser.IdentLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new AstNodes.IdentNode(ctx.getText(), line, column);
    }

    @Override
    public AstNodes.AstNode visitAssignExpr(JSLiteParser.AssignExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.ExprNode target = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode value = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.AssignNode(target, value, line, column);
    }

    // Обработка блоков кода { ... }
    @Override
    public AstNodes.AstNode visitBlock(JSLiteParser.BlockContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        List<AstNodes.StmtNode> stmts = new ArrayList<>();
        if (ctx.stmtList() != null) {
            for (JSLiteParser.StmtContext stmtCtx : ctx.stmtList().stmt()) {
                AstNodes.AstNode node = visit(stmtCtx.getChild(0));
                // Если ребенок - это просто текст "break" или "continue"
                if (node == null && (stmtCtx.getText().startsWith("break") || stmtCtx.getText().startsWith("continue"))) {
                    int ctrlLine = stmtCtx.getStart().getLine();
                    int ctrlColumn = stmtCtx.getStart().getCharPositionInLine();
                    stmts.add(new AstNodes.ControlNode(stmtCtx.getText().replace(";", ""), ctrlLine, ctrlColumn));
                    continue;
                }
                if (node instanceof AstNodes.StmtNode) {
                    stmts.add((AstNodes.StmtNode) node);
                }
            }
        }
        return new AstNodes.BlockNode(stmts, line, column);
    }

    // Обработка If
    @Override
    public AstNodes.AstNode visitIfStmt(JSLiteParser.IfStmtContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.ExprNode condition = (AstNodes.ExprNode) visit(ctx.expr());
        AstNodes.StmtNode thenBranch = (AstNodes.StmtNode) visit(ctx.stmt(0));
        AstNodes.StmtNode elseBranch = null;
        if (ctx.stmt().size() > 1) { // Если есть блок else
            elseBranch = (AstNodes.StmtNode) visit(ctx.stmt(1));
        }
        return new AstNodes.IfNode(condition, thenBranch, elseBranch, line, column);
    }

    // Обработка While
    @Override
    public AstNodes.AstNode visitWhileStmt(JSLiteParser.WhileStmtContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.ExprNode condition = (AstNodes.ExprNode) visit(ctx.expr());
        AstNodes.StmtNode body = (AstNodes.StmtNode) visit(ctx.stmt());
        return new AstNodes.WhileNode(condition, body, line, column);
    }

    // Обработка логических операций (> < >= <=)
    @Override
    public AstNodes.AstNode visitRelExpr(JSLiteParser.RelExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right, line, column); // Переиспользуем узел бинарных операций
    }

    // Обработка операций равенства (== !=)
    @Override
    public AstNodes.AstNode visitEqExpr(JSLiteParser.EqExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String op = ctx.op.getText();
        AstNodes.ExprNode left = (AstNodes.ExprNode) visit(ctx.left);
        AstNodes.ExprNode right = (AstNodes.ExprNode) visit(ctx.right);
        return new AstNodes.BinOpNode(op, left, right, line, column);
    }

    // --- ТИПЫ ДАННЫХ ---
    @Override
    public AstNodes.AstNode visitStringLiteral(JSLiteParser.StringLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String text = ctx.getText();
        return new AstNodes.StringNode(text.substring(1, text.length() - 1), line, column); // Убираем кавычки
    }

    @Override
    public AstNodes.AstNode visitNullLiteral(JSLiteParser.NullLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new AstNodes.NullNode(line, column);
    }
    @Override
    public AstNodes.AstNode visitUndefLiteral(JSLiteParser.UndefLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();
        return new AstNodes.UndefNode(line, column);
    }

    @Override
    public AstNodes.AstNode visitArrayLiteral(JSLiteParser.ArrayLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        List<AstNodes.ExprNode> elements = new ArrayList<>();
        if (ctx.exprList() != null) {
            for (JSLiteParser.ExprContext exprCtx : ctx.exprList().expr()) {
                elements.add((AstNodes.ExprNode) visit(exprCtx));
            }
        }
        return new AstNodes.ArrayNode(elements, line, column);
    }

    @Override
    public AstNodes.AstNode visitHashLiteral(JSLiteParser.HashLiteralContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        Map<String, AstNodes.ExprNode> map = new LinkedHashMap<>();
        if (ctx.hashList() != null) {
            for (JSLiteParser.HashElementContext hCtx : ctx.hashList().hashElement()) {
                String key = hCtx.IDENTIFIER() != null ? hCtx.IDENTIFIER().getText() : hCtx.STRING().getText().replace("\"", "").replace("'", "");
                map.put(key, (AstNodes.ExprNode) visit(hCtx.expr()));
            }
        }
        return new AstNodes.HashNode(map, line, column);
    }

    // --- ДОСТУП И ФУНКЦИИ ---
    @Override
    public AstNodes.AstNode visitIndexExpr(JSLiteParser.IndexExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        return new AstNodes.IndexNode(
                (AstNodes.ExprNode) visit(ctx.left),
                (AstNodes.ExprNode) visit(ctx.right),
                line, column
        );
    }

    @Override
    public AstNodes.AstNode visitDotExpr(JSLiteParser.DotExprContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        return new AstNodes.DotNode(
                (AstNodes.ExprNode) visit(ctx.left),
                ctx.IDENTIFIER().getText(),
                line, column
        );
    }

    @Override
    public AstNodes.AstNode visitCallFunc(JSLiteParser.CallFuncContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String funcName = ctx.IDENTIFIER().getText();
        List<AstNodes.ExprNode> args = new ArrayList<>();
        if (ctx.argList() != null) {
            for (JSLiteParser.ExprContext exprCtx : ctx.argList().expr()) {
                args.add((AstNodes.ExprNode) visit(exprCtx));
            }
        }
        return new AstNodes.CallNode(funcName, args, line, column);
    }

    @Override
    public AstNodes.AstNode visitFuncDecl(JSLiteParser.FuncDeclContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        String name = ctx.IDENTIFIER().getText();
        List<String> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (org.antlr.v4.runtime.tree.TerminalNode id : ctx.paramList().IDENTIFIER()) {
                params.add(id.getText());
            }
        }
        AstNodes.BlockNode body = (AstNodes.BlockNode) visit(ctx.block());
        return new AstNodes.FuncDeclNode(name, params, body, line, column);
    }

    @Override
    public AstNodes.AstNode visitReturnStmt(JSLiteParser.ReturnStmtContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.ExprNode value = ctx.expr() != null ? (AstNodes.ExprNode) visit(ctx.expr()) : null;
        return new AstNodes.ReturnNode(value, line, column);
    }

    @Override
    public AstNodes.AstNode visitForStmt(JSLiteParser.ForStmtContext ctx) {
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.AstNode init = null;
        if (ctx.varDecl() != null) init = visit(ctx.varDecl());
        else if (ctx.exprStmt() != null) init = visit(ctx.exprStmt());

        AstNodes.ExprNode cond = ctx.expr().size() > 0 ? (AstNodes.ExprNode) visit(ctx.expr(0)) : null;
        AstNodes.ExprNode step = ctx.expr().size() > 1 ? (AstNodes.ExprNode) visit(ctx.expr(1)) : null;
        AstNodes.StmtNode body = (AstNodes.StmtNode) visit(ctx.stmt());

        return new AstNodes.ForNode(init, cond, step, body, line, column);
    }

    @Override
    public AstNodes.AstNode visitPrimaryExpr(JSLiteParser.PrimaryExprContext ctx) {
        // primaryExpr chỉ chứa primary, nên lấy từ primary
        return visit(ctx.primary());
    }

    @Override
    public AstNodes.AstNode visitParenExpr(JSLiteParser.ParenExprContext ctx) {
        // take line/column from '('
        int line = ctx.getStart().getLine();
        int column = ctx.getStart().getCharPositionInLine();

        AstNodes.ExprNode expr = (AstNodes.ExprNode) visit(ctx.expr());
        return expr;
    }
}