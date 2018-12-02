package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javier.myapplication.view.adapters.PropiedadesAdapter;

public class SeleccionarProfesionalActivity extends AppCompatActivity {

    public static final String KEY_PROFESIONAL = "profesional";
    String direccion;
    String localidad;
    String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seleccionar_profesional);

        Intent intent = getIntent();
        direccion = intent.getStringExtra(PropiedadesAdapter.KEY_DIRECCION);
        localidad = intent.getStringExtra(PropiedadesAdapter.KEY_LOCALIDAD);
        descripcion = intent.getStringExtra(PropiedadesAdapter.KEY_DESCRIPCION);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/gisha.ttf");
        TextView tvProfesional = (TextView) findViewById(R.id.lblProfesional);
        tvProfesional.setTypeface(face);

        LinearLayout profesionalesLayout = (LinearLayout) findViewById(R.id.profesionalesLayout);

        int widthButton = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, getResources().getDisplayMetrics());

        Button btnProf1 = new Button(this);
        btnProf1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btnProf1.setText("ARQ. LUCIANA BELARDO");
        btnProf1.setTextColor(getResources().getColor(R.color.colorWhite));
        btnProf1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        ViewGroup.LayoutParams params1 = btnProf1.getLayoutParams();
        params1.width=widthButton;
        btnProf1.setLayoutParams(params1);

        btnProf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipIntent = new Intent(v.getContext(), SeleccionarFechaActivity.class);
                skipIntent.putExtra(KEY_PROFESIONAL, "ARQ. LUCIANA BELARDO");
                skipIntent.putExtra(PropiedadesAdapter.KEY_DIRECCION, direccion);
                skipIntent.putExtra(PropiedadesAdapter.KEY_LOCALIDAD, localidad);
                skipIntent.putExtra(PropiedadesAdapter.KEY_DESCRIPCION, descripcion);
                v.getContext().startActivity(skipIntent);
            }
        });

        //prof1.setId(99);
        profesionalesLayout.addView(btnProf1);


        Button btnProf2 = new Button(this);
        btnProf2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btnProf2.setText("ARQ. PABLO BELARDO");
        btnProf2.setTextColor(getResources().getColor(R.color.colorWhite));
        btnProf2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        //prof1.setId(99);

        ViewGroup.LayoutParams params2 = btnProf2.getLayoutParams();
        params2.width=widthButton;
        btnProf2.setLayoutParams(params2);

        btnProf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipIntent = new Intent(v.getContext(), SeleccionarFechaActivity.class);
                skipIntent.putExtra(KEY_PROFESIONAL, "ARQ. PABLO BELARDO");
                skipIntent.putExtra(PropiedadesAdapter.KEY_DIRECCION, direccion);
                skipIntent.putExtra(PropiedadesAdapter.KEY_LOCALIDAD, localidad);
                skipIntent.putExtra(PropiedadesAdapter.KEY_DESCRIPCION, descripcion);
                v.getContext().startActivity(skipIntent);
            }
        });

        profesionalesLayout.addView(btnProf2);
    }
}
