package javafxsgemec.dao;

import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.pojo.UsuarioRespuesta;
import javafxsgemec.util.ShowMessage;

public class UsuarioDAO {
    
    private static int idUsuario = 0;
    
    public void consultUsuario(){
        
    }
    
    public void consultUsuarios(){
        
    }
    
    public static UsuarioRespuesta verificarUsuario(String usuario, String password) throws SQLException{
        UsuarioRespuesta uRespuesta = new UsuarioRespuesta(ConstantsConnection.CODIGO_ERROR_CONEXIONDB);
        Usuario usuarioSesion = new Usuario();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT idUsuario, nombreUsuario, password, rol.nivelAcceso AS nivelDeAcceso " +
                                   "FROM Usuario " +
                                   "INNER JOIN rol ON usuario.idRol = rol.idRol " +
                                   "WHERE nombreUsuario = ? AND password = ?; ";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                    consultaLogin.setString(1, usuario);
                    consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                if(resultadoConsulta.next()){
                    idUsuario = resultadoConsulta.getInt("idUsuario");
                    usuarioSesion.setIdUsuario( resultadoConsulta.getInt("idUsuario"));
                    usuarioSesion.setUsername(resultadoConsulta.getString("nombreUsuario"));
                    usuarioSesion.setPassword(resultadoConsulta.getString("password"));
                    usuarioSesion.setNivelDeAcceso(resultadoConsulta.getString("nivelDeAcceso").trim());
                }
                uRespuesta.setUsuarioRespuesta(usuarioSesion);
                uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_CORRECTA);
                conexionBD.close();
            } catch (SQLException s) {
                System.out.println("///////Error DAO \"CODIGO_OPERACION_DML_FALLIDA\"");
                uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_DML_FALLIDA);
            } finally{
                conexionBD.close();
            }
        }else{
            System.out.println("///////Error DAO \"CODIGO_ERROR_CONEXIONDB\"");
            uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_ERROR_CONEXIONDB);
        }
        return uRespuesta;
    }
    
    public static int getIdUsuario(){
        return idUsuario;
    }
}
