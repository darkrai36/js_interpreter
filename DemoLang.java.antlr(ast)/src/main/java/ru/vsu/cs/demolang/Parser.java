package ru.vsu.cs.demolang;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ru.vsu.cs.demolang.antlr.*;

public class Parser {

    public static StmtListNode parse(String prog) {
        DemoLangLexer lexer = new DemoLangLexer(CharStreams.fromString(prog));
        CommonTokenStream stream = new CommonTokenStream(lexer);
        DemoLangParser parser = new DemoLangParser(stream);

        // Строим дерево
        DemoLangParser.StartContext tree = parser.start();

        // Используем visitor для построения AST
        DemoLangVisitor builder = new AstBuilder();
        StmtListNode result = (StmtListNode) builder.visit(tree);

        return result != null ? result : new StmtListNode();
    }
}
