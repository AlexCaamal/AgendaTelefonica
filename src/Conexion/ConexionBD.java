package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    public static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String user="root";
    private static final String pass="";
    private static final String url="jdbc:mysql://localhost:3306/db_agenda_telefonico";
    
      public static Connection GetConnection() {
        try {
            
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            
            return con;
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
        return con;
    }
}
