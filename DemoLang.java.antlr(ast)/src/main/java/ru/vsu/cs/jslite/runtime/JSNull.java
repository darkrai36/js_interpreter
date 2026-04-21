package ru.vsu.cs.jslite.runtime;

public class JSNull extends JSValue {
    public static final JSNull INSTANCE = new JSNull();

    private JSNull() {
    }

    @Override
    public String asString() {
        return "null";
    }

    @Override
    public boolean asBoolean() {
        return false;
    }
}
