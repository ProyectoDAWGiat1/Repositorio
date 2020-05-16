/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import Modelo.Pack;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cosmin
 */
public class PackDAO {
    
    public ConexionGeneral cg = new ConexionGeneral();
    private Connection conexion = cg.getConn();
    
    public PackDAO(){
        try{
            cg.conectar();
        } catch (SQLException e){
            System.out.println("Error conexion");
        }
    }
    
    public int insertarPack(Pack pack) throws SQLException {
        String sql = "INSERT INTO "
                + "packs (idPack,nombre,descripcion,fecha)"
                + "VALUES (?,?,?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1,pack.getIdPack());
        ps.setString(2,pack.getNombre());
        ps.setString(3, pack.getDescripcion());
        ps.setDate(4, Date.valueOf(pack.getFecha()));
     
        int i = ps.executeUpdate();

        return i;
        
    }
    
    private int eliminarPack(int idPack) throws SQLException {
        String sql = "DELETE FROM packs "+
                "WHERE idPack = ?";
        
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setInt(1,idPack);
        
        int i = ps.executeUpdate();
        
        return i;
    }
    
    private void buscarPack(int idPack){
        
    }
 
    
    public double importePack(int idPack) throws SQLException {
        String sql = "SELECT importePack(?);";
        double precioPack = 0;    
        PreparedStatement ps = ps = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;
        
        ps.setInt(1,idPack);
        
        rs = ps.executeQuery();
        
        
        
        while (rs.next()){
            precioPack = (rs.getDouble("importePack("+idPack+")"));
        }
        
        return precioPack;
    }
    
    public int getCantPacks() throws SQLException{
        String sql = "SELECT COUNT(*) FROM packs;";
        int cantPacks = 0;
        PreparedStatement ps = ps = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs;
        
        rs = ps.executeQuery();
        
        while (rs.next()){
            cantPacks = (rs.getInt("COUNT(*)"));
        }
        
        return cantPacks;
    }   
}
