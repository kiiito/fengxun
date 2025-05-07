package com.hucong.reflection;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class Reflection01 {
    public static void main(String[] args) throws Exception {

        //使用Properties类 可以读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/hucong/reflection/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();

        //使用反射机制解决
        // 1 加载类 返回class类型的对象cls 注意这里的类名就叫class
        Class cls = Class.forName(classfullpath);
        // 2 通过cls 得到加载的类com.hucong.reflection.Cat 的对象实例
        Object o = cls.newInstance();
        System.out.println("o的运行类型 = " + o.getClass());
        // 3 通过 cls 得到加载的类com.hucong.reflection.Cat 的methodName"hi"
        //即 在反射中 可以把方法视为对象(万物皆对象)
        Method method = cls.getMethod(methodName);
        // 4 通过method 调用方法 即通过方法对象来实现调用方法
        System.out.println("==================================");
        method.invoke(o);//传统方法 对象.方法() 反射机制 方法.invoke(对象)

       // java.lang.reflect.Constructor 代表类的构造方法 Constructor对象表示构造器
        Constructor constructor = cls.getConstructor();//这里没有传参 指的是无参构造器
        System.out.println(constructor);

        Constructor constructor1 = cls.getConstructor(String.class);//这里传入的String.class 就是String类的class对象
        System.out.println(constructor1);

       // java.lang.reflect.Field 代表类的成员变量 Field 对象表示某一个成员变量
        //getField 不能得到私有的属性
        Field age = cls.getField("age");
        System.out.println(age.get(o));//传统写法 对象.成员变量() 反射 成员变量对象.get(对象)
    }
}
