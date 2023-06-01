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
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.util.ResultadoOperacion;

public class RefaccionDAO {    
    public static ArrayList<Refaccion> consultRefaccionesProovedor(int idProveedor){
        ArrayList<Refaccion> listaRefacciones = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT Refaccion.idRefaccion, Refaccion.nombreRefaccion, TipoRefaccion.nombreTipoRefaccion, RefaccionProveedor.pzasDisponiblesCompra, RefaccionProveedor.precioCompra, Proveedor.idProveedor FROM Refaccion INNER JOIN TipoRefaccion on TipoRefaccion.idTipoRefaccion = Refaccion.idTipoRefaccion INNER JOIN RefaccionProveedor on RefaccionProveedor.idRefaccion = Refaccion.idRefaccion INNER JOIN Proveedor on Proveedor.idProveedor = RefaccionProveedor.idProveedor WHERE Proveedor.idProveedor = ? AND RefaccionProveedor.pzasDisponiblesCompra > 0;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                configurarConsulta.setInt(1, idProveedor);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Refaccion refaccion = new Refaccion();
                    refaccion.setIdRefaccion(resultadoConsulta.getInt("idRefaccion"));
                    refaccion.setNombreRefaccion(resultadoConsulta.getString("nombreRefaccion"));
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
    
    public static ResultadoOperacion modificarPzasDisponiblesCompraRefaccion(Refaccion refaccionModificada, int idProveedor) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setNumeroFilasAfectadas(-1);
        
        Connection conexionBD = OpenConnection.openConnectionBD();
        
        if(conexionBD != null){
            try {
                String consulta = "UPDATE RefaccionProveedor SET pzasDisponiblesCompra = ? where idRefaccion = ? and idProveedor = ?;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                configurarConsulta.setInt(1, refaccionModificada.getPzasDisponiblesCompra());
                configurarConsulta.setInt(2, refaccionModificada.getIdRefaccion());
                configurarConsulta.setInt(3, idProveedor);
                
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
    

}
