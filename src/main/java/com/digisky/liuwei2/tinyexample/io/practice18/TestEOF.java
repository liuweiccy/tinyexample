package com.digisky.liuwei2.tinyexample.io.practice18;

import java.io.*;

/**
 * @author liuwei2
 */
public class TestEOF {
    public static void main(String[] args) {
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("c:/unintall.log")
                    )
            );
            while (dataInputStream.available() != 0) {
                System.out.print((char) dataInputStream.readByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
