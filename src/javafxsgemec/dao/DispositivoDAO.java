package javafxsgemec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, imagenDispositivo, caracteristicas, idCliente, idEstadoDispositivo) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement setDispositivo = conexionBD.prepareStatement(sqlQuery);
                    setDispositivo.setString(1, newDispositivo.getMarca());
                    setDispositivo.setString(2, newDispositivo.getModelo());
                    setDispositivo.setString(3, newDispositivo.getUsuario());
                    setDispositivo.setString(4, newDispositivo.getPassword());
                    setDispositivo.setString(5, newDispositivo.getErrorDispos());
                        FileInputStream fotoDispositivo = new FileInputStream(foto);
                    setDispositivo.setBlob(6, fotoDispositivo);
                    setDispositivo.setString(7, newDispositivo.getCaracteristicas());
                    setDispositivo.setInt(8, newDispositivo.getIdCliente());
                    setDispositivo.setInt(9, newDispositivo.getIdEstado());                        
                    
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
        Dispositivo dispositivoBD = new Dispositivo();
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, imagenDispositivo, caracteristicas, dispositivo.idCliente, " +
                                  "CONCAT(cliente.nombre,' ',cliente.apellidoPaterno,' ',cliente.apellidoMaterno) AS " +
                                  "nombreCliente, dispositivo.idEstadoDispositivo, EstadoDispositivo.nombreEstadoDispositivo " +
                                  "FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente " +
                                  "LEFT JOIN estadodispositivo ON dispositivo.idEstadoDispositivo = estadodispositivo.idEstadoDispositivo " +
                                  "WHERE idDispositivo = ?;";
                PreparedStatement getDispositivo = conexionBD.prepareStatement(sqlQuery);
                getDispositivo.setInt(1, idDispositivo);                
                ResultSet resultSet = getDispositivo.executeQuery();
                if(resultSet.next()){
                    dispositivoBD.setIdDispositivo(resultSet.getInt("idDispositivo"));
                    dispositivoBD.setMarca(resultSet.getString("marca"));
                    dispositivoBD.setModelo(resultSet.getString("modelo"));
                    dispositivoBD.setUsuario(resultSet.getString("usuarioDispositivo"));
                    dispositivoBD.setPassword(resultSet.getString("passwordDispositivo"));
                    dispositivoBD.setErrorDispos(resultSet.getString("errorDispositivo"));
                    dispositivoBD.setFoto(resultSet.getBytes("imagenDispositivo"));
                    dispositivoBD.setCaracteristicas(resultSet.getString("caracteristicas"));
                    dispositivoBD.setIdCliente(resultSet.getInt("idCliente"));
                    dispositivoBD.setNombreCliente(resultSet.getString("nombreCliente"));
                    dispositivoBD.setIdEstado(resultSet.getInt("idEstadoDispositivo"));
                    dispositivoBD.setEstado(resultSet.getString("nombreEstadoDispositivo"));
                }
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
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, imagenDispositivo, caracteristicas, dispositivo.idCliente, " +
                                  "CONCAT(cliente.nombre,' ',cliente.apellidoPaterno,' ',cliente.apellidoMaterno) AS " +
                                  "nombreCliente, dispositivo.idEstadoDispositivo, EstadoDispositivo.nombreEstadoDispositivo " +
                                  "FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente " +
                                  "LEFT JOIN estadodispositivo ON dispositivo.idEstadoDispositivo = estadodispositivo.idEstadoDispositivo; ";
                PreparedStatement getAllDevices = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllDevices.executeQuery();
                dispositivosBD = new ArrayList<>();
                while(resultSet.next()){
                    Dispositivo newDispositivo = new Dispositivo();
                        newDispositivo.setIdDispositivo(resultSet.getInt("idDispositivo"));
                        newDispositivo.setMarca(resultSet.getString("marca"));
                        newDispositivo.setModelo(resultSet.getString("modelo"));
                        newDispositivo.setUsuario(resultSet.getString("usuarioDispositivo"));
                        newDispositivo.setPassword(resultSet.getString("passwordDispositivo"));
                        newDispositivo.setErrorDispos(resultSet.getString("errorDispositivo"));
                        newDispositivo.setFoto(resultSet.getBytes("imagenDispositivo"));
                        newDispositivo.setCaracteristicas(resultSet.getString("caracteristicas"));
                        newDispositivo.setIdCliente(resultSet.getInt("idCliente"));
                        newDispositivo.setNombreCliente(resultSet.getString("nombreCliente"));
                        newDispositivo.setIdEstado(resultSet.getInt("idEstadoDispositivo"));
                        newDispositivo.setEstado(resultSet.getString("nombreEstadoDispositivo"));
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
    
    public static ArrayList<Dispositivo> getDispositivosCliente(int idCliente) throws SQLException{
        ArrayList<Dispositivo> dispositivosBD = null;
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, " +
                                  "errorDispositivo, imagenDispositivo, caracteristicas, dispositivo.idCliente, " +
                                  "CONCAT(cliente.nombre,' ',cliente.apellidoPaterno,' ',cliente.apellidoMaterno) AS " +
                                  "nombreCliente, dispositivo.idEstadoDispositivo, EstadoDispositivo.nombreEstadoDispositivo " +
                                  "FROM dispositivo RIGHT JOIN cliente ON dispositivo.idCliente = cliente.idCliente " +
                                  "LEFT JOIN estadodispositivo ON dispositivo.idEstadoDispositivo = estadodispositivo.idEstadoDispositivo " +
                                  "WHERE Cliente.idCliente = ?;";
                PreparedStatement getAllDevices = conexionBD.prepareStatement(sqlQuery);
                getAllDevices.setInt(1, idCliente);
                ResultSet resultSet = getAllDevices.executeQuery();   
                dispositivosBD = new ArrayList<>();
                while(resultSet.next()){
                    Dispositivo newDispositivo = new Dispositivo();
                        newDispositivo.setIdDispositivo(resultSet.getInt("idDispositivo"));
                        newDispositivo.setMarca(resultSet.getString("marca"));
                        newDispositivo.setModelo(resultSet.getString("modelo"));
                        newDispositivo.setUsuario(resultSet.getString("usuarioDispositivo"));
                        newDispositivo.setPassword(resultSet.getString("passwordDispositivo"));
                        newDispositivo.setErrorDispos(resultSet.getString("errorDispositivo"));
                        newDispositivo.setFoto(resultSet.getBytes("imagenDispositivo"));
                        newDispositivo.setCaracteristicas(resultSet.getString("caracteristicas"));
                        newDispositivo.setIdCliente(resultSet.getInt("idCliente"));
                        newDispositivo.setNombreCliente(resultSet.getString("nombreCliente"));
                        newDispositivo.setIdEstado(resultSet.getInt("idEstadoDispositivo"));
                        newDispositivo.setEstado(resultSet.getString("nombreEstadoDispositivo"));
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
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE dispositivo SET marca = ?, modelo = ?, usuarioDispositivo = ?, " +
                                  "passwordDispositivo = ?, errorDispositivo = ?, imagenDispositivo = ?, " +
                                  "caracteristicas = ?, idCliente = ?, idEstadoDispositivo = ? " +
                                  "WHERE idDispositivo = ?;";
                PreparedStatement setDispositivo = conexionBD.prepareStatement(sqlQuery);
                    setDispositivo.setString(1, editDispositivo.getMarca());
                    setDispositivo.setString(2, editDispositivo.getModelo());
                    setDispositivo.setString(3, editDispositivo.getUsuario());
                    setDispositivo.setString(4, editDispositivo.getPassword());
                    setDispositivo.setString(5, editDispositivo.getErrorDispos());
                    setDispositivo.setBytes(6, editDispositivo.getFoto());
                    setDispositivo.setString(7, editDispositivo.getCaracteristicas());
                    setDispositivo.setInt(8, editDispositivo.getIdCliente());
                    setDispositivo.setInt(9, editDispositivo.getIdEstado());
                
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
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM dispositivo WHERE idDispositivo = ?;";                
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