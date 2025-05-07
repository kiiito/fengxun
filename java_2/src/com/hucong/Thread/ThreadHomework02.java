package com.hucong.Thread;

public class ThreadHomework02 {
    public static void main(String[] args) {
        T8 t8 = new T8();
        //一定是要同一个对象
        new Thread(t8).start();
        new Thread(t8).start();
    }
}

class T8 implements Runnable {
    private double money = 10000;

    @Override
    public void run() {
        while (true) {
            //当多个线程执行到这里 就会去争夺this这个对象锁
            //哪个对象获取到this对象锁就执行synchronized 代码块 执行完释放锁
            //争夺不到的线程 就blocked 准备下次争夺
            //this对象锁是非公平锁
            synchronized (this) {
                if (money <= 0) {
                    System.out.println("余额为0");
                    break;
                }
                money -= 1000;
                System.out.println(Thread.currentThread().getName() + " 取走1000元 "
                        + "还剩下 " + money);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}