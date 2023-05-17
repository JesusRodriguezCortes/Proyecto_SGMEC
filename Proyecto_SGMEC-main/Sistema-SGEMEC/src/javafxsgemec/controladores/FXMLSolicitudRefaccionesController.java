/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author je_zu
 */
public class FXMLSolicitudRefaccionesController implements Initializable {

    @FXML
    private Label lbCantidad;
    
    int refaccionesCompradas = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
    }    

    @FXML
    private void btnAumentarCantidadRefacciones(ActionEvent event) {
        refaccionesCompradas++;
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
    }

    @FXML
    private void btnReducirCantidadRefacciones(ActionEvent event) {
        if(refaccionesCompradas > 0){
            refaccionesCompradas--;
            lbCantidad.setText(String.valueOf(refaccionesCompradas));
        }
    }

    @FXML
    private void btnAgregarRefaccion(ActionEvent event) {
       
    }

    @FXML
    private void btnEliminarRefaccion(ActionEvent event) {
    }

    @FXML
    private void btnRealizarPedido(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
