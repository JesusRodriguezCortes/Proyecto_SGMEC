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
import javafxsgemec.util.utilidades;
import javafxsgemec.util.ShowMessage;


public class FXMLInicioSesionController implements Initializable {

    @FXML
    private Button btnIniciarSesion;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfContrasenia;
    @FXML
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
        System.out.println("ANTES DEL ERROR");
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
        if(valido)
            verificarCredencialesUsuario(usuario, contrasenia);
    }
    private void verificarCredencialesUsuario(String user, String password){
        /*try {
            usuarioSesion = UsuarioDAO.verificarUsuario(user, password);
            if(usuarioSesion.getRespuestaConexion()==ConstantsConnection.CODIGO_OPERACION_CORRECTA){
                System.out.println("Usuario consultado"+usuarioSesion.getUsarioRespuesta().getUsuario());
                irPantallaPrincipal();
            }else{
                ShowMessage.showAlertSimple("Credenciales incorrectas", 
                        "El número de personal y/o contraseña es incorrecto, favor de verificarlos", 
                        Alert.AlertType.WARNING);
            }
        } catch (SQLException | NullPointerException e) {
            ShowMessage.showAlertSimple("Error de conexión", 
                    "Hubo un error en el proceso de comunicación, inténtelo más tarde...", Alert.AlertType.ERROR);
        } */
    }

    private void irPantallaPrincipal() {
                /*try {
            ShowMessage.showAlertSimple("Bienvenido(a)", "Credenciales correctas, Bienvenido(a) "+usuarioSesion.getUsarioRespuesta().getUsuario()+" al sistema", 
                    Alert.AlertType.INFORMATION);
            String ventana = null;
            System.out.println("Nivel de acceso BD: "+usuarioSesion.getUsarioRespuesta().getNivelDeAcceso());
            if(utilidades.compararString(usuarioSesion.getUsarioRespuesta().getNivelDeAcceso(), "administrador"))
                ventana = "vistas/FXMLAdministrador.fxml";
            else if(utilidades.compararString(usuarioSesion.getUsarioRespuesta().getNivelDeAcceso(), "cliente"))
                ventana = "vistas/FXMLCliente.fxml";
            else if(utilidades.compararString(usuarioSesion.getUsarioRespuesta().getNivelDeAcceso(), "encargado"))
                ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) tfUsuario.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            ShowMessage.showAlertSimple("Error", "No se puede mostrar la pantalla principal", 
                    Alert.AlertType.ERROR);
        }*/
    }
}