package com.example.mario.model;

public class Pole extends GameCharacter{
    private Player player;
    private Flag flag;
    public Pole(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=100;
        this.ySize=600;
    }

    public void setPlayer(Player player) { this.player = player; }

    public void setFlag(Flag flag) { this.flag = flag; }

    public void move() {
        if (overlap(player)) {
            flag.setFlagdown(true);
        }
    }
}
