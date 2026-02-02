package com.hc.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hc.springboot.bean.User;

public class TestYAMLApi {
    public static void main(String[] args) throws JsonProcessingException {
        //创建YAMLFactory对象
        YAMLFactory yamlFactory = new YAMLFactory();

        //创建 对象映射器对象
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);

        //准备一个Java对象
        User user = new User("jackson", 18);

        //将Java对象转换成YAML格式的字符串
         String yaml = objectMapper.writeValueAsString(user);
         System.out.println(yaml);
    }
}
