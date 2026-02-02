package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 这是一个登陆拦截器
 * 1. 拦截所有请求
 * 2. 获取session中的用户
 * 3. 判断用户是否存在
 * 4. 不存在 拦截 返回401 状态码
 * 5. 存在 保存用户信息到TreadLocal
 * 6. 放行
 */
@Configuration
public class RefreshTokenInterceptor implements HandlerInterceptor {
    /**
     * 这里并不是springboot管理的 不能用注解注入 只能通过构造器的方式注入
     */
    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1 获取请求头的中的token
         * 2 根据token获取redis中的用户
         * 3 判断用户是否存在
         * 4 不存在 拦截 返回401 状态码
         * 5 将查询到的hash数据转化成UserDTO对象
         * 6 保存用户信息到TreadLocal
         * 7 刷新token的有效时间
         * 8 放行
         */
        // 1 获取session
//        HttpSession session = request.getSession();
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }
        // 2 获取session中的用户
//        Object user = session.getAttribute("user");
        String key = RedisConstants.LOGIN_USER_KEY + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);

        // 3判断用户是否存在
        if (userMap.isEmpty()){
            return true;
        }
        //将查询到的hash数据转化成UserDTO对象
        /**
         * 这里使用hutool的BeanUtil工具类 将hash数据转化成UserDTO对象
         */
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        // 5 存在 保存用户信息到TreadLocal
        UserHolder.saveUser(userDTO);
        //刷新token的有效时间
        stringRedisTemplate.expire(key,RedisConstants.LOGIN_USER_TTL, TimeUnit.SECONDS);
        // 6 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
