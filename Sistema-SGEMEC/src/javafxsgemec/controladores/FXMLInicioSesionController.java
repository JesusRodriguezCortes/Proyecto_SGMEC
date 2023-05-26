package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxsgemec.connectionBD.ConstantsConnection;
import javafxsgemec.dao.UsuarioDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.UsuarioRespuesta;
import javafxsgemec.util.ShowMessage;

public class FXMLInicioSesionController implements Initializable {
    
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfContrasenia;
    private Label lbError; 
    UsuarioRespuesta usuarioResponse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        String usuario = tfUsuario.getText();
        String password = tfContrasenia.getText();
        boolean datosIngresados = true;
        tfUsuario.setText("");
        tfContrasenia.setText("");
        if(tfUsuario.getText() == null){
            datosIngresados = false;
            lbError.setText("Rellene todos los campos");
        }
        if(tfContrasenia.getText() == null){
            datosIngresados = false;
            lbError.setText("Rellene todos los campos");
        }
        if(datosIngresados == true){
            verificarCredencialesUsuario(usuario, password);
        }
    }
    
    private void verificarCredencialesUsuario(String user, String password){
        try {
            usuarioResponse = UsuarioDAO.verificarUsuario(user, password);
            if(usuarioResponse.getRespuestaConexion() == ConstantsConnection.CODIGO_OPERACION_CORRECTA){
                System.out.println(
                        "------------------Usuario consultado: "+
                        usuarioResponse.getUsarioRespuesta().getNombreUsuario()+"------------------"
                );
                ShowMessage.showAlertSimple(
                    "Bienvenido(a)", 
                    "Credenciales correctas, Bienvenido(a) "+usuarioResponse.getUsarioRespuesta().getNombreUsuario()+" al sistema", 
                    Alert.AlertType.INFORMATION
                );
                irPantallaPrincipal();
            }else{
                ShowMessage.showAlertSimple(
                        "Credenciales incorrectas", 
                        "El número de personal y/o contraseña es incorrecto, favor de verificarlos", 
                        Alert.AlertType.WARNING
                );
            }
        } catch (SQLException | NullPointerException e) {
            ShowMessage.showAlertSimple(
                    "Error de conexión", 
                    "Hubo un error en el proceso de comunicación, inténtelo más tarde...", 
                    Alert.AlertType.ERROR
            );
        } 
    }

    private void irPantallaPrincipal() {
        try {
            System.out.println("------------------Nivel de acceso BD: "+
                    usuarioResponse.getUsarioRespuesta().getNivelAcceso()+"------------------"
            );
            String ventana = null;
            String nivelAcceso = usuarioResponse.getUsarioRespuesta().getNivelAcceso().trim();
            switch(nivelAcceso){
                case "cliente":
                    ventana = "vistas/FXMLCliente.fxml";   
                    break;
                case "administrador":
                    ventana = "vistas/FXMLAdministrador.fxml";
                    break;
                case "encargado":
                    ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
                    break;
                default:
                    ShowMessage.showAlertSimple(
                            "Conflicto de nivel de acceso", 
                            "Al parecer no existe el nivel de acceso que posee\n\nFATAL_ERROR_BD",
                            Alert.AlertType.ERROR
                    );
                    break;
            }
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) btnIniciarSesion.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            ShowMessage.showAlertSimple(
                    "Error", 
                    "No se puede mostrar la pantalla principal", 
                    Alert.AlertType.ERROR
            );
        }
    }
}
