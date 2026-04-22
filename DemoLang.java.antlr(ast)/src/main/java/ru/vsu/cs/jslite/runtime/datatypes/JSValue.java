package ru.vsu.cs.jslite.runtime.datatypes;

public abstract class JSValue {
    // Приведение типов (в JS можно привести к строке или к булевому значению)
    public abstract String asString();
    public abstract boolean asBoolean();

    // мат. операции (+, -, *, div, mod)
    public JSValue add(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot add values of this type.");
    }
    public JSValue sub(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot subtract values of this type.");
    }
    public  JSValue mul(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot multiply values of this type.");
    }
    public JSValue div(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot divide values of this type.");
    }
    public  JSValue mod(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot find modulo for values of this type.");
    }

    // операции сравнения (==, !=, >, <, >=, <=)
    public boolean isEquals(JSValue other) {
        return this == other;
    }
    public boolean isNotEquals(JSValue other) { return !isEquals(other); }
    public boolean isLess(JSValue other) { throw new RuntimeException("Runtime Error: Cannot compare values of this type."); }
    public boolean isLessOrEqual(JSValue other) { throw new RuntimeException("Runtime Error: Cannot compare values of this type."); }
    public boolean isGreater(JSValue other) { throw new RuntimeException("Runtime Error: Cannot compare values of this type."); }
    public boolean isGreaterOrEqual(JSValue other) { throw new RuntimeException("Runtime Error: Cannot compare values of this type."); }
}
