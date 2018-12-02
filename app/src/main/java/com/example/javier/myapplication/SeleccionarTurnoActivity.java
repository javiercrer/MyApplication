package com.example.javier.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

public class SeleccionarTurnoActivity extends AppCompatActivity {

    String profesional;
    Calendar selectedCalendar;
    String selectedAppointment;
    public static final String KEY_TURNO = "turno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seleccionar_turno);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        Intent intent = getIntent();
        profesional = intent.getStringExtra(SeleccionarProfesionalActivity.KEY_PROFESIONAL);
        selectedCalendar = (Calendar) getIntent().getSerializableExtra(SeleccionarFechaActivity.KEY_FECHA);

        TextView txtProfesional = (TextView) findViewById(R.id.lblProfesional6);
        txtProfesional.setText(profesional);

        final String[] turnos = {"8:15", "8:30", "8:45"};

        final ListView lista = (ListView)findViewById(R.id.listView6);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, turnos);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            for (int j=0;j<lista.getChildCount();j++){
                lista.getChildAt(j).setBackgroundColor(Color.WHITE);
                ((TextView)lista.getChildAt(j)).setTextColor(Color.GRAY);
            }
            selectedAppointment = turnos[i];
            view.setBackgroundColor(getResources().getColor(R.color.turnoSeleccionado));
            ((TextView)lista.getChildAt(i)).setTextColor(Color.WHITE);
            }
        });

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente9);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedAppointment!=null) {
                    /*AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                    alertDialog.setTitle("Turno seleccionado");
                    alertDialog.setMessage(selectedAppointment);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    alertDialog.show();*/
                    Intent skipIntent = new Intent(view.getContext(), TurnoAConfirmarActivity.class);
                    skipIntent.putExtra(SeleccionarProfesionalActivity.KEY_PROFESIONAL, profesional);
                    skipIntent.putExtra(SeleccionarFechaActivity.KEY_FECHA, selectedCalendar);
                    skipIntent.putExtra(KEY_TURNO, selectedAppointment);
                    view.getContext().startActivity(skipIntent);
                }
            }
        });

        ImageView imageBig = (ImageView) findViewById(R.id.imageViewBig2);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
