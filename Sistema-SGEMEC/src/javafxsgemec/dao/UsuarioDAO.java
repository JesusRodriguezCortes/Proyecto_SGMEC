package javafxsgemec.dao;

import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.UsuarioRespuesta;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class UsuarioDAO {
    public static ResultOperation createUsuario(Usuario newUsuario) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO usuario (nombreUsuario, password, idRoles) " +
                                   "VALUES(?, ?, ?)";
                PreparedStatement setUsuario = conexionBD.prepareStatement(sqlQuery);
                    setUsuario.setString(1, newUsuario.getNombreUsuario());
                    setUsuario.setString(2, newUsuario.getPassword());
                    setUsuario.setLong(3, newUsuario.getIdRoles());
                    
                int affectedRows = setUsuario.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Usuario agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del usuario.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }

    public static Usuario getUsuario(int idUsuario) throws SQLException{
        Usuario usuarioBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idUsuario, nombreUsuario, password, idRoles, roles.nivelAcceso " +
                                  "FROM usuario LEFT JOIN roles ON usuario.idRoles = roles.idRoles "+
                                  "WHERE idUsuario = ?";
                PreparedStatement getUsuario = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getUsuario.executeQuery();
                getUsuario.setInt(1, idUsuario);
                    usuarioBD.setIdUsuario(resultSet.getInt("idUsuario"));
                    usuarioBD.setNombreUsuario(resultSet.getString("nombreUsuario"));
                    usuarioBD.setPassword(resultSet.getString("password"));
                    usuarioBD.setIdRoles(resultSet.getInt("idRoles"));
                    usuarioBD.setNivelAcceso(resultSet.getString("nivelAcceso"));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return usuarioBD;
    }
    
    public static ArrayList<Usuario> getUsuarios() throws SQLException{
        ArrayList<Usuario> usuariosBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idUsuario, nombreUsuario, password, idRoles, roles.nivelAcceso " +
                                  "FROM usuario LEFT JOIN roles ON usuario.idRoles = roles.idRoles";
                PreparedStatement getAllUsers = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllUsers.executeQuery();
                usuariosBD = new ArrayList<>();
                while(resultSet.next()){
                    Usuario newUsuario = new Usuario();
                        newUsuario.setIdUsuario(resultSet.getInt("idUsuario"));
                        newUsuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
                        newUsuario.setPassword(resultSet.getString("password"));
                        newUsuario.setIdRoles(resultSet.getInt("idRoles"));
                        newUsuario.setNivelAcceso(resultSet.getString("nivelAcceso"));
                    usuariosBD.add(newUsuario);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return usuariosBD;
    }
    
    public static ResultOperation editUsuario(Usuario editUsuario) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE usuario SET nombreUsuario, password, idRoles, roles.nivelAcceso " +
                                  "WHERE idUsuario = ?";
                PreparedStatement setUsuario = conexionBD.prepareStatement(sqlQuery);
                    setUsuario.setString(1, editUsuario.getNombreUsuario());
                    setUsuario.setString(2, editUsuario.getPassword());
                    setUsuario.setInt(3, editUsuario.getIdRoles());
                    setUsuario.setString(4, editUsuario.getNivelAcceso());
                    setUsuario.setInt(5, editUsuario.getIdUsuario());
                
                int rowsAffected = setUsuario.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Usuario editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del usuario.");
                }
            } catch (SQLException e) {
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
    
    public static ResultOperation deleteUsuario(int idUsuario) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM usuario WHERE idUsuario = ?";                
                PreparedStatement delUsuario = conexionBD.prepareStatement(sqlQuery);
                    delUsuario.setInt(1, idUsuario);
                
                int rowsAffected = delUsuario.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Usuario eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del proveedor.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }
    
     public static UsuarioRespuesta verificarUsuario(String usuario, String password) throws SQLException{
        UsuarioRespuesta uRespuesta = new UsuarioRespuesta();
        uRespuesta.setRespuestaConexion(ConstantsConnection.CODIGO_OPERACION_CORRECTA);
        Usuario usuarioSesion = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT idUsuario, nombreUsuario, password, roles.nivelAcceso AS nivelDeAcceso " +
                                  "FROM usuario " +
                                  "LEFT JOIN roles ON usuario.idRoles = roles.idRoles "+
                                  "WHERE nombreUsuario = ? AND password = ? ";
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, password);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Usuario();
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdUsuario( resultadoConsulta.getInt("idUsuario") );
                    usuarioSesion.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                    usuarioSesion.setPassword(resultadoConsulta.getString("password"));
                    usuarioSesion.setNivelAcceso(resultadoConsulta.getString("nivelDeAcceso").trim());
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
