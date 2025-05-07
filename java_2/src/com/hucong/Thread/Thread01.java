package com.hucong.Thread;

/**
 * 继承Thread类创建线程
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        //启动线程 最终会执行cat的run方法
        car.start();
        //当main线程启动一个子线程Thread-0 主线程不会阻塞 会继续执行
        //但如果只是调用run方法 没有真正启动一个线程
        // 就会把run方法执行完毕 才会向下执行 造成阻塞(只会有一个主线程)
        //cat.run();
        //这时的主线程和子线程会交替执行
        for (int i = 0; i < 60; i++) {
            System.out.println("主线程 i =" + i);
            Thread.sleep(1000);
        }
    }
}

class Car extends Thread {
    @Override
    public void run() {
        int time = 0;
        while (true) {
            time++;
            System.out.println("子线程 time = " + time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (time == 80) {
                break;
            }
        }
    }
}
