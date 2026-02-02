package com.hc.user.config;



import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.io.File;

@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {
    private String avatarPath;
    private String avatarAccessUrl;
    private String allowedTypes;

    /**
     * 初始化方法，处理相对路径
     */
    @PostConstruct
    public void init() {
        // 如果是相对路径，转换为绝对路径
        if (avatarPath.startsWith("./")) {
            String projectRoot = System.getProperty("user.dir");
            avatarPath = projectRoot + File.separator + avatarPath.substring(2);
        }

        // 创建目录
        File dir = new File(avatarPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("创建头像存储目录: " + avatarPath);
            }
        }

        // 确保访问URL格式正确
        if (!avatarAccessUrl.startsWith("/")) {
            avatarAccessUrl = "/" + avatarAccessUrl;
        }
        if (!avatarAccessUrl.endsWith("/")) {
            avatarAccessUrl = avatarAccessUrl + "/";
        }
    }
}
