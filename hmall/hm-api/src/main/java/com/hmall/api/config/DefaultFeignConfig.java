package com.hmall.api.config;

import com.hmall.api.client.fallback.ItemClientFallbackFactory;
import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    /**
     * Feign日志级别1.feign.Logger.Level.FULL
     * @return Logger.Level
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Feign请求拦截器，将用户ID添加到请求头中
     * 这段代码定义了一个 RequestInterceptor（请求拦截器）Bean，用于在Feign客户端发送请求前自动拦截并处理请求
     * 主要功能是从 UserContext 中获取当前登录用户的ID，并将其作为请求头添加到Feign请求中
     * 通过这种方式，可以在微服务间调用时传递用户身份信息，确保下游服务能够识别当前操作的用户
     * 只有当用户ID存在时才会添加请求头，避免传递空值
     * @return
     */
    @Bean
    public RequestInterceptor userInfoRequestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Long userId = UserContext.getUser();
                if (userId != null) {
                    // 在Feign请求模板中添加名为"userInfo"的请求头，值为用户ID
                    requestTemplate.header("userInfo", userId.toString());
                }
            }
        };
    }

    /**
     * 服务熔断降级处理（给默认处理或者抛异常给前端）
     * Feign客户端降级处理
     * 创建一个 ItemClientFallbackFactory Bean，用于处理 ItemClient
     * 在调用 ItemClient 时发生的异常，并返回一个默认的 ItemClient 实现
     * 这样，即使 ItemClient 调用失败，也能保证服务的正常运行
     * 同时，也可以在 ItemClientFallbackFactory 中实现一些特殊的处理逻辑的降级处理逻辑
     * @return ItemClientFallbackFactory
     */
    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory(){
        return new ItemClientFallbackFactory();
    }
}
