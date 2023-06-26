package com.example.mario.model;

public class Concrete extends  GameCharacter{
    private Player player;
    private Coin coin;

    public Concrete(int x, int y) {
        this.x=x;
        this.y=y;
        this.xSize=48;
        this.ySize=48;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public void move() {
        if (overlapX(player) && (player.y + player.ySize > y - 50)) {
             coin.setAppearFlag(true);
             coin.ySpeed = 24;
             coin.popout();
        }
//        System.out.println(coin.isAppear());
    }

}
