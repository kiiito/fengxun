package com.hucong.reflection;

import java.lang.reflect.Field;

public class ReflectionAccessProperty {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //得到student类对应的class对象
        Class<?> stuClass = Class.forName("com.hucong.reflection.Student");
        //创建对象
        Object o = stuClass.newInstance();// o的运行类型是student
        System.out.println(o.getClass());
        //使用反射获取age 属性对象
        Field age = stuClass.getField("age");
        age.set(o,30);//通过反射来操作属性
        System.out.println(age.get(o));
        //使用反射获取name属性对象
        Field name = stuClass.getDeclaredField("name");
        //反射爆破 获取private属性
        name.setAccessible(true);
        //name.set(o,"co");
        name.set(null,"co");//因为name是static属性 因此 o 也可以写出null
        System.out.println(name.get(o));//获取属性值
        System.out.println(name.get(null));//获取属性值 要求name是static
    }
}
class Student{
    public int age;
    private static String name;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}
