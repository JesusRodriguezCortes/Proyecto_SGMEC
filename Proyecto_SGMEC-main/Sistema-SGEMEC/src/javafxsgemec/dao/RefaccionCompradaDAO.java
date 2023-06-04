package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.RefaccionComprada;
import javafxsgemec.util.ResultadoOperacion;

public class RefaccionCompradaDAO {
    
    public static ResultadoOperacion registrarRefaccionComprada(int idPedido, RefaccionComprada refaccionComprada) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setNumeroFilasAfectadas(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sentencia = "INSERT INTO RefaccionesCompradas (idPedidoRefacciones, idRefaccion, refaccionesCompradas, precioNetoRefacciones) values (?,?,?,?);";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idPedido);
                prepararSentencia.setInt(2, refaccionComprada.getIdRefaccion());
                prepararSentencia.setInt(3, refaccionComprada.getRefaccionesCompradas());
                prepararSentencia.setFloat(4, refaccionComprada.getPrecioNetoRefacciones());
                
                int numeroFilas = prepararSentencia.executeUpdate();
                if(numeroFilas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Refacciones compradas agregadas correctamente");
                    respuesta.setNumeroFilasAfectadas(numeroFilas);
                }else{
                    respuesta.setMensaje("Hubo un error al agregar las refaccions");
                }
            } catch (SQLException ex) {
                respuesta.setMensaje(ex.getMessage());
            } finally {
                conexionBD.close();
            }
        }else{
             respuesta.setMensaje("Por el momento no hay conexión con la base de datos...");
        }
        
        return respuesta;
    }
    
}

