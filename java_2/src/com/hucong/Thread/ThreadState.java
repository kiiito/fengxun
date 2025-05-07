package com.hucong.Thread;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new T4());
        System.out.println(thread.getName() + " 状态 " + thread.getState());
        thread.start();
        while (Thread.State.TERMINATED != thread.getState()) {
            System.out.println(thread.getName() + " 状态 " + thread.getState());
            thread.sleep(500);
        }
        System.out.println(thread.getName() + " 状态 " + thread.getState());
    }
}
class T4 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程运行中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
