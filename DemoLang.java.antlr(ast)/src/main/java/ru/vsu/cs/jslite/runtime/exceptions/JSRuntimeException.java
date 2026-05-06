package ru.vsu.cs.jslite.runtime.exceptions;

public class JSRuntimeException extends RuntimeException {
    public JSRuntimeException(String message, int line, int column) {
        super(String.format("Runtime Error at line %d:%d - %s", line, column, message));
    }
}
