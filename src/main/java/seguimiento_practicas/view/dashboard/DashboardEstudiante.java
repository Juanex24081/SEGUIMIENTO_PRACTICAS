/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas.view.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import seguimiento_practicas.panels.PanelBitacoras;
import seguimiento_practicas.panels.PanelResumen;
import seguimiento_practicas.panels.PanelSesiones;

public class DashboardEstudiante extends JFrame {

    private JPanel panelContenido;
    private JPanel sidebar;
    private JButton botonActivo = null;
    private boolean sidebarVisible = true;

    // ACTIVAR BOTÓN
    private void activarBoton(JButton btn) {
        if (botonActivo != null) {
            botonActivo.setBackground(new Color(52, 73, 94));
        }
        btn.setBackground(new Color(39, 174, 96));
        botonActivo = btn;
    }

    // BOTÓN CON ICONO
    private JButton crearBoton(String texto, String icono) {
    JButton btn = new JButton(texto);

    try {
        java.net.URL url = getClass().getResource("/icons/" + icono);

        /*System.out.println(System.getProperty("user.dir"));*/
        /*System.out.println(getClass().getResource("/icons/user.png"));*/

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);

            // FORZAR TAMAÑO SIEMPRE
            Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);

            btn.setIcon(new ImageIcon(img));
        } else {
            System.out.println("No se encontró el icono: " + icono);
        }

        } catch (Exception e) {
            System.out.println("Error cargando icono: " + icono);
        }

        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(10);

        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Times New Roman", Font.BOLD, 14));

        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setMaximumSize(new Dimension(200, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        return btn;
    }

    // CAMBIO DE PANEL CON "ANIMACIÓN"
    private void cambiarPanel(JPanel nuevo) {
        panelContenido.removeAll();

        // animación simple (fade simulada)
        Timer timer = new Timer(10, null);
        timer.addActionListener(e -> {
            panelContenido.add(nuevo, BorderLayout.CENTER);
            panelContenido.revalidate();
            panelContenido.repaint();
            timer.stop();
        });
        timer.start();
    }

    // SIDEBAR COLAPSABLE
    private void toggleSidebar() {
        sidebarVisible = !sidebarVisible;

        if (sidebarVisible) {
            sidebar.setPreferredSize(new Dimension(220, 0));
        } else {
            sidebar.setPreferredSize(new Dimension(60, 0));
        }

        sidebar.revalidate();
    }

    public DashboardEstudiante() {

        setTitle("Panel Estudiante");
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

        btnMenu.addActionListener(e -> toggleSidebar());

        JLabel usuario = new JLabel("Estudiante");
        usuario.setForeground(Color.WHITE);
        usuario.setFont(new Font("Times New Roman", Font.BOLD, 16));

        topbar.add(btnMenu, BorderLayout.WEST);
        topbar.add(usuario, BorderLayout.EAST);

        add(topbar, BorderLayout.NORTH);

        // SIDEBAR
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(44, 62, 80));

        JButton btnResumen = crearBoton("Resumen", "user.png");
        JButton btnSesiones = crearBoton("Sesiones", "user.png");
        JButton btnBitacoras = crearBoton("Bitácoras", "user.png");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnResumen);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnSesiones);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnBitacoras);

        add(sidebar, BorderLayout.WEST);

        // PANEL CONTENIDO
        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);

        // EVENTOS
        btnResumen.addActionListener(e -> {
            activarBoton(btnResumen);
            cambiarPanel(new PanelResumen());
        });

        btnSesiones.addActionListener(e -> {
            activarBoton(btnSesiones);
            cambiarPanel(new PanelSesiones());
        });

        btnBitacoras.addActionListener(e -> {
            activarBoton(btnBitacoras);
            cambiarPanel(new PanelBitacoras());
        });

        // INICIAL
        activarBoton(btnSesiones);
        cambiarPanel(new PanelSesiones());
    }

    // PANEL VACÍO (TEMPLATE)

    /* private JPanel panelVacio(String nombre) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Módulo " + nombre));
        return panel;
    } */ 

}
