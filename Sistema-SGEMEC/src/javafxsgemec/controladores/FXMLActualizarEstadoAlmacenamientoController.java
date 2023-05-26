/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.dao.EquipoComputoDAO;
import javafxsgemec.pojo.Cliente;
import javafxsgemec.pojo.EquipoComputo;
import javafxsgemec.util.ShowMessage;

/**
 * FXML Controller class
 *
 * @author alfto
 */
public class FXMLActualizarEstadoAlmacenamientoController implements Initializable {

    @FXML
    private Label lbMarca;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbSistemaOperativo;
    @FXML
    private Label lbNombreCliente;
    @FXML
    private Label lbAlmacenamiento;
    @FXML
    private Label lbRam;
    @FXML
    private Label lbProcesador;
    @FXML
    private Label lbTarjetaVideo;
    @FXML
    private CheckBox cbEnviadoCliente;
    @FXML
    private CheckBox cbRecibidoEmpresa;
    @FXML
    private CheckBox cbEnEspera;
    @FXML
    private CheckBox cbEnValoracion;
    @FXML
    private CheckBox cbDiagnosticado;
    @FXML
    private CheckBox cbPagoRealizado;
    @FXML
    private TextArea txaComentariosCliente;
    @FXML
    private TextArea txaInformacionDiagnostico;
    
    private EquipoComputo dispositivo;
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatosDispositivo();
    }    
    
    public void mostrarDatosDispositivo(){
        lbMarca.setText(dispositivo.getMarca());
        lbModelo.setText(dispositivo.getModelo());
        lbNombreCliente.setText(cliente.getNombre());
    }
    
    public void guardarDatosMantenimiento(){
        try{
            ResultOperation resultadoEditar = EquipoComputoDAO.actualizarEstadoEquipoComputo(dispositivo);
            if(!resultadoEditar.isError()){
                ShowMessage.showAlertSimple("Estado de mantenimiento editado", resultadoEditar.getMessage(), Alert.AlertType.INFORMATION);
            }else{
                ShowMessage.showAlertSimple("Error al editar", resultadoEditar.getMessage(), Alert.AlertType.ERROR);
            }
        }catch(SQLException e){
            ShowMessage.showAlertSimple("Error de conexión", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void cerrarVentana(){
        Stage escenarioPrincipal = (Stage) txaInformacionDiagnostico.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        
    }
}
