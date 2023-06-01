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
import javafxsgemec.pojo.Cliente;

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
        cargarDatosDispositivo();
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
    
    public void cargarDatosDispositivo(){
       this.lbMarca.setText(equipo.getMarca());
       this.lbModelo.setText(equipo.getModelo());
       this.lbUsuario.setText(equipo.getUsuario());
       this.lbContrasenia.setText(equipo.getPassword().replaceAll(this.lbContrasenia.getText(), "*"));
       this.lbProblemas.setText(equipo.getErrorDispos());
       switch(equipo.getIdEstado()){
            case 1:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(false);
                cbValoracion.setSelected(false);
                cbReparacion.setSelected(false);
                cbEmpaquetado.setSelected(false);
                cbEnviadoACliente.setSelected(false);
                cbRecibidoCliente.setSelected(false);
               break;
            case 2:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(false);
                cbReparacion.setSelected(false);
                cbEmpaquetado.setSelected(false);
                cbEnviadoACliente.setSelected(false);
                cbRecibidoCliente.setSelected(false);
               break;
            case 3:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(true);
                cbReparacion.setSelected(false);
                cbEmpaquetado.setSelected(false);
                cbEnviadoACliente.setSelected(false);
                cbRecibidoCliente.setSelected(false);
               break;       
            case 4:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(true);
                cbReparacion.setSelected(true);
                cbEmpaquetado.setSelected(false);
                cbEnviadoACliente.setSelected(false);
                cbRecibidoCliente.setSelected(false);
               break;
            case 5:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(true);
                cbReparacion.setSelected(true);
                cbEmpaquetado.setSelected(true);
                cbEnviadoACliente.setSelected(false);
                cbRecibidoCliente.setSelected(false);
               break;
            case 6:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(true);
                cbReparacion.setSelected(true);
                cbEmpaquetado.setSelected(true);
                cbEnviadoACliente.setSelected(true);
                cbRecibidoCliente.setSelected(false);
               break;
            case 7:
                cbEnviadoAEmpresa.setSelected(true);
                cbRecibidoEmpresa.setSelected(true);
                cbValoracion.setSelected(true);
                cbReparacion.setSelected(true);
                cbEmpaquetado.setSelected(true);
                cbEnviadoACliente.setSelected(true);
                cbRecibidoCliente.setSelected(true);
               break;
       }
    }
    
    public void obtenerEquipo(int idEquipo) throws SQLException{
        Dispositivo dps=DispositivoDAO.getDispositivo(idEquipo);
        this.equipo=dps;
    }
}
