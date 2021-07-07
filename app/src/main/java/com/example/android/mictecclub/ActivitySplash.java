package com.example.android.mictecclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anslo on 11/14/18.
 */

public class ActivitySplash extends AppCompatActivity {
    private ImageView splashpic;
    private String TAG="Paul mburu android";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Explode explode = new Explode();
//        explode.setDuration(5000);
//        getWindow().setEnterTransition(explode);

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
    }

}
