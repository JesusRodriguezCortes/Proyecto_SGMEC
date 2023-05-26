package javafxsgemec.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafxsgemec.javafxsgemec;

public class StartScene {
        public static Scene initializeScene(String ruta){
        Scene escenaPrincipal = null;
        try {
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ruta));
            escenaPrincipal = new Scene(vista);
        } catch (IOException ex) {
            Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
        }
        return escenaPrincipal;
    }
    
}
