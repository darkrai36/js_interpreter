package ru.vsu.cs.jslite.runtime.datatypes;

import java.util.List;
import java.util.stream.Collectors;

public class JSArray extends JSValue {
    public final List<JSValue> elements;
    public JSArray(List<JSValue> elements) {
        this.elements = elements;
    }

    @Override
    public String asString() {
        return "[" + elements.stream().map(JSValue::asString).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public boolean asBoolean() {
        return true;
    }
}
