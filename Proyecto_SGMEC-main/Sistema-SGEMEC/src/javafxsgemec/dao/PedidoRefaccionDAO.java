package javafxsgemec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxsgemec.pojo.PedidoRefaccion;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.util.ResultadoOperacion;

public class PedidoRefaccionDAO {
    
    public static ArrayList<String> obtenerNumeroPedidos(){
        ArrayList<String> numerosPedidos = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "select numeroPedido from pedidoRefacciones;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    String numeroPedido = new String();
                    numeroPedido = resultadoConsulta.getString("numeroPedido");
                    numerosPedidos.add(numeroPedido);
                }
                conexionBD.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    conexionBD.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            numerosPedidos = null;
        }
        
        return numerosPedidos;
    }
    
    public static ResultadoOperacion registrarPedidoRefaccion(PedidoRefaccion nuevoPedido) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setNumeroFilasAfectadas(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String Sentencia = "INSERT INTO PedidoRefacciones (fechaPedido, numeroPedido, totalPedido, direccionEntrega) VALUES (?, ?, ?, ?);";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(Sentencia);
                prepararSentencia.setString(1, nuevoPedido.getFechaPedido());
                prepararSentencia.setString(2, nuevoPedido.getNumeroPedido());
                prepararSentencia.setFloat(3, nuevoPedido.getTotalPedido());
                prepararSentencia.setString(4, nuevoPedido.getDireccionEntrega());
                
                int numeroFilas = prepararSentencia.executeUpdate();
                if(numeroFilas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Pedido agregado correctamente.");
                    respuesta.setNumeroFilasAfectadas(numeroFilas);
                }else{
                    respuesta.setMensaje("No se pudo registrar la información del pedido.");
                } 
            } catch (SQLException e) {
                respuesta.setMensaje(e.getMessage());
            }finally{
                    conexionBD.close();
            }
        }else{
            respuesta.setMensaje("Por el momento no hay conexión con la base de datos...");
        }
        
        return respuesta;
    }
    
    public void consultPedidoRefaccion(){
        
    }
    
    public void consultPedidosRefacciones(){
        
    }
    
    public void modificarPedidoRefaccion(){
        
    }
    
    public void deletePedidoRefaccion(){
        
    }
}
