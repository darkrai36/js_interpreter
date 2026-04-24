/*
package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.semantic.SemanticAnalyzer;

public class Main {
    public static void main(String[] args) {
        // Test 1: Chương trình đúng
        System.out.println("TEST 1: CORRECT PROGRAM");
        String correctCode =
                "let x = 5;\n" +
                        "let y = 10;\n" +
                        "function add(a, b) {\n" +
                        "    return a + b;\n" +
                        "}\n" +
                        "let z = add(x, y);";
        testProgram(correctCode);

        // Test 2: Biến chưa khai báo
        System.out.println("\n\nTEST 2: UNDECLARED VARIABLE");
        String undeclaredVarCode =
                "let x = 5;\n" +
                        "let y = z + 1;\n";
        testProgram(undeclaredVarCode);

        // Test 3: Khai báo trùng
        System.out.println("\n\nTEST 3: DUPLICATE VARIABLE");

        String duplicateVarCode =
                "let x = 5;\n" +
                        "let x = 10;\n";
        testProgram(duplicateVarCode);

        // Test 4: Return ngoài hàm
        System.out.println("\n\nTEST 4: RETURN OUTSIDE FUNCTION");
        String returnOutsideCode =
                "let x = 5;\n" +
                        "return x;\n";
        testProgram(returnOutsideCode);

        // Test 5: Break ngoài vòng lặp
        System.out.println("\n\nTEST 5: BREAK OUTSIDE LOOP");
        String breakOutsideCode =
                "let x = 5;\n" +
                        "break;\n";
        testProgram(breakOutsideCode);

        // Test 6: Hàm chưa khai báo
        System.out.println("\n\nTEST 6: UNDECLARED FUNCTION");
        String undeclaredFuncCode =
                "let x = 5;\n" +
                        "let y = foo(x);\n";
        testProgram(undeclaredFuncCode);

        // Test 7: Số lượng đối số không khớp
        System.out.println("\n\nTEST 7: WRONG ARGUMENT COUNT");
        String wrongArgCountCode =
                "function add(a, b) {\n" +
                        "    return a + b;\n" +
                        "}\n" +
                        "let z = add(5);\n";
        testProgram(wrongArgCountCode);

        // Test 8: Tham số hàm trùng tên
        System.out.println("\n\nTEST 8: DUPLICATE PARAMETER");
        String duplicateParamCode =
                "function bad(a, a) {\n" +
                        "    return a;\n" +
                        "}\n";
        testProgram(duplicateParamCode);

        System.out.println("ALL TESTS COMPLETED");
    }

    private static void testProgram(String sourceCode) {
        System.out.println("Source code:");
        System.out.println(sourceCode);
        System.out.println();

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("AST Tree:");
        for (String line : ast.getTree()) {
            System.out.println(line);
        }
        System.out.println();

        System.out.println("Semantic Analysis Result:");
        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        try {
            analyzer.analyze(ast);
            System.out.println("PASSED - No semantic errors");
        } catch (RuntimeException e) {
            System.out.println("FAILED - Semantic errors detected (as expected)");
        }
    }
}*//*
*/
/*
*//*

*/
/*

*//*
*/
/*

*//*

*/
/*
package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;

public class Main {
    public static void main(String[] args) {
        String sourceCode =
                "let x = 10;\n" +
                        "const y = 5;\n" +
                        "x = x + y;\n" +
                        "y = 20; // ТУТ ДОЛЖНА БЫТЬ ОШИБКА!\n";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("Запуск интерпретатора:");
        Interpreter interpreter = new Interpreter();
        interpreter.execute(ast);
    }
}*//*
*/
/*
*//*

*/
/*

package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;

public class Main {
    public static void main(String[] args) {
        String sourceCode =
                "function factorial(n) {\n" +
                        "    if (n == 0) {\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "    return n * factorial(n - 1);\n" +
                        "}\n" +
                        "\n" +
                        "let result = factorial(5);\n" +
                        "// Так как функции print у нас пока нет, мы не можем вывести результат.\n" +
                        "// Но если интерпретатор не упал - он посчитал 120 в памяти!\n";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        // Чтобы умножение работало, убедись, что в JSNumber ты добавил метод mul!
        // (аналогично add и sub)

        System.out.println("Запуск интерпретатора...");
        Interpreter interpreter = new Interpreter();
        interpreter.execute(ast);
        System.out.println("Успешно завершено!");
    }
}*//*
*/
/*

package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;

public class Main {
    public static void main(String[] args) {
        String sourceCode =
                "function factorial(n) {\n" +
                        "    if (n == 0) {\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "    return n * factorial(n - 1);\n" +
                        "}\n" +
                        "\n" +
                        "let result = factorial(5);\n" +
                        "print(\"Факториал 5 равен:\", result);\n" +
                        "print(\"А факториал 10 равен:\", factorial(10));\n";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("--- ЗАПУСК ИНТЕРПРЕТАТОРА ---");
        Interpreter interpreter = new Interpreter();
        interpreter.execute(ast);
    }
}*//*

package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;

public class Main {
    public static void main(String[] args) {
        String sourceCode =
                "let marks = [5, 4, 3];\n" +
                        "marks[3] = 5; // Динамическое расширение массива\n" +
                        "\n" +
                        "let user = {\n" +
                        "    name: \"Ivan\",\n" +
                        "    age: 20,\n" +
                        "    getGrades: function() { return marks; }\n" + // Функция внутри объекта!
                        "};\n" +
                        "\n" +
                        "user.group = \"CS-1\"; // Добавляем новое свойство\n" +
                        "\n" +
                        "print(\"Пользователь:\", user);\n" +
                        "print(\"Оценки:\", user.getGrades());\n" +
                        "print(\"Первая оценка:\", user.getGrades()[0]);\n";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("--- ЗАПУСК ИНТЕРПРЕТАТОРА ---");
        Interpreter interpreter = new Interpreter();
        interpreter.execute(ast);
    }
}
*/
package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;
import ru.vsu.cs.jslite.runtime.exceptions.JSRuntimeException;

public class Main {
    public static void main(String[] args) {
        // Программа здоровается, просит ввести возраст и считает логику!
        String sourceCode =
                "print(\"Как тебя зовут?\");\n" +
                        "let name = read();\n" +
                        "print(\"Привет,\", name, \"! Введи свой год рождения:\");\n" +
                        "let year = to_number(read());\n" +
                        "\n" +
                        "let age = 2026 - year;\n" +
                        "print(\"Тебе примерно\", age, \"лет.\");\n" +
                        "\n" +
                        "if (age >= 18) {\n" +
                        "    print(\"Ты совершеннолетний!\");\n" +
                        "} else {\n" +
                        "    print(\"Тебе еще рано.\");\n" +
                        "}\n" +
                        "print(\"Проверка деления: 10 div 3 =\", 10 div 3);\n";

        Parser parser = new Parser();
        AstNodes.AstNode ast = parser.parse(sourceCode);

        System.out.println("--- ЗАПУСК ИНТЕРПРЕТАТОРА ---");
        try {
            Interpreter interpreter = new Interpreter();
            interpreter.execute(ast);
        } catch (JSRuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}