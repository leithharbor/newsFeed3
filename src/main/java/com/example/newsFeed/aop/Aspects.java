package com.example.newsFeed.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class Aspects {
    // 포인트 컷 (어드바이스를 적용할 범위)

    // 패키지 기반 포인트 컷
    @Pointcut("execution(* com.example.newsFeed.user.service..*(..))")
//    @Pointcut("execution(* com.example.newsFeed.user.service. int method A(..))")
//    표현식 구글에 검색하면 다 나옴.
    private void newsFeedServiceLayerPointCut() {}

    // 어노테이션 기반 포인트 컷
    @Pointcut("@annotation(com.example.newsFeed.aop.TrackTime)")
    public void trackTimePointCut () {}
    /**
     * @Around
     *     // 패키지 기반 포인트 컷
     *     @Around("newsFeedServiceLayerPointCut()")
     *     // 어노테이션 기반 포인트 컷
     *     @Around("trackTimePointCut()")
     */
    @Around("newsFeedServiceLayerPointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("::: BEFORE");
        long startTime = System.currentTimeMillis();
        log.info("::: startTime: {}ms", startTime);
        try {
            Object result = joinPoint.proceed();
//            log.info("::: AFTER THROWING");
//            log.info("result: {}", result.getClass());
            return result;
        } catch (Throwable e) {
//            log.info(" AFTER THROWING");
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("::: endTime: {}ms",endTime);
            long executionTime = endTime - startTime;
            log.info("::: executionTime: {}ms", executionTime);
        }
    }

    @Around("trackTimePointCut()")
    public Object feedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("::: BEFORE");
        long startTime = System.currentTimeMillis();
        log.info("::: startTime: {}ms", startTime);
        try {
            Object result = joinPoint.proceed();
//            log.info("::: AFTER THROWING");
//            log.info("result: {}", result.getClass());
            return result;
        } catch (Throwable e) {
//            log.info(" AFTER THROWING");
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("::: endTime: {}ms",endTime);
            long executionTime = endTime - startTime;
            log.info("::: executionTime: {}ms", executionTime);
        }
    }



}
