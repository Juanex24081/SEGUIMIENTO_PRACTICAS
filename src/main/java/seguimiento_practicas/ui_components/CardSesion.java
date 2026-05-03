package seguimiento_practicas.ui_components;

import javax.swing.*;
import java.awt.*;

public class CardSesion extends JPanel {

    public CardSesion(String titulo, String estado, String convenio, String docente, Runnable accion) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // PANEL TEXTO
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblConvenio = new JLabel("Convenio: " + convenio);
        JLabel lblDocente = new JLabel("Docente: " + docente);

        panelTexto.add(lblTitulo);
        panelTexto.add(lblConvenio);
        panelTexto.add(lblDocente);

        // ESTADO
        JLabel lblEstado = new JLabel(estado);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));

        switch (estado) {
            case "ENTREGADA":
                lblEstado.setForeground(new Color(39, 174, 96));
                break;
            case "PENDIENTE":
                lblEstado.setForeground(Color.RED);
                break;
            case "EN REVISIÓN":
                lblEstado.setForeground(Color.ORANGE);
                break;
        }

        add(panelTexto, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.EAST);

        // CLICK
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion.run();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.WHITE);
            }
        });
    }
}