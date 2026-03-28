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
}