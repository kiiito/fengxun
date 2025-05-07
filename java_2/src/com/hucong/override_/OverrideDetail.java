package com.hucong.override_;

public class OverrideDetail {
    public static void main(String[] args) {

    }
}
class C{
//    public String xx(){
//        return null;
//    }
    public Object xx(){
        return null;
    }
    protected void bb(){
        System.out.println("2");
    }
}
class D extends C{
    //子类的方法的参数，方法名称要和父类的方法名称，参数完全一样，子类的返回类型与父类的返回类型一样，或者是父类返回类型的子类
    //例如父类 为object 子类为String
    public String xx(){
        return "1";
    }

    //子类方法不可缩小父类的访问权限
    //public > protected > 默认 > private
    //子类 >= 父类
     public void bb(){
         System.out.println("3");
    }
}