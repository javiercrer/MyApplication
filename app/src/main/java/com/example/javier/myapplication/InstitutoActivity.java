package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.javier.myapplication.models.UsuarioDTO;

public class InstitutoActivity extends AppCompatActivity {

    private UsuarioDTO usuarioDTO;

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
        ImageView iv_background = findViewById(R.id.imageView3);
        iv_background.setImageBitmap(bmp);

        Intent intent = getIntent();
        usuarioDTO = (UsuarioDTO) intent.getSerializableExtra(LoginActivity.KEY_USUARIO);

        Button btnCalendarioVisitas = findViewById(R.id.btnVisitas);
        btnCalendarioVisitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CalendarioVisitasActivity.class);
                startActivity(i);
            }
        });

        Button btnCerrarSesion = findViewById(R.id.btnLogin);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(view.getContext(), LoginActivity.class);
               startActivity(i);
           }
        });

        Button btnVisitenos = findViewById(R.id.btnVisitenos);
        btnVisitenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarProfesionales(view);
            }
        });

        ImageView imageBig = findViewById(R.id.imageViewBig2);
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

        Button butttonPropiedades = findViewById(R.id.btnPropiedades);
        Drawable imagePressedProp = getResources().getDrawable(R.drawable.btn_propiedades);
        butttonPropiedades.setBackground(imagePressedProp);

        Button butttonNosotros = findViewById(R.id.btnNosotros);
        Drawable imagePressedNos = getResources().getDrawable(R.drawable.btn_nosotros);
        butttonNosotros.setBackground(imagePressedNos);

        Button butttonVisitanos = findViewById(R.id.btnVisitenos);
        Drawable imagePressedVis = getResources().getDrawable(R.drawable.btn_visitanos);
        butttonVisitanos.setBackground(imagePressedVis);
    }

    public void mostrarNosotros(View v) {
        if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btn_nosotros).getConstantState())) {
            Drawable imagePressed = getResources().getDrawable(R.drawable.btn_nosotros_pressed);
            v.setBackground(imagePressed);
            Intent i = new Intent(this, NosotrosActivity.class);
            startActivity(i);
        } else {
            Drawable image = getResources().getDrawable(R.drawable.btn_nosotros);
            v.setBackground(image);
        }
    }

    public void mostrarPropiedades(View v){
        if(v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btn_propiedades).getConstantState())) {
            Drawable imagePressed = getResources().getDrawable(R.drawable.btn_propiedades_pressed);
            v.setBackground(imagePressed);

            Intent i = new Intent(this, PropiedadesActivity.class);
            startActivity(i);
        } else {
            Drawable image = getResources().getDrawable(R.drawable.btn_propiedades);
            v.setBackground(image);
        }
    }

    public void mostrarProfesionales(View v) {
        Intent skipIntent = new Intent(v.getContext(), SeleccionarProfesionalActivity.class);
        skipIntent.putExtra(LoginActivity.KEY_USUARIO, usuarioDTO);
        v.getContext().startActivity(skipIntent);
    }
}
