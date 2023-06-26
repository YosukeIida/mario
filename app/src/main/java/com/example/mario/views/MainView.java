package com.example.mario.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mario.MainActivity;
import com.example.mario.R;
import com.example.mario.helpers.BaseView;
import com.example.mario.model.Coin;
import com.example.mario.model.Enemy;
import com.example.mario.model.GameCharacter;
import com.example.mario.model.Goal;
import com.example.mario.model.Player;
import com.example.mario.model.World;


public class MainView extends BaseView {

    MainActivity mainActivity;
    ConstraintLayout constraintLayout;
    Context context;

    // 定数
    final int BRIDGE_COUNT = 5;

    // 画像用変数
    Bitmap backGroundImage;
    Bitmap playerRightImage;
    Bitmap playerLeftImage;
    Bitmap bridgeImage;
    Bitmap pipeUpImage;
    Bitmap blockImage;
    Bitmap concreetExImage;
    Bitmap poleImage;
    Bitmap flagImage;
    Bitmap enemyImage;
    Bitmap goalImage;
    Bitmap coinImage;

    // ビュー用変数
    ImageViewBuilder imageViewBuilder;
    TextView gameClearTextView;
    TextView gameOverTextView;

    public MainView(Context context) {
        super(context);
        this.context = context;
        this.mainActivity = (MainActivity) context;

        // 画像の読み込み
        backGroundImage = loadImage(R.drawable.field);
        playerRightImage = loadImage(R.drawable.player_right);
        playerLeftImage = loadImage(R.drawable.player_left);
        bridgeImage = loadImage(R.drawable.bridges);
        pipeUpImage = loadImage(R.drawable.pipe_up);
        blockImage = loadImage(R.drawable.block1);
        concreetExImage = loadImage(R.drawable.concretex);
        poleImage = loadImage(R.drawable.pole);
        flagImage = loadImage(R.drawable.flag);
        enemyImage = loadImage(R.drawable.enemy);
        goalImage = loadImage(R.drawable.goal);
        coinImage = loadImage(R.drawable.coin);

        // ビューの生成・登録
        constraintLayout = new ConstraintLayout(context);
        baseActivity.setContentView(constraintLayout);
        imageViewBuilder = new ImageViewBuilder(constraintLayout, context);

    }

    public void draw(World world) {
        // 横スクロール
        Player player = world.getPlayer();
        if (player.getX() < 750) {
            canvasBaseX = 0;
        } else if (player.getX() < 3750 + 750 ) {
            canvasBaseX = player.getX() - 750;
        } else {
            canvasBaseX = 3750;
        }

        // ImageViewBuilderリセット
        imageViewBuilder.reset();

        // 表示
        ImageView imageView = imageViewBuilder.getImageView();
        drawImage(0, 0, 8000, 700, backGroundImage, imageView);
        world.getBridges().forEach(x->drawCharacter(x, bridgeImage));
        world.getPipes().forEach(x->drawCharacter(x, pipeUpImage));
        world.getBlocks().forEach(x->drawCharacter(x, blockImage));
        world.getConcretes().forEach(x->drawCharacter(x, concreetExImage));
        drawCharacter(world.getPole(), poleImage);
        drawCharacter(world.getFlag(), flagImage);
        world.getEnemys().forEach(x->drawEnemy(x, enemyImage));
        drawGoal(world.getGoal(), goalImage);
//        world.getCoins().forEach(x->drawCoin(x, coinImage));
        world.getCoins().forEach(x->drawCharacter(x, coinImage));

        drawPlayer(player);

        // GameOver
        if (player.isDead()) {
            drawGameOver();
        }

        // GameClear
        if (player.isClear()) {
            drawGameClear();
        }

    }

    //======================
    // テキストビュー表示用の関数
    //======================
    public void drawGameClear() {
        if (gameClearTextView == null) {
            gameClearTextView = new TextView(context);
            constraintLayout.addView(gameClearTextView);
        }
        gameClearTextView.setTextSize(32);
        gameClearTextView.setTextColor(Color.WHITE);
        gameClearTextView.setText("Game Clear !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameClearTextView);
    }

    public void drawGameOver() {
        if (gameOverTextView == null) {
            gameOverTextView = new TextView(context);
            constraintLayout.addView(gameOverTextView);
        }
        gameOverTextView.setTextSize(32);
        gameOverTextView.setTextColor(Color.RED);
        gameOverTextView.setText("Game Over !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameOverTextView);
    }


    //======================
    // キャラクター表示用の関数
    //======================
    private void drawPlayer(Player player) {
        Bitmap playerImage = (player.getXSpeed() >= 0) ? playerRightImage : playerLeftImage;
        drawCharacter(player, playerImage);
    }

    private void drawCharacter(GameCharacter c, Bitmap image) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, image, imageView);
        }
    }

    private void drawEnemy(Enemy enemy, Bitmap image) {
        if ( enemy.isDead() ) {
            ImageView imageView = imageViewBuilder.getImageView();
            imageView.setVisibility(GONE);
        } else {
            drawCharacter(enemy, image);
        }
    }

    private void drawGoal(Goal goal, Bitmap image) {
        if (goal.isAppear()) {
            drawCharacter(goal, image);
        } else {
            ImageView imageView = imageViewBuilder.getImageView();
            imageView.setVisibility(GONE);
        }
    }

    private  void drawCoin(Coin coin, Bitmap image) {
        if (coin.isAppear()) {
            drawCharacter(coin, image);
        } else {
            ImageView imageView = imageViewBuilder.getImageView();
            imageView.setVisibility(GONE);
        }
    }
}


