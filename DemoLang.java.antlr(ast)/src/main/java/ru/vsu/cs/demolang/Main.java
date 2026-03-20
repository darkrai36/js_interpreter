package ru.vsu.cs.demolang;

import ru.vsu.cs.demolang.AstNode;
import ru.vsu.cs.demolang.StmtListNode;
import ru.vsu.cs.demolang.Parser;

public class Main {
    public static void main(String[] args) {
        String prog = """
            int a(int b, string s) {
                print(a);
                return a + 1;            
            }    
        
            {
                a = input();
                b = input();
            } ;;;;; /* comment 1
            input c
            */
            int aaa, b = 7, f = ggg();
            string c = a + b * (2 - 1) + 0;  // comment 2
            output(c + 1 + gjhgjhg(3, 5));
            if (a) {
                print(a);  ;;;;
                  if (a + b) print(a + b);
                else 
                  while (++a < 5)
                    for (int a = 1; a != 10 && a % 3 > b || f(); );
                       print(a);
                  break;
            }
            a++;
        """;

        StmtListNode result = Parser.parse(prog);
        for (String line : result.getTree()) {
            System.out.println(line);
        }
    }
}
