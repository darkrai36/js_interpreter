package ru.vsu.cs.jslite.runtime;

import ru.vsu.cs.jslite.AstNodes;

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
        return "[Functrion]";
    }

    @Override
    public boolean asBoolean() {
        return true;
    }
}
