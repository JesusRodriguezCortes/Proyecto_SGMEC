package javafxsgemec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class javafxsgemec extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vistas/FXMLInicioSesion.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("javafxsgemec/recursos/img/ICONO.png"));
        stage.setScene(scene);
        stage.setTitle("SGEMEC");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
