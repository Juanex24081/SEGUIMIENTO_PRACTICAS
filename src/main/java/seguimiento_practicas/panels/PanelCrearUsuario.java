package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import seguimiento_practicas.dao.UsuarioDAO;
import seguimiento_practicas.ui_components.PlaceholderTextField;

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
        panelForm.add(Box.createVerticalStrut(20));

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
        btnGuardar.addActionListener(e -> guardarUsuario());

        panelForm.add(Box.createVerticalGlue());

        add(panelForm, BorderLayout.CENTER);
    }

    /* GUARDAR USUARIO EN BD */

    private void guardarUsuario() {

        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String pass = new String(txtPassword.getPassword());
        String rol = comboRol.getSelectedItem().toString().toUpperCase();

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }

        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.insertar(nombre, correo, pass, rol);

            JOptionPane.showMessageDialog(this, "Usuario creado correctamente");

            limpiarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        comboRol.setSelectedIndex(0);
    }


    

    // CAMPO CON ICONO
    private JPanel crearCampo(String texto, String icono) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setBackground(Color.WHITE);

        PlaceholderTextField txt = new PlaceholderTextField("Ingrese " + texto.toLowerCase());
        txt.setPreferredSize(new Dimension(200, 30));

        // GUARDAR REFERENCIA
        if (texto.equals("Nombre")) {
            txtNombre = txt;
        } else if (texto.equals("Correo")) {
            txtCorreo = txt;
        }

        panel.add(txt, BorderLayout.CENTER);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    private JPanel crearCampoPassword(String texto, String icono) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setBackground(Color.WHITE);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 30));

        panel.add(txtPassword, BorderLayout.CENTER);
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