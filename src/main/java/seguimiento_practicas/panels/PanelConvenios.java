package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.swing.JTextField;

import seguimiento_practicas.dao.ConveniosDAO;
import seguimiento_practicas.model.CrearConvenioDTO;

public class PanelConvenios extends JPanel {

    private ConveniosDAO dao = new ConveniosDAO();
    private JPanel listaConvenios; // GLOBAL (IMPORTANTE)

    public PanelConvenios() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contenedor.setBackground(Color.WHITE);

        add(contenedor, BorderLayout.CENTER);

        // ================= TOP =================
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        top.setBackground(Color.WHITE);

        JButton btnCrearConvenio = new JButton("Crear Convenio");
        top.add(btnCrearConvenio);

        contenedor.add(top, BorderLayout.NORTH);

        // ================= LISTA =================
        listaConvenios = new JPanel(); // NO LOCAL
        listaConvenios.setLayout(new BoxLayout(listaConvenios, BoxLayout.Y_AXIS));
        listaConvenios.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaConvenios);
        contenedor.add(scroll, BorderLayout.CENTER);

        // EVENTO
        btnCrearConvenio.addActionListener(e -> abrirDialogo());

        // CARGAR AL INICIO
        cargarConvenios();
    }

    // ================= CARGAR =================
    private void cargarConvenios() {

        System.out.println("Cargando convenios..."); // DEBUG

        listaConvenios.removeAll();

        ArrayList<Object[]> convenios = dao.listarConvenios();

        System.out.println("Cantidad convenios: " + convenios.size()); // DEBUG

        for (Object[] c : convenios) {

            JPanel card = new JPanel(new BorderLayout());
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(Color.WHITE);

            String texto = "Convenio: " + c[1] +
                    " | Docente: " + c[2] +
                    " | Asesor: " + c[3];

            card.add(new JLabel(texto), BorderLayout.CENTER);

            int idConvenio = (int) c[0];

            JButton btnVer = new JButton("Ver");

            btnVer.addActionListener(e -> abrirDetalleConvenio(
                    idConvenio,
                    c[1].toString(), // nombre empresa
                    c[2].toString(), // fecha inicio
                    c[3].toString(),  // fecha fin
                    c[4].toString() // nombre asesor
            ));
            
            card.add(btnVer, BorderLayout.EAST);

            listaConvenios.add(card);
            listaConvenios.add(Box.createVerticalStrut(10));
        }

        listaConvenios.revalidate();
        listaConvenios.repaint();
    }

    /* BOTÓN "VER" */

    private void abrirDetalleConvenio(int idConvenio, String empresa, String fecha_inicio, String fecha_fin, String asesor) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Detalle del Covenio");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // ================= TOP =================
        JPanel top = new JPanel(new GridLayout(3,1));
        top.add(new JLabel("Convenio No: " + idConvenio));
        
        
        /*top.add(new JLabel("Empresa: " + empresa));
        top.add(new JLabel("Fecha Inicio: " + fecha_inicio));
        top.add(new JLabel("Fecha Fin: " + fecha_fin));
        top.add(new JLabel("Asesor: " + asesor));*/

        dialog.add(top, BorderLayout.NORTH);

        // ================= CENTRO =================
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        centro.add(new JLabel("Empresa: " + empresa));
        centro.add(new JLabel("Fecha Inicio: " + fecha_inicio));
        centro.add(new JLabel("Fecha Fin: " + fecha_fin));
        centro.add(new JLabel("Asesor: " + asesor));

        dialog.add(new JScrollPane(centro), BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    // ================= CREAR =================
    private void abrirDialogo() {

        JTextField txtEmpresa = new JTextField();

        JComboBox<String> cbEstado = new JComboBox<>(
                new String[]{
                        "ACTIVO",
                        "INACTIVO"
                }
        );

        JTextField txtFechaInicio = new JTextField("dd/mm/yyyy");
        
        JTextField txtFechaFin = new JTextField("dd/mm/yyyy");


        //JComboBox<String> cbDocente = new JComboBox<>();
        JComboBox<String> cbAsesor = new JComboBox<>();
        ArrayList<Object[]> asesores = dao.listarAsesores();

       /* for (Object[] d : docentes) {
            cbDocente.addItem(d[0] + " - " + d[1]);
        }*/

        for (Object[] a : asesores) {
            cbAsesor.addItem(a[0] + " - " + a[1]);
        }

        JPanel panel = new JPanel(new GridLayout(5,2,10,10));

        panel.add(new JLabel("Empresa:"));
        panel.add(txtEmpresa);

        panel.add(new JLabel("Estado:"));
        panel.add(cbEstado);

        panel.add(new JLabel("Fecha Inicio:"));
        panel.add(txtFechaInicio);

        panel.add(new JLabel("Fecha Fin:"));
        panel.add(txtFechaFin);

        /*panel.add(new JLabel("Docente: "));
        panel.add(cbDocente);*/

        panel.add(new JLabel("Asesor:"));
        panel.add(cbAsesor);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Crear Convenio",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                String empresa = txtEmpresa.getText();
                String estado = cbEstado.getSelectedItem().toString();
                
                DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");


                LocalDate fecha_inicio =
                        LocalDate.parse(
                                txtFechaInicio.getText(), formatter
                        );

                LocalDate fecha_fin =
                        LocalDate.parse(
                                txtFechaFin.getText(), formatter
                        );

                /*int idDocente = Integer.parseInt(
                        cbDocente.getSelectedItem().toString().split(" - ")[0]
                );*/

                int idAsesor = Integer.parseInt(
                        cbAsesor.getSelectedItem().toString().split(" - ")[0]
                );

                CrearConvenioDTO convenio = new CrearConvenioDTO(empresa, estado, fecha_inicio, fecha_fin, idAsesor);

                dao.crearConvenio(convenio);

                JOptionPane.showMessageDialog(this, "Convenio creado");

                cargarConvenios();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error");
                e.printStackTrace(); // DEBUG REAL
            }
        }
    }
}