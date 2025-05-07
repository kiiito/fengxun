package com.hucong.reflection;

import java.lang.reflect.Field;

public class Class02 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String classAllPath = "com.hucong.reflection.Car";
        // 1 获取到Car类对象 对应的class对象
        //<?> 表示不确定的Java类型
        Class<?> cls = Class.forName(classAllPath);
        // 2 输出cls类
        System.out.println(cls);// 显示cls对象 是哪一个类的class对象 com.hucong.reflection.Car
        System.out.println(cls.getClass());// 输出cls的运行类型 java.lang.Class
        // 3 得到包名
        System.out.println(cls.getPackage().getName());//包名
        // 4 得到全类名
        System.out.println(cls.getName());
        // 5 通过cls创建实例对象
        Car car = (Car) cls.newInstance();
        System.out.println(car);//car.toString
        // 6 通过反射获取属性
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));//宝马
        // 7 通过反射给属性赋值
        brand.set(car,"奔驰");
        System.out.println(brand.get(car));//奔驰
        // 8 得所有属性(字段)
        Field[] fields = cls.getFields();
        for (Field f :fields) {
            System.out.println(f.getName());
        }
    }
}
