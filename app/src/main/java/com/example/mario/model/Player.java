package com.example.mario.model;

/**
 * Created by t010318 on 2017/06/21.
 */

public class Player extends GameCharacter {
    private int yAccel = -1;

    private boolean deadFlag = false;
    private boolean jumpFlag = false;
    private boolean clearFlag = false;


    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 96;
        this.ySize = 96;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public boolean isJump() { return jumpFlag;}

    public boolean isDead() { return deadFlag; }
    public boolean isClear() { return clearFlag; }

    public void turnRight() {
        xSpeed = 5;
    }

    public void turnLeft() {
        xSpeed = -5;
    }

    public void stop() {
        xSpeed = 0;
    }

    public void dead() { deadFlag = true; }

    public void setClearFlag(boolean clearFlag) {
        this.clearFlag = clearFlag;
    }

    public void jump() {
        if (jumpFlag == false) {
            ySpeed = 24;
            jumpFlag = true;
        }
    }

    public void move() {
        // 位置の更新
        x += xSpeed;
        y += ySpeed;
        ySpeed += yAccel;

        // 着地咲いたらジャンプ終了
        if (y < yMin) {
            ySpeed = 0;
            jumpFlag = false;
        }


        // 衝突時の①の補正
        correctPosition();
    }
}
