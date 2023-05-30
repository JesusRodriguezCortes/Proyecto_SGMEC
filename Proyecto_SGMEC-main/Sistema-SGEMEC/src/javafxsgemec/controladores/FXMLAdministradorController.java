package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsgemec.javafxsgemec;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class FXMLAdministradorController implements Initializable {

    @FXML
    private Button btnPedidos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRealizarPedido(ActionEvent event) {
        try {
           Parent vista = FXMLLoader.load(javafxsgemec.class.getResource("vistas/FXMLSolicitudRefacciones.fxml"));
           Scene escenaPrincipal = new Scene(vista);
           Stage escenarioBase = (Stage) btnPedidos.getScene().getWindow();
           escenarioBase.setScene(escenaPrincipal);
           escenarioBase.show(); 
        } catch (IOException ex) {
            Logger.getLogger(FXMLAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
