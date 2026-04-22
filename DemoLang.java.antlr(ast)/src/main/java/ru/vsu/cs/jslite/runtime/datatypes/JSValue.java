package ru.vsu.cs.jslite.runtime.datatypes;

public abstract class JSValue {
    // Приведение типов (в JS можно привести к строке или к булевому значению)
    public abstract String asString();
    public abstract boolean asBoolean();

    public JSValue add(JSValue other) {
        throw new RuntimeException("Runtime Error: Cannot add values of this type.");
    }
    public JSValue sub(JSValue value) {
        throw new RuntimeException("Runtime Error: Cannot subtract values of this type.");
    }
    public  JSValue mul(JSValue value) {
        throw new RuntimeException("Runtime Error: Cannot multiply values of this type.");
    }

    public boolean isEquals(JSValue other) {
        return this == other;
    }
}
