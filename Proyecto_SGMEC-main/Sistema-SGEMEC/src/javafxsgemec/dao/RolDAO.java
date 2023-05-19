 package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Rol;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class RolDAO {
    public static Rol getRol(int idRol) throws SQLException{
        Rol rolBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idRol, nivelAcceso FROM roles" +
                                  "WHERE idRol = ?";
                PreparedStatement getRol = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getRol.executeQuery();
                getRol.setInt(1, idRol);
                    rolBD.setNivelAcceso(resultSet.getString("nivelAcceso"));
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
        return rolBD;
    }
    
    public static ArrayList<Rol> getRols() throws SQLException{
        ArrayList<Rol> rolesBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idRol, nivelAcceso FROM roles";
                PreparedStatement getAllRoles = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllRoles.executeQuery();
                rolesBD = new ArrayList<>();
                while(resultSet.next()){
                    Rol newRol = new Rol();
                        newRol.setIdRoles(resultSet.getInt("idRol"));
                        newRol.setNivelAcceso(resultSet.getString("nivelAcceso"));
                    rolesBD.add(newRol);
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
        return rolesBD;
    }
}
