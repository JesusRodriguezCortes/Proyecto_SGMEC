 package javafxsgemec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Servicio;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class ServicioDAO {
    public static ResultOperation createServicio(Servicio newServicio) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO servicio (nombre) VALUES(?)";
                PreparedStatement getServicio = conexionBD.prepareStatement(sqlQuery);
                getServicio.setString(1, newServicio.getNombre());
                
                int affectedRows = getServicio.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Servicio agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del servicio.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }
    
    public static Servicio getServicio(int idServicio) throws SQLException{
        Servicio servicioBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idServicio, nombre FROM servicio" +
                                  "WHERE idServicio = ?";
                PreparedStatement getServicio = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getServicio.executeQuery();
                getServicio.setInt(1, idServicio);
                    servicioBD.setNombre(resultSet.getString("nombre"));
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } catch(NullPointerException f) {
                response.setMessage(f.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return servicioBD;
    }
    
    public static ArrayList<Servicio> getServicios() throws SQLException{
        ArrayList<Servicio> serviciosBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idServicio, nombre FROM servicio";
                PreparedStatement getAllServicios = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllServicios.executeQuery();
                serviciosBD = new ArrayList<>();
                while(resultSet.next()){
                    Servicio newServicio = new Servicio();
                    newServicio.setIdServicio(resultSet.getInt("idServicio"));
                    newServicio.setNombre(resultSet.getString("nombre"));
                    serviciosBD.add(newServicio);
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } catch(NullPointerException f) {
                response.setMessage(f.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return serviciosBD;
    }
    
    public static ResultOperation editServicio(Servicio editServicio) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE servicio SET nombre = ? " +
                                  "WHERE idServicio = ?";
                PreparedStatement setServicio = conexionBD.prepareStatement(sqlQuery);
                setServicio.setString(1, editServicio.getNombre());
                setServicio.setInt(2, editServicio.getIdServicio());
                
                int rowsAffected = setServicio.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Servicio editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del servicio.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } catch(NullPointerException f) {
                response.setMessage(f.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }
    
    public static ResultOperation deleteServicio(int idServicio) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM servicio WHERE idServicio = ? ";                
                PreparedStatement delServicio = conexionBD.prepareStatement(sqlQuery);
                delServicio.setInt(1, idServicio);
                
                int rowsAffected = delServicio.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Servicio eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del servicio.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }
}
