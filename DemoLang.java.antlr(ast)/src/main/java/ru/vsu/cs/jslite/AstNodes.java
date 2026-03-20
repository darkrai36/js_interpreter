package ru.vsu.cs.jslite;

import java.util.*;

public class AstNodes {

    public interface AstNode {
        default List<AstNode> getChilds() { return Collections.emptyList(); }

        default List<String> getTree() {
            List<String> result = new ArrayList<>();
            result.add(this.toString());
            List<AstNode> childs = getChilds();
            for (int i = 0; i < childs.size(); i++) {
                AstNode child = childs.get(i);
                if (child == null) continue;
                String prefix = (i == childs.size() - 1) ? "└ " : "├ ";
                String childPrefix = (i == childs.size() - 1) ? "  " : "│ ";
                List<String> childTree = child.getTree();
                for (int j = 0; j < childTree.size(); j++) {
                    if (j == 0) result.add(prefix + childTree.get(j));
                    else result.add(childPrefix + childTree.get(j));
                }
            }
            return result;
        }
    }

    public interface ExprNode extends AstNode {}
    public interface StmtNode extends AstNode {}

    // --- БАЗОВЫЕ ТИПЫ ---
    public static class NumNode implements ExprNode {
        public final double value;
        public NumNode(double value) { this.value = value; }
        @Override public String toString() { return String.valueOf(value); }
    }

    public static class IdentNode implements ExprNode {
        public final String name;
        public IdentNode(String name) { this.name = name; }
        @Override public String toString() { return "ID: " + name; }
    }

    // --- ОПЕРАЦИИ ---
    public static class BinOpNode implements ExprNode {
        public final String op;
        public final ExprNode left, right;
        public BinOpNode(String op, ExprNode left, ExprNode right) {
            this.op = op; this.left = left; this.right = right;
        }
        @Override public List<AstNode> getChilds() { return Arrays.asList(left, right); }
        @Override public String toString() { return "BinOp: " + op; }
    }

    public static class AssignNode implements ExprNode, StmtNode {
        public final ExprNode target, value;
        public AssignNode(ExprNode target, ExprNode value) {
            this.target = target; this.value = value;
        }
        @Override public List<AstNode> getChilds() { return Arrays.asList(target, value); }
        @Override public String toString() { return "Assign: ="; }
    }

    // --- ОПЕРАТОРЫ (STATEMENTS) ---
    public static class VarDeclNode implements StmtNode {
        public final String modifier; // let, const, var
        public final String name;
        public final ExprNode value; // может быть null, если просто let x;
        public VarDeclNode(String modifier, String name, ExprNode value) {
            this.modifier = modifier; this.name = name; this.value = value;
        }
        @Override public List<AstNode> getChilds() {
            return value != null ? Collections.singletonList(value) : Collections.emptyList();
        }
        @Override public String toString() { return "VarDecl: " + modifier + " " + name; }
    }

    public static class ExprStmtNode implements StmtNode {
        public final ExprNode expr;
        public ExprStmtNode(ExprNode expr) { this.expr = expr; }
        @Override public List<AstNode> getChilds() { return Collections.singletonList(expr); }
        @Override public String toString() { return "ExprStmt"; }
    }

    public static class BlockNode implements StmtNode {
        public final List<StmtNode> statements;
        public BlockNode(List<StmtNode> statements) { this.statements = statements; }
        @Override public List<AstNode> getChilds() { return new ArrayList<>(statements); }
        @Override public String toString() { return "Block {...}"; }
    }

    public static class ProgramNode implements AstNode {
        public final List<StmtNode> statements;
        public ProgramNode(List<StmtNode> statements) { this.statements = statements; }
        @Override public List<AstNode> getChilds() { return new ArrayList<>(statements); }
        @Override public String toString() { return "Program"; }
    }
}