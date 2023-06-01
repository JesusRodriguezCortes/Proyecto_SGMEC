package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class ProveedorDAO {
    public static ResultOperation createProveedor(Proveedor newProveedor) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO proveedor (nombre, correoElectronico, telefono, direccion) " +
                                   "VALUES(?, ?, ?, ?)";
                PreparedStatement setProveedor = conexionBD.prepareStatement(sqlQuery);
                    setProveedor.setString(1, newProveedor.getNombre());
                    setProveedor.setString(2, newProveedor.getCorreoElect());
                    setProveedor.setLong(3, newProveedor.getTelefono());
                    setProveedor.setString(4, newProveedor.getDireccion());
                    
                int affectedRows = setProveedor.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Proveedor agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del proveedor.");
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

    public static Proveedor getProveedor(int idProveedor) throws SQLException{
        Proveedor proveedorBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idProveedor, nombre, correoElectronico, telefono, direccion " +
                                  "FROM proveedor "+
                                  "WHERE idProveedor = ?";
                PreparedStatement getProveedor = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getProveedor.executeQuery();
                getProveedor.setInt(1, idProveedor);
                    proveedorBD.setIdProveedor(resultSet.getInt("idProveedor"));
                    proveedorBD.setNombre(resultSet.getString("nombre"));
                    proveedorBD.setCorreoElect(resultSet.getString("correoElectronico"));
                    proveedorBD.setTelefono(resultSet.getLong("telefono"));
                    proveedorBD.setDireccion(resultSet.getString("direccion"));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return proveedorBD;
    }
    
    public static ArrayList<Proveedor> getProveedors() throws SQLException{
        ArrayList<Proveedor> proveedoresBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idProveedor, nombre, correoElectronico, telefono, direccion " +
                                  "FROM proveedor";
                PreparedStatement getAllProveedores = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllProveedores.executeQuery();
                proveedoresBD = new ArrayList<>();
                while(resultSet.next()){
                    Proveedor newProveedor = new Proveedor();
                    newProveedor.setIdProveedor(resultSet.getInt("idProveedor"));
                    newProveedor.setNombre(resultSet.getString("nombre"));
                    newProveedor.setCorreoElect(resultSet.getString("correoElectronico"));
                    newProveedor.setTelefono(resultSet.getLong("telefono"));
                    newProveedor.setDireccion(resultSet.getString("direccion"));
                    proveedoresBD.add(newProveedor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return proveedoresBD;
    }
    
    public static ResultOperation editProveedor(Proveedor editProveedor) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE proveedor SET nombre= ?, correoElectronico = ?, telefono = ?, direccion = ? " +
                                  "WHERE idProveedor = ?";
                PreparedStatement setProveedor = conexionBD.prepareStatement(sqlQuery);
                setProveedor.setString(1, editProveedor.getNombre());
                setProveedor.setString(2, editProveedor.getCorreoElect());
                setProveedor.setLong(3, editProveedor.getTelefono());
                setProveedor.setString(4, editProveedor.getDireccion());
                setProveedor.setInt(5, editProveedor.getIdProveedor());
                
                int rowsAffected = setProveedor.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Proveedor editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del proveedor.");
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
    
    public static ResultOperation deleteProveedor(int idProveedor) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM proveedor WHERE idProveedor = ?";                
                PreparedStatement delProveedor = conexionBD.prepareStatement(sqlQuery);
                delProveedor.setInt(1, idProveedor);
                
                int rowsAffected = delProveedor.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Proveedor eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del proveedor.");
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
