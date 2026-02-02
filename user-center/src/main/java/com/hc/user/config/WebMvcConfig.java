package com.hc.user.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private  UploadConfig uploadConfig;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
                /* 放自己的前端域名*/
                .allowedOrigins("http://localhost:5173", "http://127.0.0.1:5173", "http://127.0.0.1:8082", "http://127.0.0.1:8083")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取绝对路径
        String absolutePath = new File(uploadConfig.getAvatarPath()).getAbsolutePath();

        // 确保路径以斜杠结尾
        if (!absolutePath.endsWith(File.separator)) {
            absolutePath += File.separator;
        }

        // 映射静态资源
        registry.addResourceHandler("/uploads/avatars/**")
                .addResourceLocations("file:" + absolutePath);

        System.out.println("静态资源映射配置：");
        System.out.println("访问路径：/uploads/avatars/**");
        System.out.println("实际路径：file:" + absolutePath);
    }
}

