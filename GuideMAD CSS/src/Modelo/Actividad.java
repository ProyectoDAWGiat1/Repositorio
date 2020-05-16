/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;
import java.sql.Blob;
import java.time.*;
//import java.time.format.DateTimeFormatter;

/**
 *
 * @author Cosmin
 */
public class Actividad {
    private int idActividad;
    private String nombre;
    private String descripcion;
    private String url;
    private Blob imagen;
    private int idSubtipo;
    //? private double precio;


    public Actividad() {
    }

    public Actividad(String nombre) {
        this.nombre = nombre;
    }
    
    public Actividad(int idActividad, String nombre, int idSubtipo) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.idSubtipo = idSubtipo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdSubtipo() {
        return idSubtipo;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public void setIdSubtipo(int idSubtipo) {
        this.idSubtipo = idSubtipo;
    }

    public String getUrl() {
        return url;
    }

    public Blob getImagen() {
        return imagen;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
