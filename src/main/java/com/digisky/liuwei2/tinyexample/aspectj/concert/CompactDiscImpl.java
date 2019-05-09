package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

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
