package javafxsgemec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Dispositivo;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class DispositivoDAO {
    public static ResultOperation createDispositivo(Dispositivo newDispositivo, File foto) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                   "errorDispositivo, estado, imagenDispositivo, idCliente) " +
                                   "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement setDispositivo = conexionBD.prepareStatement(sqlQuery);
                    setDispositivo.setString(1, newDispositivo.getMarca());
                    setDispositivo.setString(2, newDispositivo.getModelo());
                    setDispositivo.setString(3, newDispositivo.getUsuarioDisp());
                    setDispositivo.setString(4, newDispositivo.getPasswordDisp());
                    setDispositivo.setString(5, newDispositivo.getError());
                    setDispositivo.setString(6, newDispositivo.getEstado());
                    setDispositivo.setInt(7, newDispositivo.getIdCliente());
                        FileInputStream fotoDispositivo = new FileInputStream(foto);
                    setDispositivo.setBlob(8, fotoDispositivo);
                    
                int affectedRows = setDispositivo.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Dispositivo agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del dispositivo.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } catch(FileNotFoundException f) {
                response.setMessage(f.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }

    public static Dispositivo getDispositivo(int idDispositivo) throws SQLException{
        Dispositivo dispositivoBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, estado, imagenDispositivo, dispositivo.idCliente, cliente.nombre, " +
                                  "cliente.apellidoPaterno " +
                                  "FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente "+
                                  "WHERE idDispositivo = ?";
                PreparedStatement getDispositivo = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getDispositivo.executeQuery();
                getDispositivo.setInt(1, idDispositivo);
                    dispositivoBD.setIdDispositivo(resultSet.getInt("idDispositivo"));
                    dispositivoBD.setMarca(resultSet.getString("marca"));
                    dispositivoBD.setModelo(resultSet.getString("modelo"));
                    dispositivoBD.setUsuarioDisp(resultSet.getString("usuarioDispositivo"));
                    dispositivoBD.setPasswordDisp(resultSet.getString("passwordDispositivo"));
                    dispositivoBD.setError(resultSet.getString("errorDispositivo"));
                    dispositivoBD.setEstado(resultSet.getString("estado"));
                    dispositivoBD.setFoto(resultSet.getByte("imagenDispositivo"));
                    dispositivoBD.setIdCliente(resultSet.getInt("idCliente"));
                    dispositivoBD.setNombreCliente(resultSet.getString("nombre"));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return dispositivoBD;
    }
    
    public static ArrayList<Dispositivo> getDispositivos() throws SQLException{
        ArrayList<Dispositivo> dispositivosBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, estado, imagenDispositivo, dispositivo.idCliente, cliente.nombre, " +
                                  "cliente.apellidoPaterno " +
                                  "FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente";
                PreparedStatement getAllDevices = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllDevices.executeQuery();
                dispositivosBD = new ArrayList<>();
                while(resultSet.next()){
                    Dispositivo newDispositivo = new Dispositivo();
                    newDispositivo.setIdDispositivo(resultSet.getInt("idDispositivo"));
                    newDispositivo.setMarca(resultSet.getString("marca"));
                    newDispositivo.setModelo(resultSet.getString("modelo"));
                    newDispositivo.setUsuarioDisp(resultSet.getString("usuarioDispositivo"));
                    newDispositivo.setPasswordDisp(resultSet.getString("passwordDispositivo"));
                    newDispositivo.setError(resultSet.getString("errorDispositivo"));
                    newDispositivo.setEstado(resultSet.getString("estado"));
                    newDispositivo.setFoto(resultSet.getByte("imagenDispositivo"));
                    newDispositivo.setIdCliente(resultSet.getInt("idCliente"));
                    newDispositivo.setNombreCliente(resultSet.getString("nombre"));
                    dispositivosBD.add(newDispositivo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return dispositivosBD;
    }
    
    public static ResultOperation editDispositivo(Dispositivo editDispositivo) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE dispositivo SET marca = ?, modelo = ?, usuarioDispositivo = ?, " +
                                  "passwordDispositivo = ?, errorDispositivo = ?, estado = ?, imagenDispositivo = ?, " +
                                  "idClient = ?" +
                                  "WHERE idDispositivo = ?";
                PreparedStatement setDispositivo = conexionBD.prepareStatement(sqlQuery);
                setDispositivo.setString(1, editDispositivo.getMarca());
                setDispositivo.setString(2, editDispositivo.getModelo());
                setDispositivo.setString(3, editDispositivo.getUsuarioDisp());
                setDispositivo.setString(4, editDispositivo.getPasswordDisp());
                setDispositivo.setString(5, editDispositivo.getError());
                setDispositivo.setString(6, editDispositivo.getEstado());
                setDispositivo.setByte(7, editDispositivo.getFoto());
                setDispositivo.setInt(7, editDispositivo.getIdCliente());
                setDispositivo.setInt(7, editDispositivo.getIdDispositivo());
                
                int rowsAffected = setDispositivo.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Dispositivo editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del dispositivo.");
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
    
    public static ResultOperation deleteDispositivo(int idDispositivo) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM dispositivo WHERE idDispositivo = ? ";                
                PreparedStatement delDispositivo = conexionBD.prepareStatement(sqlQuery);
                delDispositivo.setInt(1, idDispositivo);
                
                int rowsAffected = delDispositivo.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Dispositivo eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del dispositivo.");
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
