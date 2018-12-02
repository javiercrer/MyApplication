package com.example.javier.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InstitutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instituto);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView3);
        iv_background.setImageBitmap(bmp);

        /*StateListDrawable states = new StateListDrawable();
        states.addState(new int[] {android.R.attr.state_pressed},getResources().getDrawable(R.drawable.btn_nosotros));
        states.addState(new int[] {android.R.attr.state_focused},getResources().getDrawable(R.drawable.btn_nosotros_pressed));
        states.addState(new int[] { },getResources().getDrawable(R.drawable.btn_nosotros));*/
        /*Button butttonPropiedades = (Button) findViewById(R.id.btnPropiedades);
        butttonPropiedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btn_propiedades).getConstantState())) {
                    Drawable imagePressed=(Drawable)getResources().getDrawable(R.drawable.btn_propiedades_pressed);
                    v.setBackground(imagePressed);
                } else {
                    Drawable image=(Drawable)getResources().getDrawable(R.drawable.btn_propiedades);
                    v.setBackground(image);
                }
            }
        });*/

        ImageView imageBig = (ImageView) findViewById(R.id.imageViewBig2);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Button butttonPropiedades = (Button) findViewById(R.id.btnPropiedades);
        Drawable imagePressedProp=(Drawable)getResources().getDrawable(R.drawable.btn_propiedades);
        butttonPropiedades.setBackground(imagePressedProp);

        Button butttonNosotros = (Button) findViewById(R.id.btnNosotros);
        Drawable imagePressedNos=(Drawable)getResources().getDrawable(R.drawable.btn_nosotros);
        butttonNosotros.setBackground(imagePressedNos);

        Button butttonVisitanos= (Button) findViewById(R.id.btnVisitenos);
        Drawable imagePressedVis=(Drawable)getResources().getDrawable(R.drawable.btn_visitanos);
        butttonVisitanos.setBackground(imagePressedVis);
    }

    public void cerrarSesion(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void mostrarNosotros(View v) {
        if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btn_nosotros).getConstantState())) {
            Drawable imagePressed=(Drawable)getResources().getDrawable(R.drawable.btn_nosotros_pressed);
            v.setBackground(imagePressed);
            Intent i = new Intent(this, NosotrosActivity.class);
            startActivity(i);
        } else {
            Drawable image=(Drawable)getResources().getDrawable(R.drawable.btn_nosotros);
            v.setBackground(image);
        }
    }

    public void mostrarPropiedades(View v){
        if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btn_propiedades).getConstantState())) {
            Drawable imagePressed=(Drawable)getResources().getDrawable(R.drawable.btn_propiedades_pressed);
            v.setBackground(imagePressed);

            Intent i = new Intent(this, PropiedadesActivity.class);
            startActivity(i);
        } else {
            Drawable image=(Drawable)getResources().getDrawable(R.drawable.btn_propiedades);
            v.setBackground(image);
        }
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
