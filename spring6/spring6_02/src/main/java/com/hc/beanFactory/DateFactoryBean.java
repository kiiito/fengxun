package com.hc.beanFactory;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过DateFactoryBean这个工厂bean对student这个bean的属性进行加工
 */
public class DateFactoryBean implements FactoryBean<Date> {

    private String date;

    public DateFactoryBean(String date) {
        this.date = date;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(date);
        return date1;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
