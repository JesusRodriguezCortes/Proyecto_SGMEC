package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.pojo.Cotizacion;

public class CotizacionDAO {
    public static ResultOperation createCotizacion(Cotizacion newCotizacion) throws SQLException{
        ResultOperation response = new ResultOperation();
        response.setError(true);
        response.setNumberRowsAffected(-1);
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "INSERT INTO cotizacion (reparacionHardware, reparacionSoftware,"
                        + " actualizaciones, servicioLimpieza, costoRepuesto, manoDeObra, garantia, idDispositivo) \n" +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement getCotizacion = conexionBD.prepareStatement(sqlQuery);
                getCotizacion.setDouble(1, newCotizacion.getReparacionHardWare());
                getCotizacion.setDouble(2, newCotizacion.getReparacionSoftware());
                getCotizacion.setDouble(3, newCotizacion.getActualizaciones());
                getCotizacion.setDouble(4, newCotizacion.getServicioLimpieza());
                getCotizacion.setDouble(5, newCotizacion.getCostoRepusto());
                getCotizacion.setDouble(6, newCotizacion.getManoDeObra());
                getCotizacion.setDouble(7, newCotizacion.getGarantia());
                getCotizacion.setDouble(8, newCotizacion.getIdDispositivo());               
                int affectedRows = getCotizacion.executeUpdate();
                if(affectedRows > 0){
                    response.setError(false);
                    response.setMessage("Cotizacion agregada correctamente.");
                    response.setNumberRowsAffected(affectedRows);
                }else{
                    response.setMessage("No se pudo registrar la información de la cotizacion.");
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

}
