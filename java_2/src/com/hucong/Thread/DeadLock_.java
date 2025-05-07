package com.hucong.Thread;

/**
 * 模拟线程的死锁
 */
public class DeadLock_ {
    public static void main(String[] args) {
        T5 t1 = new T5(true);
        T5 t2 = new T5(false);
        t1.start();
        t2.start();
    }
}

class T5 extends Thread {
    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean flag;

    public T5(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "进入1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "进入2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "进入4");
                }
            }
        }
    }
}