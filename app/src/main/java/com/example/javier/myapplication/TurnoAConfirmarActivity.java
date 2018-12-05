package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javier.myapplication.models.ProfesionalDTO;
import com.example.javier.myapplication.models.UsuarioDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TurnoAConfirmarActivity extends AppCompatActivity {

    ProfesionalDTO profesional;
    UsuarioDTO usuarioDTO;
    Calendar selectedCalendar;
    String selectedAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.turno_aconfirmar);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        Intent intent = getIntent();
        profesional = (ProfesionalDTO) intent.getSerializableExtra(SeleccionarProfesionalActivity.KEY_PROFESIONAL);
        TextView txtProfesional = (TextView) findViewById(R.id.lblProfesional7);
        txtProfesional.setText(profesional.getNombre());

        usuarioDTO = (UsuarioDTO) intent.getSerializableExtra(LoginActivity.KEY_USUARIO);

        selectedCalendar = (Calendar) intent.getSerializableExtra(SeleccionarFechaActivity.KEY_FECHA);
        selectedAppointment = intent.getStringExtra(SeleccionarTurnoActivity.KEY_TURNO);

        TextView txtTurno = (TextView) findViewById( R.id.lblTurno);
        txtTurno.setTextColor(Color.WHITE);
        txtTurno.setGravity(Gravity.CENTER);
        txtTurno.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26.f);

        SimpleDateFormat fmt_dia = new SimpleDateFormat("EEEE dd");
        SimpleDateFormat fmt_mesAño = new SimpleDateFormat("MMMM yyyy");

        String strDia = fmt_dia.format(selectedCalendar.getTime());
        strDia = strDia.substring(0, 1).toUpperCase() + strDia.substring(1);
        String strMesAño = fmt_mesAño.format(selectedCalendar.getTime());
        strMesAño = strMesAño.substring(0, 1).toUpperCase() + strMesAño.substring(1);
        txtTurno.setText(Html.fromHtml(strDia + " <br/> " + strMesAño + " <br/> " + selectedAppointment + " hs."));

        TextView txtCorreo = (TextView) findViewById( R.id.lblCorreo);
        txtCorreo.setText( Html.fromHtml( getString(R.string.correo) ) );
        txtCorreo.setTextColor(Color.WHITE);
        txtCorreo.setGravity(Gravity.CENTER);

        ImageView imageBig = (ImageView) findViewById(R.id.imageViewBig2);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skipIntent = new Intent(view.getContext(), GraciasActivity.class);
                view.getContext().startActivity(skipIntent);
            }
        });
    }
}
