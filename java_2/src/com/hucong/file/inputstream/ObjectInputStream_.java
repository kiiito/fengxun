package com.hucong.file.inputstream;
import com.hucong.file.outputstream.Dog;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class ObjectInputStream_ {
    public static void main(String[] args) throws Exception {
        //指定反序列化的文件
        String filePath = "D:\\data.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        //读取(反序列化)的顺序需要和你保存的数据(序列化)的顺序保持一致 否则会出现异常
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        //dog 的编译类型是object 运行类型是dog
        Object dog = ois.readObject();
        System.out.println(dog);

        //如果我们需要调用Dog的方法 需要向下转型
        //需要我们将Dog类的定义 拷贝到 可以引用的位置
        Dog dog2 = (Dog)dog;
        System.out.println(dog2.getName());
        ois.close();
    }
}

