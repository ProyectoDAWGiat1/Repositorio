/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cosmin
 */
public class ConexionGeneral {

    public Connection conn;

    public ConexionGeneral(){
        try{
            conectar();
        } catch (SQLException e){
            System.out.println("Error.");
        }
    }
    
    public Connection getConn() {
        return conn;
    }

    public void conectar() throws SQLException {
        String urlBD = "jdbc:mysql://localhost:3306/guidemad?serverTimezone=UTC";
        conn = DriverManager.getConnection(urlBD, "root", "root");
    }
}
