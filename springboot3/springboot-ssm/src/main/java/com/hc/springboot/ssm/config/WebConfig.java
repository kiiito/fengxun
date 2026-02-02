package com.hc.springboot.ssm.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    /**
     * 配置过滤器
     */
    @Bean
  public WebMvcConfigurer bbb() {
      return new WebMvcConfigurer() {
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
              registry.addInterceptor(new HandlerInterceptor() {
                  @Override
                  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                      System.out.println("拦截器的preHandle方法");
                      return true;
                  }

                  @Override
                  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                      System.out.println("拦截器的postHandle方法");
                  }

                  @Override
                  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                      System.out.println("拦截器的afterCompletion方法");
                  }
              });
          }
      };
  }
}
