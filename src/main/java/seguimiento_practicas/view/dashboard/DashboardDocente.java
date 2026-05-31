package seguimiento_practicas.view.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import seguimiento_practicas.panels.PanelConvenios;
import seguimiento_practicas.panels.PanelCrearUsuario;
import seguimiento_practicas.panels.PanelGruposDocente;
import seguimiento_practicas.panels.PanelSesionesDocente;
import seguimiento_practicas.panels.PanelUsuarios;
import seguimiento_practicas.util.SideBar;

public class DashboardDocente extends JFrame {

    public DashboardDocente() {

        // SIDEBAR
        SideBar sidebarUtil = new SideBar();

        setTitle("Panel Docente");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // NAVBAR SUPERIOR
        JPanel topbar = new JPanel(new BorderLayout());
        topbar.setBackground(new Color(33, 47, 61));
        topbar.setPreferredSize(new Dimension(0, 50));

        JButton btnMenu = new JButton("☰");
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setBackground(new Color(33, 47, 61));
        btnMenu.setBorderPainted(false);

        btnMenu.addActionListener(e -> sidebarUtil.toggleSidebar());

        JLabel usuario = new JLabel("Docente");
        usuario.setForeground(Color.WHITE);
        usuario.setFont(new Font("Times New Roman", Font.BOLD, 16));

        topbar.add(btnMenu, BorderLayout.WEST);
        topbar.add(usuario, BorderLayout.EAST);

        add(topbar, BorderLayout.NORTH);

        // SIDEBAR
        sidebarUtil.sidebar = new JPanel();
        sidebarUtil.sidebar.setLayout(new BoxLayout(sidebarUtil.sidebar, BoxLayout.Y_AXIS));
        sidebarUtil.sidebar.setPreferredSize(new Dimension(220, 0));
        sidebarUtil.sidebar.setBackground(new Color(44, 62, 80));

        JButton btnGrupos = sidebarUtil.crearBoton("Grupos", "practice.png");
        JButton btnSesionesDocente = sidebarUtil.crearBoton("Sesiones", "user.png");

        sidebarUtil.sidebar.add(Box.createVerticalStrut(20));
        sidebarUtil.sidebar.add(btnGrupos);
        sidebarUtil.sidebar.add(Box.createVerticalStrut(20));
        sidebarUtil.sidebar.add(btnSesionesDocente);

        add(sidebarUtil.sidebar, BorderLayout.WEST);

        add(sidebarUtil.sidebar, BorderLayout.WEST);

        // PANEL CONTENIDO
        sidebarUtil.panelContenido = new JPanel(new BorderLayout());
        add(sidebarUtil.panelContenido, BorderLayout.CENTER);

        // EVENTOS
        btnGrupos.addActionListener(e -> {
            sidebarUtil.activarBoton(btnGrupos);
            sidebarUtil.cambiarPanel(new PanelGruposDocente());
        });

        btnSesionesDocente.addActionListener(e -> {
            sidebarUtil.activarBoton(btnSesionesDocente);
            sidebarUtil.cambiarPanel(new PanelSesionesDocente());
        });

        // INICIAL
        sidebarUtil.activarBoton(btnSesionesDocente);
        sidebarUtil.cambiarPanel(new PanelSesionesDocente());
    }

    private JPanel panelVacio(String nombre) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Módulo " + nombre));
        return panel;
    }
}

