package seguimiento_practicas.panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import seguimiento_practicas.dao.BitacoraDAO;
import seguimiento_practicas.model.BitacoraResultadoDTO;
import seguimiento_practicas.model.SesionDTO;
import seguimiento_practicas.session.UsuarioSesion;

public class PanelDetalleSesion extends JPanel {

    private SesionDTO sesion;

    public PanelDetalleSesion(SesionDTO sesion) {

        this.sesion = sesion;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(crearHeader(), BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearHeader() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("Detalle Sesión #" + sesion.id);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        panel.add(titulo, BorderLayout.WEST);

        JLabel estado = new JLabel(sesion.estado);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 14));

        switch (sesion.estado) {
            case "ENTREGADA" -> estado.setForeground(new Color(39, 174, 96));
            case "PENDIENTE" -> estado.setForeground(Color.RED);
            case "EN REVISIÓN" -> estado.setForeground(Color.ORANGE);
        }

        panel.add(estado, BorderLayout.EAST);

        return panel;
    }

    private JPanel crearContenido() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(crearFila("Empresa:", sesion.convenio));
        panel.add(Box.createVerticalStrut(10));

        panel.add(crearFila("Docente:", sesion.docente));
        panel.add(Box.createVerticalStrut(10));

        panel.add(crearFila("Fecha:", sesion.fecha));
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }

    private JPanel crearFila(String label, String valor) {

        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(Color.WHITE);

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel val = new JLabel(valor);
        val.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        fila.add(lbl, BorderLayout.WEST);
        fila.add(val, BorderLayout.CENTER);

        return fila;
    }

    private JPanel crearBotones() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JButton btnVerResultado = new JButton("Ver Resultado");
        JButton btnSubir = new JButton("Subir Bitácora");

        panel.add(btnVerResultado);
        panel.add(btnSubir);

        // acciones (luego las conectamos)
        btnVerResultado.addActionListener(e -> mostrarResultado());

        btnSubir.addActionListener(e -> subirBitacora());

        return panel;
    }

    private void mostrarResultado() {

        int idSesion = sesion.id;
        Long idEstudiante = seguimiento_practicas.session.UsuarioSesion.usuarioActual.getId();

        BitacoraDAO dao = new BitacoraDAO();
        BitacoraResultadoDTO b = dao.obtenerPorSesionYEstudiante(idSesion, idEstudiante);

        if (b == null) {
            JOptionPane.showMessageDialog(this, "Aún no hay bitácora registrada.");
            return;
        }

        String mensaje = """
            Estado: %s

            Calificación: %d

            Comentario:
            %s
            """.formatted(
                b.estado,
                b.calificacion,
                b.comentario
        );

        JOptionPane.showMessageDialog(this, mensaje, "Resultado de Bitácora", JOptionPane.INFORMATION_MESSAGE);
    }



    private void subirBitacora() {

        JFileChooser fileChooser = new JFileChooser();

        int opcion = fileChooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            String ruta = fileChooser.getSelectedFile().getAbsolutePath();

            int idSesion = sesion.id;
            Long idEstudiante = seguimiento_practicas.session.UsuarioSesion.usuarioActual.getId();

            seguimiento_practicas.dao.BitacoraDAO dao = new seguimiento_practicas.dao.BitacoraDAO();

            /*dao.insertar(idEstudiante, idSesion, ruta);*/
            dao.guardar(idEstudiante, idSesion, ruta);

            JOptionPane.showMessageDialog(this, "Bitácora subida correctamente");
        }
    }
}