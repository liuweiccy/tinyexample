package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author liuwei2
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader(BufferedInputFile.read("c:/unintall.log"));
        BufferedReader bufferedReader = new BufferedReader(stringReader);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        String line;
        int lineNum = 0;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(++ lineNum + " : " + line + "\n");
        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
