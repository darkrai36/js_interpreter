package ru.vsu.cs.jslite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.vsu.cs.jslite.Parser;
import ru.vsu.cs.jslite.AstNodes;
import ru.vsu.cs.jslite.runtime.Interpreter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InterpreterTest {

    private Parser parser;
    private Interpreter interpreter;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        interpreter = new Interpreter();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    // Helper method to run code and get output
    private String runCode(String code) {
        outputStream.reset();
        AstNodes.AstNode ast = parser.parse(code);
        interpreter.execute(ast);
        return outputStream.toString().trim();
    }

    // BASIC MATH

    @Test
    void testBasicVariables() {
        String code =
                "let x = 5;" +
                        "let y = 10;" +
                        "print(x);" +
                        "print(y);";

        String output = runCode(code);
        assertTrue(output.contains("5"));
        assertTrue(output.contains("10"));
    }

    @Test
    void testSimpleArithmetic() {
        String code =
                "let a = 10 + 5;" +
                        "let b = 20 - 8;" +
                        "let c = 4 * 3;" +
                        "let d = 15 / 3;" +
                        "print(a);" +
                        "print(b);" +
                        "print(c);" +
                        "print(d);";

        String output = runCode(code);
        assertTrue(output.contains("15"));
        assertTrue(output.contains("12"));
        assertTrue(output.contains("12"));
        assertTrue(output.contains("5"));
    }

    @Test
    void testStringOutput() {
        String code = "print(\"Hello World\");";
        String output = runCode(code);
        assertEquals("Hello World", output);
    }

    @Test
    void testStringConcatenation() {
        String code =
                "let name = \"John\";" +
                        "print(\"Hello \" + name);";

        String output = runCode(code);
        assertEquals("Hello John", output);
    }

    // CONDITION COMMANDS

    @Test
    void testIfStatement() {
        String code =
                "let x = 10;" +
                        "if (x > 5) {" +
                        "    print(\"greater\");" +
                        "}";

        String output = runCode(code);
        assertEquals("greater", output);
    }

    @Test
    void testIfElseStatement() {
        String code =
                "let x = 3;" +
                        "if (x > 5) {" +
                        "    print(\"greater\");" +
                        "} else {" +
                        "    print(\"smaller\");" +
                        "}";

        String output = runCode(code);
        assertEquals("smaller", output);
    }

    @Test
    void testComparisonOperators() {
        String code =
                "print(5 == 5);" +
                        "print(5 != 3);" +
                        "print(10 > 5);" +
                        "print(3 < 8);";

        String output = runCode(code);
        assertTrue(output.contains("1.0"));
    }

    // LOOPS

    @Test
    void testWhileLoop() {
        String code =
                "let i = 0;" +
                        "while (i < 3) {" +
                        "    print(i);" +
                        "    i = i + 1;" +
                        "}";

        String output = runCode(code);
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    @Test
    void testForLoop() {
        String code =
                "for (let i = 0; i < 3; i = i + 1) {" +
                        "    print(i);" +
                        "}";

        String output = runCode(code);
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    @Test
    void testBreakInLoop() {
        String code =
                "for (let i = 0; i < 10; i = i + 1) {" +
                        "    if (i == 2) {" +
                        "        break;" +
                        "    }" +
                        "    print(i);" +
                        "}";

        String output = runCode(code);
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertFalse(output.contains("2")); // break before print
    }

    // FUNCTIONS

    @Test
    void testFunctionDeclaration() {
        String code =
                "function add(a, b) {" +
                        "    return a + b;" +
                        "}" +
                        "print(add(5, 3));";

        String output = runCode(code);
        assertTrue(output.contains("8"));
    }

    @Test
    void testRecursiveFunction() {
        String code =
                "function factorial(n) {" +
                        "    if (n == 0) {" +
                        "        return 1;" +
                        "    }" +
                        "    return n * factorial(n - 1);" +
                        "}" +
                        "print(factorial(5));";

        String output = runCode(code);
        assertTrue(output.contains("120"));
    }

    @Test
    void testMultipleFunctionCalls() {
        String code =
                "function double(x) {" +
                        "    return x * 2;" +
                        "}" +
                        "print(double(5));" +
                        "print(double(10));" +
                        "print(double(double(3)));";

        String output = runCode(code);
        assertTrue(output.contains("10"));
        assertTrue(output.contains("20"));
        assertTrue(output.contains("12"));
    }

    // ARRAYS

    @Test
    void testArrayCreation() {
        String code =
                "let arr = [1, 2, 3, 4, 5];" +
                        "print(arr[0]);" +
                        "print(arr[2]);" +
                        "print(arr[4]);";

        String output = runCode(code);
        assertTrue(output.contains("1"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("5"));
    }

    @Test
    void testArrayModification() {
        String code =
                "let arr = [1, 2, 3];" +
                        "arr[1] = 99;" +
                        "print(arr[1]);";

        String output = runCode(code);
        assertTrue(output.contains("99"));
    }

    // OBJECT

    @Test
    void testObjectCreation() {
        String code =
                "let person = {" +
                        "    name: \"John\"," +
                        "    age: 30" +
                        "};" +
                        "print(person.name);" +
                        "print(person.age);";

        String output = runCode(code);
        assertTrue(output.contains("John"));
        assertTrue(output.contains("30"));
    }

    @Test
    void testObjectPropertyAssignment() {
        String code =
                "let obj = {x: 1};" +
                        "obj.y = 2;" +
                        "print(obj.x);" +
                        "print(obj.y);";

        String output = runCode(code);
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    // BUILT-IN FUNCTIONS

    @Test
    void testPrintMultipleArguments() {
        String code = "print(\"Hello\", \"World\", 123);";
        String output = runCode(code);
        assertEquals("Hello World 123", output);
    }

    @Test
    void testToNumberFunction() {
        String code =
                "print(to_number(\"42\"));" +
                        "print(to_number(\"3.14\"));" +
                        "print(to_number(\"100\") + 50);";

        String output = runCode(code);
        assertTrue(output.contains("42"));
        assertTrue(output.contains("3.14"));
        assertTrue(output.contains("150"));
    }

    // ERRORS

    @Test
    void testConstReassignmentError() {
        String code =
                "const x = 5;" +
                        "x = 10;";

        assertThrows(Exception.class, () -> {
            AstNodes.AstNode ast = parser.parse(code);
            interpreter.execute(ast);
        });
    }

    @Test
    void testUndefinedVariableError() {
        String code = "print(undefinedVar);";

        assertThrows(Exception.class, () -> {
            AstNodes.AstNode ast = parser.parse(code);
            interpreter.execute(ast);
        });
    }

    // MORE COMPLICATED

    @Test
    void testComplexCalculation() {
        // Test from Main.java
        String code =
                "let x = 10;" +
                        "const y = 5;" +
                        "x = x + y;" +
                        "print(x);";

        String output = runCode(code);
        assertTrue(output.contains("15"));
    }

    @Test
    void testHelloWorldProgram() {
        // Test similar to Main.java interactive program
        String code =
                "let name = \"Alice\";" +
                        "print(\"Hello\", name);" +
                        "let age = 25;" +
                        "if (age >= 18) {" +
                        "    print(\"Adult\");" +
                        "}";

        String output = runCode(code);
        assertTrue(output.contains("Hello Alice"));
        assertTrue(output.contains("Adult"));
    }

    @Test
    void testDivOperator() {
        // Test div operator from Main.java
        String code = "print(10 div 3);";
        String output = runCode(code);
        assertTrue(output.contains("3")); // Integer division
    }

    @Test
    void testModOperator() {
        String code = "print(10 mod 3);";
        String output = runCode(code);
        assertTrue(output.contains("1"));
    }
}