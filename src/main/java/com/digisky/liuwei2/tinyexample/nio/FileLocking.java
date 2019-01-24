package com.digisky.liuwei2.tinyexample.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
public class FileLocking {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("rtest.dat");
        FileLock fl = fos.getChannel().lock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("Release Lock");
            fl.release();
        }
        fos.close();
    }
}
