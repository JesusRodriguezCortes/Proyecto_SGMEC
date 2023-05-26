package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;


public class FXMLSolicitarServicioController implements Initializable {

    @FXML
    private Button btnAceptar;
    @FXML
    private ComboBox<?> cbEstado;
    @FXML
    private ComboBox<?> cbMunicipio;
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
    private ComboBox<?> cbPaqueteria;
    @FXML
    private TextField tfNumGuia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void clicEstado(ActionEvent event) {
    }

    @FXML
    private void clicMunicipio(ActionEvent event) {
    }
    
}
