package ru.vsu.cs.jslite.runtime;

import ru.vsu.cs.jslite.AstNodes;
import ru.vsu.cs.jslite.AstNodes.*;
import ru.vsu.cs.jslite.runtime.datatypes.*;
import ru.vsu.cs.jslite.runtime.exceptions.JSContinue;
import ru.vsu.cs.jslite.runtime.exceptions.JSReturn;
import ru.vsu.cs.jslite.runtime.exceptions.JSRuntimeException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {
    private Environment currentEnv;

    public Interpreter() {
        // Создаем глобальный scope (он является функцией/корнем по умолчанию)
        this.currentEnv = new Environment(null, true);
        setupBuiltIns();
    }

    // Здесь позже добавим функции print, read и т.д.
    private void setupBuiltIns() {
        // Добавляем функцию print()
        currentEnv.declareConst("print", new JSBuiltInFunction() {
            @Override
            public JSValue call(List<JSValue> args) {
                for (int i = 0; i < args.size(); i++) {
                    System.out.print(args.get(i).asString());
                    if (i < args.size() - 1) System.out.print(" ");
                }
                System.out.println(); // Перенос строки в конце
                return JSUndefined.INSTANCE;
            }
        }, 0, 0);
    }

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

        if (node instanceof IfNode) return visitIf((IfNode) node);
        if (node instanceof WhileNode) return visitWhile((WhileNode) node);
        if (node instanceof ForNode) return visitFor((ForNode) node);
        if (node instanceof FuncDeclNode) return visitFuncDecl((FuncDeclNode) node);
        if (node instanceof CallNode) return visitCall((CallNode) node);
        if (node instanceof AnonFuncNode) return visitAnonFunc((AnonFuncNode) node);
        if (node instanceof ReturnNode) return visitReturn((ReturnNode) node);
        if (node instanceof ControlNode) return visitControl((ControlNode) node);

        if (node instanceof ArrayNode) return visitArray((ArrayNode) node);
        if (node instanceof HashNode) return visitHash((HashNode) node);
        if (node instanceof IndexNode) return visitIndex((IndexNode) node);
        if (node instanceof DotNode) return visitDot((DotNode) node);
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
        JSValue value = eval(node.value);

        if (node.target instanceof IdentNode) {
            // Обычная переменная: x = 5
            String name = ((IdentNode) node.target).name;
            currentEnv.assign(name, value, node.getLine(), node.getColumn());

        } else if (node.target instanceof IndexNode) {
            // Массив или объект по скобкам: arr[0] = 5 или obj["key"] = 5
            IndexNode indexNode = (IndexNode) node.target;
            JSValue targetObj = eval(indexNode.array);
            JSValue indexVal = eval(indexNode.index);

            if (targetObj instanceof JSArray) {
                int idx = (int) ((JSNumber) indexVal).value;
                List<JSValue> list = ((JSArray) targetObj).elements;
                // JS динамически расширяет массив, если индекс больше размера
                while (list.size() <= idx) list.add(JSUndefined.INSTANCE);
                list.set(idx, value);
            } else if (targetObj instanceof JSObject) {
                ((JSObject) targetObj).properties.put(indexVal.asString(), value);
            } else {
                throw new JSRuntimeException("Cannot assign to index of non-object", node.getLine(), node.getColumn());
            }

        } else if (node.target instanceof DotNode) {
            // Объект по точке: obj.name = "Ivan"
            DotNode dotNode = (DotNode) node.target;
            JSValue targetObj = eval(dotNode.obj);
            if (targetObj instanceof JSObject) {
                ((JSObject) targetObj).properties.put(dotNode.prop, value);
            } else {
                throw new JSRuntimeException("Cannot assign to property of non-object", node.getLine(), node.getColumn());
            }
        } else {
            throw new JSRuntimeException("Invalid left-hand side in assignment", node.getLine(), node.getColumn());
        }

        return value;
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
                case "*": return left.mul(right);
            // Тут добавим *, /, > , < и тд
            }
        } catch (RuntimeException e) {
            // Перехватываем ошибки JSValue (например, нельзя вычесть строки) и прикрепляем строку/колонку!
            throw new JSRuntimeException(e.getMessage(), node.getLine(), node.getColumn());
        }
        return JSUndefined.INSTANCE;
    }

    //--- ДОСТУП И ДАННЫЕ ---
    private JSValue visitArray(ArrayNode node) {
        List<JSValue> elements = new ArrayList<>();
        for (ExprNode elem : node.elements) {
            elements.add(eval(elem));
        }
        return new JSArray(elements);
    }

    private JSValue visitHash(HashNode node) {
        Map<String, JSValue> map = new LinkedHashMap<>();
        for (AstNodes.HashElementNode elem : node.elements) {
            map.put(elem.key, eval(elem.value));
        }
        return new JSObject(map);
    }

    // Чтение по индексу arr[0] или arr["name"]
    private JSValue visitIndex(IndexNode node) {
        JSValue arrayVal = eval(node.array);
        JSValue indexVal = eval(node.index);

        if (arrayVal instanceof JSArray) {
            int idx = (int) ((JSNumber) indexVal).value;
            List<JSValue> list = ((JSArray) arrayVal).elements;
            if (idx >= 0 && idx < list.size()) return list.get(idx);
            return JSUndefined.INSTANCE; // Если вышли за границы массива - в JS это undefined
        }
        if (arrayVal instanceof JSObject) {
            String key = indexVal.asString();
            return ((JSObject) arrayVal).properties.getOrDefault(key, JSUndefined.INSTANCE);
        }
        throw new JSRuntimeException("Cannot read properties of undefined", node.getLine(), node.getColumn());
    }

    // Чтение по точке obj.name
    private JSValue visitDot(DotNode node) {
        JSValue objVal = eval(node.obj);
        if (objVal instanceof JSObject) {
            return ((JSObject) objVal).properties.getOrDefault(node.prop, JSUndefined.INSTANCE);
        }
        throw new JSRuntimeException("Cannot read property '" + node.prop + "' of undefined", node.getLine(), node.getColumn());
    }

    // --- УПРАВЛЯЮЩИЕ КОНСТРУКЦИИ (IF, WHILE, FOR) ---
    private JSValue visitIf(IfNode node) {
        JSValue condition = eval(node.condition);
        if (condition.asBoolean()) {
            eval(node.thenBranch);
        } else if (node.elseBranch != null) {
            eval(node.elseBranch);
        }
        return JSUndefined.INSTANCE;
    }

    private JSValue visitWhile(WhileNode node) {
        // Обычный Java-цикл крутит внутри себя AST-дерево JS!
        while (eval(node.condition).asBoolean()) {
            try {
                eval(node.body);
            } catch (JSBreak b) {
                break; // Перехватили JS break -> делаем Java break
            } catch (JSContinue c) {
                // Перехватили JS continue -> идем на новую итерацию
            }
        }
        return JSUndefined.INSTANCE;
    }

    private JSValue visitFor(ForNode node) {
        // Создаем новый скоп, чтобы переменная из for(let i=0) не утекла наружу
        Environment forEnv = new Environment(currentEnv, false);
        Environment oldEnv = currentEnv;
        currentEnv = forEnv;

        try {
            if (node.init != null) eval(node.init);

            while (node.condition == null || eval(node.condition).asBoolean()) {
                try {
                    eval(node.body);
                } catch (JSBreak b) {
                    break;
                } catch (JSContinue c) {
                    // continue прыгает сюда, перед шагом (step)
                }
                if (node.step != null) eval(node.step);
            }
        } finally {
            currentEnv = oldEnv;
        }
        return JSUndefined.INSTANCE;
    }

    private JSValue visitControl(ControlNode node) {
        if (node.type.equals("break")) throw new JSBreak();
        if (node.type.equals("continue")) throw new JSContinue();
        return JSUndefined.INSTANCE;
    }

    // --- ФУНКЦИИ ---
    private JSValue visitFuncDecl(FuncDeclNode node) {
        // Создаем объект функции. Передаем currentEnv как Замыкание (Closure)!
        JSFunction func = new JSFunction(node.params, node.body, currentEnv);
        // В JS функции "всплывают", используем var-подобное объявление
        currentEnv.declareVar(node.name, func);
        return JSUndefined.INSTANCE;
    }

    private JSValue visitReturn(ReturnNode node) {
        JSValue value = node.value != null ? eval(node.value) : JSUndefined.INSTANCE;
        throw new JSReturn(value); // Выкидываем "лифт" с результатом!
    }

    private JSValue visitCall(CallNode node) {
        // 1. Вычисляем то, что мы пытаемся вызвать (например, user.getGrades)
        JSValue funcVal = eval(node.funcExpr);

        // 2. Вычисляем аргументы
        List<JSValue> argValues = new ArrayList<>();
        for (ExprNode arg : node.args) {
            argValues.add(eval(arg));
        }

        // --- Встроенные функции (print) ---
        if (funcVal instanceof JSBuiltInFunction) {
            return ((JSBuiltInFunction) funcVal).call(argValues);
        }

        // --- Пользовательские функции ---
        if (!(funcVal instanceof JSFunction)) {
            throw new JSRuntimeException("Expression is not a function", node.getLine(), node.getColumn());
        }
        JSFunction func = (JSFunction) funcVal;

        // 3. Создаем область памяти вызова и привязываем параметры
        Environment funcEnv = new Environment(func.closure, true);
        for (int i = 0; i < func.params.size(); i++) {
            JSValue val = (i < argValues.size()) ? argValues.get(i) : JSUndefined.INSTANCE;
            funcEnv.declareLet(func.params.get(i), val, node.getLine(), node.getColumn());
        }

        // 4. Выполняем тело функции
        Environment oldEnv = currentEnv;
        currentEnv = funcEnv;
        try {
            eval(func.body);
        } catch (JSReturn ret) {
            return ret.value;
        } finally {
            currentEnv = oldEnv;
        }

        return JSUndefined.INSTANCE;
    }

    private JSValue visitAnonFunc(AnonFuncNode node) {
        // Создаем функцию и запоминаем текущий Scope (замыкание)
        return new JSFunction(node.params, node.body, currentEnv);
    }
}