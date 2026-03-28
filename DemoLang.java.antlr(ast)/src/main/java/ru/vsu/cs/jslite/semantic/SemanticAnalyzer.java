package ru.vsu.cs.jslite.semantic;

import ru.vsu.cs.jslite.AstNodes.*;
import java.util.*;

/**
 * Semantic analysis for JSLite
 * Check:
 * - Declaring duplicate variables/functions
 * - Using undeclared variables
 * - break/continue outside the loop
 * - return outside the function
 * - Calling an undeclared function
 * - The number of arguments does not match.
 * - Function parameters with the same name
 */
public class SemanticAnalyzer {
    private Scope currentScope;
    private final List<SemanticException> errors = new ArrayList<>();

    //Semantic analysis for the entire program
    //@throws RuntimeException if there is a semantic error
    public void analyze(AstNode root) {
        errors.clear();
        // init global scope with built-in functions.
        currentScope = createGlobalScope();

        // start analyze
        traverse(root);
        if (!errors.isEmpty()) {
            for (SemanticException e : errors) {
                System.err.println(e.toString());
            }
            throw new RuntimeException("Semantic analysis failed with " + errors.size() + " error(s)");
        }

        System.out.println("Semantic analysis completed successfully. No errors found.");
    }

    //Create global scope with built-in functions.
    private Scope createGlobalScope() {
        Scope global = new Scope(null);

        // Hàm read() - không có tham số, trả về string
        global.define(new Symbol("read", Symbol.Kind.FUNCTION, Collections.emptyList(), true));

        // Hàm print(string) - void
        global.define(new Symbol("print", Symbol.Kind.FUNCTION, Arrays.asList("p0"), true));

        // Hàm println(string) - void
        global.define(new Symbol("println", Symbol.Kind.FUNCTION, Arrays.asList("p0"), true));

        // Hàm to_int(string) - int
        global.define(new Symbol("to_int", Symbol.Kind.FUNCTION, Arrays.asList("p0"), true));

        // Hàm to_float(string) - float
        global.define(new Symbol("to_float", Symbol.Kind.FUNCTION, Arrays.asList("p0"), true));

        return global;
    }

    /**
     * Báo lỗi ngữ nghĩa tại vị trí của node
     */
    private void reportError(AstNode node, String message) {
        errors.add(new SemanticException(message, node.getLine(), node.getColumn()));
    }

    /**
     * Phương thức duyệt chính - sử dụng instanceof để phân loại node
     */
    private void traverse(AstNode node) {
        if (node == null) return;

        // Xử lý theo từng loại node
        if (node instanceof ProgramNode) {
            visitProgram((ProgramNode) node);
        } else if (node instanceof BlockNode) {
            visitBlock((BlockNode) node);
        } else if (node instanceof VarDeclNode) {
            visitVarDecl((VarDeclNode) node);
        } else if (node instanceof FuncDeclNode) {
            visitFuncDecl((FuncDeclNode) node);
        } else if (node instanceof IfNode) {
            visitIf((IfNode) node);
        } else if (node instanceof WhileNode) {
            visitWhile((WhileNode) node);
        } else if (node instanceof ForNode) {
            visitFor((ForNode) node);
        } else if (node instanceof ReturnNode) {
            visitReturn((ReturnNode) node);
        } else if (node instanceof ControlNode) {
            visitControl((ControlNode) node);
        } else if (node instanceof AssignNode) {
            visitAssign((AssignNode) node);
        } else if (node instanceof ExprStmtNode) {
            visitExprStmt((ExprStmtNode) node);
        } else if (node instanceof CallNode) {
            visitCall((CallNode) node);
        } else if (node instanceof IdentNode) {
            visitIdent((IdentNode) node);
        } else if (node instanceof BinOpNode) {
            visitBinOp((BinOpNode) node);
        } else if (node instanceof ArrayNode) {
            visitArray((ArrayNode) node);
        } else if (node instanceof HashNode) {
            visitHash((HashNode) node);
        } else if (node instanceof DotNode) {
            visitDot((DotNode) node);
        } else if (node instanceof IndexNode) {
            visitIndex((IndexNode) node);
        }
        // Các node khác (NumNode, StringNode, NullNode, UndefNode) không cần xử lý
    }

    // ==================== CÁC VISITOR METHODS ====================

    private void visitProgram(ProgramNode node) {
        for (StmtNode stmt : node.statements) {
            traverse(stmt);
        }
    }

    private void visitBlock(BlockNode node) {
        Scope blockScope = new Scope(currentScope);
        Scope oldScope = currentScope;
        currentScope = blockScope;

        for (StmtNode stmt : node.statements) {
            traverse(stmt);
        }

        currentScope = oldScope;
    }

    private void visitVarDecl(VarDeclNode node) {
        try {
            Symbol sym = new Symbol(node.name, Symbol.Kind.VARIABLE);
            currentScope.define(sym);
        } catch (SemanticException e) {
            reportError(node, e.getMessage());
        }

        if (node.value != null) {
            traverse(node.value);
        }
    }

