package javafxsgemec.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsgemec.dao.DispositivoDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.Dispositivo;
import javafxsgemec.util.ShowMessage;


public class FXMLEquiposClienteController implements Initializable {
    private Button idDetallesDelEquipo;
    ArrayList<Dispositivo> dispositivos;
    @FXML
    private GridPane gpEquipos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDispositivos();
        startGridPane();
    }    


    @FXML
    private void clicBack(MouseEvent event) throws IOException {
        String ventana = "vistas/FXMLCliente.fxml";
        Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ventana));
        Scene escenaPrincipal = new Scene(vista);
        Stage escenarioBase = (Stage) gpEquipos.getScene().getWindow();
        escenarioBase.setScene(escenaPrincipal);
        escenarioBase.show();
    }

    private void clicDetallesDelEquipo(ActionEvent event) throws IOException {
        
    }
    private BorderPane configPane(Dispositivo dis){
        BorderPane bpEquipo=new BorderPane();
        Image img =new Image("javafxsgemec/recursos/img/comp.png");
        ImageView ivEquipo=new ImageView(img);
        StackPane stackP =new StackPane();
        StackPane stackBu=new StackPane();
        StackPane stackMarca=new StackPane();
        Button b1=new Button("Ver detalles");
        b1.setOnAction(event -> {
            try {
                // Código a ejecutar cuando se hace clic en el botón
                abrirDetallesDispositivo(dis.getIdDispositivo());
            } catch (IOException ex) {
                Logger.getLogger(FXMLEquiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEquiposClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Label lbMarca=new Label();
        Label lbModelo=new Label(); 
        Label lbCliente=new Label();
        lbMarca.setText(dis.getMarca());
        lbModelo.setText(dis.getModelo());
        lbCliente.setText(dis.getNombreCliente());
        b1.setPrefHeight(5);
        b1.setPrefWidth(80);
        b1.setFont(Font.font(11));
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
        bpEquipo.setStyle("-fx-background-color: lightgray;");
        bpEquipo.setTop(stackP);
        bpEquipo.setLeft(lbCliente);
        bpEquipo.setRight(lbModelo);
        bpEquipo.setCenter(lbMarca);
        bpEquipo.setBottom(stackBu);
        return bpEquipo;
    }
    private void startGridPane(){
        int numCol = gpEquipos.getColumnConstraints().size();
        int numFil = gpEquipos.getRowConstraints().size();
        int posDisp=0;
        gpEquipos.setHgap(5);
        gpEquipos.setVgap(10);
        for (int row = 0; row < numFil; row++) {
            for (int col = 0; col < numCol; col++) {
                if(posDisp<dispositivos.size()){
                    gpEquipos.add(configPane(dispositivos.get(posDisp)), col, row);
                    posDisp++;
                }
            }
        }
    }

    private void abrirDetallesDispositivo(int idDispositivo) throws IOException, SQLException {
        try{ 
            String ventana = "vistas/FXMLDetallesDelEquipo.fxml";
            FXMLLoader accesoControllador =new FXMLLoader(javafxsgemec.class.getResource(ventana));
            Parent vista = accesoControllador.load();
            FXMLDetallesDelEquipoController  form=accesoControllador.getController();
            form.prepararEquipo(idDispositivo);
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioNuevo = new Stage();
            escenarioNuevo.setScene(escenaFormulario);
            escenarioNuevo.initModality(Modality.APPLICATION_MODAL);
            escenarioNuevo.showAndWait();
            
        } catch (SQLException sqlex) {
            ShowMessage.showAlertSimple(
                    "Error de SQL", 
                    "Error en la consulta", 
                    Alert.AlertType.ERROR
            );
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
