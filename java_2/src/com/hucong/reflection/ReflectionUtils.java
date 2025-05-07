package com.hucong.reflection;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void main(String[] args) throws ClassNotFoundException {

    }
    @Test
    //第一组方法API
    public void api_01() throws ClassNotFoundException {
        //得到class对象
        Class<?> personClass = Class.forName("com.hucong.reflection.Person");
        //getName()获取到全类名
        System.out.println(personClass.getName());
        //getSimpleName 获取简单类名
        System.out.println(personClass.getSimpleName());
        //getFields 获取所有public修饰的属性 包含本类以及父类
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println("本类以及父类的public属性" + field.getName());
        }
        //getDeclaredFields 获取本类的所有属性 包括私有
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println( "获取本类中的所有属性" + declaredField.getName());
        }
        //getMethods 获取所有public修饰的方法 包含本类以及父类
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println("获取本类及父类的public方法" +  method.getName());
        }
        //getDeclaredMethods 获取本类的所有的方法
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("获取本类的所有的方法" + declaredMethod.getName());
        }
        //getConstructors 获取所有public修饰的构造器 包含本类
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("获取所有public修饰的构造器 包含本类" + constructor.getName());
        }
        //getDeclaredConstructors 获取本类所有构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("获取本类所有构造器" + declaredConstructor.getName());
        }
        //getPackage 以Package形式返回 包信息
        System.out.println(personClass.getPackage());
        //getSuperclass 以class形式返回父类信息
        Class<?> superclass = personClass.getSuperclass();
        System.out.println("父类的class对象 =" + superclass);
        //getInterfaces 以Class[]形式返回接口信息
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("接口信息 =" + anInterface);
        }
        //getAnnotations 以Annotations[]形式返回注解信息
        Annotation[] annotations = personClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("注解信息 = " + annotation);
        }
    }
    @Test
    public void api_02() throws ClassNotFoundException {
        //得到class对象
        Class<?> personClass = Class.forName("com.hucong.reflection.Person");
        //getDeclaredFields 获取本类的所有属性 包括私有
        //规定 说明 默认修饰符是0 public是1 private是2 protected是4 static是8 final是16 数值是可以叠加的
        //例如 public + static = 9
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println( "获取本类中的所有属性" + declaredField.getName() + "该属性的修饰符值 = " +
                    declaredField.getModifiers() + "该属性的类型是 = " + declaredField.getType());
        }

        //getDeclaredMethods 获取本类的所有的方法
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("获取本类的所有的方法" + declaredMethod.getName() +
                    "该方法的访问修饰符值 = " + declaredMethod.getModifiers()
            + "该方法返回的类型 = " + declaredMethod.getReturnType());

            //输出当前这个方法的形参数组的情况
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该方法的形参类型 = " + parameterType);
            }
        }
        //getDeclaredConstructors 获取本类所有构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("获取本类所有构造器" + declaredConstructor.getName());

            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该构造器的形参类型是 = " + parameterType);
            }
        }
    }

}
class A{
    public String hobby;
    public void hi(){}
    public A(){}
}
interface IA{}
interface IB{}
@Deprecated
class Person extends A implements IA,IB {
    public String name;
    protected static int age;
    String job;
    private double sal;
    public Person(){}
    private Person(String name, int age){}
    public Person(String name){}

    public String m1(String n1,int n2,boolean n3){
        return null;
    }
    protected void m2(){}
    void m3(){}
    private void m4(){}
}
