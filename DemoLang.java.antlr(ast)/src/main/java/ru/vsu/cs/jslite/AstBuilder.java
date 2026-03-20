package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.antlr.JSLiteBaseVisitor;
import ru.vsu.cs.jslite.antlr.JSLiteParser;
import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends JSLiteBaseVisitor<AstNodes.AstNode> {

    @Override
    public AstNodes.AstNode visitProgram(JSLiteParser.ProgramContext ctx) {
        List<AstNodes.StmtNode> stmts = new ArrayList<>();
        if (ctx.stmtList() != null) {
            for (JSLiteParser.StmtContext stmtCtx : ctx.stmtList().stmt()) {
                // БЕРЕМ ТОЛЬКО ПЕРВОГО РЕБЕНКА (индекс 0), игнорируя точку с запятой ';'
                AstNodes.AstNode node = visit(stmtCtx.getChild(0));
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
}