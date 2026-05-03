/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas.controller;

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
}