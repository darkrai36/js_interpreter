// Generated from ru/vsu/cs/jslite/antlr/JSLite.g4 by ANTLR 4.13.2
package ru.vsu.cs.jslite.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JSLiteParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JSLiteVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JSLiteParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(JSLiteParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(JSLiteParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JSLiteParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(JSLiteParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(JSLiteParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(JSLiteParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(JSLiteParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(JSLiteParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(JSLiteParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(JSLiteParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(JSLiteParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(JSLiteParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(JSLiteParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(JSLiteParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(JSLiteParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DotExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotExpr(JSLiteParser.DotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(JSLiteParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IndexExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpr(JSLiteParser.IndexExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link JSLiteParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(JSLiteParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumLiteral(JSLiteParser.NumLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(JSLiteParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(JSLiteParser.NullLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UndefLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUndefLiteral(JSLiteParser.UndefLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentLiteral(JSLiteParser.IdentLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallFunc}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunc(JSLiteParser.CallFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(JSLiteParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HashLiteral}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashLiteral(JSLiteParser.HashLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link JSLiteParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(JSLiteParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(JSLiteParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(JSLiteParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#hashList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashList(JSLiteParser.HashListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JSLiteParser#hashElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashElement(JSLiteParser.HashElementContext ctx);
}