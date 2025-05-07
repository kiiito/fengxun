package com.hucong.Thread.exit_;

/**
 *  线程的常用方法
 * 1 yield 线程礼让 让其他线程执行 但礼让的时间不确定 所以也不一定礼让成功 因为这要根据CPU的运行资源
 * 2 join 线程插队 插队的线程一旦插队成功 则先执行完插入线程的所有任务
 */
public class ThreadMethod02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 20; i++) {
                System.out.println("子线程被执行 " + i + " 次");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程被执行 " + i + " 次");
            if(i == 5){
                Thread.sleep(1000);
                thread.join();
                //Thread.yield(); 礼让 但不一定成功
            }
        }

    }
}
