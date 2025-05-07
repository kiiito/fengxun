package com.hucong.Thread;

public class SellTicketExercise {
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}
class SellTicket implements Runnable {
    private int ticketNum = 100;
    private boolean loop = true;
    //synchronized 同步线程 一次最多只能有一个线程访问
    public synchronized void sell() throws InterruptedException {
        if(ticketNum <= 0){
            System.out.println("售票结束 已无票");
            loop = false;
            return ;
        }
        Thread.sleep(50);
        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 "
         + " 剩余票数 " + (--ticketNum));
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