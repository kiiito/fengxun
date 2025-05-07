package com.hucong.annotation_;

public class Deprecated_ {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.n);
    }
}
// 1 @Deprecated 修饰某个元素 表示该元素已经过时
// 2 即不在推荐使用 但仍然可以使用
// 3 可以修饰 方法 类 包 字段 参数
@Deprecated
class A{
    @Deprecated
    public  int n = 10;
}