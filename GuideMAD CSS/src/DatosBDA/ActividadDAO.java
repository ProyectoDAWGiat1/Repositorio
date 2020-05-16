/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import Modelo.Actividad;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Cosmin
 */
public class ActividadDAO {

    public ConexionGeneral cg = new ConexionGeneral();
    private Connection conexion = cg.getConn();

    public ActividadDAO(){
        try{
        cg.conectar();
        } catch (SQLException s){
            System.out.println("Error conexion.");
        }
    }

    public int insertarActividad(Actividad act) throws SQLException {

        String sql = "INSERT INTO "
                + "actividades (idActividad,nombre,descripcion,url,idSubtipo)"
                + "VALUES (?,?,?,?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, Integer.toString(act.getIdActividad()));
        ps.setString(2, act.getNombre());
        ps.setString(3, act.getDescripcion());
        ps.setString(4, act.getUrl());
        ps.setInt(5, act.getIdSubtipo());

        int i = ps.executeUpdate();

        
        return i;
    }

    public int eliminarActividad(int idActividad) throws SQLException {
        String sql = "DELETE FROM actividades"
                + " WHERE idActividad = ?";

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1,idActividad);

        int i = ps.executeUpdate();

        
        return i;
   
    }

    public Set<Actividad> buscarActividad() throws SQLException {
        Set<Actividad> listaActividades = new HashSet<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;
        conexion = cg.getConn();

        if (conexion != null) {
            consulta = "SELECT * FROM actividades ORDER BY idActividad DESC";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setUrl(rs.getString("url"));
                actividad.setImagen(rs.getBlob("imagen"));
                actividad.setIdSubtipo(rs.getInt("idSubtipo"));

                listaActividades.add(actividad);
            }
        }
        return listaActividades;
    }

}
