package com.example.javier.myapplication.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.javier.myapplication.R;
import com.example.javier.myapplication.models.ProfesionalDTO;

import java.util.ArrayList;

public class ProfesionalesAdapter extends BaseAdapter {

    public ArrayList<ProfesionalDTO> list;
    private LayoutInflater inflater;
    private Activity activity;

    public ProfesionalesAdapter(Activity activity, ArrayList<ProfesionalDTO> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.profesionales_row, null);

        ProfesionalDTO profesionalDTO = list.get(position);

        TextView txtProf = convertView.findViewById(R.id.txtProfesional);

        //Rellenamos los valores de cada columna de la fila
        txtProf.setText(profesionalDTO.getNombre());

        return convertView;
    }

    //TODO: FALTA HACER CLICK EN UN PROFESIONAL

    //TODO: POR otro lado mejorar la vista de las propiedades a como piden
}
