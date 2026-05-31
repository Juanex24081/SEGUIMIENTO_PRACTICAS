package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import seguimiento_practicas.dao.AsesorDAO;
import seguimiento_practicas.model.BitacoraAsesorDTO;
import seguimiento_practicas.model.ComentarioDTO;

public class PanelBitacorasAsesor extends JPanel {

    private AsesorDAO dao = new AsesorDAO();

    private JPanel lista;

    private int idAsesor;

    public PanelBitacorasAsesor(int idAsesor) {

        this.idAsesor = idAsesor;

        setLayout(new BorderLayout());

        JLabel titulo =
                new JLabel(
                        "BITÁCORAS",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titulo, BorderLayout.NORTH);

        lista = new JPanel();

        lista.setLayout(
                new BoxLayout(lista, BoxLayout.Y_AXIS)
        );

        JScrollPane scroll =
                new JScrollPane(lista);

        add(scroll, BorderLayout.CENTER);

        cargarBitacoras();
    }

    // =========================
    // CARGAR
    // =========================

    private void cargarBitacoras() {

        lista.removeAll();

        ArrayList<BitacoraAsesorDTO> bitacoras =
                dao.listarBitacoras(idAsesor);

        for (BitacoraAsesorDTO b : bitacoras) {

            JPanel card =
                    new JPanel(new BorderLayout());

            card.setBorder(
                    BorderFactory.createLineBorder(Color.GRAY)
            );

            card.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 120)
            );

            String texto =
                    "<html>"
                    + "<b>Estudiante:</b> "
                    + b.estudiante
                    + "<br><b>Sesión:</b> "
                    + b.idSesion
                    + "<br><b>Estado:</b> "
                    + b.estado
                    + "<br><b>Fecha:</b> "
                    + b.fecha
                    + "</html>";

            card.add(
                    new JLabel(texto),
                    BorderLayout.CENTER
            );

            JButton btnVer =
                    new JButton("Ver");

            btnVer.addActionListener(
                    e -> abrirDetalle(b)
            );

            card.add(btnVer, BorderLayout.EAST);

            lista.add(card);

            lista.add(Box.createVerticalStrut(10));
        }

        lista.revalidate();
        lista.repaint();
    }

    // =========================
    // DETALLE
    // =========================

    private void abrirDetalle(
            BitacoraAsesorDTO b
    ) {

        JDialog dialog =
                new JDialog();

        dialog.setSize(600,500);

        dialog.setLocationRelativeTo(this);

        dialog.setLayout(new BorderLayout());

        // =========================
        // INFO
        // =========================

        JPanel top =
                new JPanel(new GridLayout(6,1));

        top.add(new JLabel(
                "Estudiante: "
                + b.estudiante
        ));

        top.add(new JLabel(
                "Sesión: "
                + b.idSesion
        ));

        top.add(new JLabel(
                "Archivo: "
                + b.archivo
        ));

        top.add(new JLabel(
                "Estado: "
                + b.estado
        ));

        top.add(new JLabel(
        "Calificación: "
        + b.calificacion
    ));

    JButton btnArchivo =
            new JButton("Abrir Archivo");

    btnArchivo.addActionListener(e -> {

    try {
        System.out.println(b.archivo);
        Desktop.getDesktop().open(
                new java.io.File(b.archivo)
        );

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                dialog,
                "No se pudo abrir el archivo"
        );
    }
});

top.add(btnArchivo);
        dialog.add(top, BorderLayout.NORTH);

        // =========================
        // COMENTARIOS
        // =========================

        JTextArea area =
                new JTextArea();

        ArrayList<ComentarioDTO> comentarios =
                dao.listarComentarios(
                        b.idBitacora
                );

        for (ComentarioDTO c : comentarios) {

            area.append(
                    c.usuario
                    + " ("
                    + c.rol
                    + "):\n"
                    + c.comentario
                    + "\n\n"
            );
        }

        area.setEditable(false);

        dialog.add(
                new JScrollPane(area),
                BorderLayout.CENTER
        );

        // =========================
        // NUEVO COMENTARIO
        // =========================

        JPanel bottom =
                new JPanel(new BorderLayout());

        JTextArea txtComentario =
                new JTextArea(4,20);

        JButton btnEnviar =
                new JButton("Comentar");

        bottom.add(
                new JScrollPane(txtComentario),
                BorderLayout.CENTER
        );

        bottom.add(
                btnEnviar,
                BorderLayout.EAST
        );

        dialog.add(bottom, BorderLayout.SOUTH);

        // =========================
        // EVENTO
        // =========================

        btnEnviar.addActionListener(e -> {

            dao.comentarBitacora(

                    b.idBitacora,

                    idAsesor,

                    txtComentario.getText()
            );

            JOptionPane.showMessageDialog(
                dialog,
                "Comentario agregado"
            );

            // CERRAR
            dialog.dispose();

            // VOLVER A ABRIR ACTUALIZADO
            abrirDetalle(b);

            dialog.dispose();

            abrirDetalle(b);
        });

        dialog.setVisible(true);
    }
}