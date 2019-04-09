package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @author liuwei2
 */
public class BasicFileOutput2 {
    static String file = "BasicFileOutput2.out";

    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader(BufferedInputFile.read("c:/unintall.log"));
        BufferedReader bufferedReader = new BufferedReader(stringReader);

        PrintWriter printWriter = new PrintWriter(file);
        String line;
        int lineNum = 0;
        while ((line = bufferedReader.readLine()) != null) {
            printWriter.printf("%d : %s\n", ++lineNum, line);
        }
        bufferedReader.close();
        printWriter.close();
    }
}
