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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxsgemec.dao.ProveedorDAO;
import javafxsgemec.dao.RefaccionDAO;
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.pojo.Refaccion;
import javafxsgemec.pojo.RefaccionComprada;
import javafxsgemec.util.ShowMessage;

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
    private ComboBox<Refaccion> cbRefacciones;
    @FXML
    private Label lbNombreRefaccion;
    @FXML
    private Label lbTipoRefaccion;
    @FXML
    private Label lbPrecio;
    @FXML
    private Label lbPzasDisponibles;
    @FXML
    private TableView<RefaccionComprada> tbRefaccionesCompradas;
    @FXML
    private TableColumn tcNombreRefaccion;
    @FXML
    private TableColumn tcTipoRefaccion;
    @FXML
    private TableColumn tcPrecio;
    @FXML
    private TableColumn tcCantidad;
    @FXML
    private TableColumn tcPrecioNeto;
    
    int refaccionesCompradas = 0;
    private ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    private ObservableList<Refaccion> listaRefacciones = FXCollections.observableArrayList();
    private ObservableList<RefaccionComprada> infoRefaccionesCompradas = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
        cargarInformacionProveedor();
        
        cbProveedores.valueProperty().addListener(new ChangeListener<Proveedor>(){
            @Override
            public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
                cbRefacciones.getSelectionModel().clearAndSelect(0);
                if(newValue != null){
                    lbNombre.setText(newValue.getNombreProveedor());
                    lbCorreoElectronico.setText(newValue.getCorreoElect());
                    lbTelefono.setText(newValue.getTelefono());
                    limpiarDatos();
                    cargarInformacionRefaccionesFiltradas(newValue.getIdProovedor());
                }
            }  
        });
        
        cbRefacciones.valueProperty().addListener(new ChangeListener<Refaccion>() {
            @Override
            public void changed(ObservableValue<? extends Refaccion> observable, Refaccion oldValue, Refaccion newValue) {
                if(newValue != null){
                    lbNombreRefaccion.setText(newValue.getNombreRefaccion());
                    lbTipoRefaccion.setText(newValue.getTipoRefaccion());
                    lbPrecio.setText(String.valueOf(newValue.getPrecioCompra()));
                    lbPzasDisponibles.setText(String.valueOf(newValue.getPzasDisponiblesCompra()));
                    refaccionesCompradas = 0;
                    lbCantidad.setText(String.valueOf(refaccionesCompradas));
                }
            }
        });
    }    
    private void configurarTabla(){
        tcNombreRefaccion.setCellValueFactory(new PropertyValueFactory("nombreRefaccion"));
        tcTipoRefaccion.setCellValueFactory(new  PropertyValueFactory("tipoRefaccion"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory("precioCompra"));
        tcCantidad.setCellValueFactory(new PropertyValueFactory("refaccionesCompradas"));
        tcPrecioNeto.setCellValueFactory(new PropertyValueFactory("precioNetoRefacciones"));
        
    }
    
    private void cargarInformacionProveedor() {
        ArrayList<Proveedor> proveedoresRecuperados = ProveedorDAO.consultProveedores();
        if (proveedoresRecuperados != null) {
            if (!proveedoresRecuperados.isEmpty()) {
                listaProveedores.addAll(proveedoresRecuperados);
                cbProveedores.setItems(listaProveedores);
            }else{
                ShowMessage.showAlertSimple("Proveedores no encontrados", "No existen registros de proveedores en la base de datos", Alert.AlertType.WARNING);
            }
        }else{
            ShowMessage.showAlertSimple("Error de conexión", "Hubo un error al conectarse con la base de datos. Intentelo de nuevo mas tarde", Alert.AlertType.ERROR);
        }
    
    }
    
    private void cargarInformacionRefaccionesFiltradas(int idProveedor){
        ArrayList<Refaccion> refaccionesFiltradas = RefaccionDAO.consultRefaccionesProovedor(idProveedor);
        listaRefacciones.clear();
        if(refaccionesFiltradas != null){
            if (!refaccionesFiltradas.isEmpty()) {
                listaRefacciones.addAll(refaccionesFiltradas);
                cbRefacciones.setItems(listaRefacciones);
            }else{
                ShowMessage.showAlertSimple("Refacciones no encontradas", "No existen registros de refacciones del proveedor seleccionado", Alert.AlertType.WARNING);
            }
        }else{
            ShowMessage.showAlertSimple("Error de conexion", "Hubo un error al conectarse con la base de datos. Intentelo de nuevo mas tarde", Alert.AlertType.ERROR);
            
        }
    
    }
    
    private void limpiarDatos(){
        cbRefacciones.getSelectionModel().clearSelection();
        lbNombreRefaccion.setText("");
        lbTipoRefaccion.setText("");
        lbPrecio.setText("");
        lbCantidad.setText("0");
        lbPzasDisponibles.setText("0");
        refaccionesCompradas = 0;
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
    }

    @FXML
    private void btnAumentarCantidadRefacciones(ActionEvent event) {
        if (cbRefacciones.getSelectionModel().getSelectedIndex() != -1) {
          if(refaccionesCompradas < cbRefacciones.getSelectionModel().getSelectedItem().getPzasDisponiblesCompra()){
                refaccionesCompradas++;
                lbCantidad.setText(String.valueOf(refaccionesCompradas));       
            }   
        }
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
        int validarProveedor = cbProveedores.getSelectionModel().getSelectedIndex();
        int validarRefaccion = cbRefacciones.getSelectionModel().getSelectedIndex();
        
        if(validarProveedor != -1 && validarRefaccion != -1){
            agregarRefaccionCompradaATabla();
        }else{
            ShowMessage.showAlertSimple("Proveedor o refaccion no seleccionado(a)", "Seleccion un proveedor o refaccion para continuar", Alert.AlertType.WARNING);
        }
        
    }
    
    private void agregarRefaccionCompradaATabla(){
        RefaccionComprada refaccionComprada = new RefaccionComprada();
        refaccionComprada.setRefaccion(cbRefacciones.getSelectionModel().getSelectedItem());
        refaccionComprada.setRefaccionesCompradas(refaccionesCompradas);
        refaccionComprada.setPrecioNetoRefacciones();
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
