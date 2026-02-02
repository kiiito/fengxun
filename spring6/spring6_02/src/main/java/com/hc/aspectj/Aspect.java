package com.hc.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("aspect")
@org.aspectj.lang.annotation.Aspect //切面类是需要使用@Aspect注解进行标注
public class Aspect {//切面

    //定义通用的切点表达式
    @Pointcut("execution(* com.hc.aspectj.UserService.*(..))")
    public void currency(){}

    @Before("currency()")
    public void enhance(JoinPoint joinPoint){
        System.out.println("我是前置通知");
        //获取目标的签名joinPoint.getSignature() 在获取一个方法的具体信息
        String name = joinPoint.getSignature().getName();
        System.out.println(name);
    }
    @AfterReturning("currency()")
    public void afterReturningAdvice(){
        System.out.println("我是后置通知");
    }

    /**
     * 环绕通知是最大的通知 在前置通知之前 在后置通知之后
     * @param joinPoint
     * @throws Throwable
     */
    @Around("currency()")
    public void AroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("我是环绕通知前面的代码");
        //执行目标
        joinPoint.proceed();
        System.out.println("我是环绕通知后面的代码");
    }
    @AfterThrowing("currency()")
    public void AfterThrowingAdvice(){
        System.out.println("我是异常通知");
    }
    @After("currency()")
    public void AfterAdvice(){
        System.out.println("我是最终通知");
    }
}
