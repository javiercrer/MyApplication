package com.example.javier.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 2000; // 1 segundo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            ImageView image = (ImageView) findViewById(R.id.imageViewIntro);
            //Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(1000); // duration - a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter
            // animation
            // rate
            animation.setRepeatCount(Animation.INFINITE); // Repeat animation
            // infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the
            // end so the button will
            // fade back in
            image.startAnimation(animation);
        } else {
            // Swap without transition
        }

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pase el segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);


    }
}
