package seguimiento_practicas;

import java.awt.*;
import javax.swing.*;

public class PanelCrearUsuario extends JPanel {

    private JTextField txtNombre, txtCorreo;
    private JPasswordField txtPassword;
    private JComboBox<String> comboRol;

    public PanelCrearUsuario() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // TÍTULO
        JLabel titulo = new JLabel("Crear Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(titulo, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(Color.WHITE);

        // CENTRAR TODO
        panelForm.add(Box.createVerticalGlue());

        panelForm.add(crearCampo("Nombre", "user.png"));
        panelForm.add(Box.createVerticalStrut(10));

        panelForm.add(crearCampo("Correo", "mail.png"));
        panelForm.add(Box.createVerticalStrut(10));

        panelForm.add(crearCampoPassword("Contraseña", "lock.png"));
        panelForm.add(Box.createVerticalStrut(10));

        panelForm.add(crearCombo("Rol"));
        panelForm.add(Box.createVerticalStrut(20));

        // BOTÓN
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setMaximumSize(new Dimension(150, 40));

        panelForm.add(btnGuardar);

        panelForm.add(Box.createVerticalGlue());

        add(panelForm, BorderLayout.CENTER);
    }

    // CAMPO CON ICONO
    private JPanel crearCampo(String texto, String icono) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Times New Roman", Font.BOLD, 14));

        JTextField txt = new JTextField();
        txt.setPreferredSize(new Dimension(200, 30));

        // ICONO
        JLabel iconLabel = crearIcono(icono);

        panel.add(iconLabel, BorderLayout.WEST);
        panel.add(txt, BorderLayout.CENTER);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    private JPanel crearCampoPassword(String texto, String icono) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setBackground(Color.WHITE);

        JPasswordField txt = new JPasswordField();

        JLabel iconLabel = crearIcono(icono);

        panel.add(iconLabel, BorderLayout.WEST);
        panel.add(txt, BorderLayout.CENTER);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    // COMBO
    private JPanel crearCombo(String texto) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setBackground(Color.WHITE);

        comboRol = new JComboBox<>(new String[]{
            "Estudiante", "Docente", "Asesor"
        });

        panel.add(comboRol, BorderLayout.CENTER);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    // ICONOS ESCALADOS
    private JLabel crearIcono(String nombre) {
        JLabel label = new JLabel();

        try {
            java.net.URL url = getClass().getResource("/icons/" + nombre);

            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
            }

        } catch (Exception e) {
            System.out.println("Error icono: " + nombre);
        }

        return label;
    }
}