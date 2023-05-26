package javafxsgemec.dao;

import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.pojo.Servicio;
import javafxsgemec.pojo.UsuarioRespuesta;

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
    
     public static UsuarioRespuesta verificarUsuario(String usuario,String password) throws SQLException{
        UsuarioRespuesta uRespuesta = new UsuarioRespuesta();
        uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_CORRECTA);
        Usuario usuarioSesion = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String consulta = "select idUsuario,usuario,password, roles.nivelAcceso AS nivelDeAcceso "
                        + "FROM usuario " +
                        "INNER JOIN roles ON usuario.idRoles = roles.idRoles "+
                        "WHERE usuario = ? AND password = ? ";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Usuario();
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario( resultadoConsulta.getInt("idUsuario") );
                    usuarioSesion.setUsuario(resultadoConsulta.getString("usuario"));
                    usuarioSesion.setContrasenia(resultadoConsulta.getString("password"));
                    usuarioSesion.setNivelDeAcceso(resultadoConsulta.getString("nivelDeAcceso").trim());
                    uRespuesta.setUsuarioRespuesta(usuarioSesion);

                }
                conexionBD.close();
            } catch (SQLException s) {
                uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_DML_FALLIDA);
            } finally{
                conexionBD.close();
            }
        }else{
            uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_ERROR_CONEXIONDB);
        }
        return uRespuesta;
    }
}
