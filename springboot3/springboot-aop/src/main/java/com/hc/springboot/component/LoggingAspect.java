package com.hc.springboot.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    /**
     * 定义日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * 定义切入点 匹配所有service层的方法
     */
    @Pointcut("execution(* com.hc.springboot.service..*(..))")
    public void serviceMethod(){

    }

    /**
     * 在方法执行前执行
     *前置通知
     * @param joinPoint
     */
    @Before("serviceMethod()")

    public void logBefore(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("method:{} args:{}",name,args);
    }
}
