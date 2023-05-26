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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;

public class FXMLEncargadoMantenimientoController implements Initializable {

    @FXML
    private Button btnRealizarDiagnostico;
    @FXML
    private Button btnActualizarEstadoMan;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void clicRealizarDiagnostico(ActionEvent event) throws IOException {
            String ventana = "vistas/FXMLEquiposEncargado.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) btnActualizarEstadoMan.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }    

    @FXML
    private void clicBack(MouseEvent event) throws IOException {
            String ventana = "vistas/FXMLInicioSesion.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) btnActualizarEstadoMan.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }

    @FXML
    private void clicActualizarEstado(ActionEvent event) {
    }
}
