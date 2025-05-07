package com.hucong.Thread.exit_;

/**
 *  线程的常用方法
 * 1 setName 设置线程名称
 * 2 Thread.currentThread().getName()获取该线程名称
 * 3 start 执行线程 Java虚拟机底层调用该线程的strat0方法
 * 4 run 调用线程对象run方法
 * 5 setPriority 更改该线程的优先级
 * 6 getPriority 获取该线程的优先级
 * 7 sleep 在指定的毫秒数内让当前的正在执行的线程休眠
 * 8 interrupt 中断线程 中断阻塞状态当线程处于Object.wait()、Thread.sleep()或Thread.join()等阻塞状态时，
 * 调用interrupt()方法会抛出InterruptedException异常
 */
public class TheadMethod01 {
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setPriority(1);//设置优先级 1 为最小
        t1.setName("001");
        t1.start();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("主线程执行 " + i + Thread.currentThread().getName());
        }
        System.out.println("t1的优先级为 " + t1.getPriority());
        t1.interrupt();//线程中断 子线程不再休眠 继续执行


        //如果线程没有处于阻塞状态，interrupt()方法会设置线程的中断标志位。
        // 线程可以通过isInterrupted()方法来检查自己的中断标志位是否被设置。
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("线程正在运行");
            }
            System.out.println("线程被中断");
        });
        thread.start();
        try {
             Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
class T1 extends Thread {
    @Override
    public void run() {
        while (true){
            for (int i = 0; i < 100; i++) {
                System.out.println("子线程执行 " + i + " " +Thread.currentThread().getName());
            }
            try {
                System.out.println("子线程休眠中");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                //当线程执行到一个interrupt 方法时 就会catch一个异常 可以加入自己的业务代码
                // InterruptedException 就是捕获一个中断异常
                System.out.println("子线程"+ Thread.currentThread().getName() +"被interrupt 中断");
            }
        }
    }
}