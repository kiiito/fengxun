package com.hc.dataSource;

import java.util.Properties;

public class MyData2 {
    private Properties properties;

    public MyData2() {
    }

    public MyData2(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyData1{" +
                "properties=" + properties +
                '}';
    }

}
