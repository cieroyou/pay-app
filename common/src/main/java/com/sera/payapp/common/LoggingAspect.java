package com.sera.payapp.common;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProducer loggingProducer;

    @Before("execution(* com.sera.payapp.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(@NotNull JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
//        log.info("Before executing method: {}", methodName);
    }
}
