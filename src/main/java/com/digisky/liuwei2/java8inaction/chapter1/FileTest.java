package com.digisky.liuwei2.java8inaction.chapter1;

import java.io.File;
import java.io.FileFilter;

/**
 * File文件的Java8的处理方式
 *
 * @author liuwei2
 * @date 2019/09/23 17:53
 */
public class FileTest {
    public static void main(String[] args) {
        // Java8函数式处理
        File[] files = new File(".").listFiles(File::isHidden);

        // Java非函数式处理
        File[] files1 = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
    }
}
