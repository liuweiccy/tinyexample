package com.digisky.liuwei2.tinyexample.io.practice18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author liuwei2
 */
public class Practice7 {
    public static void main(String[] args) {
        try {
            String s = "local";
            FileReader fileReader = new FileReader("c:/unintall.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            LinkedList<String> linkedList = new LinkedList<>();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.toUpperCase().contains(s.toUpperCase())) {
                    linkedList.add(line);
                }
            }

            if (linkedList.size() > 0) {
                for (int i = 0; i < linkedList.size() ; i++) {
                    System.out.println(linkedList.get(linkedList.size() - 1 - i));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
