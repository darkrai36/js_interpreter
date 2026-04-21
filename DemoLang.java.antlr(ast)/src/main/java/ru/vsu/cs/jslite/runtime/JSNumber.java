package ru.vsu.cs.jslite.runtime;

public class JSNumber extends JSValue{
    public final double value;

    public JSNumber(double value) {
        this.value = value;
    }

    @Override
    public String asString() {
        if (value == (long) value) {
            return String.format("%d", value);
        }
        return String.format("%s", value);
    }

    @Override
    public boolean asBoolean() {
        return value != 0.0;
    }

    @Override
    public JSValue add(JSValue other) {
        if (other instanceof JSString) {
            return new JSString(this.asString() + other.asString());
        }
        if (other instanceof JSNumber) {
            return new JSNumber(this.value + ((JSNumber) other).value);
        }
        return super.add(other);
    }

    @Override
    public JSValue sub(JSValue other) {
        if (other instanceof JSNumber) {
            return new JSNumber(this.value - ((JSNumber) other).value);
        }
        return super.sub(other);
    }

    @Override
    public boolean isEquals(JSValue other) {
        if (other instanceof JSNumber) {
            return value == ((JSNumber) other).value;
        }
        return false;
    }
}
