package com.digisky.liuwei2.tinyexample.concurrent;

import java.util.concurrent.Exchanger;

/**
 * @author liuwei2
 */
public class ExchangerTest{
    public static void main(String[] arg){

        Exchanger<People> ex = new Exchanger<>();
        Runnable taskA = ()->{
            People pA = new People("A",1);
            try {
                Thread.sleep(2000);
                // ex.exchange(pA);
                // 调用后将会返回一个 将会被清除数据的pA类型一样的实例  exchange 调用后taskA将会被阻塞 直到 taskB调用exchange后才会继续
                pA = ex.exchange(pA);
                Thread.sleep(2000);
                System.out.println("任务A："+pA.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable taskB = ()->{
            //首先申明一个没有数据的实例待会儿接受数据
            People pB = new People();
            try {
                Thread.sleep(5000);
                //调用后，pB实例将会获得pA的数据
                pB = ex.exchange(pB);
                Thread.sleep(2000);
                System.out.println("任务B："+pB.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(taskA,"Thread-A").start();
        new Thread(taskB,"Thread-B").start();
    }
}
