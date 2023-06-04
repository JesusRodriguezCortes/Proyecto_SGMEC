package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;
import javafxsgemec.util.ShowMessage;


public class FXMLAdministradorController implements Initializable {

    @FXML
    private Button btnAdministrarRefacciones;
    @FXML
    private Button btnSolicitarRefacciones;
    @FXML
    private Button btnAdministrarEquipos;
    @FXML
    private Button btnLiberarEquipos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBack(MouseEvent event) throws IOException {
            String ventana = "vistas/FXMLInicioSesion.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) btnLiberarEquipos.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }

    @FXML
    private void clicAdministrarRefacciones(ActionEvent event) {
    }

    @FXML
    private void clicSolicitarRefacciones(ActionEvent event) {
        String ventana = "vistas/FXMLSolicitudRefacciones.fxml";
        try {
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) btnLiberarEquipos.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        } catch (IOException ex) {
            ShowMessage.showAlertSimple("No se pudo abrir la ventana", "Hubo un error al abrir la ventana. Intentelo de nuevo mas tarde", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void clicAdministrarServicios(ActionEvent event) {
    }

    @FXML
    private void clicRecepcionarEquipoComputo(ActionEvent event) {
    }

    @FXML
    private void clicAdministrarEquipos(ActionEvent event) {
    }

    @FXML
    private void clicConsultarEquipoLiberado(ActionEvent event) {
    }

    @FXML
    private void clicLiberarEquipo(ActionEvent event) {
    }
    
}
