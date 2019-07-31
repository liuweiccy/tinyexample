package com.digisky.liuwei2.tinyexample.thread;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.annotation.JunitPerfRequire;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author liuwei2
 */
@Slf4j
public class ThreadGroupTest {

    @Test
    public void testEnumerate() throws InterruptedException {
        ThreadGroup myGroup = new ThreadGroup("myGroup");

        Thread thread = new Thread(myGroup, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "myThread");
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recurseSize = mainGroup.enumerate(list);
        log.debug(String.valueOf(recurseSize));

        recurseSize = mainGroup.enumerate(list, false);
        log.debug(String.valueOf(recurseSize));
    }

    @Test
    public void testEnumerateThreadGroup() throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("myGroup1");
        ThreadGroup group2 = new ThreadGroup(group1,"myGroup2");

        TimeUnit.SECONDS.sleep(2);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeGroupCount()];
        int recurseSize = mainGroup.enumerate(list);
        log.debug(String.valueOf(recurseSize));

        recurseSize = mainGroup.enumerate(list, false);
        log.debug(String.valueOf(recurseSize));

        mainGroup.list();
    }

    @Test
    @JunitPerfConfig(warmUp = 1000, duration = 5000, reporter = HtmlReporter.class, threads = 3)
    @JunitPerfRequire(min = 80, max = 200, average = 120, timesPerSecond = 5, percentiles = {"10:100", "50:120", "99:150"})
    public void testHello() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
