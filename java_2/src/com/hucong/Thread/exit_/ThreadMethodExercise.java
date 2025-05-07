package com.hucong.Thread.exit_;

public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
        T3 t3 = new T3();
        Thread thread  = new Thread(t3);
        for (int i = 1; i <= 10 ; i++) {
            System.out.println("主线程执行 " + i);
            Thread.sleep(1000);
            if (i == 5) {
                System.out.println("子线程开始执行");
                thread.start();
                thread.join();
            }
        }
        System.out.println("主线程执行结束");
    }

}
class T3 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("子线程执行 " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("子线程执行结束");
    }
}