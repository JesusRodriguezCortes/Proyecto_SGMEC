package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.RefaccionComprada;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class RefaccionCompradaDAO {
    
    public static ResultOperation registrarRefaccionComprada(int idPedido, RefaccionComprada refaccionComprada) throws SQLException{
        ResultOperation respuesta = new ResultOperation(true, "", -1);
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
                    respuesta.setMessage("Refacciones compradas agregadas correctamente");
                    respuesta.setNumberRowsAffected(numeroFilas);
                }else{
                    respuesta.setMessage("Hubo un error al agregar las refaccions");
                }
            } catch (SQLException ex) {
                respuesta.setMessage(ex.getMessage());
            } finally {
                conexionBD.close();
            }
        }else{
             respuesta.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        
        return respuesta;
    }
    
}

