package ru.vsu.cs.jslite;

public class Main {
    public static void main(String[] args) {
        // Наш тестовый код на JS
        String sourceCode = "let x = 10 + 5 * 2;\n" +
                "x = x - 3;";

        // Создаем наш парсер-обертку
        Parser parser = new Parser();

        // Получаем готовое дерево
        AstNodes.AstNode ast = parser.parse(sourceCode);

        // Красивый вывод в консоль
        System.out.println("Исходный код:\n" + sourceCode + "\n");
        System.out.println("AST Дерево:");
        if (ast != null) {
            for (String line : ast.getTree()) {
                System.out.println(line);
            }
        }
    }
}