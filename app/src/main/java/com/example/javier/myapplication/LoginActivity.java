package com.example.javier.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javier.myapplication.models.UsuarioDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_USUARIO = "usuario";

    private UsuarioDTO usuarioDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = findViewById(R.id.imageView);
        iv_background.setImageBitmap(bmp);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/gisha.ttf");

        TextView tvNombre = findViewById(R.id.lblNombre);
        tvNombre.setTypeface(face);

        TextView tvEmail = findViewById(R.id.lblEmail);
        tvEmail.setTypeface(face);

        TextView tvTelefono = findViewById(R.id.lblTelefono);
        tvTelefono.setTypeface(face);

        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setTypeface(face);

        ImageView imageBig = findViewById(R.id.imageViewBig);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void continuar(View view){

        //TODO: Agregar validaciones de campos
        //TODO: revisar si ese mail ya esta siendo utilizado en el servicio

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        EditText txtEmail = findViewById(R.id.txtEmail);
        Matcher mather = pattern.matcher(txtEmail.getText().toString());

        if (mather.find() == true) {
            usuarioDTO = new UsuarioDTO();

            EditText txtNombre = findViewById(R.id.txtNombre);
            usuarioDTO.setNombreApellido(txtNombre.getText().toString());

            usuarioDTO.setEmail(txtEmail.getText().toString());

            EditText txtTelefono = findViewById(R.id.txtTelefono);
            usuarioDTO.setTelefono(txtTelefono.getText().toString());

            Intent i = new Intent(this, InstitutoActivity.class);
            i.putExtra(KEY_USUARIO, usuarioDTO);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "El email ingresado es inválido.", Toast.LENGTH_LONG).show();
        }
    }
}
