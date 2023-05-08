package javafxsgemec.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafxsgemec.util.utilidades;


public class FXMLInicioSesionController implements Initializable {

    @FXML
    private Button btnIniciarSesion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        Stage escenarioBase = (Stage) btnIniciarSesion.getScene().getWindow();
        escenarioBase.setScene(utilidades.inicializarEscena("vistas/FXMLCliente.fxml"));
        escenarioBase.setTitle("PANTALLA PRINCIPAL");
        escenarioBase.show(); 
    }
    
}
