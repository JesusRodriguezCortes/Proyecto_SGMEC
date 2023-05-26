package javafxsgemec.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafxsgemec.dao.CotizacionDAO;
import javafxsgemec.dao.DiagnosticoDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Cotizacion;
import javafxsgemec.pojo.Diagnostico;
import javafxsgemec.pojo.Dispositivo;
import javafxsgemec.util.ShowMessage;


public class FXMLRealizarDiagnosticoController implements Initializable {

    private Text txtMarca;
    @FXML
    private ImageView ivComputadora;
    @FXML
    private TextArea txarDiagnostico;
    private TextArea txarCotizacion;
    @FXML
    private TextFlow txfwComentarios;
    private Text txtModelo;
    private Text txtUsuario;
    private Text txtContrasenia;
    private Dispositivo equipo;
    @FXML
    private Label lbMarca;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbContrasenia;
    @FXML
    private TextField lbActualizaciones;
    @FXML
    private TextField lbHardware;
    @FXML
    private TextField lbCostoRepuesto;
    @FXML
    private TextField lbSoftware;
    @FXML
    private TextField lbServicio;
    @FXML
    private TextField lbManoDeObra;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbNumeros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validarSiempreNumeros();
    }    


    @FXML
    private void clicCancelar(ActionEvent event) throws IOException {
            String ventana = "vistas/FXMLEquiposEncargado.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) lbMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }


    @FXML
    private void clicBack(MouseEvent event) throws IOException {
            String ventana = "vistas/FXMLEquiposEncargado.fxml";
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) lbMarca.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
    }

    @FXML
    private void clicAgregarRefaccion(ActionEvent event) {
    }

    @FXML
    private void clicEnviarCotizacion(ActionEvent event) throws SQLException {
        System.out.println(txarDiagnostico.getText().length());
        if((txarDiagnostico.getText().length()==0))
          ShowMessage.showAlertSimple("CAMPOS VACIOS", "Por favor asegurese de llenar los campos vacios", Alert.AlertType.WARNING);
        else{
            Diagnostico diag= new Diagnostico();
            diag.setResultadoDiagnostico(txarDiagnostico.getText());
            diag.setIdDispositivo(equipo.getIdDispositivo());
            diag.setIdServicio(1);
            Cotizacion cotizacion=new Cotizacion(
                Double.parseDouble((lbSoftware.getText().isEmpty()?"0":lbSoftware.getText())),
                Double.parseDouble((lbHardware.getText().isEmpty()?"0":lbHardware.getText())),
                Double.parseDouble((lbActualizaciones.getText().isEmpty()? "0":lbActualizaciones.getText())),
                Double.parseDouble((lbServicio.getText().isEmpty()?"0":lbServicio.getText())),
                Double.parseDouble((lbCostoRepuesto.getText().isEmpty()?"0":lbCostoRepuesto.getText())),
                Double.parseDouble((lbManoDeObra.getText().isEmpty()?"0":lbManoDeObra.getText())),
                equipo.getIdDispositivo());
            DiagnosticoDAO.createDiagnostico(diag);
            CotizacionDAO.createCotizacion(cotizacion);
            
        }
    }

    public void prepararEquipo(Dispositivo dispositivo) {
        this.equipo=dispositivo;
        lbMarca.setText(dispositivo.getMarca());
        lbModelo.setText(dispositivo.getModelo());
        lbContrasenia.setText(dispositivo.getPasswordDisp());
        lbUsuario.setText(dispositivo.getUsuarioDisp());
        txfwComentarios.setTextAlignment(TextAlignment.LEFT);
        txfwComentarios.getChildren().add(new Text(dispositivo.getError()));
    }

    @FXML
    private void clicVerImagenes(ActionEvent event) throws IOException {
        String ventana = "vistas/FXMLImagenCliente.fxml";
        FXMLLoader accesoControllador = new FXMLLoader(javafxsgemec.class.getResource(ventana));
        Parent vista = accesoControllador.load();
        FXMLImagenClienteController form=accesoControllador.getController();
        System.out.println("Esta es la imagen "+this.equipo.getFoto());
        form.prepararDispositivo(this.equipo);
        Scene escenaNueva=new Scene(vista);
        Stage escenarioNuevo=new Stage();
        escenarioNuevo.setScene(escenaNueva);
        escenarioNuevo.showAndWait(); 
    }
    public void validarSiempreNumeros(){
        lbActualizaciones.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                updateSum();
                return change;
            }
            return null;
        }));
        lbCostoRepuesto.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                updateSum();
                return change;
            }
            return null;
        }));
        lbSoftware.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                updateSum();
                return change;
            }
            return null;
            
        }));
        lbServicio.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        }));
        lbManoDeObra.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        }));
        lbHardware.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                updateSum();
                return change;
            }
            return null;
        }));
        
    }

    private void updateSum() {
        try{
        double suma=Double.parseDouble((lbActualizaciones.getText().isEmpty()? "0":lbActualizaciones.getText()))+
                Double.parseDouble((lbCostoRepuesto.getText().isEmpty()?"0":lbCostoRepuesto.getText()))+
                Double.parseDouble((lbSoftware.getText().isEmpty()?"0":lbSoftware.getText()))+
                Double.parseDouble((lbServicio.getText().isEmpty()?"0":lbServicio.getText()))+
                Double.parseDouble((lbManoDeObra.getText().isEmpty()?"0":lbManoDeObra.getText()))+
                Double.parseDouble((lbHardware.getText().isEmpty()?"0":lbHardware.getText()));
        lbTotal.setText("$ "+Double.toString(suma));
        }catch(NumberFormatException ne){
            lbNumeros.setText("Ingrese numeros");
        }
    }
    
}
