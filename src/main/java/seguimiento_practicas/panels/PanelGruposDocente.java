package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import seguimiento_practicas.dao.GruposDAO;
import seguimiento_practicas.model.CrearGrupoDTO;
import seguimiento_practicas.session.UsuarioSesion;

public class PanelGruposDocente extends JPanel {

    private GruposDAO dao = new GruposDAO();
    private JPanel listaGrupos; // GLOBAL (IMPORTANTE)

    public PanelGruposDocente() {

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
        listaGrupos = new JPanel(); // NO LOCAL
        listaGrupos.setLayout(new BoxLayout(listaGrupos, BoxLayout.Y_AXIS));
        listaGrupos.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaGrupos);
        contenedor.add(scroll, BorderLayout.CENTER);

        // EVENTO
        btnCrearGrupo.addActionListener(e -> abrirDialogo());

        // CARGAR AL INICIO
        cargarGrupos();
    }

    // ================= CARGAR =================
    private void cargarGrupos() {

        System.out.println("Cargando grupos..."); // DEBUG

        listaGrupos.removeAll();

        long idUsuario = UsuarioSesion.usuarioActual.getId();

        ArrayList<Object[]> gruposDocente =dao.listarReporteGruposDocente(idUsuario);

        System.out.println("Cantidad grupos: " + gruposDocente.size()); // DEBUG

        for (Object[] gDo : gruposDocente) {

            JPanel card = new JPanel(new BorderLayout());
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(Color.WHITE);

            String texto =
            "Grupo: " + gDo[1]
                    + " | Docente: " + gDo[2]
                    + " | Asesor: " + gDo[3]
                    + " | Estudiantes: " + gDo[4];

            card.add(new JLabel(texto), BorderLayout.CENTER);

            int idGrupo = (int) gDo[0];

            JButton btnVer = new JButton("Ver");

            btnVer.addActionListener(e -> abrirDetalleGrupo(
                    idGrupo,
                    gDo[1].toString(), // nombre grupo
                    gDo[2].toString(), // docente
                    gDo[3].toString()  // asesor
            ));
            
            card.add(btnVer, BorderLayout.EAST);

            listaGrupos.add(card);
            listaGrupos.add(Box.createVerticalStrut(10));
        }

        listaGrupos.revalidate();
        listaGrupos.repaint();
    }

    /* BOTÓN "VER" */

private void abrirDetalleGrupo(
        int idGrupo,
        String nombre,
        String docente,
        String asesor) {

    JDialog dialog = new JDialog();
    dialog.setTitle("Detalle Grupo");
    dialog.setSize(700, 600);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new BorderLayout());

    // ===== DATOS DEL GRUPO =====

    JPanel top = new JPanel(new GridLayout(3, 1));

    top.add(new JLabel("Grupo: " + nombre));
    top.add(new JLabel("Docente: " + docente));
    top.add(new JLabel("Asesor: " + asesor));

    dialog.add(top, BorderLayout.NORTH);

    // ===== TABS =====

    JTabbedPane tabs = new JTabbedPane();

    // =========================
    // TAB ESTUDIANTES
    // =========================

    JPanel panelEstudiantes = new JPanel();
    panelEstudiantes.setLayout(
            new BoxLayout(
                    panelEstudiantes,
                    BoxLayout.Y_AXIS
            )
    );

    ArrayList<String> estudiantes =
            dao.obtenerEstudiantesVista(idGrupo);

    if (estudiantes.isEmpty()) {

        panelEstudiantes.add(
                new JLabel(
                        "No hay estudiantes"
                )
        );

    } else {

        for (String e : estudiantes) {

            panelEstudiantes.add(
                    new JLabel("• " + e)
            );
        }
    }

    // =========================
    // TAB SESIONES
    // =========================

    JPanel panelSesiones = new JPanel();

    panelSesiones.setLayout(
            new BoxLayout(
                    panelSesiones,
                    BoxLayout.Y_AXIS
            )
    );

    ArrayList<Object[]> sesiones =
            dao.obtenerSesionesGrupo(idGrupo);

    if (sesiones.isEmpty()) {

        panelSesiones.add(
                new JLabel(
                        "No hay sesiones"
                )
        );

    } else {

        for (Object[] s : sesiones) {

            JPanel fila = new JPanel(
                    new FlowLayout(
                            FlowLayout.LEFT
                    )
            );

            JLabel lbl = new JLabel(
                    "Sesión "
                    + s[0]
                    + " | Fecha: "
                    + s[1]
                    + " | Horas: "
                    + s[2]
                    + " | Estado: "
                    + s[3]
            );

            JButton btnBitacoras =
                    new JButton(
                            "Ver Bitácoras"
                    );

            int idSesion = (int) s[0];

            btnBitacoras.addActionListener(
                    ev -> abrirBitacorasSesion(idSesion)
            );

            fila.add(lbl);
            fila.add(btnBitacoras);

            panelSesiones.add(fila);
        }
    }

    // ===== AGREGAR TABS =====

    tabs.addTab(
            "Estudiantes",
            new JScrollPane(panelEstudiantes)
    );

    tabs.addTab(
            "Sesiones",
            new JScrollPane(panelSesiones)
    );

    dialog.add(
            tabs,
            BorderLayout.CENTER
    );

    dialog.setVisible(true);
}

private void abrirBitacorasSesion(
        int idSesion) {

    JDialog dialog = new JDialog();

    dialog.setTitle(
            "Bitácoras Sesión "
            + idSesion
    );

    dialog.setSize(600,400);

    dialog.setLocationRelativeTo(this);

    JPanel panel = new JPanel();

    panel.setLayout(
            new BoxLayout(
                    panel,
                    BoxLayout.Y_AXIS
            )
    );

    ArrayList<Object[]> bitacoras =
            dao.obtenerBitacorasSesion(
                    idSesion
            );

    if(bitacoras.isEmpty()) {

        panel.add(
                new JLabel(
                        "No hay bitácoras"
                )
        );

    } else {

        for(Object[] b : bitacoras) {

            panel.add(
                    new JLabel(

                    "Estudiante: "
                    + b[0]

                    + " | Estado: "
                    + b[1]

                    + " | Nota: "
                    + b[2]

                    + " | Fecha: "
                    + b[3]
                    )
            );
        }
    }

    dialog.add(
            new JScrollPane(panel)
    );

    dialog.setVisible(true);
}

    // ================= CREAR =================
    private void abrirDialogo() {

        JTextField txtNombre = new JTextField();

        JComboBox<String> cbDocente = new JComboBox<>();
        JComboBox<String> cbAsesor = new JComboBox<>();

        ArrayList<Object[]> docentes = dao.listarDocenteUnico();
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