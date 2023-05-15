package javafxsgemec.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafxsgemec.modelo.ConexionBD;
import javafxsgemec.modelo.pojo.Usuario;
import javafxsgemec.modelo.pojo.UsuarioRespuesta;
import javafxsgemec.util.Constantes;

public class UsuarioDAO {
    
    public static UsuarioRespuesta verificarUsuario(String usuario, 
            String password) throws SQLException{
        UsuarioRespuesta uRespuesta = new UsuarioRespuesta();
        uRespuesta.setRespuestaConexion(Constantes.OPERACION_EXITOSA);
        Usuario usuarioSesion = null;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idUsuario, usuario, contrasenia, nivelDeAcceso "
                        + "FROM usuario " +
                        "WHERE usuario = ? AND contrasenia = ? ";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Usuario();
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario( resultadoConsulta.getInt("idUsuario") );
                    usuarioSesion.setUsuario(resultadoConsulta.getString("usuario"));
                    usuarioSesion.setContrasenia(resultadoConsulta.getString("contrasenia"));
                    usuarioSesion.setNivelDeAcceso(resultadoConsulta.getString("nivelDeAcceso").trim());
                    uRespuesta.setUsuarioRespuesta(usuarioSesion);

                }
                conexionBD.close();
            } catch (SQLException s) {
                uRespuesta.setRespuestaConexion(Constantes.ERROR_CONSULTA);
            } finally{
                conexionBD.close();
            }
        }else{
            uRespuesta.setRespuestaConexion(Constantes.ERROR_CONEXION);
        }
        return uRespuesta;
    }
}
