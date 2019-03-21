package com.digisky.liuwei2.tinyexample.aspectj.concert;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuwei2
 */
@Slf4j
@Aspect
public class TrackCounter {
    private Map<Integer, Integer> trackCounts = new HashMap<>(16);

    @Pointcut("execution(* com.digisky.liuwei2.tinyexample.aspectj.concert.CompactDisc.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {}

    @Before(value = "trackPlayed(trackNum)", argNames = "trackNum")
    public void countTrack(int trackNum) {
        int currentCount = getPlayCount(trackNum);
        log.info("No.[{}]已经播放了{}次, 即将开始{}次播放", trackNum, currentCount, currentCount + 1);
        trackCounts.put(trackNum, currentCount + 1);
    }

    public int getPlayCount(int trackNum) {
        return trackCounts.getOrDefault(trackNum, 0);
    }
}
