package com.hucong.Thread.exit_;

/**
 *  线程的常用方法
 *  守护线程 一般都是为工作线程服务 当所有工作线程结束 守护线程自动结束
 */
public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {
       Thread thread =  new Thread(()->{
            while (true){
                System.out.println("子线程在运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //如果我们希望当主线程结束后 子线程自动结束
        // 只需要将子线程设置为守护线程即可 但必须设置在启动线程之前否则会报错
       thread.setDaemon(true);
       thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程在运行");
            Thread.sleep(1000);
        }
    }
}
