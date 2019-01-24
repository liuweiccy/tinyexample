package com.digisky.liuwei2.tinyexample.io;

import java.io.*;

/**
 * @author liuwei2
 */
public class CBT {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cbt.txt"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("cbt_new.txt")));

        String s;
        while ((s = reader.readLine()) != null) {
            String s2;
            if ((s2 = reader.readLine()) != null) {
                writer.printf("%s %s\n", s, s2);
            }
        }

        reader.close();
        writer.close();
    }
}
