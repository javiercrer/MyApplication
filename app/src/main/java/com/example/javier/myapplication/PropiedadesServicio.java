package com.example.javier.myapplication;

import com.example.javier.myapplication.models.PropiedadDTO;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface PropiedadesServicio {

    @GET("/propiedades")
    void getPelicula(Callback<List<PropiedadDTO>> callback);
}
