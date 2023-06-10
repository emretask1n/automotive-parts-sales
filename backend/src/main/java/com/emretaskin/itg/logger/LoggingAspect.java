package com.emretaskin.itg.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.emretaskin.itg.service.impl.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.debug("Method {} is called with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("execution(* com.emretaskin.itg.service.impl.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        LOGGER.debug("Method {} is finished.", joinPoint.getSignature().getName());
    }

    @Around("execution(* com.emretaskin.itg.service.impl.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        LOGGER.debug("Method {} is executed in {} ms.", joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
        return result;
    }

    @AfterReturning(pointcut = "execution(* com.emretaskin.itg.service.impl.*.*(..))",
            returning = "result")
    public void logMethodReturnValue(JoinPoint joinPoint, Object result) {
        LOGGER.debug("Method {} returns value {}", joinPoint.getSignature().getName(), result);
    }
}
