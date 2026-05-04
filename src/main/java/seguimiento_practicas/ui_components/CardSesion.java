package seguimiento_practicas.ui_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import seguimiento_practicas.model.SesionDTO;

public class CardSesion extends JPanel {

    public CardSesion(SesionDTO s, Runnable accion) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // TEXTO
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Sesión #" + s.id);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblConvenio = new JLabel("Convenio: " + s.convenio);
        JLabel lblDocente = new JLabel("Docente: " + s.docente);
        JLabel lblFecha = new JLabel("Fecha: " + s.fecha);

        panelTexto.add(lblTitulo);
        panelTexto.add(lblConvenio);
        panelTexto.add(lblDocente);
        panelTexto.add(lblFecha);

        // ESTADO
        JLabel lblEstado = new JLabel(s.estado);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));

        switch (s.estado) {
            case "ENTREGADA" -> lblEstado.setForeground(new Color(39, 174, 96));
            case "PENDIENTE" -> lblEstado.setForeground(Color.RED);
            case "EN REVISIÓN" -> lblEstado.setForeground(Color.ORANGE);
        }

        add(panelTexto, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.EAST);

        // CLICK
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                accion.run();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });
    }
}