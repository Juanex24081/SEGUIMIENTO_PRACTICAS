package seguimiento_practicas.view.dashboard;

import seguimiento_practicas.panels.PanelAsesor;
import seguimiento_practicas.session.UsuarioSesion;

import javax.swing.*;
import java.awt.*;

public class DashboardAsesor extends JFrame {

    public DashboardAsesor() {

        setTitle("Panel Asesor Académico");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // =========================
        // TOPBAR
        // =========================

        JPanel topbar = new JPanel(new BorderLayout());

        topbar.setBackground(new Color(33,47,61));

        topbar.setPreferredSize(new Dimension(0,50));

        JLabel titulo = new JLabel(
                "ASESOR ACADÉMICO"
        );

        titulo.setForeground(Color.WHITE);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        titulo.setBorder(
                BorderFactory.createEmptyBorder(0,20,0,0)
        );

        topbar.add(titulo, BorderLayout.WEST);

        // =========================
        // USUARIO
        // =========================

        JLabel usuario = new JLabel(
                UsuarioSesion.usuarioActual.getNombre()
        );

        usuario.setForeground(Color.WHITE);

        usuario.setBorder(
                BorderFactory.createEmptyBorder(0,0,0,20)
        );

        topbar.add(usuario, BorderLayout.EAST);

        add(topbar, BorderLayout.NORTH);

        // =========================
        // PANEL PRINCIPAL
        // =========================

        add(

            new PanelAsesor(
                                (int) UsuarioSesion.usuarioActual.getId()
            ),

            BorderLayout.CENTER
        );

        setVisible(true);
    }
}