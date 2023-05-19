package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.PedidoRefacciones;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class PedidoRefaccionesDAO {
    public static ResultOperation createPedidoRefacciones(PedidoRefacciones newPedidoRefacciones) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO pedidoRefacciones (fechaPedido, numeroPedido, totalPedido) " +
                                   "VALUES(?, ?, ?)";
                PreparedStatement setPedidoRefacciones = conexionBD.prepareStatement(sqlQuery);
                    setPedidoRefacciones.setString(1, newPedidoRefacciones.getFechaPedido());
                    setPedidoRefacciones.setInt(2, newPedidoRefacciones.getNumeroPedido());
                    setPedidoRefacciones.setFloat(3, newPedidoRefacciones.getTotalPedido());
                    
                int affectedRows = setPedidoRefacciones.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Pedido de refacciones creado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del pedido.");
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

    public static PedidoRefacciones getPedidoRefacciones(int idPedidoRefacciones) throws SQLException{
        PedidoRefacciones pedidoBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idPedidoRefacciones, fechaPedido, numeroPedido, totalPedido " +
                                  "FROM pedidoRefacciones "+
                                  "WHERE idPedidoRefacciones = ?";
                PreparedStatement getPedidoRefacciones = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getPedidoRefacciones.executeQuery();
                getPedidoRefacciones.setInt(1, idPedidoRefacciones);
                    pedidoBD.setIdPedidoRefacc(resultSet.getInt("idPedidoRefacciones"));
                    pedidoBD.setFechaPedido(resultSet.getString("fechaPedido"));
                    pedidoBD.setNumeroPedido(resultSet.getInt("numeroPedido"));
                    pedidoBD.setTotalPedido(resultSet.getFloat("totalPedido"));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return pedidoBD;
    }
    
    public static ArrayList<PedidoRefacciones> getPedidoRefaccioness() throws SQLException{
        ArrayList<PedidoRefacciones> pedidosBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idPedidoRefacciones, fechaPedido, numeroPedido, totalPedido " +
                                  "FROM pedidoRefacciones";
                PreparedStatement getAllPedidos = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllPedidos.executeQuery();
                pedidosBD = new ArrayList<>();
                while(resultSet.next()){
                    PedidoRefacciones newPedido = new PedidoRefacciones();
                    newPedido.setIdPedidoRefacc(resultSet.getInt("idPedidoRefacciones"));
                    newPedido.setFechaPedido(resultSet.getString("nombre"));
                    newPedido.setNumeroPedido(resultSet.getInt("correoElectronico"));
                    newPedido.setTotalPedido(resultSet.getFloat("telefono"));
                    pedidosBD.add(newPedido);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return pedidosBD;
    }
    
    public static ResultOperation editPedidoRefacciones(PedidoRefacciones editPedidoRefacciones) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE pedidoRefacciones SET fechaPedido = ?, " +
                                  "numeroPedido = ?, totalPedido = ? " +
                                  "WHERE idPedidoRefacciones = ?";
                PreparedStatement setPedido = conexionBD.prepareStatement(sqlQuery);
                setPedido.setString(1, editPedidoRefacciones.getFechaPedido());
                setPedido.setInt(2, editPedidoRefacciones.getNumeroPedido());
                setPedido.setFloat(3, editPedidoRefacciones.getTotalPedido());
                setPedido.setInt(4, editPedidoRefacciones.getIdPedidoRefacc());
                
                int rowsAffected = setPedido.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Pedido editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del pedido.");
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
    
    public static ResultOperation deletePedidoRefacciones(int idPedido) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM pedidoRefacciones WHERE idPedidoRefacciones = ?";                
                PreparedStatement delPedido = conexionBD.prepareStatement(sqlQuery);
                delPedido.setInt(1, idPedido);
                
                int rowsAffected = delPedido.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Pedido eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del pedido.");
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
