package seguimiento_practicas.panels;

import seguimiento_practicas.model.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelUsuarios extends JPanel {
    
    private JTable tabla;
    private JTextField txtBuscar;
    private DefaultTableModel modelo;

    private int filaSeleccionada = -1;

    public PanelUsuarios() {
        setLayout(new BorderLayout());

        // PANEL SUPERIOR
        JPanel top = new JPanel();

        txtBuscar = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Mostrar todos");

        top.add(new JLabel("Cédula:"));
        top.add(txtBuscar);
        top.add(btnBuscar);
        top.add(btnLimpiar);

        add(top, BorderLayout.NORTH);

        // TABLA
        modelo = new DefaultTableModel(
                new Object[]{"Nombre", "Cédula", "Rol"}, 0
        );

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // BOTONES
        JPanel bottom = new JPanel();

        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        bottom.add(btnEditar);
        bottom.add(btnEliminar);

        add(bottom, BorderLayout.SOUTH);

        // CARGAR DATOS
        cargarTabla(Datos.listaUsuarios);

        // EVENTOS
        btnBuscar.addActionListener(e -> filtrar());
        btnLimpiar.addActionListener(e -> cargarTabla(Datos.listaUsuarios));

        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            filaSeleccionada = tabla.getSelectedRow();
        });
    }

    // CARGAR TABLA
    private void cargarTabla(ArrayList<Object[]> lista) {
        modelo.setRowCount(0);

        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }

    // FILTRAR
    private void filtrar() {
        String cedula = txtBuscar.getText();

        if (cedula.isEmpty()) {
            cargarTabla(Datos.listaUsuarios);
            return;
        }

        ArrayList<Object[]> filtrados = new ArrayList<>();

        for (Object[] usuario : Datos.listaUsuarios) {
            if (usuario[1].toString().contains(cedula)) {
                filtrados.add(usuario);
            }
        }

        cargarTabla(filtrados);
    }

    // ELIMINAR
    private void eliminarUsuario() {

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar este usuario?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            String cedula = tabla.getValueAt(filaSeleccionada, 1).toString();

            Datos.listaUsuarios.removeIf(u -> u[1].equals(cedula));

            cargarTabla(Datos.listaUsuarios);
            filaSeleccionada = -1;

            JOptionPane.showMessageDialog(this, "Usuario eliminado");
        }
    }

    // EDITAR
    private void editarUsuario() {

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }

        String nombre = tabla.getValueAt(filaSeleccionada, 0).toString();
        String cedula = tabla.getValueAt(filaSeleccionada, 1).toString();
        String rol = tabla.getValueAt(filaSeleccionada, 2).toString();

        JTextField txtNombre = new JTextField(nombre);
        JTextField txtCedula = new JTextField(cedula);
        JComboBox<String> cbRol = new JComboBox<>(
                new String[]{"ESTUDIANTE", "DOCENTE", "ASESOR"}
        );
        cbRol.setSelectedItem(rol);

        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Cédula:"));
        panel.add(txtCedula);
        panel.add(new JLabel("Rol:"));
        panel.add(cbRol);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Editar Usuario",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            for (Object[] u : Datos.listaUsuarios) {
                if (u[1].equals(cedula)) {
                    u[0] = txtNombre.getText();
                    u[1] = txtCedula.getText();
                    u[2] = cbRol.getSelectedItem().toString();
                    break;
                }
            }

            cargarTabla(Datos.listaUsuarios);
            JOptionPane.showMessageDialog(this, "Usuario actualizado");
        }
    }
}