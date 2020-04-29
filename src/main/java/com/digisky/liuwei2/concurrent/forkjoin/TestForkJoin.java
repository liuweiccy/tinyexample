package com.digisky.liuwei2.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author liuwei2
 * 2020/4/29 18:47
 */
public class TestForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(6);
        Fibonacci fibonacci = new Fibonacci(50);
        Integer res = forkJoinPool.invoke(fibonacci);
        System.out.println(res);
    }


    static class Fibonacci extends RecursiveTask<Integer> {
        private final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }
}
