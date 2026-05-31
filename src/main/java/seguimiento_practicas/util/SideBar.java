package seguimiento_practicas.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SideBar {
    
    public JPanel panelContenido;
    public JPanel sidebar;
    public JButton botonActivo = null;
    public boolean sidebarVisible = true;

    // ACTIVAR BOTÓN
    public void activarBoton(JButton btn) {
        if (botonActivo != null) {
            botonActivo.setBackground(new Color(52, 73, 94));
        }
        btn.setBackground(new Color(39, 174, 96));
        botonActivo = btn;
    }

    // BOTÓN CON ICONO
    public JButton crearBoton(String texto, String icono) {
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
    public void cambiarPanel(JPanel nuevo) {
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
    public void toggleSidebar() {
        sidebarVisible = !sidebarVisible;

        if (sidebarVisible) {
            sidebar.setPreferredSize(new Dimension(220, 0));
        } else {
            sidebar.setPreferredSize(new Dimension(60, 0));
        }

        sidebar.revalidate();
    }
    
}
