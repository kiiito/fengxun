package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @ConditionalOnClass(DispatcherServlet.class) 注解的作用：
// 1. 条件化配置：只有当classpath中存在 DispatcherServlet 类时，才会加载当前配置类
// 2. 防止错误加载：避免在没有Spring MVC环境的项目中错误地加载MVC相关配置
// 3. 自动装配控制：配合Spring Boot的自动配置机制，实现按需加载配置
// 4. 这里主要是防止网关模块 错误地加载MVC相关配置
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 默认所有请求都拦截
        //.addPathPatterns() 可通过这个自定义拦截路径
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
