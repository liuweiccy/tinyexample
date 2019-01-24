package com.digisky.liuwei2.tinyexample.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author liuwei2
 */
public class MemoryInput {
    public static void main(String[] args) {
        try {
            StringReader stringReader = new StringReader(BufferedInputFile.read("c:/unintall.log"));
            int c;
            while ((c = stringReader.read()) != -1) {
               System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
