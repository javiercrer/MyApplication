package com.example.javier.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.javier.myapplication.models.PropiedadDTO;
import com.example.javier.myapplication.view.adapters.PropiedadesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PropiedadesActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://192.168.0.62:80/bigdiseno/apis/public/propiedades";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PropiedadDTO> propiedadesList;

    //private TextView salidaRetrofix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedades);

        ImageView imageViewInicio = findViewById(R.id.imageViewInicio);
        imageViewInicio.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InstitutoActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        propiedadesList = new ArrayList<>();

        //loadUrlData();
        loadMockData();

        //https://www.youtube.com/watch?v=yQQSMbAFx3o

        /*salidaRetrofix = (TextView) findViewById(R.id.txtPruebaRetrofit);

        //retrofit adapter
        Retrofit retrofitAdapter = new Retrofit.Builder().baseUrl("http://localhost/bigdiseno/apis/public/").build();

                //.addConverterFactory(GsonConverterFactory.create()).build();

        PropiedadesServicio servicioProp = retrofitAdapter.create(PropiedadesServicio.class);
        servicioProp.getPelicula(new Callback<List<PropiedadDTO>>() {
            @Override
            public void onResponse(Call<List<PropiedadDTO>> call, Response<List<PropiedadDTO>> response) {
                if (response.isSuccessful()) {
                    List<PropiedadDTO> colPeliculas = response.body();
                    salidaRetrofix.setText(colPeliculas.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PropiedadDTO>> call, Throwable throwable) {
                salidaRetrofix.setText(throwable.getLocalizedMessage());
            }
        });*/

        //TODO:la = getAdapter();
        /*setListAdapter(la);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        */
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                registerForContextMenu(parent);
                view.showContextMenu();

                Cursor cursorProp = (Cursor) la.getItem(position);
                Integer idProp = cursorProp.getInt(cursorProp.getColumnIndexOrThrow("id"));
                String direccion = cursorProp.getString(cursorProp.getColumnIndexOrThrow("Direccion"));
                String localidad = cursorProp.getString(cursorProp.getColumnIndexOrThrow("Localidad"));
                String descripcion = cursorProp.getString(cursorProp.getColumnIndexOrThrow("descripcion"));
                Toast.makeText(getApplicationContext(), "Selected Id=" + idProp +
                        ", direccion=" + direccion +
                        ", localidad=" + localidad +
                        ", descripcion=" + descripcion, Toast.LENGTH_LONG).show();
            }
        });*/

        //registerForContextMenu(lv);
    }

    private void loadMockData() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setDireccion("Blanque 1410");
        propiedadDTO.setLocalidad("Rosario");
        propiedadDTO.setDescripcion("La mansion blanque");

        propiedadesList.add(propiedadDTO);

        PropiedadDTO propiedadDTO2 = new PropiedadDTO();
        propiedadDTO2.setDireccion("Valparaiso 1742");
        propiedadDTO2.setLocalidad("Rosario");
        propiedadDTO2.setDescripcion("Edificio");

        propiedadesList.add(propiedadDTO2);

        adapter = new PropiedadesAdapter(propiedadesList, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        //CursorAdapter la = getAdapter();
        //la.changeCursor(libros.crearCursor());
    }*/

    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        PropiedadDTO propiedadDTO = new PropiedadDTO();
                        propiedadDTO.setDireccion(jo.getString("direccion"));
                        propiedadDTO.setLocalidad(jo.getString("localidad"));
                        propiedadDTO.setDescripcion(jo.getString("descripcion"));

                        propiedadesList.add(propiedadDTO);

                    }

                    adapter = new PropiedadesAdapter(propiedadesList, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PropiedadesActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
