package com.example.musicvalidation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.musicvalidation.service.*.*(..))")
    public void beforeService(JoinPoint joinPoint) {
        System.out.println(" Bắt đầu gọi: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.musicvalidation.service.*.*(..))")
    public void afterService(JoinPoint joinPoint) {
        System.out.println(" Hoàn tất: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.musicvalidation.service.*.*(..))", throwing = "ex")
    public void onException(JoinPoint joinPoint, Throwable ex) {
        System.out.println(" Lỗi tại: " + joinPoint.getSignature().getName() + " - " + ex.getMessage());
    }
}
