package seguimiento_practicas.panels;

import seguimiento_practicas.dao.PracticasDAO;
import seguimiento_practicas.model.CrearGrupoDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelPracticas extends JPanel {

    private PracticasDAO dao = new PracticasDAO();
    private JPanel listaGrupos; // 🔥 GLOBAL (IMPORTANTE)

    public PanelPracticas() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contenedor.setBackground(Color.WHITE);

        add(contenedor, BorderLayout.CENTER);

        // ================= TOP =================
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        top.setBackground(Color.WHITE);

        JButton btnCrearGrupo = new JButton("Crear Grupo");
        top.add(btnCrearGrupo);

        contenedor.add(top, BorderLayout.NORTH);

        // ================= LISTA =================
        listaGrupos = new JPanel(); // 🔥 NO LOCAL
        listaGrupos.setLayout(new BoxLayout(listaGrupos, BoxLayout.Y_AXIS));
        listaGrupos.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaGrupos);
        contenedor.add(scroll, BorderLayout.CENTER);

        // 🔥 EVENTO
        btnCrearGrupo.addActionListener(e -> abrirDialogo());

        // 🔥 CARGAR AL INICIO
        cargarGrupos();
    }

    // ================= CARGAR =================
    private void cargarGrupos() {

        System.out.println("Cargando grupos..."); // DEBUG

        listaGrupos.removeAll();

        ArrayList<Object[]> grupos = dao.listarGrupos();

        System.out.println("Cantidad grupos: " + grupos.size()); // DEBUG

        for (Object[] g : grupos) {

            JPanel card = new JPanel(new BorderLayout());
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(Color.WHITE);

            String texto = "Grupo: " + g[1] +
                    " | Docente: " + g[2] +
                    " | Asesor: " + g[3];

            card.add(new JLabel(texto), BorderLayout.CENTER);

            int idGrupo = (int) g[0];

            JButton btnVer = new JButton("Ver");

            btnVer.addActionListener(e -> abrirDetalleGrupo(
                    idGrupo,
                    g[1].toString(), // nombre grupo
                    g[2].toString(), // docente
                    g[3].toString()  // asesor
            ));
            
            card.add(btnVer, BorderLayout.EAST);

            listaGrupos.add(card);
            listaGrupos.add(Box.createVerticalStrut(10));
        }

        listaGrupos.revalidate();
        listaGrupos.repaint();
    }

    /* BOTÓN "VER" */

    private void abrirDetalleGrupo(int idGrupo, String nombre, String docente, String asesor) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Detalle del Grupo");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(3,1));
        top.add(new JLabel("Grupo: " + nombre));
        top.add(new JLabel("Docente: " + docente));
        top.add(new JLabel("Asesor: " + asesor));

        dialog.add(top, BorderLayout.NORTH);

        // ================= CENTRO =================
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        ArrayList<String> estudiantes = dao.listarEstudiantesPorGrupo(idGrupo);

        if (estudiantes.isEmpty()) {
            centro.add(new JLabel("No hay estudiantes en este grupo"));
        } else {
            for (String est : estudiantes) {
                centro.add(new JLabel("• " + est));
            }
        }

        dialog.add(new JScrollPane(centro), BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    // ================= CREAR =================
    private void abrirDialogo() {

        JTextField txtNombre = new JTextField();

        JComboBox<String> cbDocente = new JComboBox<>();
        JComboBox<String> cbAsesor = new JComboBox<>();

        ArrayList<Object[]> docentes = dao.listarDocentes();
        ArrayList<Object[]> asesores = dao.listarAsesores();

        for (Object[] d : docentes) {
            cbDocente.addItem(d[0] + " - " + d[1]);
        }

        for (Object[] a : asesores) {
            cbAsesor.addItem(a[0] + " - " + a[1]);
        }

        JPanel panel = new JPanel(new GridLayout(3,2,5,5));

        panel.add(new JLabel("Nombre grupo:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Docente:"));
        panel.add(cbDocente);

        panel.add(new JLabel("Asesor:"));
        panel.add(cbAsesor);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Crear Grupo",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                String nombre = txtNombre.getText();

                int idDocente = Integer.parseInt(
                        cbDocente.getSelectedItem().toString().split(" - ")[0]
                );

                int idAsesor = Integer.parseInt(
                        cbAsesor.getSelectedItem().toString().split(" - ")[0]
                );

                CrearGrupoDTO grupo = new CrearGrupoDTO(nombre, idDocente, idAsesor);

                dao.crearGrupo(grupo);

                JOptionPane.showMessageDialog(this, "Grupo creado");

                cargarGrupos();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error");
                e.printStackTrace(); // DEBUG REAL
            }
        }
    }
}