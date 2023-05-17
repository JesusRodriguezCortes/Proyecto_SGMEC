package javafxsgemec.connectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnection {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String bd = "<DATABASE>";
    private static String ip = "<IPADDRESS>";
    private static String puerto = "<PORT>";
    private static String urlConexion = 
            "jdbc:mysql://"+ip+":"+puerto+"/"+bd+
            "?allowPublicKeyRetrieval=true&useSSL=false"; 
    private static String usuario = "<USER_NAME>";
    private static String password = "<PASSWORD>";
    
    public static Connection openConnectionBD(){
        Connection conexionBD = null;
        try {
            Class.forName(driver);
            conexionBD = DriverManager.getConnection(urlConexion, usuario, password);
        } catch (ClassNotFoundException ex ) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexionBD;
    }
}
