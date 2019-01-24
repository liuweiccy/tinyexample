package com.digisky.liuwei2.tinyexample.io;

import java.io.*;

/**
 * @author liuwei2
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        String file = "StoringAndRecoveringData.txt";
        DataOutputStream dataOutputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)));

        dataOutputStream.writeDouble(3.1415);
        dataOutputStream.writeUTF("Hello Java");
        dataOutputStream.writeDouble(1.4131413);
        dataOutputStream.writeUTF("Hello Go");
        dataOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
    }
}
