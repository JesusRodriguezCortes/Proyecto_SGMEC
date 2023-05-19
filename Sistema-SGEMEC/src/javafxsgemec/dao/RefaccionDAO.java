package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Refaccion;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class RefaccionDAO {
    public static ResultOperation createRefaccion(Refaccion newRefaccion) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO refaccion (nombre, tipoRefaccion, codifoRefaccion, pzasDisponiblesCompra, " +
                                  "pzasDisponiblesVenta, precioVenta, precioCompra, idProveedor, idDiagnostico) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement getCliente = conexionBD.prepareStatement(sqlQuery);
                    getCliente.setString(1, newRefaccion.getNombre());
                    getCliente.setString(2, newRefaccion.getTipoRefaccion());
                    getCliente.setString(3, newRefaccion.getSKU());
                    getCliente.setInt(4, newRefaccion.getPzsCompra());
                    getCliente.setInt(5, newRefaccion.getPzsVenta());
                    getCliente.setFloat(6, newRefaccion.getPrecioCompra());
                    getCliente.setFloat(7, newRefaccion.getPzsVenta());
                    getCliente.setFloat(8, newRefaccion.getIdProveedor());
                    getCliente.setFloat(9, newRefaccion.getIdDiagnostico());
                
                int affectedRows = getCliente.executeUpdate();
                if(affectedRows > 0){ 
                    response.setError(false);
                    response.setMessage("Refacción agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información de la refacción.");
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
    
    public static Refaccion getRefaccion(int idRefaccion) throws SQLException{
        Refaccion refaccionBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT refaccion.idRefaccion as 'idRefaccion', refaccion.nombre as 'nombreRefaccion', " +
                                  "tipoRefaccion, codifoRefaccion, pzasDisponiblesCompra, precioCompra, pzasDisponiblesVenta, " +
                                  "precioVenta, proveedor.idProveedor, proveedor.nombre as 'nombreProveedor', " +
                                  "diagnostico.idDiagnostico, diagnostico.fechaFin " +
                                  "FROM refaccion LEFT JOIN proveedor ON refaccion.idProveedor = proveedor.idProveedor " +
                                  "LEFT JOIN diagnostico ON diagnostico.idDiagnostico " +
                                  "WHERE refaccion.idRefaccion = ?";
                PreparedStatement getRefaccion = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getRefaccion.executeQuery();
                getRefaccion.setInt(1, idRefaccion);
                    refaccionBD.setIdRefaccion(resultSet.getInt("idRefaccion"));
                    refaccionBD.setNombre(resultSet.getString("nombreRefaccion"));
                    refaccionBD.setTipoRefaccion(resultSet.getString("tipoRefaccion"));
                    refaccionBD.setSKU(resultSet.getString("codifoRefaccion"));
                    refaccionBD.setPzsCompra(resultSet.getInt("pzasDisponiblesCompra"));
                    refaccionBD.setPrecioCompra(resultSet.getFloat("precioCompra"));
                    refaccionBD.setPzsVenta(resultSet.getInt("pzasDisponiblesVenta"));
                    refaccionBD.setPrecioVenta(resultSet.getFloat("precioVenta"));
                    refaccionBD.setIdProveedor(resultSet.getInt("'idProveedor'"));
                    refaccionBD.setNombreProveedor(resultSet.getString("nombreProveedor"));
                    refaccionBD.setIdDiagnostico(resultSet.getInt("idDiagnostico"));
                    refaccionBD.setFechaFin(resultSet.getString("fechaFin"));
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
        return refaccionBD;
    }
    
    public static ArrayList<Refaccion> getRefacciones() throws SQLException{
        ArrayList<Refaccion> RefaccionesBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT refaccion.idRefaccion as 'idRefaccion', refaccion.nombre as 'nombreRefaccion', tipoRefaccion, " +
                                  "codifoRefaccion, pzasDisponiblesCompra, precioCompra, pzasDisponiblesVenta, precioVenta, " +
                                  "proveedor.idProveedor, proveedor.nombre as 'nombreProveedor', diagnostico.idDiagnostico, " +
                                  "diagnostico.fechaFin " +
                                  "FROM refaccion LEFT JOIN proveedor ON refaccion.idProveedor = proveedor.idProveedor " +
                                  "LEFT JOIN diagnostico ON diagnostico.idDiagnostico";
                PreparedStatement getAllRefacciones = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllRefacciones.executeQuery();
                RefaccionesBD = new ArrayList<>();
                while(resultSet.next()){
                    Refaccion newRefaccion = new Refaccion();
                        newRefaccion.setIdRefaccion(resultSet.getInt("idRefaccion"));
                        newRefaccion.setNombre(resultSet.getString("nombreRefaccion"));
                        newRefaccion.setTipoRefaccion(resultSet.getString("tipoRefaccion"));
                        newRefaccion.setSKU(resultSet.getString("codigoRefaccion"));
                        newRefaccion.setPzsCompra(resultSet.getInt("pzasDisponiblesCompra"));
                        newRefaccion.setPrecioCompra(resultSet.getFloat("precioCompra"));
                        newRefaccion.setPzsVenta(resultSet.getInt("pzasDisponiblesVenta"));
                        newRefaccion.setPrecioVenta(resultSet.getFloat("precioVenta"));
                        newRefaccion.setIdProveedor(resultSet.getInt("'idProveedor'"));
                        newRefaccion.setNombreProveedor(resultSet.getString("nombreProveedor"));
                        newRefaccion.setIdDiagnostico(resultSet.getInt("idDiagnostico"));
                        newRefaccion.setFechaFin(resultSet.getString("fechaFin"));
                    RefaccionesBD.add(newRefaccion);
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
        return RefaccionesBD;
    }
    
    public static ResultOperation editRefaccion(Refaccion editRefaccion) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE refaccion SET nombre = ?, tipoRefaccion = ?, codifoRefaccion = ?," +
                                  "pzasDisponiblesCompra = ?, pzasDisponiblesVenta = ?, precioVenta = ? " +
                                  "precioCompra = ?, idProveedor = ?, idDiagnostico = ?" +
                                  "WHERE idRefaccion = ?";
                PreparedStatement setRefaccion = conexionBD.prepareStatement(sqlQuery);
                    setRefaccion.setString(1, editRefaccion.getNombre());
                    setRefaccion.setString(2, editRefaccion.getTipoRefaccion());
                    setRefaccion.setString(3, editRefaccion.getSKU());
                    setRefaccion.setInt(4, editRefaccion.getPzsCompra());
                    setRefaccion.setInt(5, editRefaccion.getPzsVenta());
                    setRefaccion.setFloat(6, editRefaccion.getPrecioVenta());
                    setRefaccion.setFloat(7, editRefaccion.getPrecioCompra());
                    setRefaccion.setInt(8, editRefaccion.getIdProveedor());
                    setRefaccion.setInt(9, editRefaccion.getIdDiagnostico());
                    setRefaccion.setInt(10, editRefaccion.getIdRefaccion());
                
                int rowsAffected = setRefaccion.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Refaccion editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información dela refaccion.");
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
    
    public static ResultOperation deleteRefaccion(int idRefaccion) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM refaccion WHERE idRefaccion = ?";                
                PreparedStatement delRefaccion = conexionBD.prepareStatement(sqlQuery);
                delRefaccion.setInt(1, idRefaccion);
                
                int rowsAffected = delRefaccion.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Refaccion eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información de la refaccion.");
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
