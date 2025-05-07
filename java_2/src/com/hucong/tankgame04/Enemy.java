package com.hucong.tankgame04;

import java.util.Vector;

public class Enemy extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            //这里我们判断如果Shots size() = 0 创建一颗子弹 放入集合 并启动
            if (isLive && shots.size() == 0){
                Shot shot = null;
                switch (getDirect()) {
                    case 0:
                        shot = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                //将发射的子弹加入到集合当中
                shots.add(shot);
                //启动我们的射击线程
                new Thread(shot).start();
            }

            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                    case 1:
                        for (int i = 0; i < 30; i++) {
                            moveRight();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                    case 3: for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                        break;
            }
            //随机变换坦克的方向
            setDirect((int)(Math.random() * 4));
            if (!(isLive)){
               break;
            }
        }
    }

}
