package ru.vsu.cs.jslite.runtime.datatypes;

import java.util.Map;
import java.util.stream.Collectors;

public class JSObject extends JSValue {
    public final Map<String, JSValue> properties;
    public JSObject(Map<String, JSValue> properties) {
        this.properties = properties;
    }

    @Override
    public String asString() {
        return "{" + properties.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue().asString())
                .collect(Collectors.joining(", ")) + "}";
    }

    @Override
    public boolean asBoolean() {
        return true;
    }
}
