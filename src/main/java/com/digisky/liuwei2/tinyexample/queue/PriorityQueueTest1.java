package com.digisky.liuwei2.tinyexample.queue;

import java.util.PriorityQueue;

/**
 * @author liuwei2
 */
public class PriorityQueueTest1 {
    private final static PriorityQueue<MyTask> PRIORITY_QUEUE = new PriorityQueue<>();
    private final static Integer CAP = 1000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < CAP; i++) {
            PRIORITY_QUEUE.offer(new MyTask("name" + i, (int)(Math.random() * 10000)));
        }

        ThreadGroup group = new ThreadGroup("生成-");

        Thread thread = new Thread(group, () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(PRIORITY_QUEUE.poll());
            }
        },"sss");
        thread.start();

        thread.join();
    }
}

class MyTask implements Comparable<MyTask> {
    private String name;
    private Integer priority;

    public MyTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(MyTask myTask) {
        return myTask.priority.compareTo(this.priority);
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
