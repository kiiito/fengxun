package com.hucong.single_;
/**
 * 单例模式饿汉式
 * 一开始类的加载就会调用静态方法，可能会造成占用资源
 */
public class SingleTon01 {
    public static void main(String[] args) {
//        System.out.println(GirlFriend.instance());
        GirlFriend instance =GirlFriend.instance();
        System.out.println(instance);
    }
}
class GirlFriend{
    private String name;
    //保障我们只能创建一个GirlFriend对象
    //将构造器私有化 2 在类的内部直接创建 3提供一个公共的静态方法 返回gf对象
    private static GirlFriend gf = new GirlFriend("如烟");
    private GirlFriend(String name) {
        this.name = name;
    }
    public static GirlFriend instance(){
        return gf;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}