package ru.vsu.cs.jslite;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.vsu.cs.jslite.antlr.JSLiteLexer;
import ru.vsu.cs.jslite.antlr.JSLiteParser;

public class Parser {

    // Метод принимает строку с кодом и возвращает корень AST-дерева
    public AstNodes.AstNode parse(String sourceCode) {
        // 1. Лексический анализ
        JSLiteLexer lexer = new JSLiteLexer(CharStreams.fromString(sourceCode));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 2. Синтаксический анализ
        JSLiteParser parser = new JSLiteParser(tokens);
        JSLiteParser.ProgramContext tree = parser.program(); // Начинаем с корня "program"

        // 3. Построение нашего AST
        AstBuilder builder = new AstBuilder();
        return builder.visit(tree);
    }
}