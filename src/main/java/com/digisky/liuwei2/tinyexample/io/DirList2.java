package com.digisky.liuwei2.tinyexample.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class DirList2 {

    public static void main(String[] args) throws IOException {
        File path = new File("D:\\git\\tinyexample\\src\\main\\java\\com\\digisky\\liuwei2\\tinyexample\\io");
        File[] list;
        list = path.listFiles(new DirFilter2("java"));

        long sum = 0L;
        if (list != null) {
            for (File file : list) {
                sum += file.length();
                System.out.println(file.getName() + "-----" + file.length() + " byte");
            }
            System.out.println("所有文件大小:" + sum + " byte");
        }
    }
}


class DirFilter2 implements FileFilter {

    private String ext;

    public DirFilter2(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().contains(ext);
    }
}
