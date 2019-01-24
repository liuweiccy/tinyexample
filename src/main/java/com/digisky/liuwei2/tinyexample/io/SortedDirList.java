package com.digisky.liuwei2.tinyexample.io;

import java.io.File;

/**
 * @author liuwei2
 */
public class SortedDirList {

    public static void main(String[] args) {
        String path;
        String text;
        path = "D:\\git\\tinyexample\\src\\main\\java\\com\\digisky\\liuwei2\\tinyexample\\io";
        text = "1";

        File filePath = new File(path);

        String[] list1 = filePath.list();
        list1 = list1 == null ? new String[0] : list1;
        String[] list2 = filePath.list(new DirFilter1(text));
        list2 = list2 == null ? new String[0] : list2;

        System.out.println("包含文件夹:");
        for (String name : list1) {
            System.out.println(name);
        }

        System.out.println("\n包含指定[" + text +"]文件夹:");
        for (String name : list2) {
            System.out.println(name);
        }
    }
}
