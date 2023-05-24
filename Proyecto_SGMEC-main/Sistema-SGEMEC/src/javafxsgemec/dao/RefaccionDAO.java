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
    
    public static boolean modificarPzasDisponiblesCompraRefaccion(Refaccion refaccionComprada){
        boolean status = false;
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT idRefaccion, Refaccion.nombre, TipoRefaccion.nombreTipoRefaccion, pzasDisponiblesCompra, precioCompra FROM Refaccion INNER JOIN TipoRefaccion ON Refaccion.idTipoRefaccion = TipoRefaccion.idTipoRefaccion INNER JOIN Proveedor on Refaccion.idProveedor = Proveedor.idProveedor WHERE Proveedor.idProveedor = ?;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
              
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                
                
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
  
        }
        return status;
    }
    
    public void deleteRefaccion(){
        
    }
}
