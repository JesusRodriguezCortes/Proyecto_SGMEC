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
import javafxsgemec.pojo.EquipoComputo;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class EquipoComputoDAO {
    public void crearEquipoComputo(){
        
    }
    
    public void consultEquipoComputo(){
        
    }
    
    public void consultEquiposComputo(){
        
    }
    
    public void modificarEquipoComputo(){
        
    }
    
    public void deleteEquipoComputo(){
        
    }
    
    /**
     *
     * @param editEquipo
     * @return
     */
    public static ResultOperation actualizarEstadoEquipoComputo(EquipoComputo editEquipo) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try{
                String sqlQuery = "UPDATE dispositivo SET estado = ? WHERE idDispositivo = ?";
                PreparedStatement setDispositivo = conexionBD.prepareStatement(sqlQuery);
                setDispositivo.setString(1, editEquipo.getEstado());
                
                int rowsAffected = setDispositivo.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Estado del mantenimiento del equipo de cómputo editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del equipo.");
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
