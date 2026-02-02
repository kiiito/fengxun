package com.hc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 标注配置类
@Configuration
//组件扫描
@ComponentScan({"com.hc.service"})
//引入其他的配置文件
@PropertySource("classpath:jdbc.properties")
//导入其他的配置到spring配置当中
@Import({MyBatisConfig.class,DataSourceConfig.class})
//开启事务管理机制
@EnableTransactionManagement
public class SpringConfig {
}
