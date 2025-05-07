package com.hucong.tankgame04;

public class Tank {
    private int x;
    private int y;
    boolean isLive = true;
    private int direct;
    private int speed = 3;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上下左右移动的方法
    public void moveUp(){
        if(y > 5) {
            y -= speed;
        }
    }
    public void moveRight() {
        if(x < 925) {
            x += speed;
        }

    }
    public void moveDown() {
        if(y < 635) {
            y += speed;
        }
    }
    public void moveLeft() {
        if ( x > 5) {
            x -= speed;
        }
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
