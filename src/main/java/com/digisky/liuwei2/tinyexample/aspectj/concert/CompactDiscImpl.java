package com.digisky.liuwei2.tinyexample.aspectj.concert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuwei2
 */
@Service
@Slf4j
public class CompactDiscImpl implements CompactDisc {
    @Override
    public void playTrack(int number) {
        log.info("播放歌曲No.{}", number);
    }
}
