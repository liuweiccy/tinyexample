package com.digisky.liuwei2.tinyexample.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class FormatMemoryInput {
    public static void main(String[] args) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(
                    BufferedInputFile.read("c:/unintall.log").getBytes()
            ));
            while (true) {
                System.out.print((char) dataInputStream.readByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
