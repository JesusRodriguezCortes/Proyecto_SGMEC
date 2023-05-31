package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafxsgemec.pojo.DireccionCompleta;
import javafxsgemec.pojo.direcciones.Estado;
import javafxsgemec.pojo.direcciones.Municipio;
import javafxsgemec.pojo.direcciones.CodigoPostal;
import javafxsgemec.pojo.direcciones.Colonia;
import javafxsgemec.pojo.direcciones.Direccion;
import javafxsgemec.util.ShowMessage;


public class FXMLSolicitarServicioController implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private ComboBox<String> cbMunicipio;
    @FXML
    private Label lbCiudad;
    @FXML
    private Label lbColonia;
    @FXML
    private Label lbCalle;
    @FXML
    private Label lbEntreCalles;
    @FXML
    private Label lbCiudadAlter;
    @FXML
    private Label lbColoniaAlter;
    @FXML
    private Label lbCalleAlter;
    @FXML
    private Label lbEntreCallesAlter;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<String> cbPaqueteria;
    @FXML
    private TextField tfNumGuia;
    private ObservableList<Estado> estados = FXCollections.observableArrayList();
    private ObservableList<Municipio> municipios = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            this.estados = FXCollections.observableArrayList(DireccionDAO.getEstados());
        }catch(SQLException e){
            e.getCause();
        }
        iniciarCBEstados();
    }    

    private void iniciarCBEstados(){
        try{
            cbEstado.setItems(FXCollections.observableArrayList(DireccionDAO.getEstados().toString()));
            cbEstado.valueProperty().addListener((observable, oldValue, newValue) -> {
                cbMunicipio.getItems().clear();
                for (Estado estado : this.estados) {
                    if (estado.getNombreEstado().equals(newValue)) {
                        try {
                            cbMunicipio.setItems(
                                    FXCollections.observableArrayList(DireccionDAO.getMunicipiosEstado(estado.getIdEstado()).toString())
                            );
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLSolicitarServicioController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
    
    /*private void cargarMunicipios(String estado){
        try{
            
        } catch (SQLException e){
            e.printStackTrace();
            ShowMessage.showAlertSimple(
                    "Error de conexión",
                    "Por el momento no se pudo obtener los municipios.\nNo hay conexión con la base de datos...",
                    Alert.AlertType.ERROR
            );
        }
    }*/
    
    @FXML
    private void clicGuardar(ActionEvent event) {
        
    }

    @FXML
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
