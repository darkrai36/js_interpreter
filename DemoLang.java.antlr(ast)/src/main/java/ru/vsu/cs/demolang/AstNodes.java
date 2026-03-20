package ru.vsu.cs.demolang;

import java.util.*;
import java.util.stream.Collectors;

public class AstNodes {
    // чисто для названия
}

interface AstNode {
    String toString();

    default List<AstNode> getChilds() {
        return Collections.emptyList();
    }

    default List<String> getTree() {
        List<String> result = new ArrayList<>();
        result.add(this.toString());
        List<AstNode> childs = getChilds();
        for (int i = 0; i < childs.size(); i++) {
            AstNode child = childs.get(i);
            String prefix = (i == childs.size() - 1) ? "└ " : "├ ";
            String childPrefix = (i == childs.size() - 1) ? "  " : "│ ";

            List<String> childTree = child.getTree();
            for (int j = 0; j < childTree.size(); j++) {
                String line = childTree.get(j);
                if (j == 0) {
                    result.add(prefix + line);
                } else {
                    result.add(childPrefix + line);
                }
            }
        }
        return result;
    }

    default void visit(java.util.function.Consumer<AstNode> func) {
        func.accept(this);
        getChilds().forEach(func);
    }

    default AstNode get(int index) {
        List<AstNode> childs = getChilds();
        return index < childs.size() ? childs.get(index) : null;
    }
}

interface ExprNode extends AstNode {}

interface ValueNode extends ExprNode {}

interface StmtNode extends AstNode {}

class NumNode implements ValueNode {
    private final double num;

    public NumNode(String num) {
        this.num = Double.parseDouble(num);
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}

class IdentNode implements ExprNode {
    private final String name;

    public IdentNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

enum IncDecOp {
    PREFIX_INC("++()"),
    PREFIX_DEC("--()"),
    SUFFIX_INC("()++"),
    SUFFIX_DEC("()--");

    private final String value;

    IncDecOp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class IncDecNode implements ExprNode, StmtNode {
    private final IncDecOp op;
    private final IdentNode ident;

    public IncDecNode(IncDecOp op, IdentNode ident) {
        this.op = op;
        this.ident = ident;
    }

    @Override
    public List<AstNode> getChilds() {
        return Collections.singletonList(ident);
    }

    @Override
    public String toString() {
        return op.getValue();
    }
}

enum UnOp {
    NOT("!"),
    PLUS("+"),
    MINUS("-");

    private final String value;

    UnOp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UnOp fromString(String value) {
        for (UnOp op : UnOp.values()) {
            if (op.getValue().equals(value)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + value);
    }
}

class UnOpNode implements ExprNode {
    private final UnOp op;
    private final ExprNode arg;

    public UnOpNode(UnOp op, ExprNode arg) {
        this.op = op;
        this.arg = arg;
    }

    @Override
    public List<AstNode> getChilds() {
        return Collections.singletonList(arg);
    }

