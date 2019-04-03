package com.digisky.liuwei2.tinyexample.nio;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liuwei2
 */
public class MappedIO {
    private static long numOfInts = 4000000000L;
    private static int numOfUbuffInts = 200000;

    private abstract static class Tester {
        private String name;
        public Tester(String name) {
            this.name = name;
        }

        public void runTest() throws IOException {
            System.out.print(name + ": ");
            long start = System.nanoTime();
            test();
            double duration = System.nanoTime() - start;
            System.out.format("%.2f\n", duration / 1.0e9);
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {
      new Tester("Stream Write") {
          @Override
          public void test() throws IOException {
              DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
              for (int i = 0; i < numOfInts; i++) {
                  dos.writeInt(i);
              }
              dos.close();
          }
      },
      new Tester("Mapped Write") {
          @Override
          public void test() throws IOException {
              FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
              IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
              for (int i = 0; i < numOfInts; i++) {
                  ib.put(i);
              }
              fc.close();
          }
      }
    };

    public static void main(String[] args) throws IOException {
        for (Tester tester : tests) {
            tester.runTest();
        }
    }
}
