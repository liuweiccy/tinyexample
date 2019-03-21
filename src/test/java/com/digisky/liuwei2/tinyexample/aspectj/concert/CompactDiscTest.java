package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.junit.Assert;
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
public class CompactDiscTest {
    @Autowired
    private CompactDisc blankDisc;
    @Autowired
    private TrackCounter trackCounter;

    @Test
    public void playTrackTest() {
        blankDisc.playTrack(5);
        blankDisc.playTrack(5);
        blankDisc.playTrack(3);
        blankDisc.playTrack(5);
        blankDisc.playTrack(3);
        blankDisc.playTrack(5);

        Assert.assertEquals(4, trackCounter.getPlayCount(5));
        Assert.assertEquals(2, trackCounter.getPlayCount(3));
    }

}