    @Override
    public String toString() {
        return op.getValue();
    }
}

enum BinOp {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    REM("%"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    EQ("=="),
    NE("!="),
    LOGIC_OR("||"),
    LOGIC_AND("&&");

    private final String value;

    BinOp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BinOp fromString(String value) {
        for (BinOp op : BinOp.values()) {
            if (op.getValue().equals(value)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + value);
    }
}

class BinOpNode implements ExprNode {
    private final BinOp op;
    private final ExprNode arg1;
    private final ExprNode arg2;

    public BinOpNode(BinOp op, ExprNode arg1, ExprNode arg2) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public List<AstNode> getChilds() {
        return Arrays.asList(arg1, arg2);
    }

    @Override
    public String toString() {
        return op.getValue();
    }
}

class AssignNode implements ExprNode, StmtNode {
    private final ExprNode var;
    private final ExprNode val;

    public AssignNode(ExprNode var, ExprNode val) {
        this.var = var;
        this.val = val;
    }

    @Override
    public List<AstNode> getChilds() {
        return Arrays.asList(var, val);
    }

    @Override
    public String toString() {
        return "=";
    }
}

class VarsDeclNode implements StmtNode {
    private final IdentNode type;
    private final List<ExprNode> vars;

    public VarsDeclNode(IdentNode type, List<ExprNode> vars) {
        this.type = type;
        this.vars = vars;
    }

    @Override
    public List<AstNode> getChilds() {
        return new ArrayList<>(vars);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}

class CallNode implements ExprNode, StmtNode {
    private final IdentNode name;
    private final List<ExprNode> params;

    public CallNode(IdentNode name, List<ExprNode> params) {
        this.name = name;
        this.params = params;
    }

    @Override
    public List<AstNode> getChilds() {
        return new ArrayList<>(params);
    }

    @Override
    public String toString() {
        return name + "()";
    }
}

class IfNode implements StmtNode {
    private final ExprNode cond;
    private final StmtNode thenStmt;
    private final StmtNode elseStmt;

    public IfNode(ExprNode cond, StmtNode thenStmt, StmtNode elseStmt) {
        this.cond = cond;
        this.thenStmt = thenStmt != null ? thenStmt : new StmtListNode();
        this.elseStmt = elseStmt;
    }

    @Override
    public List<AstNode> getChilds() {
        List<AstNode> childs = new ArrayList<>();
        childs.add(cond);
        childs.add(thenStmt);
        if (elseStmt != null) {
            childs.add(elseStmt);
        }
        return childs;
    }

    @Override
    public String toString() {
        return "if";
    }
}

class WhileNode implements StmtNode {
    private final ExprNode cond;
    private final StmtNode body;

    public WhileNode(ExprNode cond, StmtNode body) {
        this.cond = cond;
        this.body = body != null ? body : new StmtListNode();
    }

    @Override
    public List<AstNode> getChilds() {
        return Arrays.asList(cond, body);
    }

    @Override
    public String toString() {
        return "while";
    }
}

class ForNode implements StmtNode {
    private final StmtNode init;
    private final ExprNode cond;
    private final StmtNode next;
    private final StmtNode body;

    public ForNode(StmtNode init, ExprNode cond, StmtNode next, StmtNode body) {
        this.init = init != null ? init : new StmtListNode();
        this.cond = cond != null ? cond : new NumNode("1");
        this.next = next != null ? next : new StmtListNode();
        this.body = body != null ? body : new StmtListNode();
    }

    @Override
    public List<AstNode> getChilds() {
        return Arrays.asList(init, cond, next, body);
    }

    @Override
    public String toString() {
        return "for";
    }
}

class BreakNode implements StmtNode {
    @Override
    public String toString() {
        return "break";
    }
}

class ContinueNode implements StmtNode {
    @Override
    public String toString() {
        return "continue";
    }
}

class ReturnNode implements StmtNode {
    private final ExprNode value;

    public ReturnNode(ExprNode value) {
        this.value = value;
    }

    public ReturnNode() {
        this.value = null;
    }

    @Override
    public List<AstNode> getChilds() {
        return value != null ? Collections.singletonList(value) : Collections.emptyList();
    }

    @Override
    public String toString() {
        return "return";
    }
}

class StmtListNode implements StmtNode {
    private final List<StmtNode> stmts;

    public StmtListNode(List<StmtNode> stmts) {
        this.stmts = stmts;
    }

    public StmtListNode(StmtNode... stmts) {
        this.stmts = Arrays.asList(stmts);
    }

    @Override
    public List<AstNode> getChilds() {
        return new ArrayList<>(stmts);
    }

    @Override
    public String toString() {
        return "...";
    }
}

class FuncNode implements StmtNode {
    private final IdentNode type;
    private final IdentNode name;
    private final List<VarsDeclNode> params;
    private final StmtNode body;

    public FuncNode(IdentNode type, IdentNode name, List<VarsDeclNode> params, StmtNode body) {
        this.type = type;
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public List<AstNode> getChilds() {
        return Collections.singletonList(body);
    }

    @Override
    public String toString() {
        String paramsStr = params.stream()
                .map(p -> p.toString() + " " + p.getChilds().get(0))
                .collect(Collectors.joining(", "));
        return type + " " + name + "(" + paramsStr + ")";
    }
}
