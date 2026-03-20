// Generated from ru/vsu/cs/demolang/antlr/DemoLang.g4 by ANTLR 4.13.2
package ru.vsu.cs.demolang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DemoLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DemoLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(DemoLangParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(DemoLangParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(DemoLangParser.LvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#inc_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc_dec(DemoLangParser.Inc_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(DemoLangParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(DemoLangParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#binary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary(DemoLangParser.BinaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DemoLangParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(DemoLangParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#ident_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent_assign(DemoLangParser.Ident_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(DemoLangParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#ident_or_ident_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent_or_ident_assign(DemoLangParser.Ident_or_ident_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#vars_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars_decl(DemoLangParser.Vars_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(DemoLangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(DemoLangParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(DemoLangParser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#expr_or_empty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_or_empty(DemoLangParser.Expr_or_emptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(DemoLangParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(DemoLangParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(DemoLangParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#func_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_param(DemoLangParser.Func_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(DemoLangParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(DemoLangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_list(DemoLangParser.Stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(DemoLangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoLangParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(DemoLangParser.StartContext ctx);
}