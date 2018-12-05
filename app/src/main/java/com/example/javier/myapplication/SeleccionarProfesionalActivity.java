package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.javier.myapplication.models.ProfesionalDTO;
import com.example.javier.myapplication.models.UsuarioDTO;
import com.example.javier.myapplication.view.adapters.ProfesionalesAdapter;
import com.example.javier.myapplication.view.adapters.PropiedadesAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class SeleccionarProfesionalActivity extends AppCompatActivity {

    public static final String KEY_PROFESIONAL = "profesional";
    UsuarioDTO usuarioDTO;
    String direccion;
    String localidad;
    String descripcion;

    private ArrayList<ProfesionalDTO> list;
    ProfesionalDTO selectedProfesional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seleccionar_profesional);

        Intent intent = getIntent();
        usuarioDTO = (UsuarioDTO) intent.getSerializableExtra(LoginActivity.KEY_USUARIO);
        direccion = intent.getStringExtra(PropiedadesAdapter.KEY_DIRECCION);
        localidad = intent.getStringExtra(PropiedadesAdapter.KEY_LOCALIDAD);
        descripcion = intent.getStringExtra(PropiedadesAdapter.KEY_DESCRIPCION);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/gisha.ttf");
        TextView tvProfesional = findViewById(R.id.lblProfesional);
        tvProfesional.setTypeface(face);

        final ListView lista = findViewById(R.id.listViewProfesionales);
        populateList();
        ProfesionalesAdapter adapter = new ProfesionalesAdapter(this, list);
        lista.setAdapter(adapter);

        ImageView imageBig = findViewById(R.id.imageViewBig2);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    //Rellenamos la lista con varias filas de ejemplo
    private void populateList() {
        list = new ArrayList<ProfesionalDTO>();

        list.add(new ProfesionalDTO("ARQ. LUCIANA BELARDO"));
        list.add(new ProfesionalDTO("ARQ. PABLO PARRONDO"));
        list.add(new ProfesionalDTO("ARQ. LUCIANA BELARDO"));
        list.add(new ProfesionalDTO("ARQ. PABLO PARRONDO"));
        list.add(new ProfesionalDTO("ARQ. LUCIANA BELARDO"));       //5 elementos como maximo VER
        /*list.add(new ProfesionalDTO("ARQ. PABLO PARRONDO"));
        list.add(new ProfesionalDTO("ARQ. LUCIANA BELARDO"));
        list.add(new ProfesionalDTO("ARQ. PABLO PARRONDO"));
        list.add(new ProfesionalDTO("ARQ. LUCIANA BELARDO"));
        list.add(new ProfesionalDTO("ARQ. PABLO PARRONDO"));*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        final ListView lista = findViewById(R.id.listViewProfesionales);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                for (int j=0;j<lista.getChildCount();j++){
                    //lista.getChildAt(j).setBackgroundColor(getResources().getColor(R.color.arqColor));
                    LinearLayout linearLayoutProf = (LinearLayout)lista.getChildAt(j);
                    TextView txtProfesional = (TextView)linearLayoutProf.getChildAt(0);
                    txtProfesional.setBackgroundColor(getResources().getColor(R.color.arqColor));
                    //txtProfesional.setTextColor(Color.WHITE);
                }
                selectedProfesional = list.get(i);
                //view.setBackgroundColor(getResources().getColor(R.color.correoColor));
                LinearLayout linearLayoutProf = (LinearLayout)lista.getChildAt(i);
                TextView txtProfesional = (TextView)linearLayoutProf.getChildAt(0);
                txtProfesional.setBackgroundColor(getResources().getColor(R.color.correoColor));
                //txtProfesional.setTextColor(Color.WHITE);
            }
        });

        Button btnSiguiente = findViewById(R.id.btnSiguiente7);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipIntent = new Intent(v.getContext(), SeleccionarFechaActivity.class);
                skipIntent.putExtra(KEY_PROFESIONAL, selectedProfesional);
                skipIntent.putExtra(PropiedadesAdapter.KEY_DIRECCION, direccion);
                skipIntent.putExtra(PropiedadesAdapter.KEY_LOCALIDAD, localidad);
                skipIntent.putExtra(PropiedadesAdapter.KEY_DESCRIPCION, descripcion);
                skipIntent.putExtra(LoginActivity.KEY_USUARIO, usuarioDTO);
                v.getContext().startActivity(skipIntent);
            }
        });
    }
}
