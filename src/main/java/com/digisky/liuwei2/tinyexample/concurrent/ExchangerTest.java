package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
public class ExchangerTest{
    public static void main(String[] arg){
        Exchanger<People> ex = new Exchanger<>();

        Runnable taskA = ()->{
            People pA = new People("A",1);
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + ": 进入阻塞");
                // 调用后将会返回一个 将会被清除数据的pA类型一样的实例  exchange 调用后taskA将会被阻塞 直到 taskB调用exchange后才会继续
                pA = ex.exchange(pA);
                System.out.println("任务A："+pA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable taskB = ()->{
            //首先申明一个没有数据的实例待会儿接受数据
            People pB = new People("B", 2);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": 进入阻塞");
                //调用后，pB实例将会获得pA的数据
                pB = ex.exchange(pB);
                System.out.println("任务B："+pB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(taskA,"Thread-A").start();
        new Thread(taskB,"Thread-B").start();
    }
}
