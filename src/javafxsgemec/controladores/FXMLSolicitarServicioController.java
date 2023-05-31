package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;
import javafxsgemec.dao.DireccionDAO;
import javafxsgemec.dao.SolicitudServicioDAO;
import javafxsgemec.dao.PaqueteriaDAO;
import javafxsgemec.pojo.DireccionCompleta;
import javafxsgemec.pojo.direcciones.Estado;
import javafxsgemec.pojo.direcciones.Municipio;
import javafxsgemec.pojo.direcciones.CodigoPostal;
import javafxsgemec.pojo.direcciones.Colonia;
import javafxsgemec.pojo.direcciones.Direccion;
import javafxsgemec.util.ShowMessage;


public class FXMLSolicitarServicioController implements Initializable {

    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private ComboBox<String> cbMunicipio;
    @FXML
    private Label lbColonia;
    @FXML
    private Label lbCalle;
    @FXML
    private Label lbColoniaAlter;
    @FXML
    private Label lbCalleAlter;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<String> cbPaqueteria;
    @FXML
    private TextField tfNumGuia;
    private ObservableList<String> nombresEstados = FXCollections.observableArrayList();
    private ObservableList<String> nombresMunicipios = FXCollections.observableArrayList();
    private ObservableList<String> nombresPaqueterias = FXCollections.observableArrayList();
    @FXML
    private Label lbMunicipio;
    @FXML
    private Label lbMunicipioAlter;
    @FXML
    private Label lbErrorSolicitud;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEstados();
        cargarPaqueterias();
        btnGuardar.setDisable(true);
        // Configurar el evento de selección del comboBox1
        cbEstado.setOnAction(event -> {
            // Obtener el valor seleccionado en comboBox1
            String seleccion = cbEstado.getValue();

            // Actualizar el contenido del comboBox2 en base a la selección del comboBox1
            cargarMunicipios(seleccion);
        });
        tfNumGuia.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarHabilitarBoton();
        });
        cbPaqueteria.valueProperty().addListener((observable, oldValue, newValue) -> {
            verificarHabilitarBoton();
        });
    }    
    
    public void verificarHabilitarBoton(){
        // Verificar la condición deseada
        String texto = tfNumGuia.getText();
        String seleccion = cbPaqueteria.getValue();

        if (texto.length()<5 || seleccion == null || seleccion.isEmpty()) {
            btnGuardar.setDisable(true); // Si no se cumple la condición, deshabilitar el botón
        } else {
            btnGuardar.setDisable(false); // Si se cumple la condición, habilitar el botón
        }
    }

    private void cargarEstados(){
        try{
            ArrayList<String> nombresEstados = DireccionDAO.getNombresEstados();
            this.nombresEstados = FXCollections.observableArrayList(nombresEstados);
            cbEstado.setItems(this.nombresEstados);
            cbEstado.valueProperty().addListener((observable, oldValue, newValue) -> {
                cbMunicipio.getItems().clear();
                for (String nombre : this.nombresEstados) {
                    if (nombre.equals(newValue)) {
                        cargarMunicipios(newValue);
                    }
                }
            });
        } catch (SQLException e){
            e.printStackTrace();
            ShowMessage.showAlertSimple(
                    "Error de conexión",
                    "Por el momento no se pudo obtener los estados.\nNo hay conexión con la base de datos...",
                    Alert.AlertType.ERROR
            );
        }
    }
    
    private void cargarMunicipios(String nombreEstado){
        try{
            int idEstado = DireccionDAO.getIdEstado(nombreEstado);
            cbMunicipio.getItems().clear();
            ArrayList<String> nombresMunicipios = DireccionDAO.getNombresMunicipiosEstado(idEstado);
            Collections.sort(nombresMunicipios);
            this.nombresMunicipios = FXCollections.observableArrayList(nombresMunicipios);
            cbMunicipio.getItems().addAll(this.nombresMunicipios);
        } catch (SQLException e){
            e.printStackTrace();
            ShowMessage.showAlertSimple(
                    "Error de conexión",
                    "Por el momento no se pudo obtener los municipios.\nNo hay conexión con la base de datos...",
                    Alert.AlertType.ERROR
            );
        }
    }
    
    private void cargarPaqueterias(){
        try{
            ArrayList<String> nombresPaqueterias = PaqueteriaDAO.getNombresPaqueterias();
            this.nombresPaqueterias = FXCollections.observableArrayList(nombresPaqueterias);
            cbPaqueteria.setItems(this.nombresPaqueterias);
        } catch (SQLException e){
            e.printStackTrace();
            ShowMessage.showAlertSimple(
                    "Error de conexión",
                    "Por el momento no se pudo obtener las paqueterias.\nNo hay conexión con la base de datos...",
                    Alert.AlertType.ERROR
            );
        }
    }
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        try{
            if(verificarDatosSolicitud() == false){
                String numeroGuia = this.tfNumGuia.getText();
                int idPaqueteria = PaqueteriaDAO.getIdPaqueteria(cbPaqueteria.getValue());
                int idDiagnostico = 0;
                SolicitudServicioDAO.createSolicitud(numeroGuia,idPaqueteria,idDiagnostico);
                System.out.println("");
                this.lbErrorSolicitud.setVisible(false);
            }else{
                this.lbErrorSolicitud.setVisible(true);
            }
        }catch(SQLException e){
            ShowMessage.showAlertSimple(
                    "Error de conexión", 
                    "No se pudo registrar la información en la base de datos, \nintente más terde.", 
                    Alert.AlertType.ERROR
            );
        }
    }
    
    private boolean verificarDatosSolicitud(){
        try{
            boolean existe = SolicitudServicioDAO.verificarDatosSolicitud(
                    this.tfNumGuia.getText(), 
                    this.cbPaqueteria.getValue()
            );
            if(existe == true){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private void clicAceptar(ActionEvent event) throws IOException {
        String ventana = "vistas/FXMLDetallesDelEquipo.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) tfNumGuia.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();  
    }

    @FXML
    private void clicBack(MouseEvent event) throws IOException {
        String ventana = "vistas/FXMLDetallesDelEquipo.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) tfNumGuia.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();  
    }
    
    @FXML
    private void clicEstado(ActionEvent event) {
    }

    @FXML
    private void clicMunicipio(ActionEvent event) {
        
    }
}
