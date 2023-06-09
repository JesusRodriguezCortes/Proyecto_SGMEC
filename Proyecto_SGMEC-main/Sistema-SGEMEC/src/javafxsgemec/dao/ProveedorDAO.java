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
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.connectionBD.OpenConnection;

public class ProveedorDAO {    
    public static ArrayList<Proveedor> consultProveedores(){
        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idProveedor, nombre, telefono, correoElectronico FROM Proveedor;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Proveedor proveedor = new Proveedor();
                    proveedor.setIdProovedor(resultadoConsulta.getInt("idProveedor"));
                    proveedor.setNombreProveedor(resultadoConsulta.getString("nombre"));
                    proveedor.setCorreoElect(resultadoConsulta.getString("correoElectronico"));
                    proveedor.setTelefono(resultadoConsulta.getString("telefono"));
                    listaProveedores.add(proveedor);
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
            listaProveedores = null;
        }
        
        return listaProveedores;   
    }

    
}
