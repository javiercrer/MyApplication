package com.example.javier.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javier.myapplication.view.adapters.VisitasAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarioVisitasActivity extends AppCompatActivity {

    private ArrayList<HashMap> list;
    private HashMap turnoViewSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.calendario_visitas);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        final ListView listview = (ListView) findViewById(R.id.listViewVisitas);
        populateList();
        VisitasAdapter adapter = new VisitasAdapter(this, list);
        listview.setAdapter(adapter);

        //EL LIST VIEW NO CUENTA EN ESTE MOMENTO CON LOS HIJOS POR LO QUE HABRIA QUE ESTUDIAR QUE EVENTO YA TIENE CARGADA LA LISTA
        /*for (int j=0;j<listview.getCount();j++){
            listview.getChildAt(j).setBackgroundColor(Color.WHITE);
            ViewGroup row = (ViewGroup)listview.getChildAt(j);
            for (int k=0;k<row.getChildCount();k++) {
                ((TextView)row.getChildAt(k)).setTextColor(Color.GRAY);
            }
        }*/

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                for (int j=0;j<listview.getChildCount();j++){
                    LinearLayout linearLayout = ((LinearLayout)listview.getChildAt(j));
                    linearLayout.setBackgroundColor(Color.WHITE);
                    for (int k=0;k<linearLayout.getChildCount();k++) {
                        ((TextView)linearLayout.getChildAt(k)).setTextColor(Color.GRAY);
                    }
                }
                view.setBackgroundColor(getResources().getColor(R.color.turnoSeleccionado));
                LinearLayout row = ((LinearLayout)listview.getChildAt(i));
                for (int k=0;k<row.getChildCount();k++) {
                    ((TextView)row.getChildAt(k)).setTextColor(Color.WHITE);
                }
                turnoViewSelected = list.get(i);
            }
        });

        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarTurno(view);
            }
        });
    }

    public void cancelarTurno(View view) {
        if(turnoViewSelected!=null) {
            if(turnoViewSelected.get("COLUMNA_4").equals("Confirmado")) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                //alertDialog.setTitle("Turno seleccionado");
                alertDialog.setMessage("Su turno ya se encuentra confirmado");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } else if(turnoViewSelected.get("COLUMNA_4").equals("Cancelado")) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                //alertDialog.setTitle("Turno seleccionado");
                alertDialog.setMessage("Su turno ya se encuentra cancelado");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } else if(turnoViewSelected.get("COLUMNA_4").equals("Solicitado")) {
                /*CancelDialog viewCancel = new CancelDialog();
                viewCancel.show*/

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarioVisitasActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view,null);

                // Specify alert dialog is not cancelable/not ignorable
                builder.setCancelable(false);

                // Set the custom layout as alert dialog view
                builder.setView(dialogView);

                // Get the custom alert dialog view widgets reference
                Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);

                // Create the alert dialog
                final AlertDialog dialog = builder.create();

                // Set positive/yes button click listener
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog
                        dialog.cancel();
                        Toast.makeText(getApplication(),
                                "Aceptar", Toast.LENGTH_SHORT).show();
                    }
                });

                // Set negative/no button click listener
                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        dialog.dismiss();
                        Toast.makeText(getApplication(),
                                "Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });

                // Display the custom alert dialog on interface
                dialog.show();
            }
        }
    }

    //Rellenamos la lista con varias filas de ejemplo
    private void populateList() {
        list = new ArrayList<HashMap>();

        HashMap temp = new HashMap();
        temp.put("COLUMNA_1","24/10/18");
        temp.put("COLUMNA_2", "14:25");
        temp.put("COLUMNA_3", "Julio Asaland");
        temp.put("COLUMNA_4", "Confirmado");
        list.add(temp);

        HashMap temp1 = new HashMap();
        temp1.put("COLUMNA_1","10/11/18");
        temp1.put("COLUMNA_2", "10:15");
        temp1.put("COLUMNA_3", "Rodolfo Lomens");
        temp1.put("COLUMNA_4", "Solicitado");
        list.add(temp1);

        HashMap temp2 = new HashMap();
        temp2.put("COLUMNA_1","13/11/18");
        temp2.put("COLUMNA_2", "11:20");
        temp2.put("COLUMNA_3", "Mario Malassi");
        temp2.put("COLUMNA_4", "Cancelado");
        list.add(temp2);

        HashMap temp3 = new HashMap();
        temp3.put("COLUMNA_1","13/11/18");
        temp3.put("COLUMNA_2", "11:20");
        temp3.put("COLUMNA_3", "Mario Malassi");
        temp3.put("COLUMNA_4", "Confirmado");
        list.add(temp3);

        HashMap temp4 = new HashMap();
        temp4.put("COLUMNA_1","13/11/18");
        temp4.put("COLUMNA_2", "11:20");
        temp4.put("COLUMNA_3", "Mario Malassi");
        temp4.put("COLUMNA_4", "Confirmado");
        list.add(temp4);

        HashMap temp5 = new HashMap();
        temp5.put("COLUMNA_1","13/11/18");
        temp5.put("COLUMNA_2", "11:20");
        temp5.put("COLUMNA_3", "Mario Malassi");
        temp5.put("COLUMNA_4", "Confirmado");
        list.add(temp5);

        HashMap temp6 = new HashMap();
        temp6.put("COLUMNA_1","13/11/18");
        temp6.put("COLUMNA_2", "11:20");
        temp6.put("COLUMNA_3", "Mario Malassi");
        temp6.put("COLUMNA_4", "Confirmado");
        list.add(temp6);

        HashMap temp7 = new HashMap();
        temp7.put("COLUMNA_1","13/11/18");
        temp7.put("COLUMNA_2", "11:20");
        temp7.put("COLUMNA_3", "Mario Malassi");
        temp7.put("COLUMNA_4", "Confirmado");
        list.add(temp7);

        HashMap temp8 = new HashMap();
        temp8.put("COLUMNA_1","13/11/18");
        temp8.put("COLUMNA_2", "11:20");
        temp8.put("COLUMNA_3", "Mario Malassi");
        temp8.put("COLUMNA_4", "Confirmado");
        list.add(temp8);
    }
}
