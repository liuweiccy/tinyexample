package com.digisky.liuwei2.tinyexample.io;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author liuwei2
 */
public class Directory {

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "TreeInfo{" +
                    "files=" + files +
                    ", dirs=" + dirs +
                    '}';
        }

        static TreeInfo recurseDirs(File startDir, String regex) {
            TreeInfo result = new TreeInfo();
            for (File item : Objects.requireNonNull(startDir.listFiles())) {
                if (item.isDirectory()) {
                    result.dirs.add(item);
                    result.addAll(recurseDirs(startDir, regex));
                } else {
                    if (item.getName().matches(regex)) {
                        result.files.add(item);
                    }
                }
            }
            return result;
        }

        public static TreeInfo walk(String start, String regex) {
            return recurseDirs(new File(start), regex);
        }

        public static TreeInfo walk(File file, String regex) {
            return recurseDirs(file, regex);
        }

        public static TreeInfo walk(String start) {
            return recurseDirs(new File(start), ".*");
        }

        public static TreeInfo walk(File start) {
            return recurseDirs(start, ".*");
        }

        public static void main(String[] args) {
            System.out.println(walk("D:\\git\\tinyexample\\src\\main\\java\\com\\digisky\\liuwei2\\tinyexample\\io"));
        }

    }
}