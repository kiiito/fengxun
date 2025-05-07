package com.hucong.innerclass;

/**
 * 演示直接将匿名内部类当做实参传递
 */
public class InnerClassExercise {
    public static void main(String[] args) {
        //直接当做实参传递 简洁高效
        f1(new IL() {
            @Override
            public void show() {
                System.out.println("你好");
            }
        });

        //传统方式
        f1(new Car());

        // 2
        Cellphone cellphone = new Cellphone();
        //cellphone.test();
        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });
        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });
    }
    //静态方法 形参是接口类型
    public static void f1(IL il){
        il.show();
    }
}
interface IL{
    void show();
}
class Car implements IL{

    @Override
    public void show() {
        System.out.println("你好");
    }
}

// 2
interface Bell{
    void ring();
}
class Cellphone{
    public void alarmClock(Bell bell){
        bell.ring();
    }
//    public void test() {
//        alarmClock(new Bell() {
//            @Override
//            public void ring() {
//                System.out.println("懒猪起床了");
//            }
//        });
//        alarmClock(new Bell() {
//            @Override
//            public void ring() {
//                System.out.println("小伙伴上课了");
//            }
//        });
//    }
}