package com.example.mario.model;

import java.util.LinkedList;
import java.util.List;

public class World {
    // オブジェクト用変数
    private Player player;
    private List<Bridge> bridges;
    private List<Pipe> pipes;
    private List<Block> blocks;
    private List<Concrete> concretes;
    private Pole pole;
    private Flag flag;
    private List<Enemy> enemys;
    private Goal goal;
    private List<Coin> coins;

    public World() {
        // オブジェクトの生成
        player = new Player(0,100);
        bridges = new LinkedList<Bridge>();
        for (int i=0; i<12; i++) {
            bridges.add(new Bridge(i * 240, 0));
        }
        for (int i=12; i<31; i++) {
            bridges.add(new Bridge(i*240+200, 0));
        }
        pipes = new LinkedList<>();
        pipes.add(new Pipe(1800, 0));
        pipes.add(new Pipe(2200, 0));
        pipes.add(new Pipe(2640, 0));
        pipes.add(new Pipe(3100, 0));
        pipes.add(new Pipe(3620, -60));

        blocks = new LinkedList<Block>();
        blocks.add(new Block(1392, 256));
        blocks.add(new Block(1488, 256));
        blocks.add(new Block(1584, 256));
        blocks.add(new Block(3376, 256));
        blocks.add(new Block(3472, 256));
        blocks.add(new Block(3568, 256));
        for (int i=0; i<10; i++) {
            for (int j=0; j<i; j++) {
                blocks.add(new Block(i*48+3700, j*48+48));
            }
        }

        concretes = new LinkedList<Concrete>();
        concretes.add(new Concrete(1440, 256));
        concretes.add(new Concrete(1488, 496));
        concretes.add(new Concrete(1536, 256));
        concretes.add(new Concrete(3424, 256));
        concretes.add(new Concrete(3520, 256));

        coins = new LinkedList<Coin>();
        coins.add(new Coin(1440, 256));
        coins.add(new Coin(1488, 496));
        coins.add(new Coin(1536, 256));
        coins.add(new Coin(3424, 256));
        coins.add(new Coin(3520, 256));


        pole = new Pole(4400, 0);
        flag = new Flag(4456, 420);

        enemys = new LinkedList<Enemy>();
        enemys.add(new Enemy(1900, 48));
        enemys.add(new Enemy(2300, 48));
        enemys.add(new Enemy(2400, 48));
        enemys.add(new Enemy(2500, 48));
        enemys.add(new Enemy(3400, 48));

        goal = new Goal(4800, 48);

        // モデルの接続
        for(Enemy enemy : enemys) {
            enemy.setPlayer(player);
        }
        pole.setPlayer(player);
        pole.setFlag(flag);
        flag.setGoal(goal);
        goal.setPlayer(player);
        for(Concrete concrete : concretes ){
            concrete.setPlayer(player);
        }
        for(int i=0; i<coins.size(); i++) {
            Concrete concrete = concretes.get(i);
            Coin coin = coins.get(i);
            concrete.setCoin(coin);
        }

//        for(int i=0; i<concretes.size(); i++) {
//            Coin coin = coins.get(i);
//            Concrete concrete = concretes.get(i);
//            coin.setConcrete(concrete);
//        }





        // addLimitCharacterはコンストラクタに記述
        bridges.forEach(x->player.addLimitCharacter(x));
        pipes.forEach(x->player.addLimitCharacter(x));
        blocks.forEach(x->player.addLimitCharacter(x));
        concretes.forEach(x->player.addLimitCharacter(x));



    }

    public void move() {
        // オブジェクトの更新
        player.move();
        enemys.forEach(x->x.move());
        pole.move();
        flag.move();
        goal.move();
        concretes.forEach(x->x.move());
        coins.forEach(x->x.move());
        for (int i=0; i<coins.size(); i++) {
        }



    }

    //Getter

    public Player getPlayer() {
        return player;
    }

    public List<Bridge> getBridges() { return bridges; }
    public List<Pipe> getPipes() { return  pipes; }
    public List<Block> getBlocks() { return blocks; }
    public List<Concrete> getConcretes() { return concretes; }

    public Pole getPole() { return pole; }

    public Flag getFlag() { return flag; }

    public List<Enemy> getEnemys() { return enemys; }
    public Goal getGoal() { return goal; }

    public List<Coin> getCoins() { return coins; }

}
