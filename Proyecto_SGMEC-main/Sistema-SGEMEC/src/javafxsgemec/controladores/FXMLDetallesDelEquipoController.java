/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxsgemec.dao.DispositivoDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Dispositivo;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class FXMLDetallesDelEquipoController implements Initializable {

    @FXML
    private ImageView ivComputadora;
    @FXML
    private Text txtConrasenia;
    @FXML
    private CheckBox cbEnviadoAEmpresa;
    @FXML
    private CheckBox cbRecibidoEmpresa;
    @FXML
    private CheckBox cbValoracion;
    @FXML
    private CheckBox cbReparacion;
    @FXML
    private CheckBox cbEmpaquetado;
    @FXML
    private CheckBox cbEnviadoACliente;
    @FXML
    private CheckBox cbRecibidoCliente;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbServicio;
    @FXML
    private Label lbProblemas;
    @FXML
    private Label lbMarca;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbNumeroSerie;
    @FXML
    private Label lbSistemaOperativo;
    @FXML
    private Label lbTamanioPantalla;
    @FXML
    private Label lbMemoriaRAM;
    @FXML
    private Label lbAlmacenamiento;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbContrasenia;
    private Dispositivo equipo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clicBack(MouseEvent event) throws IOException {
        String ventana = "vistas/FXMLEquiposCliente.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) lbTamanioPantalla.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();        
    }

    @FXML
    private void clicSolicitarServicio(ActionEvent event) throws IOException {
        String ventana = "vistas/FXMLSolicitarServicio.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) lbTamanioPantalla.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();        
    }
    public void prepararEquipo(int idEquipo) throws SQLException{
        Dispositivo dps=DispositivoDAO.getDispositivo(idEquipo);
        this.equipo=dps;
    }
    
}
