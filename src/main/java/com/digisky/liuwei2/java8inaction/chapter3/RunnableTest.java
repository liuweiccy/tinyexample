package com.digisky.liuwei2.java8inaction.chapter3;

/**
 * 测试行为参数化的3种表达方式，类，匿名类，lambda
 *
 * @author liuwei2
 * @date 2019/10/09 15:10
 */
public class RunnableTest {
    private static Runnable r1 = () -> System.out.println("Hello World1");

    private static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello World2");
        }
    };

    static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        process(r1);

        process(r2);

        process(() -> System.out.println("Hello World3"));
    }
}
