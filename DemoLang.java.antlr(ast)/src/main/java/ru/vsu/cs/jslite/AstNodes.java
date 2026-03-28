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

        int getLine();
        int getColumn();
    }

    public interface ExprNode extends AstNode {}
    public interface StmtNode extends AstNode {}

    // --- БАЗОВЫЕ ТИПЫ ---
    public static class NumNode implements ExprNode {
        public final double value;
        private final int line, column;
        public NumNode(double value, int line, int column) {
            this.value = value;
            this.line = line;
            this.column = column;}
        @Override public String toString() { return String.valueOf(value); }
        @Override public int getLine() { return line; }
        @Override public int getColumn() { return column; }
    }

    public static class IdentNode implements ExprNode {
        public final String name;
        private final int line, column;

        public IdentNode(String name, int line, int column) {
            this.name = name;
            this.line = line;
            this.column = column;
        }
        @Override public String toString() { return "ID: " + name; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class StringNode implements ExprNode {
        public final String value;
        private final int line, column;

        public StringNode(String value, int line, int column) {
            this.value = value;
            this.line = line;
            this.column = column;
        }
        @Override public String toString() { return "String: " + value; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class NullNode implements ExprNode {
        private final int line, column;

        public NullNode(int line, int column) {
            this.line = line;
            this.column = column;
        }

        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
        @Override public String toString() { return "null"; }
    }

    public static class UndefNode implements ExprNode {
        private final int line, column;

        public UndefNode(int line, int column) {
            this.line = line;
            this.column = column;
        }

        @Override public String toString() { return "undefined"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ArrayNode implements ExprNode {
        public final List<ExprNode> elements;
        private final int line, column;

        public ArrayNode(List<ExprNode> elements, int line, int column) {
            this.elements = elements;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return new ArrayList<>(elements); }
        @Override public String toString() { return "Array [...]"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class HashNode implements ExprNode {
        public final Map<String, ExprNode> elements;
        private final int line, column;

        public HashNode(Map<String, ExprNode> elements, int line, int column) {
            this.elements = elements;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return new ArrayList<>(elements.values()); }
        @Override public String toString() { return "Hash {...} (keys: " + String.join(", ", elements.keySet()) + ")"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    // --- ОПЕРАЦИИ ---
    public static class BinOpNode implements ExprNode {
        public final String op;
        public final ExprNode left, right;
        private final int line, column;

        public BinOpNode(String op, ExprNode left, ExprNode right, int line, int column) {
            this.op = op;
            this.left = left;
            this.right = right;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Arrays.asList(left, right); }
        @Override public String toString() { return "BinOp: " + op; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class AssignNode implements ExprNode, StmtNode {
        public final ExprNode target, value;
        private final int line, column;

        public AssignNode(ExprNode target, ExprNode value, int line, int column) {
            this.target = target;
            this.value = value;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Arrays.asList(target, value); }
        @Override public String toString() { return "Assign: ="; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    // --- ОПЕРАТОРЫ (STATEMENTS) ---
    public static class VarDeclNode implements StmtNode {
        public final String modifier; // let, const, var
        public final String name;
        public final ExprNode value; // может быть null, если просто let x;
        private final int line, column;

        public VarDeclNode(String modifier, String name, ExprNode value, int line, int column) {
            this.modifier = modifier;
            this.name = name;
            this.value = value;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() {
            return value != null ? Collections.singletonList(value) : Collections.emptyList();
        }
        @Override public String toString() { return "VarDecl: " + modifier + " " + name; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ExprStmtNode implements StmtNode {
        public final ExprNode expr;
        private final int line, column;

        public ExprStmtNode(ExprNode expr, int line, int column) {
            this.expr = expr;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Collections.singletonList(expr); }
        @Override public String toString() { return "ExprStmt"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class BlockNode implements StmtNode {
        public final List<StmtNode> statements;
        private final int line, column;

        public BlockNode(List<StmtNode> statements, int line, int column) {
            this.statements = statements;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return new ArrayList<>(statements); }
        @Override public String toString() { return "Block {...}"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ProgramNode implements AstNode {
        public final List<StmtNode> statements;
        private final int line, column;

        public ProgramNode(List<StmtNode> statements, int line, int column) {
            this.statements = statements;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return new ArrayList<>(statements); }
        @Override public String toString() { return "Program"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class IfNode implements StmtNode {
        public final ExprNode condition;
        public final StmtNode thenBranch;
        public final StmtNode elseBranch;
        private final int line, column;// может быть null, если нет else

        public IfNode(ExprNode condition, StmtNode thenBranch, StmtNode elseBranch, int line, int column) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() {
            List<AstNode> childs = new ArrayList<>(Arrays.asList(condition, thenBranch));
            if (elseBranch != null) childs.add(elseBranch);
            return childs;
        }
        @Override public String toString() { return "If"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class WhileNode implements StmtNode {
        public final ExprNode condition;
        public final StmtNode body;
        private final int line, column;

        public WhileNode(ExprNode condition, StmtNode body, int line, int column) {
            this.condition = condition;
            this.body = body;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Arrays.asList(condition, body); }
        @Override public String toString() { return "While"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ForNode implements StmtNode {
        public final AstNode init;
        public final ExprNode condition, step;
        public final StmtNode body;
        private final int line, column;

        public ForNode(AstNode init, ExprNode condition, ExprNode step, StmtNode body, int line, int column) {
            this.init = init;
            this.condition = condition;
            this.step = step;
            this.body = body;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Arrays.asList(init, condition, step, body); }
        @Override public String toString() { return "For"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ControlNode implements StmtNode { // Для break и continue
        public final String type;
        private final int line, column;

        public ControlNode(String type, int line, int column) {
            this.type = type;
            this.line = line;
            this.column = column;
        }

        @Override public String toString() { return type; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    // --- ДОСТУП К ДАННЫМ И ФУНКЦИИ ---
    public static class IndexNode implements ExprNode {
        public final ExprNode array, index;
        private final int line, column;

        public IndexNode(ExprNode array, ExprNode index, int line, int column) {
            this.array = array;
            this.index = index;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Arrays.asList(array, index); }
        @Override public String toString() { return "IndexAccess []"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class DotNode implements ExprNode {
        public final ExprNode obj;
        public final String prop;
        private final int line, column;

        public DotNode(ExprNode obj, String prop, int line, int column) {
            this.obj = obj;
            this.prop = prop;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Collections.singletonList(obj); }
        @Override public String toString() { return "DotAccess ." + prop; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class CallNode implements ExprNode {
        public final String funcName;
        public final List<ExprNode> args;
        private final int line, column;

        public CallNode(String funcName, List<ExprNode> args, int line, int column) {
            this.funcName = funcName;
            this.args = args;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return new ArrayList<>(args); }
        @Override public String toString() { return "Call: " + funcName + "()"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class FuncDeclNode implements StmtNode {
        public final String name;
        public final List<String> params;
        public final BlockNode body;
        private final int line, column;

        public FuncDeclNode(String name, List<String> params, BlockNode body, int line, int column) {
            this.name = name;
            this.params = params;
            this.body = body;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return Collections.singletonList(body); }
        @Override public String toString() { return "Function: " + name + "(" + String.join(", ", params) + ")"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }

    public static class ReturnNode implements StmtNode {
        public final ExprNode value;
        private final int line, column;

        public ReturnNode(ExprNode value, int line, int column) {
            this.value = value;
            this.line = line;
            this.column = column;
        }

        @Override public List<AstNode> getChilds() { return value != null ? Collections.singletonList(value) : Collections.emptyList(); }
        @Override public String toString() { return "Return"; }
        @Override public int getLine() {return line;}
        @Override public int getColumn() {return column;}
    }
}