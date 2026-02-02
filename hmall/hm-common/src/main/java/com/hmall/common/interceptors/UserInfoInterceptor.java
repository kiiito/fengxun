package com.hmall.common.interceptors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户信息拦截器 需要在mvc配置中添加拦截器配置中配置
 */
public class UserInfoInterceptor implements HandlerInterceptor {
    /**
     * 请求处理之前 preHandle在请求处理之前执行，返回值为true表示继续处理，返回值为false表示取消处理
     * 1 获取登录用户信息
     * 2 判断是否获取到了用户 如果有 存入到 ThreadLocal
     * 3 放行
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return  boolean是否放行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1 获取登录用户信息
        String header = request.getHeader("userInfo");
        // 2 判断是否获取到了用户 如果有 存入到 ThreadLocal
        if (StringUtils.isNotBlank( header)){
            UserContext.setUser(Long.valueOf(header));
        }
        // 3 放行
        return true;
    }

    /**
     * 请求处理完成之后 afterCompletion在请求处理完成后，视图渲染之前执行，
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户
        UserContext.removeUser();
    }
}
