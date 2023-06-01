package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafxsgemec.pojo.Cliente;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.util.ShowMessage;

public class ClienteDAO {
    
    public static int idCliente = 0;
    
    public static int getCliente(int idUsuario) throws SQLException{
        Connection conexionBD = OpenConnection.openConnectionBD();
        System.out.println("ID USUARIO "+idUsuario);
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT * FROM Cliente WHERE idUsuario = ?;";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sqlQuery);
                prepararSentencia.setInt(1, idUsuario);
                ResultSet resultSet = prepararSentencia.executeQuery();
                    resultSet.getInt("idCliente");
                    idCliente = resultSet.getInt("idCliente");
                    System.out.println("ID LIENTE RECUPERADO DE LA BASE "+idCliente);
            } catch (SQLException e) {
                    e.getMessage();
                    e.printStackTrace();
            } catch(NullPointerException f) {
                System.out.println(f.getMessage());
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
        System.out.println("//////////DAO STATIC idCliente = "+idCliente);
        return idCliente;
    }
    
    public static int getIdCliente(){
        return idCliente;
    }
}
