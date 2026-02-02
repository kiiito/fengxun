package com.hc.mySpringFramework.core;

import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext{
    /**
     * 创建map集合来存储bean对象
     */
    private Map<String,Object> singletonObjects = new HashMap<>();

    /**
     * 解析mySpring的配置文件 初始化所有bean对象
     * @param configLocation spring配置文件的路径 应当放在类路径下
     */

    public ClassPathXmlApplicationContext(String configLocation) {
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
