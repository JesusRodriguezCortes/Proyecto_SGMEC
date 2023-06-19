package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.util.ShowMessage;

public class PaqueteriaDAO {
    public static ArrayList<String> getNombresPaqueterias() throws SQLException{
        ArrayList<String> nombresPaqueterias = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT * FROM Paqueteria; ";
                PreparedStatement getEstado = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getEstado.executeQuery();
                while(resultSet.next()){
                    nombresPaqueterias.add(resultSet.getString("nombrePaqueteria"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch(NullPointerException f) {
                f.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            ShowMessage.showAlertSimple(
                    "Error de conexión",
                    "Por el momento no hay conexión con la base de datos...",
                    Alert.AlertType.ERROR
            );
        }
        return nombresPaqueterias;
    }
    
    public static int getIdPaqueteria(String nombrePaqueteria) throws SQLException{
        int idPaqueteria = 0;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT * FROM Paqueteria WHERE nombrePaqueteria = ?; ";
                PreparedStatement getIdPaqueteria = conexionBD.prepareStatement(sqlQuery);
                    getIdPaqueteria.setString(1, nombrePaqueteria);
                ResultSet resultSet = getIdPaqueteria.executeQuery();
                
                if(resultSet.next()){
                    idPaqueteria = resultSet.getInt("idPaqueteria");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch(NullPointerException f) {
                f.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            ShowMessage.showAlertSimple(
                "Error de conexión",
                "Por el momento no hay conexión con la base de datos...",
                Alert.AlertType.ERROR
            );
        }
        return idPaqueteria;
    }
}
