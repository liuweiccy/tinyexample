package com.digisky.liuwei2.tinyexample.serialize;

import java.io.*;

/**
 * @author liuwei2
 */
public class FreezeAlien {
    public static void main(String[] args) throws IOException {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("X.file"));
        Alien alien = new Alien();
        out.writeObject(alien);
    }
}
