package seguimiento_practicas.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import seguimiento_practicas.dao.UsuarioDAO;
import seguimiento_practicas.model.Usuario;
import seguimiento_practicas.session.UsuarioSesion;
import seguimiento_practicas.view.dashboard.DashboardAsesor;
import seguimiento_practicas.view.dashboard.DashboardDirector;
import seguimiento_practicas.view.dashboard.DashboardDocente;
import seguimiento_practicas.view.dashboard.DashboardEstudiante;

public class LoginController {

    private final UsuarioDAO dao = new UsuarioDAO();

    public void login(String correo, String pass, JFrame vista) {

        Usuario usuario = dao.login(correo, pass);

        if (usuario == null) {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas");
            return;
        }

        UsuarioSesion.usuarioActual = usuario;

        abrirDashboard(usuario, vista);
    }

    private void abrirDashboard(Usuario u, JFrame vista) {

        switch (u.getRol()) {

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

            default -> JOptionPane.showMessageDialog(vista, "Rol inválido");
        }
    }
}