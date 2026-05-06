package ru.vsu.cs.jslite;

import ru.vsu.cs.jslite.runtime.Interpreter;
import ru.vsu.cs.jslite.runtime.exceptions.JSRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: Please provide the path to the sourcecode file.");
            System.err.println("How to: java -cp <classpath> ru.vsu.cs.jslite.Main <path.txt>");
            return;
        }

        String filePath = args[0];
        String sourceCode;

        try {
            Path path = Paths.get(filePath);
            sourceCode = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.err.println("Can't read file: " + e.getMessage());
            return;
        }

        Parser parser = new Parser();
        AstNodes.AstNode ast;
        try {
            ast = parser.parse(sourceCode);
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
            return;
        }

        System.out.println("--- ЗАПУСК ИНТЕРПРЕТАТОРА ---");
        try {
            Interpreter interpreter = new Interpreter();
            interpreter.execute(ast);
        } catch (JSRuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}