package com.example.mario.model;

public class Flag extends GameCharacter{
    private Goal goal;

    public Flag(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=100;
        this.ySize=90;
        this.ySpeed=0;
    }

    private boolean flagdown = false;

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setFlagdown(boolean flagdown) {
        this.flagdown = flagdown;
    }

    public void move() {
        y = (int) (y + ySpeed);
        if (flagdown && y > 0) {
            ySpeed = -2;
        } else {
            ySpeed = 0;
        }
        if (y <= 0) {
            goal.setAppearFlag(true);
        }
    }

}
