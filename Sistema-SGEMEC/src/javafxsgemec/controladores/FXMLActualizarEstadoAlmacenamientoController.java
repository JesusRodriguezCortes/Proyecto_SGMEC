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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.dao.DispositivoDAO;
import javafxsgemec.pojo.Cliente;
import javafxsgemec.pojo.Dispositivo;
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
    private Label lbNombreCliente;
    @FXML
    private TextArea txaComentariosCliente;
    
    private Dispositivo dispositivo;
    private Cliente cliente;
    @FXML
    private ToggleGroup estado;

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
        txaComentariosCliente.setText(dispositivo.getErrorDispos());
    }
    
    public int convertirEstadoAId(){
        int ID;     
        switch(estado.getSelectedToggle().toString()){
            case "1. Enviado por el cliente":
                ID = 1;
                break;
            case "2. Recibido por la empresa":
                ID = 2;
                break;
            case "3. En espera":
                ID = 3;
                break;
            case "4. En reparación":
                ID = 4;
                break;
            case "5. Enviado por la empresa":
                ID = 5;
                break;
            case "6. Pago realizado":
                ID = 6;
                break;
            case "7. Diagnosticado":
                ID = 7;
                break;
            case "8. En valoración":
                ID = 8;
                break;
            case "9. Empaquetado":
                ID = 9;
                break;
            case "10. Recibido por el cliente":
                ID = 10;
                break;
            default:
                ID = 0;
                break;
        }
        
        return ID;
    }
    
    public void guardarDatosMantenimiento(){
        if(estado.getSelectedToggle()!=null){
        try{
            dispositivo.setIdEstado(convertirEstadoAId());
            ResultOperation resultadoEditar = DispositivoDAO.editEstadoDispositivo(dispositivo);
            if(!resultadoEditar.isError()){
                ShowMessage.showAlertSimple("Estado de mantenimiento editado", resultadoEditar.getMessage(), Alert.AlertType.INFORMATION);
            }else{
                ShowMessage.showAlertSimple("Error al editar", resultadoEditar.getMessage(), Alert.AlertType.ERROR);
            }
        }catch(SQLException e){
            ShowMessage.showAlertSimple("Error de conexión", e.getMessage(), Alert.AlertType.ERROR);
        }
        }else{
            ShowMessage.showAlertSimple("Ningún estado seleccionado", "Debes seleccionar uno de los"
                    + "estados para poder guardar", Alert.AlertType.WARNING);
        }            
    }
    private void cerrarVentana(){
        Stage escenarioPrincipal = (Stage) txaComentariosCliente.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    private void clicCancelar(ActionEvent event) {
        cerrarVentana();
    }

}
