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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;
//import javafxsgemec.pojo.Diagnostico;
import javafxsgemec.pojo.Dispositivo;
import javafxsgemec.util.ShowMessage;


public class FXMLRealizarDiagnosticoController implements Initializable {

    private Text txtMarca;
    @FXML
    private ImageView ivComputadora;
    @FXML
    private TextArea txarDiagnostico;
    @FXML
    private TextArea txarCotizacion;
    @FXML
    private TextFlow txfwComentarios;
    private Text txtModelo;
    private Text txtUsuario;
    private Text txtContrasenia;
    private Dispositivo equipo;
    @FXML
    private Label lbMarca;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbContrasenia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clicCancelar(ActionEvent event) throws IOException {
            String ventana = "vistas/FXMLEquiposEncargado.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) lbMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }


    @FXML
    private void clicBack(MouseEvent event) throws IOException {
            String ventana = "vistas/FXMLEquiposEncargado.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) lbMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }

    @FXML
    private void clicAgregarRefaccion(ActionEvent event) {
    }

    @FXML
    private void clicEnviarCotizacion(ActionEvent event) {
        System.out.println(txarDiagnostico.getText().length());
        if((txarDiagnostico.getText().length()==0)||(txarCotizacion.getText().length()==0)){
          ShowMessage.showAlertSimple(
                  "CAMPOS VACIOS", 
                  "Por favor asegurese de llenar los campos vacios", 
                  Alert.AlertType.WARNING
          );
        }else{
            //Diagnostico diag = new Diagnostico();
        }
    }

    void prepararEquipo(Dispositivo dispositivo) {
        this.equipo=dispositivo;
            lbMarca.setText(dispositivo.getMarca());
            lbModelo.setText(dispositivo.getModelo());
            lbContrasenia.setText(dispositivo.getPassword());
            lbUsuario.setText(dispositivo.getUsuario());
            txfwComentarios.setTextAlignment(TextAlignment.LEFT);
            txfwComentarios.getChildren().add(new Text(dispositivo.getErrorDispos()));
    }

    @FXML
    private void clicVerImagenes(ActionEvent event) {
        
    }
    
}
