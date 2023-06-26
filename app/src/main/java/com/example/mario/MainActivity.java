package com.example.mario;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.mario.R;
import com.example.mario.helpers.BaseActivity;
import com.example.mario.model.Player;
import com.example.mario.model.World;
import com.example.mario.views.MainView;

public class MainActivity extends BaseActivity {

    World world;

    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gravityEnabled = true;
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        world = new World();
        mainView = new MainView(this);

    }

    public void update() {
        Player player = world.getPlayer();
        if (accelerationController.y > 2) {
            player.turnRight();
        } else if (accelerationController.y < -2) {
            player.turnLeft();
        } else {
            player.stop();
        }
//        if (accelerationController.x > 2) {
//            player.jump();
//        }


        world.move();
        mainView.draw(world);
//        if (touchController.touchEvent != null) {
//            if (touchController.touchEvent.getX() < 750) {
//                player.turnLeft();
//            } else {
//                player.turnRight();
//            }
//        } else {
//            player.stop();
//        }
    }

    //===========
    // イベントリスナー
    //===========
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:    // 画面から指を離したら
                world.getPlayer().stop();
                break;
            case MotionEvent.ACTION_DOWN: // 画面をタップしたら
                float tapx = event.getX();
                float tapy = event.getY();
                // タップした座標が中央から右側なら右向き、中央から左側なら左向き
//                if(tapy<350) {
                world.getPlayer().jump();
//                }else{
//                    if (tapx < 750) {
//                        world.getPlayer().turnLeft();
//                    } else {
//                        world.getPlayer().turnRight();
//                    }
//                }

        }
        return true;
    }

    public void retry() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}