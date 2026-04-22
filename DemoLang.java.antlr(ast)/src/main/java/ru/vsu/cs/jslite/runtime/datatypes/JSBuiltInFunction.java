package ru.vsu.cs.jslite.runtime.datatypes;

import java.util.List;

public abstract class JSBuiltInFunction extends JSValue {
    public abstract JSValue call(List<JSValue> args);

    @Override
    public String asString() { return "[Native Function]"; }

    @Override
    public boolean asBoolean() { return true; }
}
