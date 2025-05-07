package com.hucong.collection_.table_;

import java.util.Properties;

public class Properties_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
         * Properties 继承 Hashtable
         * 所以 key 和 value 都不能为空
         */
        Properties properties = new Properties();
        properties.put("ganYu",100);
        //properties.put(null,100);//异常
        //properties.put("hu",null);//异常
        properties.put("eula",80);
        properties.put("eula",100);//替换

        System.out.println(properties);

        //通过k获取对应值
        System.out.println(properties.get("eula"));
        //删除
        properties.remove("eula");
        System.out.println(properties);
        //修改
        properties.put("ganYu",90);//利用替换
    }
}
