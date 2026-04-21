package ru.vsu.cs.jslite.runtime;

import ru.vsu.cs.jslite.semantic.SemanticException;
import ru.vsu.cs.jslite.semantic.Symbol;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp quản lý phạm vi (scope) cho các biến và hàm
 * Hỗ trợ phạm vi lồng nhau
 */
public class Environment {
    private static class Record {
        JSValue value;
        boolean isConst;
        Record(JSValue value, boolean isConst) { this.value = value; this.isConst = isConst; }
    }
    private final Map<String, Record> variables = new HashMap<>();
    private final Environment parent;
    private final boolean isFunctionScope;

    public Environment(Environment parent, boolean isFunctionScope) {
        this.parent = parent;
        this.isFunctionScope = isFunctionScope;
    }

    public void declareLet(String name, JSValue value, int line, int col) {
        if (variables.containsKey(name)) {
            throw new JSRuntimeException("Identifier '" + name + "' has already been declared", line, col);
        }
        variables.put(name, new Record(value, false));
    }

    private Environment findFunctionScope() {
        Environment env = this;
        while (env.parent != null && !env.isFunctionScope) {
            env = env.parent;
        }
        return env;
    }

    public void declareVar(String name, JSValue value) {
        Environment target = findFunctionScope();
        // В JS можно объявлять var дважды, он просто перезапишется
        target.variables.put(name, new  Record(value, false));
    }

    public void declareConst(String name, JSValue value, int line, int col) {
        Environment target = findFunctionScope();
        if (target.variables.containsKey(name)) {
            throw new JSRuntimeException("Identifier '" + name + "' has already been declared", line, col);
        }
        target.variables.put(name, new Record(value, true));
    }

    public JSValue get(String name, int line, int col) {
        if (variables.containsKey(name)) return variables.get(name).value;
        if (parent != null) return parent.get(name, line, col);
        throw new JSRuntimeException(name + " is not defined", line, col);
    }

    public void assign(String name, JSValue newValue, int line, int col) {
        if (variables.containsKey(name)) {
            Record record = variables.get(name);
            if (record.isConst) {
                throw new JSRuntimeException("Assignment to constant variable '" + name + "'", line, col);
            }
            record.value = newValue;
            return;
        }
        if (parent != null) {
            parent.assign(name, newValue, line, col);
            return;
        }
        throw new JSRuntimeException(name + " is not defined", line, col);
    }
}