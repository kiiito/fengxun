package com.hucong.tankgame05;

import java.util.Vector;

public class Hero extends Tank {
    //定义一个shot对象 表示一个射击线程
    Shot shot = null;
    //  可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public  void ShotEnemyTank() {
        //创建shot对象 根据当前hero对象的方向和位置来创建shot
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
}
