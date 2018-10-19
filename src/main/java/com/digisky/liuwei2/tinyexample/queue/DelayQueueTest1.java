package com.digisky.liuwei2.tinyexample.queue;

import com.digisky.liuwei2.tinyexample.util.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
public class DelayQueueTest1 {
    private final static DelayQueue<Exam> DELAY_QUEUE = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Exam exam1 = new Exam("语文1", LocalDateTime.now(), 180, 40, 4);
        Exam exam2 = new Exam("语文2", LocalDateTime.now(), 180, 30, 1);
        Exam exam3 = new Exam("语文3", LocalDateTime.now(), 180, 10, 3);
        Exam exam4 = new Exam("语文4", LocalDateTime.now(), 180, 20, 1);

        DELAY_QUEUE.put(exam1);
        DELAY_QUEUE.put(exam4);
        DELAY_QUEUE.put(exam3);
        DELAY_QUEUE.put(exam2);

        Thread thread = new Thread(() -> {
            for (;;) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Exam exam = DELAY_QUEUE.take();
                    Util.print(exam);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    private static class Exam implements Delayed {
        private String name;
        private LocalDateTime startDateTime;
        /** 考试时长(分钟) */
        private int duration;
        /** 最早交卷时间延迟(分钟) */
        private int delay;
        /** 考试优先级 */
        private int prority;

        public Exam(String name, LocalDateTime startDateTime, int duration, int delay, int prority) {
            this.name = name;
            this.startDateTime = startDateTime;
            this.duration = duration;
            this.delay = delay;
            this.prority = prority;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            LocalDateTime end = startDateTime.plusSeconds(delay);
            Duration duration = Duration.between(LocalDateTime.now(), end);
            return unit.convert(duration.toMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == null) {
                return 1;
            }
            if (o.getClass() != Exam.class) {
                return 1;
            }
            Exam exam = (Exam) o;
            return Integer.compare(this.prority, exam.prority);
        }

        @Override
        public String toString() {
            return "Exam{" +
                    "name='" + name + '\'' +
                    ", startDateTime=" + startDateTime +
                    ", duration=" + duration +
                    ", delay=" + delay +
                    ", prority=" + prority +
                    '}';
        }
    }
}
