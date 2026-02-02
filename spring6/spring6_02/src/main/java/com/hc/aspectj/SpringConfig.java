package com.hc.aspectj;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //代替spring.xml
@ComponentScan({"com.hc.aspectj"})//扫描组件
@EnableAspectJAutoProxy(proxyTargetClass = true)//启用aspectj的自动代理机制
public class SpringConfig {
}
