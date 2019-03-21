package com.digisky.liuwei2.tinyexample.aspectj.concert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuwei2
 */
@Slf4j
@Service
public class EncoreableImpl implements Encoreable {
    @Override public void performEncore() {
        log.info("再来表演一次");
    }
}
