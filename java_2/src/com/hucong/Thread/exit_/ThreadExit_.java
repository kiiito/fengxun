package com.hucong.Thread.exit_;

/**
 * 通知线程退出
 */
public class ThreadExit_ {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        //让主线程休眠5秒 方便观察
        System.out.println("主线程休眠5秒");
        Thread.sleep(5 * 1000);
        //终止子线程
        t.setLoop(false);
    }
}
class T extends Thread {
    int count = 0;
    boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            System.out.println("子线程正在执行" + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}