package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Cliente;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class ClienteDAO {
    public static ResultOperation createCliente(Cliente newCliente) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO Cliente " +
                                    "(nombre, apellidoPaterno, direccion, telefono, correoElectronico, idUsuario) " +
                                    "VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement getCliente = conexionBD.prepareStatement(sqlQuery);
                getCliente.setString(1, newCliente.getNombre());
                getCliente.setString(2, newCliente.getApellidoPaterno());
                getCliente.setString(3, newCliente.getDireccion());
                getCliente.setLong(4, newCliente.getTelefono());
                getCliente.setString(5, newCliente.getCorreoElectronico());
                getCliente.setInt(6, newCliente.getIdUsuario());
                
                int affectedRows = getCliente.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Cliente agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del cliente.");
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
    
    public static Cliente getCliente(int idCliente) throws SQLException{
        Cliente clienteBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idCliente, nombre, apellidoPaterno, direccion, telefono, correoElectronico, " +
                                  "usuario.idUsuario, nombreUsuario, 'password', roles.idRoles, nivelAcceso " +
                                  "FROM cliente LEFT JOIN usuario ON cliente.idUsuario = usuario.idUsuario " +
                                  "LEFT JOIN roles ON usuario.idRoles = roles.idRoles " +
                                  "WHERE cliente.idCliente = ?";
                PreparedStatement getCliente = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getCliente.executeQuery();
                getCliente.setInt(1, idCliente);
                    clienteBD.setIdCliente(resultSet.getInt("idCliente"));
                    clienteBD.setNombre(resultSet.getString("nombre"));
                    clienteBD.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                    clienteBD.setDireccion(resultSet.getString("direccion"));
                    clienteBD.setTelefono(resultSet.getInt("telefono"));
                    clienteBD.setCorreoElectronico(resultSet.getString("correoElectronico"));
                    clienteBD.setIdUsuario(resultSet.getInt("idUsuario"));
                    clienteBD.setNombreUsuario(resultSet.getString("nombreUsuario"));
                    clienteBD.setPassword(resultSet.getString("'password'"));
                    clienteBD.setIdRoles(resultSet.getInt("roles.idRoles"));
                    clienteBD.setNivelAcceso(resultSet.getString("nivelAcceso"));
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
        return clienteBD;
    }
    
    public static ArrayList<Cliente> getClientes() throws SQLException{
        ArrayList<Cliente> clientesBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idCliente, nombre, apellidoPaterno, direccion, telefono, correoElectronico, " +
                                  "usuario.idUsuario, nombreUsuario, 'password', roles.idRoles, nivelAcceso " +
                                  "FROM cliente LEFT JOIN usuario ON cliente.idUsuario = usuario.idUsuario " +
                                  "LEFT JOIN roles ON usuario.idRoles = roles.idRoles";
                PreparedStatement getAllClientes = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllClientes.executeQuery();
                clientesBD = new ArrayList<>();
                while(resultSet.next()){
                    Cliente newCliente = new Cliente();
                        newCliente.setIdCliente(resultSet.getInt("idCliente"));
                        newCliente.setNombre(resultSet.getString("nombre"));
                        newCliente.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                        newCliente.setDireccion(resultSet.getString("direccion"));
                        newCliente.setTelefono(resultSet.getInt("telefono"));
                        newCliente.setCorreoElectronico(resultSet.getString("correoElectronico"));
                        newCliente.setIdUsuario(resultSet.getInt("idUsuario"));
                        newCliente.setNombreUsuario(resultSet.getString("nombreUsuario"));
                        newCliente.setPassword(resultSet.getString("'password'"));
                        newCliente.setIdRoles(resultSet.getInt("roles.idRoles"));
                        newCliente.setNivelAcceso(resultSet.getString("nivelAcceso"));
                    clientesBD.add(newCliente);
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
        return clientesBD;
    }
    
    public static ResultOperation editCliente(Cliente editCliente) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE cliente SET nombre = ?, apellidoPaterno = ?, direccion = ?," +
                                  "telefono = ?, correoElectronico = ?, idUsuario = ? " +
                                  "WHERE idCliente = ?";
                PreparedStatement setCliente = conexionBD.prepareStatement(sqlQuery);
                    setCliente.setString(1, editCliente.getNombre());
                    setCliente.setString(2, editCliente.getApellidoPaterno());
                    setCliente.setString(3, editCliente.getDireccion());
                    setCliente.setLong(4, editCliente.getTelefono());
                    setCliente.setString(5, editCliente.getCorreoElectronico());
                    setCliente.setInt(6, editCliente.getIdUsuario());
                
                int rowsAffected = setCliente.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Cliente editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del cliente.");
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
    
    public static ResultOperation deleteCliente(int idCliente) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM cliente WHERE idCliente = ? ";                
                PreparedStatement delCliente = conexionBD.prepareStatement(sqlQuery);
                delCliente.setInt(1, idCliente);
                
                int rowsAffected = delCliente.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Cliente eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del cliente.");
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
