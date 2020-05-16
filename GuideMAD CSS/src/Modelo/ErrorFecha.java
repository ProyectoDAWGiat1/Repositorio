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
public class ErrorFecha extends Exception {

    public ErrorFecha() {
        super("No puede ser la fecha final inferior a la inicial");
    }

    public ErrorFecha(String error) {
        super(error);
    }
}
