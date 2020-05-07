/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;
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
    private Image imagen;
    private int idSubtipo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private LocalTime duracion;
    private int plazas;
//    private DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;

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

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public void setIdSubtipo(int idSubtipo) {
        this.idSubtipo = idSubtipo;
    }

    public String getUrl() {
        return url;
    }

    public Image getImagen() {
        return imagen;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) throws ErrorFecha {
        if(fechaInicio.isAfter(this.fechaFinal)){
            throw new ErrorFecha("No puede ponerse una fecha inicial superior a la final.");
        } else {
            this.fechaInicio = fechaInicio;
        }
    }

    public void setFechaFinal(LocalDateTime fechaFinal) throws ErrorFecha{
        if(fechaFinal.isBefore(this.fechaInicio)){
            throw new ErrorFecha();
        } else {
            this.fechaFinal = fechaFinal;
        }
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    private static class ErrorFecha extends Exception {

        public ErrorFecha() {
            super("No puede ser la fecha final inferior a la inicial");
        }
        
        public ErrorFecha(String error){
            super(error);
        }
    }
    
    
}
