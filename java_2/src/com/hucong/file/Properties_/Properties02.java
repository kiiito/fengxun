package com.hucong.file.Properties_;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties02 {
    public static void main(String[] args) throws IOException {
        //使用Properties类来读取文件
        // 1 创建Properties对象
        Properties properties = new Properties();
        // 2 加载指定的配置文件
        properties.load(new FileReader("src/com/hucong/file/Properties_/myaql.properties"));
        // 3 把k-v显示控制台
        properties.list(System.out);
        // 4 根据key值 获取对应的值
        System.out.println("用户名=" + properties.getProperty("user"));
        System.out.println("密码是=" + properties.getProperty("pwd"));
    }
}
