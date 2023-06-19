/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxsgemec.connectionBD.ResultOperation;
import javafxsgemec.dao.DispositivoDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Dispositivo;
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
    @FXML
    private ToggleGroup estado;
    @FXML
    private ComboBox<Dispositivo> cbxSeleccionDispositivo;
    private ObservableList<Dispositivo> listaDispositivos = FXCollections.observableArrayList();
    @FXML
    private RadioButton rb4;
    @FXML
    private RadioButton rb5;
    @FXML
    private RadioButton rb6;
    @FXML
    private RadioButton rb7;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb3;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarRadioButtons();
        
        
        cbxSeleccionDispositivo.valueProperty().addListener(new ChangeListener<Dispositivo>() {
        @Override
        public void changed(ObservableValue<? extends Dispositivo> observable, Dispositivo oldValue, Dispositivo newValue){
            if(newValue != null){
                lbMarca.setText(newValue.getMarca());
                lbModelo.setText(newValue.getModelo());
                txaComentariosCliente.setText(newValue.getErrorDispos());
                lbNombreCliente.setText(newValue.getNombreCliente());
                
            }
        }
    });       
        
    }
    
    void iniciarRadioButtons(){
        rb1.setUserData("1. Enviado por el cliente");
        rb2.setUserData("2. Recibido por la empresa");
        rb3.setUserData("3. En valoración");
        rb4.setUserData("4. En reparación");
        rb5.setUserData("5. Empaquetado");
        rb6.setUserData("6. Enviado por la empresa");
        rb7.setUserData("7. Recibido por el cliente");
        
    }
        
    public int convertirEstadoAId(){
        int ID;     
        switch(estado.getSelectedToggle().getUserData().toString()){
            case "1. Enviado por el cliente":
                ID = 1;
                break;
            case "2. Recibido por la empresa":
                ID = 2;
                break;
            case "3. En valoración":
                ID = 3;
                break;
            case "4. En reparación":
                ID = 4;
                break;
            case "5. Empaquetado":
                ID = 5;
                break;
            case "6. Enviado por la empresa":
                ID = 6;
                break;
            case "7. Recibido por el cliente":
                ID = 7;
                break;
            default:
                ID = 0;
                break;
        }
        
        return ID;
    }
    
    public void guardarDatosMantenimiento() throws SQLException{
        if(estado.getSelectedToggle()!=null){
            dispositivo.setIdEstado(convertirEstadoAId());
            ResultOperation resultadoEditar = DispositivoDAO.editEstadoDispositivo(dispositivo);
            if(!resultadoEditar.isError()){
                ShowMessage.showAlertSimple("Estado de mantenimiento editado", resultadoEditar.getMessage(), Alert.AlertType.INFORMATION);
            }else{
                ShowMessage.showAlertSimple("Error al editar", resultadoEditar.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            ShowMessage.showAlertSimple("Ningún estado seleccionado", "Debes seleccionar uno de los"
                    + "estados para poder guardar", Alert.AlertType.WARNING);
        }            
    }
    
    
    private void limpiarDatos(){
        cbxSeleccionDispositivo.getSelectionModel().select(-1);
        lbMarca.setText("");
        lbModelo.setText("");
        txaComentariosCliente.setText("");
        lbNombreCliente.setText("");
    }
    
    @FXML
    private void clicCancelar(ActionEvent event) throws IOException {
        String ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) txaComentariosCliente.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();
    }

    @FXML
    private void cbxAccion(ActionEvent event) {
        limpiarDatos();
        cbxSeleccionDispositivo.valueProperty().addListener(new ChangeListener<Dispositivo>() {
        @Override
        public void changed(ObservableValue<? extends Dispositivo> observable, Dispositivo oldValue, Dispositivo newValue){
            if(newValue != null){
                lbMarca.setText(newValue.getMarca());
                lbModelo.setText(newValue.getModelo());
                txaComentariosCliente.setText(newValue.getErrorDispos());
                lbNombreCliente.setText(newValue.getNombreCliente());
                }
        }
        }); 
    }

}
