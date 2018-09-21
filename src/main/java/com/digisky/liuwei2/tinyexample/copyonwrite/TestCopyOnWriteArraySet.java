package com.digisky.liuwei2.tinyexample.copyonwrite;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CopyOnWrite(写时复制)的并发安全性
 *
 * @author liuwei2
 */
public class TestCopyOnWriteArraySet {

    public void test() throws InterruptedException {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        CopyOnWriteArrayList<Integer> COWSet = new CopyOnWriteArrayList<>(Arrays.asList(array));

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));
        service.submit(new WriteThread(COWSet));
        service.submit(new ReadThread(COWSet));

        Thread.sleep(1000);
        System.out.println("列表长度： " + COWSet.size());
    }

    public static void main(String[] args) throws InterruptedException {
        new TestCopyOnWriteArraySet().test();
        Thread.sleep(1000);

        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String d1 = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println((d == d1));
        System.out.println((a == "hello" + 2));

    }
}
