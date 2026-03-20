package ru.vsu.cs.jslite;

public class Main {
    public static void main(String[] args) {
        String sourceCode =
                "let user = { name: \"Ivan\", age: 20 };\n" +
                        "let marks = [5, 4, 5, 3];\n" +
                        "\n" +
                        "function calculateAverage(arr) {\n" +
                        "    let sum = 0;\n" +
                        "    for (let i = 0; i < 4; i = i + 1) {\n" +
                        "        if (arr[i] == null) { break; }\n" +
                        "        sum = sum + arr[i];\n" +
                        "    }\n" +
                        "    return sum / 4;\n" +
                        "}\n" +
                        "\n" +
                        "user.avg = calculateAverage(marks);";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("Исходный код:\n" + sourceCode + "\n");
        System.out.println("AST Дерево:");
        if (ast != null) {
            for (String line : ast.getTree()) {
                System.out.println(line);
            }
        }
    }
}