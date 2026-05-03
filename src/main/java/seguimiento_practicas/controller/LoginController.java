package seguimiento_practicas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import seguimiento_practicas.util.Conexion;
import seguimiento_practicas.view.dashboard.*;

public class LoginController {

    public void login(String correo, String pass, JFrame vista) {

        String sql = "SELECT rol FROM usuarios WHERE correo=? AND contrasena=?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, correo);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String rol = rs.getString("rol");

                switch (rol) {

                    case "DIRECTOR" -> {
                        new DashboardDirector().setVisible(true);
                        vista.dispose();
                    }

                    case "ESTUDIANTE" -> {
                        new DashboardEstudiante().setVisible(true);
                        vista.dispose();
                    }

                    case "DOCENTE" -> {
                        new DashboardDocente().setVisible(true);
                        vista.dispose();
                    }

                    case "ASESOR" -> {
                        new DashboardAsesor().setVisible(true);
                        vista.dispose();
                    }

                    default -> JOptionPane.showMessageDialog(vista, "Rol no válido");
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Credenciales incorrectas");
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error BD: " + e.getMessage());
        }
    }
}



/*package seguimiento_practicas.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import seguimiento_practicas.view.dashboard.DashboardAsesor;
import seguimiento_practicas.view.dashboard.DashboardDirector;
import seguimiento_practicas.view.dashboard.DashboardDocente;
import seguimiento_practicas.view.dashboard.DashboardEstudiante;

public class LoginController {

    public void login(String correo, String pass, JFrame vista) {

        // SIN BASE DE DATOS
        System.out.println("LOGIN NUEVO SIN BD");

        if (correo.equals("director") && pass.equals("123")) {
            new DashboardDirector().setVisible(true);
            vista.dispose();

        } else if (correo.equals("estudiante") && pass.equals("123")) {
            new DashboardEstudiante().setVisible(true);
            vista.dispose();

        } else if (correo.equals("docente") && pass.equals("123")) {
            new DashboardDocente().setVisible(true);
            vista.dispose();

        } else if (correo.equals("asesor") && pass.equals("123")) {
            new DashboardAsesor().setVisible(true);
            vista.dispose();

        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas");
        }
    }
}*/