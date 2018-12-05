package com.example.javier.myapplication.models;

import java.io.Serializable;

public class ProfesionalDTO implements Serializable {
    private int id;
    private String nombre;

    public ProfesionalDTO() {

    }

    public ProfesionalDTO(String nom) {
        this.nombre = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
