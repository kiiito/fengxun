package com.hucong.tankgame03;

public class Tank {
    private int x;
    private int y;

    private int direct;
    private int speed = 3;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上下左右移动的方法
    public void moveUp(){
        y -= speed;
    }
    public void moveRight() {
        x += speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
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
