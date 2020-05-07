/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cosmin
 */
public class Pack {
    private int idPack;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private Map<Integer,Actividad> actividades;
    private int cantidadAct;

    public Pack(){
        actividades = new HashMap<>();
        cantidadAct = 0;
    }
    
    public void introducirActividad(Actividad act){
        for (int idAct : actividades.keySet()) {
            if(act.getIdActividad() != idAct){
                cantidadAct++;
                actividades.put(cantidadAct,act);
            }
        }
    }
    
    public void eliminarActividad(int idAct){
        for (int idActividad : actividades.keySet()) {
            if(idAct == idActividad){
                actividades.remove(idAct);
            }
        }
    }
    
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdPack() {
        return idPack;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
