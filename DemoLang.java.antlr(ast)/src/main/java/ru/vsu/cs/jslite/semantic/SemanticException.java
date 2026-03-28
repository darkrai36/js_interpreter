package ru.vsu.cs.jslite.semantic;

//Exception for semantic errors
public class SemanticException extends RuntimeException {
    private final int line;
    private final int column;

    public SemanticException(String message, int line, int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("Semantic error at line %d:%d - %s",
                line, column, getMessage());
    }
}