package com.CallSlang;

import com.slang.*;
import com.slang.Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void TestFileScript(String filename) throws Exception {
        if (filename == null) {
            return;
        }
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(new FileInputStream(filename));
        try {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }

        RDParser parse = new RDParser(text.toString());
        Module p = null;
        p = parse.doParse();
        if (p == null) {
            System.out.println("Parse Process Failed");
            return;
        }
        RuntimeContext f = new RuntimeContext(p);
        Symbol fp = p.Execute(f, null)  ;

    }

    public static void main(String[] args) throws Exception, IOException {
        if (args == null || args.length != 1) {
            System.out.println("CallSlang <scriptname>\n");
            return;
        }

        TestFileScript(args[0]);
        System.in.read();
    }
}
