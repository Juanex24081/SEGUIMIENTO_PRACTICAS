package seguimiento_practicas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:oracle:thin:@localhost:1522:ORCL";
    private static final String USER = "practicas";
    private static final String PASS = "practicas";

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexión exitosa a Oracle");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}