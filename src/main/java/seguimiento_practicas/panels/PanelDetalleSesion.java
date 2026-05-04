package seguimiento_practicas.panels;

import javax.swing.*;
import java.awt.*;

import seguimiento_practicas.model.SesionDTO;

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

        JButton btnBitacoras = new JButton("Ver Bitácoras");
        JButton btnSubir = new JButton("Subir Bitácora");

        panel.add(btnBitacoras);
        panel.add(btnSubir);

        // acciones (luego las conectamos)
        btnBitacoras.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir bitácoras"));

        btnSubir.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Subir archivo"));

        return panel;
    }
}