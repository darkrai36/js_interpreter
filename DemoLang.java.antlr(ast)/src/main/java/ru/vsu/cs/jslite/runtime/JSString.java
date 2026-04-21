package ru.vsu.cs.jslite.runtime;

public class JSString extends JSValue {
    public final String value;

    public JSString(String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return this.value;
    }

    @Override
    public boolean asBoolean() {
        return !value.isEmpty();
    }

    @Override
    public JSValue add(JSValue other) {
        return new JSString(value + other.asString());
    }

    @Override
    public boolean isEquals(JSValue other) {
        if (other instanceof JSString) {
            return (this.value.equals(other.asString()));
        }
        return false;
    }
}
