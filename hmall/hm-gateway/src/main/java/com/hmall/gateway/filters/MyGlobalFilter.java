package com.hmall.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

public class MyGlobalFilter implements GlobalFilter, Order {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求体
        ServerHttpRequest request = exchange.getRequest();
        //获取请求头
        HttpHeaders headers = request.getHeaders();
        System.out.println("headers = " + headers);
        //放行
        return chain.filter(exchange);
    }

    /**
     * 设置优先级
     * @return 优先级0
     */
    @Override
    public int value() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
