package com.hc.springbootannotation.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
//纳入ioc容器管理
@Component
//@EnableAutoConfiguration //表示开启自动配置 主要是用于扫描@Configuration注解的类 并将其纳入ioc容器管理
//将配置文件的属性值一次性注入到对应的bean中
@ConfigurationProperties(prefix = "spring.datasource")
public class AppConfig {
    //对应配置文件中的属性
    private String username;
    private String password;

    /**
     * 底层在在实现给属性赋值时 会调用set方法
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
