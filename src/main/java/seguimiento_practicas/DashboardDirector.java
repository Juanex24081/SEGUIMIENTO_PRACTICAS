package seguimiento_practicas;

import java.awt.*;
import javax.swing.*;

public class DashboardDirector extends JFrame {

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
        java.net.URL url = getClass().getResource("/src/icons/" + icono);

        System.out.println(System.getProperty("user.dir"));
        System.out.println(getClass().getResource("/icons/user.png"));

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

    public DashboardDirector() {

        setTitle("Panel Director");
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

        JLabel usuario = new JLabel("Director");
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

        JButton btnUsuarios = crearBoton("Usuarios", "user.png");
        JButton btnCrear = crearBoton("Crear", "add.png");
        JButton btnPracticas = crearBoton("Prácticas", "practice.png");
        JButton btnConvenios = crearBoton("Convenios","convenio.png");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnUsuarios);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnCrear);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnPracticas);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnConvenios);

        add(sidebar, BorderLayout.WEST);

        // PANEL CONTENIDO
        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);

        // EVENTOS
        btnUsuarios.addActionListener(e -> {
            activarBoton(btnUsuarios);
            cambiarPanel(new PanelUsuarios());
        });

        btnCrear.addActionListener(e -> {
            activarBoton(btnCrear);
            cambiarPanel(new PanelCrearUsuario());
        });

        btnPracticas.addActionListener(e -> {
            activarBoton(btnPracticas);
            cambiarPanel(panelVacio("Prácticas"));
        });

        btnConvenios.addActionListener(e -> {
            activarBoton(btnConvenios);
            cambiarPanel(panelVacio("Convenios"));
        });

        // INICIAL
        activarBoton(btnUsuarios);
        cambiarPanel(new PanelUsuarios());
    }

    private JPanel panelVacio(String nombre) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Módulo " + nombre));
        return panel;
    }
}