package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.DireccionCompleta;
import javafxsgemec.pojo.direcciones.Estado;
import javafxsgemec.pojo.direcciones.Municipio;
import javafxsgemec.pojo.direcciones.CodigoPostal;
import javafxsgemec.pojo.direcciones.Colonia;
import javafxsgemec.pojo.direcciones.Direccion;
import javafxsgemec.util.ShowMessage;

public class DireccionDAO {
    public static ArrayList<DireccionCompleta> getDireccionesClientes() throws SQLException{
        ArrayList<DireccionCompleta> direccionesBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT Estado.idEstado, Estado.nombreEstado, Municipio.idMunicipio, Municipio.nombreMunicipio, " +
                                  "Colonia.idColonia, Colonia.nombreColonia, CodigoPostal.idCodigoPostal, CodigoPostal.codigoPostal, " +
                                  "Direccion.idDireccion, Direccion.calle, Direccion.numeroExt, Direccion.numeroInt " +
                                  "FROM Estado LEFT JOIN Municipio ON Estado.idEstado = Municipio.idEstado " +
                                  "LEFT JOIN CodigoPostal ON Municipio.idMunicipio = CodigoPostal.idMunicipio " +
                                  "LEFT JOIN Colonia ON CodigoPostal.idCodigoPostal = Colonia.idCodigoPostal " +
                                  "LEFT JOIN Direccion ON Colonia.idDireccion = Direccion.idDireccion " +
                                  "RIGHT JOIN Cliente ON Cliente.idDireccion = Direccion.idDireccion; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllAddress.executeQuery();
                direccionesBD = new ArrayList<>();
                while(resultSet.next()){
                    DireccionCompleta newDireccion = new DireccionCompleta();
                        newDireccion.setIdEstado(resultSet.getInt("idEstado"));
                        newDireccion.setNombreEstado(resultSet.getString("nombreEstado"));
                        newDireccion.setIdMunicipio(resultSet.getInt("idMunicipio"));
                        newDireccion.setNombreMunicipio(resultSet.getString("nombreMunicipio"));
                        newDireccion.setIdCodigoPostal(resultSet.getInt("idColonia"));
                        newDireccion.setCodigoPostal(resultSet.getString("nombreColonia"));
                        newDireccion.setIdColonia(resultSet.getInt("idCodigoPostal"));
                        newDireccion.setNombreColonia(resultSet.getString("codigoPostal"));
                        newDireccion.setIdDireccion(resultSet.getInt("idDireccion"));
                        newDireccion.setCalle(resultSet.getString("calle"));
                        newDireccion.setNumeroExt(resultSet.getString("numeroExt"));
                        newDireccion.setNumeroInt(resultSet.getString("numeroInt"));
                    direccionesBD.add(newDireccion);
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
        return direccionesBD;
    }
    
    public static DireccionCompleta getDireccionCliente(int idCliente) throws SQLException{
       DireccionCompleta direccionBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT Estado.idEstado, Estado.nombreEstado, Municipio.idMunicipio, Municipio.nombreMunicipio, " +
                                  "Colonia.idColonia, Colonia.nombreColonia, CodigoPostal.idCodigoPostal, CodigoPostal.codigoPostal, " +
                                  "Direccion.idDireccion, Direccion.calle, Direccion.numeroExt, Direccion.numeroInt " +
                                  "FROM Estado LEFT JOIN Municipio ON Estado.idEstado = Municipio.idEstado " +
                                  "LEFT JOIN CodigoPostal ON Municipio.idMunicipio = CodigoPostal.idMunicipio " +
                                  "LEFT JOIN Colonia ON CodigoPostal.idCodigoPostal = Colonia.idCodigoPostal " +
                                  "LEFT JOIN Direccion ON Colonia.idDireccion = Direccion.idDireccion " +
                                  "RIGHT JOIN Cliente ON Cliente.idDireccion = Direccion.idDireccion " +
                                  "WHERE Cliente.idCliente = ?; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                getAllAddress.setInt(1, idCliente);
                ResultSet resultSet = getAllAddress.executeQuery();
                direccionBD = new DireccionCompleta();
                    direccionBD.setIdEstado(resultSet.getInt("idEstado"));
                    direccionBD.setNombreEstado(resultSet.getString("nombreEstado"));
                    direccionBD.setIdMunicipio(resultSet.getInt("idMunicipio"));
                    direccionBD.setNombreMunicipio(resultSet.getString("nombreMunicipio"));
                    direccionBD.setIdCodigoPostal(resultSet.getInt("idColonia"));
                    direccionBD.setCodigoPostal(resultSet.getString("nombreColonia"));
                    direccionBD.setIdColonia(resultSet.getInt("idCodigoPostal"));
                    direccionBD.setNombreColonia(resultSet.getString("codigoPostal"));
                    direccionBD.setIdDireccion(resultSet.getInt("idDireccion"));
                    direccionBD.setCalle(resultSet.getString("calle"));
                    direccionBD.setNumeroExt(resultSet.getString("numeroExt"));
                    direccionBD.setNumeroInt(resultSet.getString("numeroInt"));
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
        return direccionBD;
    }
    
    public static ArrayList<Estado> getEstados() throws SQLException{
        ArrayList<Estado> estadosBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idEstado, NombreEstado FROM Estado; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                ResultSet resultSet = getAllAddress.executeQuery();
                estadosBD = new ArrayList<>();
                while(resultSet.next()){
                    Estado newEstado = new Estado();
                        newEstado.setIdEstado(resultSet.getInt("idEstado"));
                        newEstado.setNombreEstado(resultSet.getString("NombreEstado"));
                    estadosBD.add(newEstado);
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
        return estadosBD;
    }
    
    public static ArrayList<Municipio> getMunicipiosEstado(int idEstado) throws SQLException{
        ArrayList<Municipio> municipiosBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idMunicipio, nombreMunicipio FROM Municipio " +
                                  "RIGHT JOIN Estado ON Municipio.idEstado = Estado.idEstado " +
                                  "WHERE Estado.idEstado = ?; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                getAllAddress.setInt(1, idEstado);
                ResultSet resultSet = getAllAddress.executeQuery();
                municipiosBD = new ArrayList<>();
                while(resultSet.next()){
                    Municipio newMunicipio = new Municipio();
                        newMunicipio.setIdMunicipio(resultSet.getInt("idMunicipio"));
                        newMunicipio.setNombreMunicipio(resultSet.getString("nombreMunicipio"));
                    municipiosBD.add(newMunicipio);
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
        return municipiosBD;
    }
    
    public static ArrayList<CodigoPostal> getCodigosPostalesMunicipio(int idMunicipio) throws SQLException{
        ArrayList<CodigoPostal> codigosPostalesBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idCodigoPostal, codigoPostal FROM CodigoPostal " +
                                  "RIGHT JOIN Municipio ON CodigoPostal.idMunicipio = Municipio.idMunicipio  " +
                                  "WHERE Municipio.idMunicipio = ?;";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                getAllAddress.setInt(1, idMunicipio);
                ResultSet resultSet = getAllAddress.executeQuery();
                codigosPostalesBD = new ArrayList<>();
                while(resultSet.next()){
                    CodigoPostal newCodigoPostal = new CodigoPostal();
                        newCodigoPostal.setIdCodigoPostal(resultSet.getInt("idCodigoPostal"));
                        newCodigoPostal.setCodigoPostal(resultSet.getString("codigoPostal"));
                    codigosPostalesBD.add(newCodigoPostal);
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
        return codigosPostalesBD;
    }
    
    public static ArrayList<Colonia> getColoniasCodigoPostal(int idCodigoPostal) throws SQLException{
        ArrayList<Colonia> coloniasBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idColonia, nombreColonia FROM Colonia " +
                                  "RIGHT JOIN CodigoPostal ON Colonia.idCodigoPostal = CodigoPostal.idCodigoPostal " +
                                  "WHERE CodigoPostal.idCodigoPostal = ?; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                getAllAddress.setInt(1, idCodigoPostal);
                ResultSet resultSet = getAllAddress.executeQuery();
                coloniasBD = new ArrayList<>();
                while(resultSet.next()){
                    Colonia newColonia = new Colonia();
                        newColonia.setIdColonia(resultSet.getInt("idColonia"));
                        newColonia.setNombreColonia(resultSet.getString("nombreColonia"));
                    coloniasBD.add(newColonia);
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
        return coloniasBD;
    }
    
    /*public static ResultSet setDireccionCliente (int idCodigoPostal) throws SQLException{
        ArrayList<Colonia> coloniasBD = null;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String sqlQuery = "SELECT idColonia, nombreColonia FROM Colonia " +
                                  "RIGHT JOIN CodigoPostal ON Colonia.idCodigoPostal = CodigoPostal.idCodigoPostal " +
                                  "WHERE CodigoPostal.idCodigoPostal = ?; ";
                PreparedStatement getAllAddress = conexionBD.prepareStatement(sqlQuery);
                getAllAddress.setInt(1, idCodigoPostal);
                ResultSet resultSet = getAllAddress.executeQuery();
                coloniasBD = new ArrayList<>();
                while(resultSet.next()){
                    Colonia newColonia = new Colonia();
                        newColonia.setIdColonia(resultSet.getInt("idColonia"));
                        newColonia.setNombreColonia(resultSet.getString("nombreColonia"));
                    coloniasBD.add(newColonia);
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
        return coloniasBD;
    }*/
}
