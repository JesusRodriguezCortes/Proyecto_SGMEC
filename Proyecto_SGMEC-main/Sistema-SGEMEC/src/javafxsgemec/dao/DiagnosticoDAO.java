package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsgemec.pojo.Diagnostico;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;

public class DiagnosticoDAO {
    public static ResultOperation createDiagnostico(Diagnostico newDiagnostico) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO diagnostico \n" +
                "( fechaInicio, fechaFin, resultadoDiagnostico, idDispositivo, idServicio) \n" +
                    "VALUES( ?, ?, ?, ?, ?)";
                PreparedStatement getDiagnostico = conexionBD.prepareStatement(sqlQuery);
                getDiagnostico.setString(1, newDiagnostico.getFechaInicio());
                getDiagnostico.setString(2, newDiagnostico.getFechaFin());
                getDiagnostico.setString(3, newDiagnostico.getResultadoDiagnostico());
                getDiagnostico.setInt(4, newDiagnostico.getIdDispositivo());
                getDiagnostico.setInt(5, newDiagnostico.getIdServicio());  
                int affectedRows = getDiagnostico.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Diagnostico agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información del diagnóstico.");
                }
            } catch (SQLException e) {
                response.setMessage(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            response.setMessage("Por el momento no hay conexión con la base de datos...");
        }
        return response;
    }

    
    public static Diagnostico getDiagnostico(int idDiagnostico) throws SQLException{
        Diagnostico diagnosticoBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT diagnostico.idDiagnostico, resultadoDiagnostico, servicio.idServicio, " +
                                  "servicio.nombre as 'TipoServicio', cotizacion, fechaInicio, fechaFin, " +
                                  "dispositivo.idDispositivo, marca, modelo, errorDispositivo, estado, " +
                                  "imagenDispositivo, refaccion.idRefaccion, refaccion.nombre as 'Refaccion' " +
                                  "FROM diagnostico " +
                                  "LEFT JOIN servicio ON diagnostico.idServicio = servicio.idServicio " +
                                  "LEFT JOIN dispositivo ON diagnostico.idDispositivo = dispositivo.idDispositivo " +
                                  "LEFT JOIN refaccion ON diagnostico.idDiagnostico = refaccion.idDiagnostico " +
                                  "WHERE diagnostico.idDiagnostico = ?";
                PreparedStatement getDiagnostico = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getDiagnostico.executeQuery();
                getDiagnostico.setInt(1, idDiagnostico);
                    diagnosticoBD.setIdDiagnostico(resultSet.getInt("idDiagnostico"));
                    diagnosticoBD.setResultadoDiagnostico(resultSet.getString("resultadoDiagnostico"));
                    diagnosticoBD.setIdServicio(resultSet.getInt("idServicio"));
                    diagnosticoBD.setNombreServicio(resultSet.getString("TipoServicio"));
                    diagnosticoBD.setCotizacion(resultSet.getFloat("cotizacion"));
                    diagnosticoBD.setFechaInicio(resultSet.getString("fechaInicio"));
                    diagnosticoBD.setFechaFin(resultSet.getString("fechaFin"));
                    diagnosticoBD.setIdDispositivo(resultSet.getInt("idDispositivo"));
                    diagnosticoBD.setMarcaDispositivo(resultSet.getString("marca"));
                    diagnosticoBD.setModeloDispositivo(resultSet.getString("modelo"));
                    diagnosticoBD.setErrorDispositivo(resultSet.getString("errorDispositivo"));
                    diagnosticoBD.setEstado(resultSet.getString("estado"));
                    diagnosticoBD.setFoto(resultSet.getByte("imagenDispositivo"));
                    
                    //TO DO 
                    //OBTENER TODAS LAS REFACCIONES UTILIZADAS
                    
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
        return diagnosticoBD;
    }
    
    public static ArrayList<Diagnostico> getDiagnosticos() throws SQLException{
        ArrayList<Diagnostico> diagnosticosBD = null;
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT diagnostico.idDiagnostico, resultadoDiagnostico, servicio.idServicio, " +
                                  "servicio.nombre as 'TipoServicio', cotizacion, fechaInicio, fechaFin, " +
                                  "dispositivo.idDispositivo, marca, modelo, errorDispositivo, estado, " +
                                  "imagenDispositivo, refaccion.idRefaccion, refaccion.nombre as 'Refaccion' " +
                                  "FROM diagnostico " +
                                  "LEFT JOIN servicio ON diagnostico.idServicio = servicio.idServicio " +
                                  "LEFT JOIN dispositivo ON diagnostico.idDispositivo = dispositivo.idDispositivo " +
                                  "LEFT JOIN refaccion ON diagnostico.idDiagnostico = refaccion.idDiagnostico ";
                PreparedStatement getAllDiagnosticos = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllDiagnosticos.executeQuery();
                diagnosticosBD = new ArrayList<>();
                while(resultSet.next()){
                    Diagnostico newDiagnostico = new Diagnostico();
                    newDiagnostico.setIdDiagnostico(resultSet.getInt("idDiagnostico"));
                    newDiagnostico.setResultadoDiagnostico(resultSet.getString("resultadoDiagnostico"));
                    newDiagnostico.setIdServicio(resultSet.getInt("idServicio"));
                    newDiagnostico.setNombreServicio(resultSet.getString("TipoServicio"));
                    newDiagnostico.setCotizacion(resultSet.getFloat("cotizacion"));
                    newDiagnostico.setFechaInicio(resultSet.getString("fechaInicio"));
                    newDiagnostico.setFechaFin(resultSet.getString("fechaFin"));
                    newDiagnostico.setIdDispositivo(resultSet.getInt("idDispositivo"));
                    newDiagnostico.setMarcaDispositivo(resultSet.getString("marca"));
                    newDiagnostico.setModeloDispositivo(resultSet.getString("modelo"));
                    newDiagnostico.setErrorDispositivo(resultSet.getString("errorDispositivo"));
                    newDiagnostico.setEstado(resultSet.getString("estado"));
                    newDiagnostico.setFoto(resultSet.getByte("imagenDispositivo"));
                    
                    //TO DO 
                    //OBTENER TODAS LAS REFACCIONES UTILIZADAS
                    
                    diagnosticosBD.add(newDiagnostico);
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
        return diagnosticosBD;
    }
    
    public static ResultOperation editDiagnostico(Diagnostico editDiagnostico) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "UPDATE diagnostico SET cotizacion = ?, fechaInicio = ?, fechaFin = ?, " +
                                  "resultadoDiagnostico = ?, idDispositivo = ?, idServicio = ? " +
                                  "WHERE idDiagnostico = ?";
                PreparedStatement setDiagnostico = conexionBD.prepareStatement(sqlQuery);
                setDiagnostico.setFloat(1, editDiagnostico.getCotizacion());
                setDiagnostico.setString(2, editDiagnostico.getFechaInicio());
                setDiagnostico.setString(3, editDiagnostico.getFechaFin());
                setDiagnostico.setString(4, editDiagnostico.getResultadoDiagnostico());
                setDiagnostico.setInt(5, editDiagnostico.getIdDispositivo());
                setDiagnostico.setInt(6, editDiagnostico.getIdServicio());
                setDiagnostico.setInt(7, editDiagnostico.getIdDiagnostico());
                
                int rowsAffected = setDiagnostico.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Diagnostico editado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo editar la información del diagnostico.");
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
    
    public static ResultOperation deleteDiagnostico(int idDiagnostico) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String sqlQuery = "DELETE FROM diagnostico WHERE idDiagnostico = ?";                
                PreparedStatement delDiagnostico = conexionBD.prepareStatement(sqlQuery);
                delDiagnostico.setInt(1, idDiagnostico);
                
                int rowsAffected = delDiagnostico.executeUpdate();
                if(rowsAffected > 0){
                    response.setError(false);
                    response.setMessage("Diagnostico eliminado correctamente.");
                    response.setNumberRowsAffected(rowsAffected);
                }else{
                    response.setMessage("No se pudo eliminar la información del diagnostico.");
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
}
