package com.hc.test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;
import java.lang.reflect.Method;

public class JavassistTest {

    @Test
    public void testGenerateFirstClass() throws Exception {
        //获取类池 这个类池就是给我生成class
        ClassPool pool = ClassPool.getDefault();
        //制造类 需要将类名告诉javassist
        CtClass ctClass = pool.makeClass("com.hc.dao.impl.AccountDaoImpl");
        //制造方法
        String methodCode = "public void insert(){System.out.println(123);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //将方法添加到类当中
        ctClass.addMethod(ctMethod);
        //在内存当中生成class
        ctClass.toClass();

        //类加载到jvm当中 返回AccountDaoImpl类的字节码
        Class<?> aClass = Class.forName("com.hc.dao.impl.AccountDaoImpl");
        //创建对象
        Object obj = aClass.newInstance();
        //获取AccountDaoImpl中的insert方法
        Method insert = aClass.getDeclaredMethod("insert");
        //调用方法insert
        insert.invoke(obj);

    }

}
