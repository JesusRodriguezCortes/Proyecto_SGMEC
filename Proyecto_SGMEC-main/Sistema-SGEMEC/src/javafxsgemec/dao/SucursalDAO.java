/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxsgemec.connectionBD.OpenConnection;
import javafxsgemec.pojo.Sucursal;

/**
 *
 * @author je_zu
 */
public class SucursalDAO {
    public static ArrayList<Sucursal> consultSucurales(){
        ArrayList<Sucursal> listaSucursales = new ArrayList<>();
        Connection conexionBD = OpenConnection.openConnectionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT idSucursal, direccionSucursal FROM sucursal;";
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Sucursal sucursal = new Sucursal();
                    sucursal.setIdSucursal(resultadoConsulta.getInt("idSucursal"));
                    sucursal.setDireccionSucursal(resultadoConsulta.getString("direccionSucursal"));
                    listaSucursales.add(sucursal);
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
            listaSucursales = null;
        }
        
        return listaSucursales;   
    }
}
