package javafxsgemec.controladores;

import java.io.ByteArrayInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Dispositivo;


public class FXMLImagenClienteController implements Initializable {

    @FXML
    private ImageView ivBack;
    private Dispositivo dis;
    @FXML
    private ImageView ivComputadora;
    @FXML
    private Label idSinImagen;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("sE INICIALIZO LA VENTANA");
    }    

    @FXML
    private void clicBack(MouseEvent event) {
        Stage escenarioBase =(Stage) ivBack.getScene().getWindow();
        escenarioBase.close();
    }
    public void prepararDispositivo(Dispositivo dis){
        this.dis=dis;
        try {
            ByteArrayInputStream inputFoto = new ByteArrayInputStream(dis.getFoto());
            Image imgFotoEdicion = new Image(inputFoto);
            ivComputadora.setImage(imgFotoEdicion);
        } catch (NullPointerException e) {
            idSinImagen.setText("No Hay imagenes para mostrar");
        }
    }
 
}
