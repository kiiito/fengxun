package com.hucong.file.Properties_;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Properties03 {
    public static void main(String[] args) throws IOException {
        //使用Properties来创建配置文件 修改文件内容
        Properties properties = new Properties();
        //创建
        //如果该文件没有key就是创建 有key就是修改
        //Properties 的父类是hashtable 底层就是hashtable 核心方法
        properties.setProperty("charset","utf8");
        properties.setProperty("user","涂家骏");//注意保存时 是中文的unicode码值
        properties.setProperty("pwd","1234");

        //将k-v 存储文件中即可
        //null 值代表着注释 null代表空
        properties.store(new FileOutputStream("src/com/hucong/file/Properties_\\mysql02.properties"),null);
    }
}
