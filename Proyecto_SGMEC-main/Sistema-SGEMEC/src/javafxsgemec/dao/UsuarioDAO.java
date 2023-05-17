package javafxsgemec.dao;

import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafxsgemec.pojo.Servicio;

public class UsuarioDAO {
    public void crearUsuario(){
        
    }
    
    public void consultUsuario(){
        
    }
    
    public void consultUsuarios(){
        
    }
    
    public void modificarUsuario(){
        
    }
    
    public void deleteUsuario(){
        
    }
    
    public static Usuario verificarUsuario(String usuario, String password) throws SQLException{
        Usuario userTemp = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String consulta =   "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno " +
                                    "FROM usuario " +
                                    "WHERE username = ? AND password = ?";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                userTemp = new Usuario();
                if(resultadoConsulta.next()){
                    //userTemp.setIdUsuario( resultadoConsulta.getInt("idUsuario") );
                }else{
                    //EN CASO DE QUE NO EXISTA ALGÚN USUARIO CON COINCIDENCIA
                    //userTemp.setIdUsuario(-1);
                }
            } catch (SQLException s) {
                s.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return userTemp;
    }
}
