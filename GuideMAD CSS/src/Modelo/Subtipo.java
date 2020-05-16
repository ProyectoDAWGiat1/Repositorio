/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Cosmin
 */
public class Subtipo {
    private int idSubtipo;
    private String nombre;
    private int idTipo;

    public int getIdSubtipo() {
        return idSubtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdSubtipo(int idSubtipo) {
        this.idSubtipo = idSubtipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    @Override
    public String toString(){
        return "ID Tipo: "+idTipo+" - "+nombre+" - ID Subtipo: "+idSubtipo;
    }
    
    
}
