package com.digisky.liuwei2.tinyexample.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class DirList {

    public static void main(String[] args) throws IOException {
        File file = new File(".");
        FileFilter filter = pathname -> pathname.getName().endsWith("xml");

        File[] files = file.listFiles(filter);
        if (files != null) {
            for (File file1 : files) {
                System.out.println(file1.getName());
                FileReader fileReader = new FileReader(file1);
                char[] b = new char[1024*1024];
                int length = fileReader.read(b);
                System.out.println(length);
                System.out.println(String.valueOf(b));
            }
        }
    }
}
