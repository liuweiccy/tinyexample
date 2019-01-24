package com.digisky.liuwei2.tinyexample.serialize;

import java.io.*;
import java.util.Random;

import static com.digisky.liuwei2.tinyexample.util.Util.*;

/**
 * @author liuwei2
 */
class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };

    private Worm next;
    private char c;

    public Worm(int i, char c) {
        System.out.println("Worm constructor: " + i);
        this.c = c;
        if (--i > 0) {
            next = new Worm(i, (char) (c + 1));
        }
    }

    public Worm() {
        System.out.println("Default constructor");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data data : d) {
            result.append(data);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6, 'a');
        print("w1 = " + w);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) ois.readObject();
        Worm w2 = (Worm) ois.readObject();
        print(s + "w2 = " + w2);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();

        print(bout.toByteArray());
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s = (String) in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        print(s + "w3 = " + w3);
    }
















}
