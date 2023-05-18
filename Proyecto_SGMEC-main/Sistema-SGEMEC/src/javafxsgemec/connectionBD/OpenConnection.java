package javafxsgemec.connectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnection {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD = "SGMEC";
    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    
    /*private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "gestiondetutorias";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String URL_CONEXION = "jdbc:mysql://"+HOSTNAME+":"+PORT+"/"+DATABASE+"?serverTimezone=UTC";
    */
    
    
    private static final String URLCONEXION = "jdbc:mysql://"+IP+":"+PUERTO+"/"+BD+"?allowPublicKeyRetrieval=true&useSSL=false"; 
    private static final String USUARIO = "AdminSGMEC";
    private static final String PASSWORD = "AdminSGMEC";
    
    public static Connection openConnectionBD(){
        Connection conexionBD = null;
        try {
            Class.forName(DRIVER);
            conexionBD = DriverManager.getConnection(URLCONEXION, USUARIO, PASSWORD);
        } catch (ClassNotFoundException ex ) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("//////////////"+conexionBD);
        return conexionBD;
    }
}
