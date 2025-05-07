package com.hucong.reflection;

public class GetClass_ {
    public static void main(String[] args) throws ClassNotFoundException {
         // 1 Class.forName
        String classAllPath = "com.hucong.reflection.Car";
        Class<?> cls1 = Class.forName(classAllPath);
        System.out.println(cls1);

        // 2 类名.class 应用场景 用于参数传递
        Class<Car> cls2 = Car.class;
        System.out.println(cls2);

        // 3 对象.getClass() 应用场景 有对象实例
        Car car = new Car();
        Class<? extends Car> cls3 = car.getClass();
        System.out.println(cls3);

        // 4 通过类加载器(4种)类获取到类的class对象
        //1 先得到 类加载器 car
        ClassLoader classLoader = car.getClass().getClassLoader();
        //2 通过类加载器得到class对象
        Class<?> cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4);

        //cls 1~4 其实是同一个对象
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());

        // 5 基本数据 int char boolean float double byte long short
        Class<Integer> integerClass = int.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass);

        // 6 基本数据类型对应的包装类 可以通过 .TYPE 得到class对象
        Class<Integer> type1 = Integer.TYPE;
        Class<Boolean> type2 = Boolean.TYPE;
        System.out.println(type1);

        // 相等
        System.out.println(integerClass.hashCode());
        System.out.println(type1.hashCode());
    }
}
