/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafxsgemec.dao.ProveedorDAO;
import javafxsgemec.pojo.Proveedor;

/**
 * FXML Controller class
 *
 * @author je_zu
 */
public class FXMLSolicitudRefaccionesController implements Initializable {

    @FXML
    private Label lbCantidad;
    
    @FXML
    private ComboBox<Proveedor> cbProveedores;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreoElectronico;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbDireccion;
    
    int refaccionesCompradas = 0;
    private ObservableList<Proveedor> listaProveedores;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
        cargarInformacionProveedor();
        
        cbProveedores.valueProperty().addListener(new ChangeListener<Proveedor>(){
            @Override
            public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
                lbNombre.setText(newValue.getNombre());
                lbCorreoElectronico.setText(newValue.getCorreoElect());
                lbTelefono.setText(newValue.getTelefono());
                lbDireccion.setText(newValue.getDireccion());
            }  
        });
    }    
    
    private void cargarInformacionProveedor() {
        ArrayList<Proveedor> proveedoresRecuperados = ProveedorDAO.consultProveedores();
        if (proveedoresRecuperados != null) {
            if (!proveedoresRecuperados.isEmpty()) {
                listaProveedores = FXCollections.observableArrayList();
                listaProveedores.addAll(proveedoresRecuperados);
                cbProveedores.setItems(listaProveedores);
            }
        }
    
    }

    @FXML
    private void btnAumentarCantidadRefacciones(ActionEvent event) {
        refaccionesCompradas++;
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
    }

    @FXML
    private void btnReducirCantidadRefacciones(ActionEvent event) {
        if(refaccionesCompradas > 0){
            refaccionesCompradas--;
            lbCantidad.setText(String.valueOf(refaccionesCompradas));
        }
    }

    @FXML
    private void btnAgregarRefaccion(ActionEvent event) {
       
    }

    @FXML
    private void btnEliminarRefaccion(ActionEvent event) {
    }

    @FXML
    private void btnRealizarPedido(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
