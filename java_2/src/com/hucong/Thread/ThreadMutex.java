package com.hucong.Thread;

/**
 *  同步方法互斥锁
 * 1 同步方法如果没有使用static修饰 默认锁对象为this(要求多个线程的锁对象为同一个)
 * 也可以是其他对象
 * 2 如果方法当中有static修饰 默认锁对象 为当前类class
 */
public class ThreadMutex {
    public static void main(String[] args) {
        SellTicket01 sellTicket01 = new SellTicket01();
        new Thread(sellTicket01).start();
        new Thread(sellTicket01).start();
        new Thread(sellTicket01).start();
    }
}
class SellTicket01 implements Runnable {
    private int ticketNum = 100;
    private boolean loop = true;
      Object object = new Object();
      //public synchronized static void m() 锁是加载到SellTicket01.class类当中
      public synchronized static void m(){
      }

      public static void m2() {
          synchronized(SellTicket01.class){
              System.out.println("m2");
          }
      }
    //synchronized 同步线程 一次最多只能有一个线程访问
    public/* synchronized */ void sell() throws InterruptedException {
        //object 也是在 sellTicket01 下创建的 可以被多个线程使用
        //this代表了指向的对象 只要在同一对象下的创建的都获得锁
        synchronized (this /* object */) {
            if (ticketNum <= 0) {
                System.out.println("售票结束 已无票");
                loop = false;
                return;
            }
            Thread.sleep(50);
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 "
                    + " 剩余票数 " + (--ticketNum));
        }
    }
    @Override
    public void run() {
        while (loop){
            try {
                sell();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
