    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import Modelo.Detallepack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Cosmin
 */
public class DetallepackDAO {
    public ConexionGeneral cg = new ConexionGeneral();
    private Connection conexion = cg.getConn();
    
    
    public DetallepackDAO(){
        try{
            cg.conectar();
        } catch (SQLException e){
            System.out.println("Error conexion");
        }
    }
    
    public int insertarDP(Detallepack dp) throws SQLException{
        String sql = "INSERT INTO detallepacks (idPack, numLinea, idActividad, precio, fechaInicio, fechaFinal, numPlazas)"+
                " VALUES (?,?,?,?,?,?,?);";
        String idPack = "SELECT idPack FROM packs WHERE idPack = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
       
        
        ps.setInt(1,dp.getIdPack());
        ps.setInt(2,dp.getNumLinea());
        ps.setInt(3,dp.getActividad(dp.getNumLinea()).getIdActividad());
        ps.setDouble(4, dp.getPrecio());
        Timestamp fechaInicio = Timestamp.valueOf(dp.getFechaInicio());
        Timestamp fechaFinal = Timestamp.valueOf(dp.getFechaInicio());
        ps.setTimestamp(5,fechaInicio);
        ps.setTimestamp(6,fechaFinal);
        ps.setInt(7,dp.getPlazas());
        
        int i = ps.executeUpdate();
        
        return i;
    }
    //no se si deberia ser necesario o mediante el borrado en cascada de pack funcionaria ya.
//    public void eliminarDP(){
//        
//    }
}
