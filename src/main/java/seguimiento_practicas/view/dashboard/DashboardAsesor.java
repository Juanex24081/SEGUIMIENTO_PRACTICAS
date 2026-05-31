package seguimiento_practicas.view.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import seguimiento_practicas.panels.PanelBitacorasAsesor;
import seguimiento_practicas.panels.PanelSesiones;
import seguimiento_practicas.session.UsuarioSesion;
import seguimiento_practicas.util.SideBar;

public class DashboardAsesor extends JFrame {

    public DashboardAsesor() {

        // SIDEBAR
        SideBar sidebarUtil = new SideBar();

        setTitle("Panel Asesor");
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

        JLabel usuario = new JLabel("Asesor");
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

        JButton btnSesiones = sidebarUtil.crearBoton("Sesiones", "user.png");
        JButton btnBitacoras = sidebarUtil.crearBoton("Bitácoras", "user.png");

        sidebarUtil.sidebar.add(Box.createVerticalStrut(20));
        sidebarUtil.sidebar.add(btnSesiones);
        sidebarUtil.sidebar.add(Box.createVerticalStrut(10));
        sidebarUtil.sidebar.add(btnBitacoras);

        add(sidebarUtil.sidebar, BorderLayout.WEST);

        // PANEL CONTENIDO
        sidebarUtil.panelContenido = new JPanel(new BorderLayout());
        add(sidebarUtil.panelContenido, BorderLayout.CENTER);

        // EVENTOS
        btnSesiones.addActionListener(e -> {
            sidebarUtil.activarBoton(btnSesiones);
            sidebarUtil.cambiarPanel(new PanelSesiones());
        });

        btnBitacoras.addActionListener(e -> {
            sidebarUtil.activarBoton(btnBitacoras);
            sidebarUtil.cambiarPanel(new PanelBitacorasAsesor((int) UsuarioSesion.usuarioActual.getId()));
        });

        // INICIAL
        sidebarUtil.activarBoton(btnSesiones);
        sidebarUtil.cambiarPanel(new PanelSesiones());
       


        // =========================
        // USUARIO
        // =========================

        JLabel usuarioActual = new JLabel(
                UsuarioSesion.usuarioActual.getNombre()
        );

        usuarioActual.setForeground(Color.WHITE);

        usuarioActual.setBorder(
                BorderFactory.createEmptyBorder(0,0,0,20)
        );

        topbar.add(usuarioActual, BorderLayout.EAST);

        add(topbar, BorderLayout.NORTH);

        // =========================
        // PANEL PRINCIPAL
        // =========================

        /*add(

            new PanelAsesor( //REVISAR ASKJFASJOPDJASNPJVCPASJEDPAs
                (int) UsuarioSesion.usuarioActual.getId()
            ),

            BorderLayout.CENTER
        );*/

        setVisible(true);
    }
}