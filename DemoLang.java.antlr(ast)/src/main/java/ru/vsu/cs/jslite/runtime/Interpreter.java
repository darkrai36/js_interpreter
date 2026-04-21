package ru.vsu.cs.jslite.runtime;

import ru.vsu.cs.jslite.AstNodes.*;

public class Interpreter {
    private Environment currentEnv;

    public Interpreter() {
        // Создаем глобальный scope (он является функцией/корнем по умолчанию)
        this.currentEnv = new Environment(null, true);
        setupBuiltIns();
    }

    // Здесь позже добавим функции print, read и т.д.
    private void setupBuiltIns() { }

    // Главный метод выполнения, запускающий обход!
    public void execute(AstNode root) {
        try {
            eval(root);
            System.out.println("Program executed successfully.");
        } catch (JSRuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    // Вместо traverse(void), теперь eval(JSValue)
    private JSValue eval(AstNode node) {
        if (node == null) return JSUndefined.INSTANCE;

        if (node instanceof ProgramNode) return visitProgram((ProgramNode) node);
        if (node instanceof BlockNode) return visitBlock((BlockNode) node);
        if (node instanceof VarDeclNode) return visitVarDecl((VarDeclNode) node);
        if (node instanceof AssignNode) return visitAssign((AssignNode) node);
        if (node instanceof ExprStmtNode) return visitExprStmt((ExprStmtNode) node);
        if (node instanceof IdentNode) return visitIdent((IdentNode) node);
        if (node instanceof NumNode) return visitNum((NumNode) node);
        if (node instanceof StringNode) return visitString((StringNode) node);
        if (node instanceof BinOpNode) return visitBinOp((BinOpNode) node);

        // Для узлов, которые пока не реализовали
        return JSUndefined.INSTANCE;
    }

    // --- БАЗОВЫЕ ТИПЫ ---
    private JSValue visitNum(NumNode node) {
        return new JSNumber(node.value); // Превращаем узел дерева в значение рантайма!
    }

    private JSValue visitString(StringNode node) {
        return new JSString(node.value);
    }

    private JSValue visitIdent(IdentNode node) {
        // Достаем значение из памяти
        return currentEnv.get(node.name, node.getLine(), node.getColumn());
    }

    // --- ОПЕРАТОРЫ И ПЕРЕМЕННЫЕ ---
    private JSValue visitProgram(ProgramNode node) {
        JSValue lastResult = JSUndefined.INSTANCE;
        for (StmtNode stmt : node.statements) {
            lastResult = eval(stmt);
        }
        return lastResult;
    }

    private JSValue visitBlock(BlockNode node) {
        // Создаем новый блок памяти (isFunctionScope = false)
        Environment blockEnv = new Environment(currentEnv, false);
        Environment oldEnv = currentEnv;
        currentEnv = blockEnv; // Погружаемся в блок

        JSValue lastResult = JSUndefined.INSTANCE;
        try {
            for (StmtNode stmt : node.statements) {
                lastResult = eval(stmt);
            }
        } finally {
            currentEnv = oldEnv; // Возвращаемся обратно (ОЧЕНЬ ВАЖНО, даже если была ошибка!)
        }
        return lastResult;
    }

    private JSValue visitVarDecl(VarDeclNode node) {
        JSValue value = node.value != null ? eval(node.value) : JSUndefined.INSTANCE;

        switch (node.modifier) {
            case "let": currentEnv.declareLet(node.name, value, node.getLine(), node.getColumn()); break;
            case "var": currentEnv.declareVar(node.name, value); break;
            case "const": currentEnv.declareConst(node.name, value, node.getLine(), node.getColumn()); break;
        }
        return JSUndefined.INSTANCE; // Объявление не возвращает результата
    }

    private JSValue visitAssign(AssignNode node) {
        JSValue value = eval(node.value); // Вычисляем правую часть

        // Пока поддерживаем присваивание только простым переменным (позже добавим массивы a[0] = 1)
        if (node.target instanceof IdentNode) {
            String name = ((IdentNode) node.target).name;
            currentEnv.assign(name, value, node.getLine(), node.getColumn());
        } else {
            throw new JSRuntimeException("Invalid left-hand side in assignment", node.getLine(), node.getColumn());
        }
        return value; // В JS присваивание возвращает значение (например, a = b = 5)
    }

    private JSValue visitExprStmt(ExprStmtNode node) {
        return eval(node.expr);
    }

    // --- ВЫЧИСЛЕНИЯ ---
    private JSValue visitBinOp(BinOpNode node) {
        JSValue left = eval(node.left);
        JSValue right = eval(node.right);

        try {
            switch (node.op) {
                case "+": return left.add(right);
                case "-": return left.sub(right); // (Убедись, что добавил sub в JSValue и JSNumber)
                case "==": return new JSNumber(left.isEquals(right) ? 1.0 : 0.0); // JS boolean эмулируем числами
                // Тут добавим *, /, > , < и тд
            }
        } catch (RuntimeException e) {
            // Перехватываем ошибки JSValue (например, нельзя вычесть строки) и прикрепляем строку/колонку!
            throw new JSRuntimeException(e.getMessage(), node.getLine(), node.getColumn());
        }
        return JSUndefined.INSTANCE;
    }
}