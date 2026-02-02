package com.hc.aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspectj {
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前置执行");
        joinPoint.proceed();
        System.out.println("环绕后置执行");
    }
}
