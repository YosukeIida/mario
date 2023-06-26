package com.example.mario.model;

public class Goal extends GameCharacter {
    private Player player;

    public Goal(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=80;
        this.ySize=80;
    }

    private boolean appearFlag = false;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAppearFlag(boolean appearFlag) { this.appearFlag = appearFlag; }

    public boolean isAppear() { return appearFlag; }



    public void move() {
        if (overlap(player)) {
            player.setClearFlag(true);
        }
    }
}
