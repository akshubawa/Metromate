/*
package com.example.amigos.metromate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen extends AppCompatActivity {

    Animation top_animation, bottom_animation;
    ImageView image_view;

    private static int SPLASH_SCREEN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading_screen);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        image_view = findViewById(R.id.image_view);

        //image_view.setAnimation(top_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingScreen.this, StationsActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}*/
