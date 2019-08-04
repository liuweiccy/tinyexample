package com.digisky.liuwei2.tinyexample.aspectj.concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuwei2
 */
@Slf4j
@Aspect
public class EncoreableIntroducer {

    @DeclareParents(value = "com.digisky.liuwei2.tinyexample.aspectj.concert.Performance+", defaultImpl = EncoreableImpl.class)
    public static Encoreable encoreable;
}
