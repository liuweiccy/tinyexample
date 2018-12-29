package com.digisky.liuwei2.tinyexample.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class DirListPractice1 {

    public static void main(String[] args) throws IOException {
        File path = new File(".\\src\\main\\java\\com\\digisky\\liuwei2\\tinyexample\\io");
        String text;
        if (args.length == 0) {
            text = "";
        } else {
            text = args[0].trim();
        }

        String[] fileNames = path.list(new DirFilter1(text));

        for (String name : fileNames) {
            System.out.println(name);
        }
    }
}


