package ru.vsu.cs.jslite.semantic;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp quản lý phạm vi (scope) cho các biến và hàm
 * Hỗ trợ phạm vi lồng nhau
 */
public class Scope {
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final Scope parent;
    private boolean isLoopScope = false;
    private boolean isFunctionScope = false;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    /**
     * Thêm một ký hiệu vào scope hiện tại
     * @throws SemanticException nếu ký hiệu đã tồn tại trong scope này
     */
    public void define(Symbol sym) throws SemanticException {
        if (symbols.containsKey(sym.getName())) {
            throw new SemanticException(
                    "Identifier '" + sym.getName() + "' already declared in this scope",
                    0, 0
            );
        }
        symbols.put(sym.getName(), sym);
    }

    /**
     * Tìm kiếm ký hiệu trong scope hiện tại và các scope cha
     * @return Symbol nếu tìm thấy, null nếu không
     */
    public Symbol resolve(String name) {
        Symbol sym = symbols.get(name);
        if (sym != null) {
            return sym;
        }
        if (parent != null) {
            return parent.resolve(name);
        }
        return null;
    }

//    Kiểm tra xem ký hiệu có tồn tại trong scope hiện tại không
    public boolean existsInCurrentScope(String name) {
        return symbols.containsKey(name);
    }

    public Scope getParent() {
        return parent;
    }

    public boolean isLoopScope() {
        return isLoopScope;
    }

    public void setLoopScope(boolean loopScope) {
        isLoopScope = loopScope;
    }

    public boolean isFunctionScope() {
        return isFunctionScope;
    }

    public void setFunctionScope(boolean functionScope) {
        isFunctionScope = functionScope;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "symbols=" + symbols.keySet() +
                ", isLoopScope=" + isLoopScope +
                ", isFunctionScope=" + isFunctionScope +
                '}';
    }
}