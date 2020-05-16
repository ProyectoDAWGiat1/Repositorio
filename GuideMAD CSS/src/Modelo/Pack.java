/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DatosBDA.PackDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Cosmin
 */
public class Pack {

    private PackDAO gestionPack = new PackDAO();
    private int cantidadPacks = 0;

    private int idPack;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private Set<Actividad> actividades;
    private Set<Detallepack> dp;

    public Pack() {
        actividades = new HashSet<>();
        try{
            setCantidadPacks();
        } catch (SQLException e){
            System.out.println("Error");
        }
        idPack = cantidadPacks;
        //solo para probar de momento
        fecha = LocalDate.now();
        dp = new HashSet<>();
    }

    public void setCantidadPacks() throws SQLException{
        cantidadPacks = gestionPack.getCantPacks() + 1;
    }
//    public Detallepack getDp() {
//        return dp;
//    }
    
    public Set<Detallepack> getDPs(){
        return this.dp;
    }
    
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

//    public void setActividades(Set<Actividad> actividades) {
//        this.actividades = dp.getSetActs();
//    }
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

//    public void addDetallePack(Detallepack dp){
//        if(dp.get)
//    }
    public String getActividadesString() {
        String actividades = "";
        for (Actividad act : this.actividades) {
            actividades += act + "\n";
        }

        return actividades;
    }

    public void insertarDP(Detallepack dp) {
        this.dp.add(dp);
    }

    public String getDP() {
        String dpacks = "";
        for (Detallepack detallepack : dp) {
            dpacks += detallepack;

        }

        return dpacks;
    }
    
    public double precioPack(int idPack) throws SQLException {
        double precioPack = 0;
        
        precioPack = gestionPack.importePack(idPack);
        
        return precioPack;
    }
    
    
//    public void introducirActividad(Actividad act) {
//        actividades.add(act);
//        
//        for (Actividad actividad : actividades) {
//            if (actividad.getIdActividad() != act.getIdActividad()) {
//                actividades.add(act);
//            }
//        }
//    }
//
//    public void eliminarActividad(int idAct) {
//        if (!actividades.isEmpty()) {
//            for (Actividad act : actividades) {
//                if (idAct == act.getIdActividad()) {
//                    actividades.remove(idAct);
//                }
//            }
//        }
//    }
}
