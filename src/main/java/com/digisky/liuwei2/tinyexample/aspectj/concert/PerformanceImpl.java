package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
@Service
public class PerformanceImpl implements Performance {
    @Override public void perform() {
        log.info("表演.....");
    }
}