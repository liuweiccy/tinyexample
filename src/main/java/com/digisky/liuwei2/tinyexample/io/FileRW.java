package com.digisky.liuwei2.tinyexample.io;

import java.io.*;
import java.util.Arrays;

/**
 * @author liuwei2
 */
public class FileRW {

    public static void main(String[] args) throws IOException {
        File file = new File("C:/EventInfo.java");
        FileReader fileReader = new FileReader(file);
        char[] text = new char[1024];
        int n = fileReader.read(text);
        if (n > 0) {
            System.out.println(String.valueOf(text, 0, n));
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Hello, Go with Java");
        fileWriter.flush();

        fileReader.close();
        fileWriter.close();

        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("thx");
        printWriter.flush();
        printWriter.close();

    }
}
