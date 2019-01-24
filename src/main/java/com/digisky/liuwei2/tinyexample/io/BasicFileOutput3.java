package com.digisky.liuwei2.tinyexample.io;

import java.io.*;

/**
 * @author liuwei2
 */
public class BasicFileOutput3 {
    static String file = "BasicFileOutput3.out";

    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader(BufferedInputFile.read("c:/unintall.log"));
        LineNumberReader bufferedReader = new LineNumberReader(stringReader);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        long s = System.nanoTime();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(bufferedReader.getLineNumber() + " : " + line + "\n");
        }
        long e = System.nanoTime();

        System.out.printf("有缓冲区耗时: %dns\n", e-s);

        s = System.nanoTime();
        FileWriter fileWriter = new FileWriter(file);
        while ((line = bufferedReader.readLine()) != null) {
            fileWriter.write(bufferedReader.getLineNumber() + " : " + line + "\n");
        }
        e = System.nanoTime();
        System.out.printf("无缓冲区耗时: %dns\n", e-s);

        fileWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
