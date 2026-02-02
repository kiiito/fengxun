package com.hc.testNoXml;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 全注解式开发
 * 将一个类当做xml配置文件
 */

@Configuration
@ComponentScan("com.hc.testNoXml")
public class SpringConfig {
}
