package javafxsgemec.controladores;

import com.sun.glass.ui.Cursor;
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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
    private Label lbColoniaAlter;
    @FXML
    private Label lbCalleAlter;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<String> cbPaqueteria;
    @FXML
    private TextField tfNumGuia;
    @FXML
    private Label lbMunicipioAlter;
    @FXML
    private Label lbErrorSolicitud;
    @FXML
    private Rectangle rtLugarEntrega;
    @FXML
    private Label lbLugarEntrega;
    @FXML
    private Label lbMunicipioLE;
    @FXML
    private Label lbColoniaLE;
    @FXML
    private Label lbCalleLB;
    @FXML
    private Label lbMunicipioAL;
    @FXML
    private Label lbColoniaAL;
    @FXML
    private Label lbCalleAL;
    @FXML
    private Pane paneLugasEntrg;
    @FXML
    private Label lbDisponibilidadSuc;
    
    private ObservableList<String> nombresEstados = FXCollections.observableArrayList();
    private ObservableList<String> nombresMunicipios = FXCollections.observableArrayList();
    private ObservableList<String> nombresPaqueterias = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lbDisponibilidadSuc.setVisible(false);
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
        desactivarItems();
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
                SolicitudServicioDAO.createSolicitud(numeroGuia, idDiagnostico, idPaqueteria);
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
        switch(cbPaqueteria.getValue().trim()){
            case "DHL":
                lbMunicipioAlter.setText("Xalapa");
                lbColoniaAlter.setText("Manuel de Gutierrez");
                lbCalleAlter.setText("Odontologos 14");
                break;
            case "Correos de méxico":
                lbMunicipioAlter.setText("Xalapa");
                lbColoniaAlter.setText("San Roque");
                lbCalleAlter.setText("Adolfo Ruiz Cortinez 20"); 
                break;
            case "FedEx":
                lbMunicipioAlter.setText("Xalapa");
                lbColoniaAlter.setText("Centro");
                lbCalleAlter.setText("Ignacio Allende"); 
                break;
            case "UPS": 
                lbMunicipioAlter.setText("Xalapa");
                lbColoniaAlter.setText("Xalapa 200");
                lbCalleAlter.setText("Rebsamen 23");  
                break;
            default:
                System.out.println("NO SELECCIONA");
                break;
        }
    }

    @FXML
    private void clicMunicipioP(ActionEvent event) {
        switch(cbMunicipio.getValue().trim()){
            case "Xalapa":
                this.lbDisponibilidadSuc.setText("Hay una sucursal en \"Xalapa\"");
                this.lbDisponibilidadSuc.setVisible(true);
                activarItems();
                break;
            default:
                this.lbDisponibilidadSuc.setText("No hay una sucursal en \""+this.cbMunicipio.getValue()+"\"");
                this.lbDisponibilidadSuc.setVisible(true);
                desactivarItems();
                System.out.println("NO SELECCIONA ESTADO Y/O MUNICIPIO");
                break;
        }
    }

    private void desactivarItems() {
        //paneLugasEntrg.setVisible(false);
        //rtLugarEntrega.setVisible(false);
        lbLugarEntrega.setVisible(false);
        lbMunicipioLE.setVisible(false);
        lbColoniaLE.setVisible(false);
        lbCalleLB.setVisible(false);
        lbMunicipioAL.setVisible(false);
        lbColoniaAL.setVisible(false);
        lbCalleAL.setVisible(false);
    }

    private void activarItems() {
        //paneLugasEntrg.setVisible(true);
        //rtLugarEntrega.setVisible(true);
        lbLugarEntrega.setVisible(true);
        lbMunicipioLE.setVisible(true);
        lbColoniaLE.setVisible(true);
        lbCalleLB.setVisible(true);
        lbMunicipioAL.setVisible(true);
        lbColoniaAL.setVisible(true);
        lbCalleAL.setVisible(true);
    }
}
