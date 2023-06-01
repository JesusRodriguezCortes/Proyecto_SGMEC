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
    UsuarioRespuesta usuarioSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        String usuario = tfUsuario.getText();
        String contrasenia = tfContrasenia.getText();
        boolean valido = true;
        tfUsuario.setText("");
        tfContrasenia.setText("");
        if(usuario.isEmpty()){
            valido = false;
            lbError.setText("Rellene todos los campos");
        }
        if(contrasenia.isEmpty()){
            valido = false;
            lbError.setText("Rellene todos los campos");
        }
        if(valido == true){
            verificarCredencialesUsuario(usuario, contrasenia);
        }
    }
    private void verificarCredencialesUsuario(String user, String password){
        try {
            usuarioSesion = UsuarioDAO.verificarUsuario(user, password);
            if(usuarioSesion.getRespuestaConexion() == ConstantsConnection.CODIGO_OPERACION_CORRECTA){
                ShowMessage.showAlertSimple(
                    "Bienvenido(a)", 
                    "Credenciales correctas, Bienvenido(a) "+usuarioSesion.getUsarioRespuesta().getUsername()+" al sistema", 
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
        } catch (SQLException e) {
            System.out.println("//Error controller sql: \""+e.getMessage()+"\"");
            ShowMessage.showAlertSimple(
                    "Error de conexión", 
                    "Hubo un error en el proceso de comunicación, inténtelo más tarde...", 
                    Alert.AlertType.ERROR
            );
        } catch (NullPointerException e){
            System.out.println("//Error controller npe: \""+e.getMessage()+"\"");
            System.out.println("//ID de usuario: \""+usuarioSesion.getUsarioRespuesta().getUsername()+"\"");
            ShowMessage.showAlertSimple(
                    "Error de datos", 
                    "Hubo un error en el proceso de comunicación, inténtelo más tarde...", 
                    Alert.AlertType.ERROR
            );
        }
    }

    private void irPantallaPrincipal() {
        try {
            String nivelDeAcceso = usuarioSesion.getUsarioRespuesta().getNivelDeAcceso();
            System.out.println("Nivel de acceso BD: "+nivelDeAcceso);
            String ventana = "";
            nivelDeAcceso = nivelDeAcceso.trim();
            switch(nivelDeAcceso){
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
                    System.out.println("DEFAULT Nivel de acceso BD: "+nivelDeAcceso);
                    ShowMessage.showAlertSimple(
                            "ERROR FATAL", 
                            "No puede ingresar por conflicto de nivel de usuario", 
                            Alert.AlertType.ERROR
                    );
                    break;
            }
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) tfUsuario.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        }catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR I/O: "+ex.getMessage());
            ShowMessage.showAlertSimple(
                    "Error", 
                    "No se puede mostrar la pantalla principal", 
                    Alert.AlertType.ERROR
            );
        }
    }
}
