package ru.vsu.cs.jslite.runtime.datatypes;

public class JSBoolean extends JSValue {
    public final boolean value;

    public JSBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value ? "true" : "false";
    }

    @Override
    public boolean asBoolean() {
        return value;
    }

    @Override
    public boolean isEquals(JSValue other) {
        if (other instanceof JSBoolean) {
            return this.value == ((JSBoolean) other).value;
        }
        return false;
    }
}
