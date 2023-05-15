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
import javafx.scene.control.Alert;
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
    public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }

    public static boolean compararString(Object obj,String miCadena) {
        if (obj instanceof String) {
            String otraCadena = (String) obj;
            // Obtener la longitud de las dos cadenas
            int longitud = miCadena.length();
            // Comprobar si las dos cadenas tienen la misma longitud
            if (longitud == otraCadena.length()) {
                // Comparar caracter por caracter
                for (int i = 0; i < longitud; i++) {
                    if (miCadena.charAt(i) != otraCadena.charAt(i)) {
                        return false;
                    }
                }
                // Si todas las comparaciones son iguales, las dos cadenas son iguales
                return true;
            }
        }
        return false;
    }
}
