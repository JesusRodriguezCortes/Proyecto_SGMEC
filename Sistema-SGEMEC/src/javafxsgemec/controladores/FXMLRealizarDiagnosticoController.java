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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;


public class FXMLRealizarDiagnosticoController implements Initializable {
    @FXML
    private Text txtMarca;
    @FXML
    private ImageView ivComputadora;
    @FXML
    private TextArea txarDiagnostico;
    @FXML
    private TextArea txarCotizacion;
    @FXML
    private TextFlow txfwComentarios;
    @FXML
    private Text txtModelo;
    @FXML
    private Text txtUsuario;
    @FXML
    private Text txtContrasenia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicCancelar(ActionEvent event) throws IOException {
            String ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) txtMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }


    @FXML
    private void clicBack(MouseEvent event) throws IOException {
            String ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) txtMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }

    @FXML
    private void clicAgregarRefaccion(ActionEvent event) {
    }

    @FXML
    private void clicEnviarCotizacion(ActionEvent event) {
    }  
}
