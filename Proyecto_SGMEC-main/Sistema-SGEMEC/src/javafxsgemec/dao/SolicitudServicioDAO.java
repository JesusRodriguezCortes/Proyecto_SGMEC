package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.pojo.SolicitudServicio;
import javafxsgemec.pojo.SolicitudServicio;
import javafxsgemec.util.ShowMessage;

public class SolicitudServicioDAO {
    public static ResultOperation createSolicitud(String numeroGuia, int idDiagnostico, int idPaqueteria) throws SQLException{
        ResultOperation response = new ResultOperation(true, "", -1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO solicitudservicio (numeroGuia, idDiagnostico, idPaqueteria) " +
                                  "VALUES (?,NULL,?);";
                PreparedStatement setSolicitud = conexionBD.prepareStatement(sqlQuery);
                    setSolicitud.setString(1, numeroGuia);
                    //setSolicitud.setInt(2, idDiagnostico);
                    setSolicitud.setInt(2, idPaqueteria);
                    
                int affectedRows = setSolicitud.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Solicitud de servicio agregado correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la solicitud de servicio.");
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
    
    public static boolean verificarDatosSolicitud(String numeroGuia, String paqueteria) throws SQLException{
        ArrayList<SolicitudServicio> solicitudesServicios = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idSolicitudServicio, numeroGuia, idDiagnostico, solicitudservicio.idPaqueteria, nombrePaqueteria " +
                                  "FROM solicitudservicio " +
                                  "LEFT JOIN paqueteria ON solicitudservicio.idPaqueteria = paqueteria.idPaqueteria " +
                                  "WHERE numeroGuia = ? AND paqueteria.nombrePaqueteria = ?;";
                PreparedStatement getSolicitud = conexionBD.prepareStatement(sqlQuery);
                    getSolicitud.setString(1, numeroGuia);
                    getSolicitud.setString(2, paqueteria);
                ResultSet resultSet = getSolicitud.executeQuery();
                
                while(resultSet.next()){
                    SolicitudServicio newSolicitud = new SolicitudServicio();
                        newSolicitud.setIdSolicitud(resultSet.getInt("idSolicitudServicio"));
                        newSolicitud.setNumeroGuia(resultSet.getString("numeroGuia"));
                        newSolicitud.setIdDiagnostico(resultSet.getInt("idDiagnostico"));
                        newSolicitud.setIdPaqueteria(resultSet.getInt("idPaqueteria"));
                        newSolicitud.setNombrePaqueteria(resultSet.getString("nombrePaqueteria"));
                    solicitudesServicios.add(newSolicitud);
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
        if(solicitudesServicios.size() >  0){
            return true;
        }else{
            return false;
        }
    }
}
