package com.example.javier.myapplication.models;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    String nombreApellido;
    String Email;
    String telefono;

    public UsuarioDTO() {

    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
