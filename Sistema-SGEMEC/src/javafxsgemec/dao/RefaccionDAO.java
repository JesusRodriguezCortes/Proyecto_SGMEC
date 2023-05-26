package javafxsgemec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxsgemec.pojo.Refaccion;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.util.ResultadoOperacion;

public class RefaccionDAO {
    public void crearRefaccion(){
        
    }
    
    public void consultRefaccion(){
        
    }
    
    public void consultRefacciones(){
        
    }
    
    public void modificarRefaccion(){
        
    }
    
    public static ArrayList<Refaccion> consultRefaccionesProovedor(int idProveedor){
        ArrayList<Refaccion> listaRefacciones = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT idRefaccion, Refaccion.nombre, TipoRefaccion.nombreTipoRefaccion, pzasDisponiblesCompra, precioCompra FROM Refaccion INNER JOIN TipoRefaccion ON Refaccion.idTipoRefaccion = TipoRefaccion.idTipoRefaccion INNER JOIN Proveedor on Refaccion.idProveedor = Proveedor.idProveedor WHERE Proveedor.idProveedor = ?;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                configurarConsulta.setInt(1, idProveedor);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Refaccion refaccion = new Refaccion();
                    refaccion.setIdRefaccion(resultadoConsulta.getInt("idRefaccion"));
                    refaccion.setNombreRefaccion(resultadoConsulta.getString("nombre"));
                    refaccion.setTipoRefaccion(resultadoConsulta.getString("nombreTipoRefaccion"));
                    refaccion.setPzasDisponiblesCompra(resultadoConsulta.getInt("pzasDisponiblesCompra"));
                    refaccion.setPrecioCompra(resultadoConsulta.getFloat("PrecioCompra"));
                    listaRefacciones.add(refaccion);
                }
                conexionBD.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    conexionBD.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            listaRefacciones = null;
        }
        
        
        
        return  listaRefacciones;
    }
    
    public static ResultadoOperacion modificarPzasDisponiblesCompraRefaccion(Refaccion refaccionModificada) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setNumeroFilasAfectadas(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "UPDATE Refaccion SET pzasDisponiblesCompra = ? WHERE idRefaccion = ?; ;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                configurarConsulta.setInt(1, refaccionModificada.getPzasDisponiblesCompra());
                configurarConsulta.setInt(2, refaccionModificada.getIdRefaccion());
                
                int numeroFilas = configurarConsulta.executeUpdate();
                if(numeroFilas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Informacion de refaccion modificada correctamente");
                    respuesta.setNumeroFilasAfectadas(numeroFilas);
                }else{
                    respuesta.setMensaje("No se pudo modificar la informacion de refaccion.");
                }
                
                
                conexionBD.close();
            } catch (SQLException ex) {
                respuesta.setMensaje(ex.getMessage());
            }finally{
                    conexionBD.close();
                
            }
        }else{
            respuesta.setMensaje("Por el momento no hay conexión con la base de datos...");
        }
        return respuesta;
    }
    
    public void deleteRefaccion(){
        
    }
}
