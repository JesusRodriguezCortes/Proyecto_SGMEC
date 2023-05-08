/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgemec.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafxsgemec.javafxsgemec;

/**
 *
 * @author Cesar
 */
public class utilidades {
        public static Scene inicializarEscena(String ruta){
        Scene escenaPrincipal = null;
        try {
            Parent vista = FXMLLoader.load(javafxsgemec.class.getResource(ruta));
            escenaPrincipal = new Scene(vista);
        } catch (IOException ex) {
            Logger.getLogger(utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return escenaPrincipal;
    }
    
}
