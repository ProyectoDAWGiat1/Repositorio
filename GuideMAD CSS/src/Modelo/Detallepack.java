package Modelo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Detallepack {

    private int idPack;
    private int numLinea;
//  private Set<Actividad> actividades;
    private Map<Integer, Actividad> actividades;
    private double precio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    //duracion la calcuraremos nosotros restando la hora de una visita con la otra.
//    private LocalTime duracion;
    private int numPlazas;
    private DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
    private Pack pack;

    public Detallepack() {
        actividades = new HashMap<>();
        numLinea = 0;
        

    }

    public void setPack(Pack p1) {
        this.pack = p1;;
    }

    public void setIdPack(){
        this.idPack = pack.getIdPack();
    }
    
    public int getIdPack(){
        return this.idPack;
    }
    
    public int getNumLinea(){
        return numLinea;
    }
    
    public void setNumLinea(int num){
        this.numLinea = num;
    }
    
    public void setFechas(LocalDateTime fechaIni, LocalDateTime fechaFin){
        fechaInicio = fechaIni;
        fechaFinal = fechaFin;
    }
    
    public void comprobarFechaInicio(LocalDateTime fechaInicio) throws ErrorFecha {

        if (fechaFinal != null) {
            if (fechaInicio.isAfter(this.fechaFinal)) {
                throw new ErrorFecha("No puede ponerse una fecha inicial superior a la final.");
            } else {
                this.fechaInicio = fechaInicio;
            }
        }
    }

//    public int numDias(){
//        
//    }
    
    public void comprobarFechaFinal(LocalDateTime fechaFinal) throws ErrorFecha {
        if (fechaInicio != null) {
            if (fechaFinal.isBefore(this.fechaInicio)) {
                throw new ErrorFecha();
            } else {
                this.fechaFinal = fechaFinal;
            }
        }
        this.fechaFinal = fechaFinal;
    }

//    public void setDuracion(LocalTime duracion) {
//        this.duracion = duracion;
//    }
    public void setPlazas(int plazas) {
        this.numPlazas = plazas;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

//    public LocalTime getDuracion() {
//        return duracion;
//    }
    public int getPlazas() {
        return numPlazas;
    }

    public void crearFilasDP(Actividad act, int numPlazas, double precio) throws ErrorFecha {
//        for (int i : actividades.keySet()) {
//            if (act.getIdActividad() != i) {
//                numLinea++;
//                actividades.put(numLinea, act);
//            }
//        }
        numLinea++;
        actividades.put(numLinea, act);
        this.numPlazas = numPlazas;
        this.precio = precio;
    }

    public Map<Integer, Actividad> getMapActs() {
        return actividades;
    }
    
    public Actividad getActividad(int numLinea){
        Actividad act = null;
        for (Integer integer : actividades.keySet()) {
            if(numLinea == integer){
                act = actividades.get(numLinea);
            }
        }
        
        return act;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public String getActs() {
        String acts = "";

        for (Actividad act : actividades.values()) {
            acts += act + "\n";
        }

        return acts;
    }
    
    @Override
    
    public String toString(){
        return idPack+" "+numLinea+" "+actividades.get(numLinea)+" "+fechaInicio.toString()+" "+fechaFinal.toString()+" "+numPlazas+" "+precio+"\n";
    }
}
