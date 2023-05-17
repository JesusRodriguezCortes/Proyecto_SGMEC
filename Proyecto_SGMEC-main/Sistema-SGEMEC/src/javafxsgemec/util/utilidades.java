
package javafxsgemec.util;

import javafx.scene.control.Alert;

public class utilidades {

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
