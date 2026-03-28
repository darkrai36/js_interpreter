package ru.vsu.cs.jslite.semantic;

import java.util.List;
import java.util.Collections;

/**
 * A class represents a symbol (variable or function) in the symbol table.
 */
public class Symbol {
    public enum Kind {
        VARIABLE,
        FUNCTION
    }

    private final String name;
    private final Kind kind;
    private final List<String> parameters; // only for FUNCTION
    private final boolean builtIn;


     //Constructor for variables (not functions)
    public Symbol(String name, Kind kind) {
        this(name, kind, Collections.emptyList(), false);
    }

//     Constructor for variables with built-in options
    public Symbol(String name, Kind kind, boolean builtIn) {
        this(name, kind, Collections.emptyList(), builtIn);
    }

//    Constructor for func
    public Symbol(String name, Kind kind, List<String> parameters, boolean builtIn) {
        this.name = name;
        this.kind = kind;
        this.parameters = parameters;
        this.builtIn = builtIn;
    }

    public String getName() {
        return name;
    }

    public Kind getKind() {
        return kind;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public boolean isBuiltIn() {
        return builtIn;
    }

    @Override
    public String toString() {
        if (kind == Kind.FUNCTION) {
            return "Function: " + name + "(" + String.join(", ", parameters) + ")";
        } else {
            return "Variable: " + name;
        }
    }
}