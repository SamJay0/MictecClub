package com.example.android.mictecclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
<<<<<<< HEAD
=======
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
>>>>>>> c3150c43cd0723e4ffcfb6f2e44d94598ae5b519

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anslo on 11/14/18.
 */

public class ActivitySplash extends AppCompatActivity {
<<<<<<< HEAD
=======
    private ImageView splashpic;
>>>>>>> c3150c43cd0723e4ffcfb6f2e44d94598ae5b519
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
<<<<<<< HEAD
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

        };
        new Timer().schedule(task,2000);
=======

        splashpic = (ImageView) findViewById(R.id.splashpic);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        splashpic.startAnimation(myanim);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }

        };
        timer.start();


//        TimerTask task=new TimerTask() {
//            @Override
//            public void run() {
//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);
//            }
//
//        };
//        new Timer().schedule(task,2000);
>>>>>>> c3150c43cd0723e4ffcfb6f2e44d94598ae5b519
    }
}
