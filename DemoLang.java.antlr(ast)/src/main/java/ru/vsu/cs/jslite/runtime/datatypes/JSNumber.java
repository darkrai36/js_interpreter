package ru.vsu.cs.jslite.runtime.datatypes;

public class JSNumber extends JSValue {
    public final double value;

    public JSNumber(double value) {
        this.value = value;
    }

    @Override
    public String asString() {
        // Если число целое (например, 120.0), выводим как "120"
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        // Иначе выводим с точкой (например, "120.5")
        return String.valueOf(value);
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

    @Override
    public JSValue mul(JSValue other) {
        if (other instanceof JSNumber) {
            return new JSNumber(this.value * ((JSNumber) other).value);
        }
        return super.mul(other);
    }
}
