package com.digisky.liuwei2.tinyexample.thread.consumerproducuer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei2
 * 2020/4/1 17:09
 */
public class TestConsumerProducer {
    public static void main(String[] args) {
        List<String> depot = new ArrayList<>();
        Consumer consumer = new Consumer(depot);
        Producer producer = new Producer(depot);

        consumer.setName("消费者");
        producer.setName("生产者");

        consumer.start();
        producer.start();
    }
}
