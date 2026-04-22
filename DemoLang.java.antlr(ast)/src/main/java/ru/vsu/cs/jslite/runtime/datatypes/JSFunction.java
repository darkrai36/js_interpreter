package ru.vsu.cs.jslite.runtime.datatypes;

import ru.vsu.cs.jslite.AstNodes;
import ru.vsu.cs.jslite.runtime.Environment;

import java.util.List;

public class JSFunction extends JSValue {
    public final List<String> params;
    public final AstNodes.BlockNode body;
    public final Environment closure;

    public JSFunction(List<String> params, AstNodes.BlockNode body, Environment closure) {
        this.params = params;
        this.body = body;
        this.closure = closure;
    }

    @Override
    public String asString() {
        return "[Function]";
    }

    @Override
    public boolean asBoolean() {
        return true;
    }
}
