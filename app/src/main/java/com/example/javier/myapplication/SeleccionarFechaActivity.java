package com.example.javier.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javier.myapplication.models.ProfesionalDTO;
import com.example.javier.myapplication.models.UsuarioDTO;

import java.util.Calendar;

public class SeleccionarFechaActivity extends AppCompatActivity {

    ProfesionalDTO profesional;
    UsuarioDTO usuarioDTO;
    /*@BindView(R.id.calendarView)
    MaterialCalendarView widget;*/

    Calendar selectedCalendar = Calendar.getInstance();

    public static final String KEY_FECHA = "fecha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seleccionar_fecha);

        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.fondo),size.x,size.y,true);
        ImageView iv_background = findViewById(R.id.imageView6);
        iv_background.setImageBitmap(bmp);

        Intent intent = getIntent();
        profesional = (ProfesionalDTO) intent.getSerializableExtra(SeleccionarProfesionalActivity.KEY_PROFESIONAL);
        usuarioDTO = (UsuarioDTO) intent.getSerializableExtra(LoginActivity.KEY_USUARIO);

        TextView txtProfesional = findViewById(R.id.lblProfesional);
        txtProfesional.setText(profesional.getNombre());

        CalendarView calendarView = findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                selectedCalendar.set(year, month, dayOfMonth);
            }
        });

        Button btnSiguiente = findViewById(R.id.btnSiguiente8);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedCalendar!=null) {
                    Intent skipIntent = new Intent(view.getContext(), SeleccionarTurnoActivity.class);
                    skipIntent.putExtra(SeleccionarProfesionalActivity.KEY_PROFESIONAL, profesional);
                    skipIntent.putExtra(KEY_FECHA, selectedCalendar);
                    skipIntent.putExtra(LoginActivity.KEY_USUARIO, usuarioDTO);
                    view.getContext().startActivity(skipIntent);
                }
            }
        });

        /*ButterKnife.bind(this);

        // Add a decorator to disable prime numbered days
        widget.addDecorator(new PrimeDayDisableDecorator());
        // Add a second decorator that explicitly enables days <= 10. This will work because
        // decorators are applied in order, and the system allows re-enabling
        widget.addDecorator(new EnableOneToTenDecorator());

        final LocalDate calendar = LocalDate.now();
        widget.setSelectedDate(calendar);

        final LocalDate min = LocalDate.of(calendar.getYear(), Month.JANUARY, 1);
        final LocalDate max = LocalDate.of(calendar.getYear() + 1, Month.OCTOBER, 31);

        widget.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();*/

        ImageView imageBig = findViewById(R.id.imageViewBig2);
        imageBig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.bigdiseno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
/*
    private static class PrimeDayDisableDecorator implements DayViewDecorator {

        @Override public boolean shouldDecorate(final CalendarDay day) {
            return PRIME_TABLE[day.getDay()];
        }

        @Override public void decorate(final DayViewFacade view) {
            view.setDaysDisabled(true);
        }

        private static boolean[] PRIME_TABLE = {
                false,  // 0?
                false,
                true, // 2
                true, // 3
                false,
                true, // 5
                false,
                true, // 7
                false,
                false,
                false,
                true, // 11
                false,
                true, // 13
                false,
                false,
                false,
                true, // 17
                false,
                true, // 19
                false,
                false,
                false,
                true, // 23
                false,
                false,
                false,
                false,
                false,
                true, // 29
                false,
                true, // 31
                false,
                false,
                false, //PADDING
        };
    }

    private static class EnableOneToTenDecorator implements DayViewDecorator {

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.getDay() <= 10;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(false);
        }
    }*/
}
