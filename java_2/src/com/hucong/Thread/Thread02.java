package com.hucong.Thread;

/**
 * 实现Runnable接口创建线程
 */
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start();不能在使用
        //将实现Runnable的dog对象放入Thread的对象当中 再去调用start方法
        Thread thread = new Thread(dog);
        thread.start();

        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}
class Dog implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (true){
            //获取线程名称
            System.out.println("子线程 count = " + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count == 10){
                break;
            }
        }
    }
}
//线程代理类 模拟一个极简的Thread类
class ThreadProxy implements Runnable {
    private Runnable target = null;
    @Override
    public void run() {
        if (target != null){
            target.run();//动态绑定
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }
    public void start(){
        start0();//这个方法时真正实现多线程方法
    }
    public void start0(){
        run();
    }
}
class Animal{}
class Tiger extends Animal implements Runnable {
    @Override
    public void run() {
        System.out.println("老虎叫");
    }
}