package javafxsgemec.controladores;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsgemec.dao.DispositivoDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Dispositivo;
import javafxsgemec.util.ShowMessage;


public class FXMLEquiposEncargadoController implements Initializable {
    ArrayList<Dispositivo> dispositivos;
    private Button idRealizarDiagnostico;
    @FXML
    private GridPane gpEquipos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDispositivos();
        startGridPane();
    }    

    @FXML
    private void clicBack(MouseEvent event) throws IOException {
        String ventana = "vistas/FXMLEncargadoMantenimiento.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) gpEquipos.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();
    }

    private void clicRealizarDiagnostico(ActionEvent event) throws IOException {
        String ventana = "vistas/FXMLRealizarDiagnostico.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) idRealizarDiagnostico.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();
    }
        private BorderPane configPane(Dispositivo dis){
        DropShadow contorno = new DropShadow();            
        BorderPane bpEquipo=new BorderPane();
        BorderPane centro=new BorderPane();
        BorderPane left=new BorderPane();
        BorderPane right=new BorderPane();
        Image img =new Image("javafxsgemec/recursos/img/comp.png");
        ImageView ivEquipo=new ImageView(img);
        StackPane stackP =new StackPane();
        StackPane stackBu=new StackPane();
        StackPane stackMarca=new StackPane();
        Button b1=new Button("Realizar Diagnostico");
        Label lbMarca=new Label();
        Label lbModelo=new Label(); 
        Label lbCliente=new Label();
        b1.setOnAction(event -> {
            try {
                abrirDetallesDispositivo(dis);
            } catch (IOException ex) {
                Logger.getLogger(FXMLEquiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEquiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        left.setTop(new Label("Cliente"));
        lbCliente.setText(dis.getNombreCliente());
        left.setBottom(lbCliente);
        centro.setTop(new Label("Marca"));
        lbMarca.setText(dis.getMarca());
        centro.setBottom(lbMarca);
        lbModelo.setText(dis.getModelo());
        b1.setPrefHeight(5);
        b1.setPrefWidth(120);
        b1.setFont(Font.font(10));
        right.setTop(new Label("Modelo"));
        right.setBottom(lbModelo);
        ivEquipo.setLayoutX(50);
        ivEquipo.setLayoutY(50);
        ivEquipo.setPreserveRatio(true);
        ivEquipo.setFitHeight(50);
        ivEquipo.setFitWidth(50);
        stackP.getChildren().add(ivEquipo);
        stackP.setAlignment(Pos.TOP_CENTER);
        stackBu.getChildren().add(b1);
        stackBu.setAlignment(Pos.BOTTOM_CENTER);
        stackMarca.setAlignment(Pos.CENTER);
        contorno.setColor(javafx.scene.paint.Color.BLACK);
        contorno.setWidth(10);
        contorno.setHeight(10);
        bpEquipo.setEffect(contorno);
        bpEquipo.setStyle("-fx-background-color: lightgray;");
        bpEquipo.setTop(stackP);
        bpEquipo.setLeft(left);
        bpEquipo.setRight(right);
        bpEquipo.setCenter(centro);
        bpEquipo.setBottom(stackBu);
        return bpEquipo;
    }
    private void startGridPane(){
        int numCol = gpEquipos.getColumnConstraints().size();
        int numFil = gpEquipos.getRowConstraints().size();
        int posDisp=0;
        gpEquipos.setHgap(10);
        gpEquipos.setVgap(90);
        for (int row = 0; row < numFil; row++) {
            for (int col = 0; col < numCol; col++) {
                if(posDisp<dispositivos.size()){
                    gpEquipos.add(configPane(dispositivos.get(posDisp)), col, row);
                    posDisp++;
                }
            }
        }
    }

    private void abrirDetallesDispositivo(Dispositivo dispositivo) throws IOException, SQLException {
        try{ 
            String ventana = "vistas/FXMLRealizarDiagnostico.fxml";
            FXMLLoader accesoControllador =new FXMLLoader(javafxsgemec.class.getResource(ventana));
            Parent vista = accesoControllador.load();
            FXMLRealizarDiagnosticoController  form=accesoControllador.getController();
            form.prepararEquipo(dispositivo);
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioNuevo =(Stage)gpEquipos.getScene().getWindow();
            escenarioNuevo.setScene(escenaFormulario);
            //escenarioNuevo.initModality(Modality.APPLICATION_MODAL);
            escenarioNuevo.show();
        } catch (NullPointerException ioe){
            ShowMessage.showAlertSimple(
                    "Error de puntero", 
                    "Objeto vacio", 
                    Alert.AlertType.ERROR
            );
        }
    }

    private void cargarDispositivos() {
        try{
             dispositivos=DispositivoDAO.getDispositivos();           
        }catch(SQLException ex){
            ShowMessage.showAlertSimple("Error de consulta", "Hay un error de consulta", Alert.AlertType.ERROR);
        }

    }
    
}
