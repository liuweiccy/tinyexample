package com.digisky.liuwei2.tinyexample.io.practice18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * @author liuwei2
 */
public class Practice12 {
    public static void main(String[] args) {
        try {
            String file = "Practice12.out";
            FileReader fileReader = new FileReader("c:/unintall.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            LinkedList<String> linkedList = new LinkedList<>();
            while ((line = bufferedReader.readLine()) != null) {
                linkedList.add(line);
            }
            bufferedReader.close();

            if (linkedList.size() > 0) {
                PrintWriter printWriter = new PrintWriter(file);
                int no = 0;
                for (String s : linkedList) {
                    printWriter.printf("%d %s\n", ++no, s);
                }
                printWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
