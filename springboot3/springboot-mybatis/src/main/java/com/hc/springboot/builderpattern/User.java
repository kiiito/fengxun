package com.hc.springboot.builderpattern;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 创建建造器模式可以用注解的方式来完成
 * 使用@Slf4j注解可以在类中直接使用log
 */
@Builder
@Data
@Slf4j
public class User {
    private String name;
    private int age;

    /**
     * 采用@Singular注解可以将List转换为单个值添加到集合中
     */
    @Singular("addPhone")
    private List<String> phone;

    public void print(){
        log.info("正在使用slf4j打印日志");
    }
}
