package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author liuwei2
 */
public class Redircting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("BasicFileOutput.out"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("Redircting.out")));

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }

        out.close();
        System.setOut(console);

        System.out.println("END");
    }
}
