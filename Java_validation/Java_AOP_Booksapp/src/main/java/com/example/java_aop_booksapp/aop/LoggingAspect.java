package com.example.java_aop_booksapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private int visitorCount = 0;

    @After("execution(* com.example.java_aop_booksapp.service.BorrowService.borrowBook(..)) || execution(* com.example.java_aop_booksapp.service.BorrowService.returnBook(..))")
    public void logBookChange(JoinPoint joinPoint) {
        System.out.println("[LOG] Action: " + joinPoint.getSignature().getName() + " - " + LocalDateTime.now());
    }

    @Before("execution(* com.example.java_aop_booksapp.controller..*(..))")
    public void countVisitors(JoinPoint joinPoint) {
        visitorCount++;
        System.out.println("[VISITOR LOG] Visitor #" + visitorCount + " accessed " + joinPoint.getSignature().getName());
    }
}
