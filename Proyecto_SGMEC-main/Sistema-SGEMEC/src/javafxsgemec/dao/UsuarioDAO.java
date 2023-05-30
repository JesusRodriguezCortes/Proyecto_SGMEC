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
    public static UsuarioRespuesta verificarUsuario(String usuario, String password) throws SQLException{
        UsuarioRespuesta userResponse = new UsuarioRespuesta();
        userResponse.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_CORRECTA);
        Usuario usuarioSesion = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idUsuario, nombreUsuario, password, rol.nivelAcceso AS nivelDeAcceso " +
                                  "FROM usuario " +
                                  "LEFT JOIN rol ON usuario.idRol = rol.idRol "+
                                  "WHERE usuario = ? AND password = ? ";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(sqlQuery);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Usuario();
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario( resultadoConsulta.getInt("idUsuario") );
                    //usuarioSesion.setNombreUsuario(resultadoConsulta.getString("usuario"));
                    //usuarioSesion.setPassword(resultadoConsulta.getString("password"));
                    //usuarioSesion.setNivelAcceso(resultadoConsulta.getString("nivelDeAcceso").trim());
                    userResponse.setUsuarioRespuesta(usuarioSesion);
                }
                conexionBD.close();
            } catch (SQLException s) {
                userResponse.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_DML_FALLIDA);
            } finally{
                conexionBD.close();
            }
        }else{
            userResponse.setRespuestaConexion(ConstantsConnection.CODIGO_ERROR_CONEXIONDB);
        }
        return userResponse;
    }
}