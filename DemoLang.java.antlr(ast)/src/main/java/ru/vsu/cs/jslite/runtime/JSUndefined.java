package ru.vsu.cs.jslite.runtime;

public class JSUndefined extends JSValue {
    public static final JSUndefined INSTANCE = new JSUndefined();
    private JSUndefined() {}
    @Override
    public String asString() {
        return "undefined";
    }

    @Override
    public boolean asBoolean() {
        return false;
    }
}
