package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView);
        iv_background.setImageBitmap(bmp);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/gisha.ttf");
        TextView tvNombre = (TextView) findViewById(R.id.lblNombre);
        tvNombre.setTypeface(face);

        TextView tvEmail = (TextView) findViewById(R.id.lblEmail);
        tvEmail.setTypeface(face);

        TextView tvTelefono = (TextView) findViewById(R.id.lblTelefono);
        tvTelefono.setTypeface(face);

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setTypeface(face);

        ImageView imageBig = (ImageView) findViewById(R.id.imageViewBig);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void continuar(View view){
        Intent i = new Intent(this, InstitutoActivity.class);
        startActivity(i);
    }
}
