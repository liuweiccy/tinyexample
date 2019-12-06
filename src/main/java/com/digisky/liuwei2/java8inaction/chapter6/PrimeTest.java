package com.digisky.liuwei2.java8inaction.chapter6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * 使用lambda求质数
 * @author liuwei2
 */
public class PrimeTest {
    private boolean isPrime(int candidate) {
        int candidateRoot = (int)Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(this::isPrime));
    }

    public static void main(String[] args) {
        PrimeTest primeTest = new PrimeTest();
        print(primeTest.partitionPrimes(10));
    }
}
