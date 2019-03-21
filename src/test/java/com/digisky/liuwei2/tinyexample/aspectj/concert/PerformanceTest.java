package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuwei2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PerformanceTest {

    @Autowired
    private Performance performance;

    @Test
    public void performanceTest() {
        performance.perform();
    }

    @Test
    public void encoreableTest() {
        Encoreable encoreable = (Encoreable)performance;
        performance.perform();
        encoreable.performEncore();
    }
}