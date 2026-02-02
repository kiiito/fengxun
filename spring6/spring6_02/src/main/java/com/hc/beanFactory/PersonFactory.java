package com.hc.beanFactory;

import org.springframework.beans.factory.FactoryBean;


public class PersonFactory implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    /**
     * 这里是默认方法 默认返回true 表示单例的
     * 要想修改多例 返回false就行
     * @return
     */
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
