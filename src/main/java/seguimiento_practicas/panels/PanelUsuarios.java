package seguimiento_practicas.panels;

import seguimiento_practicas.dao.UsuarioDAO;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelUsuarios extends JPanel {
    
    private JTable tabla;
    private JTextField txtBuscar;
    private DefaultTableModel modelo;

    private int filaSeleccionada = -1;

    private UsuarioDAO dao = new UsuarioDAO();

    public PanelUsuarios() {

        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ================= TOP =================
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        top.setBackground(Color.WHITE);

        txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Mostrar todos");

        top.add(new JLabel("Buscar ID:"));
        top.add(txtBuscar);
        top.add(btnBuscar);
        top.add(btnLimpiar);

        add(top, BorderLayout.NORTH);

        // ================= TABLA =================
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Rol"}, 0
        );

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Usuarios registrados"));

        add(scroll, BorderLayout.CENTER);

        // ================= BOTONES =================
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        bottom.setBackground(Color.WHITE);

        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEditar = new JButton("Editar");

        bottom.add(btnEditar);
        bottom.add(btnEliminar);

        add(bottom, BorderLayout.SOUTH);

        // ================= DATOS =================
        cargarTabla(dao.listarUsuarios());

        // ================= EVENTOS =================
        btnBuscar.addActionListener(e -> filtrar());
        btnLimpiar.addActionListener(e -> cargarTabla(dao.listarUsuarios()));

        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            filaSeleccionada = tabla.getSelectedRow();
        });
    }

    // ================= CARGAR TABLA =================
    private void cargarTabla(ArrayList<Object[]> lista) {

        modelo.setRowCount(0);

        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }

    // ================= FILTRAR =================
    private void filtrar() {

        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            cargarTabla(dao.listarUsuarios());
            return;
        }

        ArrayList<Object[]> filtrados = new ArrayList<>();

        for (Object[] usuario : dao.listarUsuarios()) {

            if (usuario[0].toString().contains(texto)) {
                filtrados.add(usuario);
            }
        }

        cargarTabla(filtrados);
    }

    // ================= ELIMINAR =================
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

            int id = Integer.parseInt(
                    tabla.getValueAt(filaSeleccionada, 0).toString()
            );

            dao.eliminarUsuario(id);

            cargarTabla(dao.listarUsuarios());
            filaSeleccionada = -1;

            JOptionPane.showMessageDialog(this, "Usuario eliminado");
        }
    }

    // ================= EDITAR =================
    private void editarUsuario() {

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }

        int id = Integer.parseInt(
                tabla.getValueAt(filaSeleccionada, 0).toString()
        );

        String nombre = tabla.getValueAt(filaSeleccionada, 1).toString();
        String rol = tabla.getValueAt(filaSeleccionada, 2).toString();

        JTextField txtNombre = new JTextField(nombre);

        JComboBox<String> cbRol = new JComboBox<>(
                new String[]{"ESTUDIANTE", "DOCENTE", "ASESOR"}
        );
        cbRol.setSelectedItem(rol);

        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Rol:"));
        panel.add(cbRol);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Editar Usuario",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            dao.actualizarUsuario(
                    id,
                    txtNombre.getText(),
                    cbRol.getSelectedItem().toString()
            );

            cargarTabla(dao.listarUsuarios());

            JOptionPane.showMessageDialog(this, "Usuario actualizado");
        }
    }
}