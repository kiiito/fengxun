package com.hucong.Thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

public class ThreadHomework01 {
    public static void main(String[] args) {
        T7 t7 = new T7();
        Thread thread1 = new Thread(t7);
        thread1.start();
        T6 t6 = new T6(t7);
        t6.start();
    }
}
class T6 extends Thread  {
    private T7 t7;

    public T6(T7 t7) {
        this.t7 = t7;
    }
     Scanner sc = new Scanner(System.in);
    @Override
    public void run() {
        System.out.println("请输入你的指令(Q)表示退出");
        char key = sc.next().toUpperCase().charAt(0);
        if(key == 'Q'){
            t7.setLoop(false);
        }

    }
}
class T7 implements Runnable {
    Random random = new Random();
    private boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            //(int)(Math.random() *100 + 1)
                System.out.println(random.nextInt(100));
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
