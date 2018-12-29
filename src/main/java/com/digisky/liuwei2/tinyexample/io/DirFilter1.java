package com.digisky.liuwei2.tinyexample.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author liuwei2
 */
public class DirFilter1 implements FilenameFilter {
    private String text;

    public DirFilter1(String text) {
        this.text = text;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.contains(text);
    }
}