    private void visitFuncDecl(FuncDeclNode node) {
        // Kiểm tra khai báo trùng trong scope hiện tại
        Symbol existing = currentScope.resolve(node.name);
        if (existing != null) {
            reportError(node, "Function '" + node.name + "' is already declared");
        } else {
            // Thêm hàm vào scope hiện tại
            Symbol funcSym = new Symbol(node.name, Symbol.Kind.FUNCTION, node.params, false);
            try {
                currentScope.define(funcSym);
            } catch (SemanticException e) {
                reportError(node, e.getMessage());
            }
        }

        // Tạo scope mới cho thân hàm
        Scope funcScope = new Scope(currentScope);
        funcScope.setFunctionScope(true);
        Scope oldScope = currentScope;
        currentScope = funcScope;

        // Thêm các tham số vào scope của hàm
        Set<String> paramNames = new HashSet<>();
        for (String param : node.params) {
            if (paramNames.contains(param)) {
                reportError(node, "Duplicate parameter name: '" + param + "' in function '" + node.name + "'");
            } else {
                paramNames.add(param);
                try {
                    currentScope.define(new Symbol(param, Symbol.Kind.VARIABLE));
                } catch (SemanticException e) {
                    reportError(node, e.getMessage());
                }
            }
        }

        // Duyệt thân hàm
        traverse(node.body);

        // Khôi phục scope cũ
        currentScope = oldScope;
    }

    private void visitReturn(ReturnNode node) {
        // Tìm scope hàm cha
        Scope scope = currentScope;
        boolean inFunction = false;
        while (scope != null) {
            if (scope.isFunctionScope()) {
                inFunction = true;
                break;
            }
            scope = scope.getParent();
        }

        if (!inFunction) {
            reportError(node, "'return' statement cannot be used outside a function");
        }

        if (node.value != null) {
            traverse(node.value);
        }
    }

    private void visitControl(ControlNode node) {
        // Tìm scope vòng lặp cha
        Scope scope = currentScope;
        boolean inLoop = false;
        while (scope != null) {
            if (scope.isLoopScope()) {
                inLoop = true;
                break;
            }
            scope = scope.getParent();
        }

        if (!inLoop) {
            reportError(node, "'" + node.type + "' statement cannot be used outside a loop");
        }
    }

    private void visitWhile(WhileNode node) {
        boolean wasLoop = currentScope.isLoopScope();
        currentScope.setLoopScope(true);

        traverse(node.condition);
        traverse(node.body);

        currentScope.setLoopScope(wasLoop);
    }

    private void visitFor(ForNode node) {
        boolean wasLoop = currentScope.isLoopScope();
        currentScope.setLoopScope(true);

        if (node.init != null) traverse(node.init);
        if (node.condition != null) traverse(node.condition);
        if (node.step != null) traverse(node.step);
        traverse(node.body);

        currentScope.setLoopScope(wasLoop);
    }

    private void visitIf(IfNode node) {
        traverse(node.condition);
        traverse(node.thenBranch);
        if (node.elseBranch != null) {
            traverse(node.elseBranch);
        }
    }

    private void visitAssign(AssignNode node) {
        traverse(node.target);
        traverse(node.value);
    }

    private void visitExprStmt(ExprStmtNode node) {
        traverse(node.expr);
    }

    private void visitCall(CallNode node) {
        Symbol sym = currentScope.resolve(node.funcName);

        if (sym == null) {
            reportError(node, "Function '" + node.funcName + "' is not defined");
        } else if (sym.getKind() != Symbol.Kind.FUNCTION) {
            reportError(node, "'" + node.funcName + "' is not a function");
        } else {
            // Kiểm tra số lượng đối số
            int expectedParams = sym.getParameters().size();
            int actualArgs = node.args.size();

            if (expectedParams != actualArgs) {
                reportError(node, "Function '" + node.funcName + "' expects " +
                        expectedParams + " argument(s), but got " + actualArgs);
            }
        }

        for (ExprNode arg : node.args) {
            traverse(arg);
        }
    }

    private void visitIdent(IdentNode node) {
        Symbol sym = currentScope.resolve(node.name);
        if (sym == null) {
            reportError(node, "Identifier '" + node.name + "' is not defined");
        }
    }

    private void visitBinOp(BinOpNode node) {
        traverse(node.left);
        traverse(node.right);
    }

    private void visitArray(ArrayNode node) {
        for (ExprNode elem : node.elements) {
            traverse(elem);
        }
    }

    private void visitHash(HashNode node) {
        for (ExprNode value : node.elements.values()) {
            traverse(value);
        }
    }

    private void visitDot(DotNode node) {
        traverse(node.obj);
        // Property name không cần kiểm tra vì JS là dynamic
    }

    private void visitIndex(IndexNode node) {
        traverse(node.array);
        traverse(node.index);
    }
}