package com.example.javier.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javier.myapplication.models.PropiedadDTO;

public class DetallePropiedadActivity extends AppCompatActivity {

    public static final String KEY_DIRECCION = "domicilio";
    public static final String KEY_LOCALIDAD = "localidad";
    public static final String KEY_DESCRIPCION = "descripcion";

    PropiedadDTO propiedadDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedad);

        propiedadDTO = new PropiedadDTO();
        propiedadDTO.setDireccion("Blanque 1410");
        propiedadDTO.setLocalidad("Rosario");
        propiedadDTO.setDescripcion("La mansion blanque");

        TextView direccion = (TextView) findViewById(R.id.itemDireccion);
        direccion.setText(propiedadDTO.getDireccion());
        TextView localidad = (TextView) findViewById(R.id.itemLocalidad);
        localidad.setText(propiedadDTO.getLocalidad());
        TextView descripcion = (TextView) findViewById(R.id.itemDescripcion);
        descripcion.setText(propiedadDTO.getDescripcion());

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutPropiedad);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent skipIntent = new Intent(v.getContext(), SeleccionarProfesionalActivity.class);
            skipIntent.putExtra(KEY_DIRECCION, propiedadDTO.getDireccion());
            skipIntent.putExtra(KEY_LOCALIDAD, propiedadDTO.getLocalidad());
            skipIntent.putExtra(KEY_DESCRIPCION, propiedadDTO.getDescripcion());
            v.getContext().startActivity(skipIntent);
            }
        });
    }
}
