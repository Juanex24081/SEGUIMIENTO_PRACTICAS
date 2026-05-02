/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaUsuarios extends JFrame {

    private JTextField txtBuscar, txtNombre, txtCedula, txtGrupo;
    private JComboBox<String> cbRol;
    private JTable tabla;

    public VistaUsuarios() {
        setTitle("Administrar Usuarios");
        setSize(800, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // PANEL SUPERIOR (BÚSQUEDA)
        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        panelTop.add(txtBuscar);
        panelTop.add(btnBuscar);

        add(panelTop, BorderLayout.NORTH);

        // PANEL CENTRO (FORMULARIO)
        JPanel panelForm = new JPanel(new GridLayout(5,2));

        txtNombre = new JTextField();
        txtCedula = new JTextField();
        txtGrupo = new JTextField();

        cbRol = new JComboBox<>(new String[]{
            "ESTUDIANTE", "DOCENTE", "ASESOR"
        });

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Cédula:"));
        panelForm.add(txtCedula);

        panelForm.add(new JLabel("Grupo (solo estudiante):"));
        panelForm.add(txtGrupo);

        panelForm.add(new JLabel("Rol:"));
        panelForm.add(cbRol);

        JButton btnGuardar = new JButton("Guardar");
        panelForm.add(btnGuardar);

        add(panelForm, BorderLayout.CENTER);

        // TABLA
        tabla = new JTable(new DefaultTableModel(
                new Object[]{"Nombre", "Cédula", "Rol", "Grupo"}, 0
        ));

        add(new JScrollPane(tabla), BorderLayout.SOUTH);

        // EVENTOS

        btnGuardar.addActionListener(e -> guardarUsuario());

        btnBuscar.addActionListener(e -> buscarUsuario());
    }

    private void guardarUsuario() {
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String grupo = txtGrupo.getText();
        String rol = cbRol.getSelectedItem().toString();

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        modelo.addRow(new Object[]{nombre, cedula, rol, grupo});

        JOptionPane.showMessageDialog(this, "Usuario guardado (temporal)");
    }

    private void buscarUsuario() {
        String buscar = txtBuscar.getText();

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String nombre = modelo.getValueAt(i, 0).toString();

            if (nombre.equalsIgnoreCase(buscar)) {
                JOptionPane.showMessageDialog(this, "Usuario encontrado en fila " + i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No encontrado");
    }
}