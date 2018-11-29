package com.example.javier.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javier.myapplication.models.PropiedadDTO;

import java.util.List;

public class PropiedadesAdapter extends RecyclerView.Adapter<PropiedadesAdapter.ViewHolder> {

    private List<PropiedadDTO> propiedadesList;
    private Context context;

    public static final String KEY_DIRECCION = "domicilio";
    public static final String KEY_LOCALIDAD = "localidad";
    public static final String KEY_DESCRIPCION = "descripcion";

    public PropiedadesAdapter(List<PropiedadDTO> propiedadesList, Context context) {
        this.propiedadesList = propiedadesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.propiedades_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final PropiedadDTO propiedadDTO = propiedadesList.get(position);
        holder.direccion.setText(propiedadDTO.getDireccion());
        holder.localidad.setText(propiedadDTO.getLocalidad());
        holder.descripcion.setText(propiedadDTO.getDescripcion());

        /*Picasso.with(context)
                .load(developersList.getAvatar_url())
                .into(holder.avatar_url);*/

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropiedadDTO propiedadDTO = propiedadesList.get(position);
                Intent skipIntent = new Intent(v.getContext(), SeleccionarProfesionalActivity.class);
                skipIntent.putExtra(KEY_DIRECCION, propiedadDTO.getDireccion());
                skipIntent.putExtra(KEY_LOCALIDAD, propiedadDTO.getLocalidad());
                skipIntent.putExtra(KEY_DESCRIPCION, propiedadDTO.getDescripcion());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return propiedadesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        public TextView direccion;
        public TextView localidad;
        public TextView descripcion;
        public LinearLayout linearLayout;

        //public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects

            direccion = (TextView) itemView.findViewById(R.id.itemDireccion);
            localidad = (TextView) itemView.findViewById(R.id.itemLocalidad);
            descripcion = (TextView) itemView.findViewById(R.id.itemDescripcion);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }

    }
}
