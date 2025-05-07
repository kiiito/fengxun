package com.hucong.file.outputstream;

import java.io.*;

public class ObjectOutputStream_ {
    public static void main(String[] args) throws Exception {
        //序列化后 保存的文件格式 不是存文本 而是按照他的格式来保存
        String filePath = "D:\\data.txt";
        //ObjectOutputStream()有一个构造器可以传入InputStream的子类
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        //序列化数据到D:\data.dat
        oos.writeInt(100);//int -> Integer (实现了Serializable)
        oos.writeBoolean(true);//boolean -> boolean(实现了Serializable)
        oos.writeChar('a');
        oos.writeDouble(9.9);
        oos.writeUTF("黄帝内经");//String (实现了Serializable)
        //保存一个对象
        oos.writeObject(new Dog("大黄",10,"黄色","中国"));
        oos.close();
        System.out.println("数据以序列化形式保存完成");
    }
}

//如果需要序列化某个类的对象 需要实现Serializable(最优 因为他没有需要实现的方法) 或者实现Externalizable
