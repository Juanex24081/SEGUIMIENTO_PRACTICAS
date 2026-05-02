/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas;

import java.sql.*;
public class Conexion {
 private static final String URL = "jdbc:mysql://localhost:3306/practicas";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error conexión: " + e.getMessage());
            return null;
        }
    }
}