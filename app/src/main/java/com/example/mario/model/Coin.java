package com.example.mario.model;

public class Coin extends GameCharacter {

    private Concrete concrete;
    private boolean appearFlag = false;
    private boolean popoutFlag = false;
    private int yAccel = -1;

    public Coin(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=48;
        this.ySize=48;
        this.ySpeed=0;
    }

    public void setAppearFlag(boolean appearFlag) {
        this.appearFlag = appearFlag;
    }

    public boolean isAppear() {
        return appearFlag;
    }

    public void setConcrete(Concrete concrete) {
        this.concrete = concrete;
    }

    public void popout() {
        if ( popoutFlag ) {
            ySpeed = 24;
            popoutFlag = false;
            System.out.println("呼ばれた");
        }
    }

    public void move() {
        y += ySpeed;
        ySpeed += yAccel;

        if (y < 0) {
            y = 0;
        }
        System.out.println(y);

    }

}
