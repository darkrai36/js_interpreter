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

    public static class StringNode implements ExprNode {
        public final String value;
        public StringNode(String value) { this.value = value; }
        @Override public String toString() { return "String: " + value; }
    }

    public static class NullNode implements ExprNode {
        @Override public String toString() { return "null"; }
    }

    public static class UndefNode implements ExprNode {
        @Override public String toString() { return "undefined"; }
    }

    public static class ArrayNode implements ExprNode {
        public final List<ExprNode> elements;
        public ArrayNode(List<ExprNode> elements) { this.elements = elements; }
        @Override public List<AstNode> getChilds() { return new ArrayList<>(elements); }
        @Override public String toString() { return "Array [...]"; }
    }

    public static class HashNode implements ExprNode {
        public final Map<String, ExprNode> elements;
        public HashNode(Map<String, ExprNode> elements) { this.elements = elements; }
        @Override public List<AstNode> getChilds() { return new ArrayList<>(elements.values()); }
        @Override public String toString() { return "Hash {...} (keys: " + String.join(", ", elements.keySet()) + ")"; }
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

    public static class IfNode implements StmtNode {
        public final ExprNode condition;
        public final StmtNode thenBranch;
        public final StmtNode elseBranch; // может быть null, если нет else

        public IfNode(ExprNode condition, StmtNode thenBranch, StmtNode elseBranch) {
            this.condition = condition; this.thenBranch = thenBranch; this.elseBranch = elseBranch;
        }
        @Override public List<AstNode> getChilds() {
            List<AstNode> childs = new ArrayList<>(Arrays.asList(condition, thenBranch));
            if (elseBranch != null) childs.add(elseBranch);
            return childs;
        }
        @Override public String toString() { return "If"; }
    }

    public static class WhileNode implements StmtNode {
        public final ExprNode condition;
        public final StmtNode body;

        public WhileNode(ExprNode condition, StmtNode body) {
            this.condition = condition; this.body = body;
        }
        @Override public List<AstNode> getChilds() { return Arrays.asList(condition, body); }
        @Override public String toString() { return "While"; }
    }

    public static class ForNode implements StmtNode {
        public final AstNode init;
        public final ExprNode condition, step;
        public final StmtNode body;
        public ForNode(AstNode init, ExprNode cond, ExprNode step, StmtNode body) {
            this.init = init; this.condition = cond; this.step = step; this.body = body;
        }
        @Override public List<AstNode> getChilds() { return Arrays.asList(init, condition, step, body); }
        @Override public String toString() { return "For"; }
    }

    public static class ControlNode implements StmtNode { // Для break и continue
        public final String type;
        public ControlNode(String type) { this.type = type; }
        @Override public String toString() { return type; }
    }

    // --- ДОСТУП К ДАННЫМ И ФУНКЦИИ ---
    public static class IndexNode implements ExprNode {
        public final ExprNode array, index;
        public IndexNode(ExprNode array, ExprNode index) { this.array = array; this.index = index; }
        @Override public List<AstNode> getChilds() { return Arrays.asList(array, index); }
        @Override public String toString() { return "IndexAccess []"; }
    }

    public static class DotNode implements ExprNode {
        public final ExprNode obj;
        public final String prop;
        public DotNode(ExprNode obj, String prop) { this.obj = obj; this.prop = prop; }
        @Override public List<AstNode> getChilds() { return Collections.singletonList(obj); }
        @Override public String toString() { return "DotAccess ." + prop; }
    }

    public static class CallNode implements ExprNode {
        public final String funcName;
        public final List<ExprNode> args;
        public CallNode(String funcName, List<ExprNode> args) { this.funcName = funcName; this.args = args; }
        @Override public List<AstNode> getChilds() { return new ArrayList<>(args); }
        @Override public String toString() { return "Call: " + funcName + "()"; }
    }

    public static class FuncDeclNode implements StmtNode {
        public final String name;
        public final List<String> params;
        public final BlockNode body;
        public FuncDeclNode(String name, List<String> params, BlockNode body) {
            this.name = name; this.params = params; this.body = body;
        }
        @Override public List<AstNode> getChilds() { return Collections.singletonList(body); }
        @Override public String toString() { return "Function: " + name + "(" + String.join(", ", params) + ")"; }
    }

    public static class ReturnNode implements StmtNode {
        public final ExprNode value;
        public ReturnNode(ExprNode value) { this.value = value; }
        @Override public List<AstNode> getChilds() { return value != null ? Collections.singletonList(value) : Collections.emptyList(); }
        @Override public String toString() { return "Return"; }
    }
}