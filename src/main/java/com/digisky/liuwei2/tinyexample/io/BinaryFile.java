package com.digisky.liuwei2.tinyexample.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class BinaryFile {
    public static byte[] read(File file) throws IOException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
        byte[] data = new byte[buffer.available()];
        buffer.read(data);
        buffer.close();
        return data;
    }

    public static byte[] read(String filename) throws IOException {
        return read(new File(filename));
    }
}
