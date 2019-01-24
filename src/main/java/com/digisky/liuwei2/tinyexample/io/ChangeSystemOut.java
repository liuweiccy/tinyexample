package com.digisky.liuwei2.tinyexample.io;

import java.io.PrintWriter;

/**
 * @author liuwei2
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }
}
