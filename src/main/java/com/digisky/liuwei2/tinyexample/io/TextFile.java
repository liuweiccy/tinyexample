package com.digisky.liuwei2.tinyexample.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author liuwei2
 */
public class TextFile extends ArrayList<String> {

    public static String read(String filename) {
        StringBuilder sb = new StringBuilder();

        BufferedReader bufferedReader = null;
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            bufferedReader = new BufferedReader(fileReader);

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static void write(String filename, String text) {
        try (PrintWriter printWriter = new PrintWriter(new File(filename).getAbsoluteFile())) {
            printWriter.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TextFile(String filename, String splitter) {
        super(Arrays.asList(read(filename).split(splitter)));
        if ("".equals(get(0))) {remove(0);}
    }

    public TextFile(String filename) {
        this(filename, "\n");
    }

    public void write(String filename) {
        try {
            try (PrintWriter printWriter = new PrintWriter(new File(filename).getAbsoluteFile())) {
                for (String item : this) {
                    printWriter.println(item);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String text1 = "TextFile1.out";
        String text2 = "TextFile2.out";
        String file = read("c:/unintall.log");
        write(text1, file);
        TextFile textFile = new TextFile(text1);
        textFile.write(text2);

        TreeSet<String> word = new TreeSet<>(new TextFile(text1, "\\W+"));
        System.out.println(word.headSet("a"));
    }
}
