/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import Modelo.Actividad;
import Modelo.Subtipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Cosmin
 */
public class SubtipoDAO {
    private ConexionGeneral cg = new ConexionGeneral();
    
    public Set<Subtipo> buscarSubtipos() throws SQLException {
        cg.conectar();
        Set<Subtipo> listaSubtipos = new HashSet<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;
        
        Connection conexion = cg.getConn();
        if(conexion != null){
            consulta = "SELECT * FROM subtipo";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
        
            while (rs.next()){
                Subtipo subtipo = new Subtipo();
                subtipo.setIdSubtipo(rs.getInt("idSubtipo"));
                subtipo.setNombre(rs.getString("nombre"));
                subtipo.setIdTipo(rs.getInt("idTipo"));
                listaSubtipos.add(subtipo);
            }            
        }
        return listaSubtipos;
    }
    
    
}
