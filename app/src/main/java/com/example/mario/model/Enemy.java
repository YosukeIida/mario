package com.example.mario.model;

public class Enemy extends GameCharacter {
    private Player player;

    public Enemy(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=48;
        this.ySize=48;
        this.xSpeed=2;
        this.xMin=x;
    }
    private boolean enemyDeadFlag = false;

    public boolean isDead() { return enemyDeadFlag; }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void move() {
        x = x + xSpeed;
        if (x > xMin + 80) {
            xSpeed = -2;
        } else if (x < xMin){
            xSpeed = 2;
        }

//        System.out.println(player.ySpeed);
        if (overlap(player) && !isDead()) {
            if (player.ySpeed < -1.0) {
                enemyDeadFlag = true;
            } else {
                player.dead();
            }
        }
    }
}
