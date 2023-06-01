/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.modelo.pojo.EstadoDispositivo;

/**
 *
 * @author alfto
 */
public class EstadoDispositivoDAO {
    
    public static ResultOperation actualizarEstadoDispositivo(EstadoDispositivo editEstado) throws SQLException{
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE estadodispositivo SET nombreEstadoDispositivo = ? "
                        + "WHERE idEstadoDispositivo = ?";
                PreparedStatement setEstado = conexionBD.prepareStatement(sqlQuery);
                setEstado.setString(1, editEstado.getEstado());
                setEstado.setInt(2, editEstado.getIdEstadoDispositivo());
                
                int rowsAffected = setEstado.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Estado actualizado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del estado del dispositivo.");
                }
            }catch (SQLException e) {
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
    
}
