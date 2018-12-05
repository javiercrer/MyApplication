package com.example.javier.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 4000; // 1 segundo

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
            animation.setDuration(3000); // duration - a second
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

        Button btnPropiedadNueva = findViewById(R.id.btnPropiedadCerca);
        btnPropiedadNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarNotificacion(view);
            }
        });

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pase el segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }

    public void mostrarNotificacion(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);

        Notification.Builder notiBuilder = new Notification.Builder(this)
                .setContentTitle("BELARDO & PARRONDO")
                //.setContentText("Una propiedad se encuentra cerca")
                .setSmallIcon(android.R.drawable.ic_lock_silent_mode_off)
                .setLargeIcon(bmp)
                .setStyle(new Notification.InboxStyle()
                        .addLine("Una propiedad se encuentra cerca de")
                        .addLine("tu ubicación. Hace CLICK para verla."));
        //
        //.build();

        /*
        mas estilos de notificaciones:
        https://developer.android.com/guide/topics/ui/notifiers/notifications
        https://developer.android.com/reference/android/support/v4/app/NotificationCompat.InboxStyle#classes
        https://developer.android.com/training/notify-user/custom-notification#java
         */

        //Activity q se lanza al hacer click en la notificacion

        //Habria que hacer una pantalla donde muestre el detalle de la nueva propiedad
        Intent i = new Intent(this, DetallePropiedadActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        i,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        //asociar el futuro intento al builder de la notificacion
        notiBuilder.setContentIntent(resultPendingIntent);

        int notificationId = 123456789;
        //android con mismo id se da cuenta que es un mensaje proveniente de la misma aplicacion
        //por lo que posteriormente se podria avisar que tiene dos mensajes pendientes

        //https://www.youtube.com/watch?v=CPk2vQEIc5c

        //el atributo flags de la notificación nos permite ciertas opciones
        Notification notification = notiBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;//oculta la notificación una vez pulsada
        /*//idem para defaults
        notification.defaults |= Notification.DEFAULT_SOUND; //sonido
        //añadimos efecto de vibración, necesitamos el permiso <uses-permission android:name="android.permission.VIBRATE" />
        notification.defaults |= Notification.DEFAULT_VIBRATE;*/

        //Gestor de notificaciones
        NotificationManager notifManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifManager.notify(notificationId, notification);

        //notiBuilder.build()
        //minSdkVersion 16
    }
}
