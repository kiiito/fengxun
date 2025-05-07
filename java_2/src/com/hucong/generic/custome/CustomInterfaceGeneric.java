package com.hucong.generic.custome;

public class CustomInterfaceGeneric {
    public static void main(String[] args) {
        /**
         *  自定义泛型接口
         * 1 接口中 静态成员也不能使用泛型
         * 2 泛型接口的类型 在继承接口或实现接口时确定
         * 3 没有指定泛型 默认是Object
         */
    }
}
interface IUsb<U,R>{
    //普通方法中 可以使用接口泛型
    R get(U u);
    //U name 不能这么写
    void hi(R r);
    void run(R r1, R r2,U u1,U u2);
    default R method(U u){
        return null;
    }
}
//在继承接口 指定泛型接口的类型
interface IA extends IUsb<String,Double>{}

//当我们去实现IA接口时 因为IA在继承IUsb 接口时 指定了U 为String R为Double
//在实现IUsb接口的方法时 使用String替换U Double替换R
class AA implements IA{
    @Override
    public Double get(String s) {
        return null;
    }

    @Override
    public void hi(Double aDouble) {

    }

    @Override
    public void run(Double r1, Double r2, String u1, String u2) {

    }
}
//实现接口时 直接指定泛型接口的类型
//给U 指定Integer 给R 指定Float
//所以 当我们实现IUsb方法时 会使用Integer替换U Float 替换 R
class BB implements IUsb<Integer,Float>{

    @Override
    public Float get(Integer integer) {
        return null;
    }

    @Override
    public void hi(Float aFloat) {

    }

    @Override
    public void run(Float r1, Float r2, Integer u1, Integer u2) {

    }
}
