package com.hucong.interface_;

/**
 * 接口多态传递现象
 *
 */
public class InterfacePolyPass {
    public static void main(String[] args) {
        //接口类型的变量可以指向 三星了该接口类的对象实例
        IG ig = new Test01();
        //IG继承了IH 类test01实现了IG接口 那么相当于 test01也实现了IH接口
        IH ih = new Test01();
    }
}
interface IH{
    void hi();
}
interface IG extends IH{}
class Test01 implements IG{
    @Override
    public void hi() {

    }
}