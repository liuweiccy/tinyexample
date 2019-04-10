package com.digisky.liuwei2.tinyexample.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author liuwei2
 */
public class DirList1 {

    public static void main(String[] args) throws IOException {
        File path = new File("D:\\git\\tinyexample\\src\\main\\java\\com\\digisky\\liuwei2\\tinyexample\\io");
        String[] list;
        list = path.list(new DirFilter("java"));

        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
    }
}


class DirFilter implements FilenameFilter {

    private String ext;

    public DirFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        System.out.println(dir.getName() + "-----------" + name);
        return name.endsWith("." + ext);
    }
}
