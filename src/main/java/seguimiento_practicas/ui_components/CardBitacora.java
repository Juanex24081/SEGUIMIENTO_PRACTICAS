package seguimiento_practicas.ui_components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import seguimiento_practicas.model.BitacoraListaDTO;

public class CardBitacora extends JPanel {

    public CardBitacora(BitacoraListaDTO b) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Sesión #" + b.idSesion);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel archivo = new JLabel("Archivo: " + new File(b.archivo).getName());
        JLabel fecha = new JLabel("Fecha: " + b.fecha);

        info.add(titulo);
        info.add(archivo);
        info.add(fecha);

        JLabel estado = new JLabel(b.estado);

        switch (b.estado) {
            case "ENVIADA" -> estado.setForeground(Color.ORANGE);
            case "CALIFICADA" -> estado.setForeground(new Color(39, 174, 96));
        }

        add(info, BorderLayout.CENTER);
        add(estado, BorderLayout.EAST);

        // CLICK → abrir archivo
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirArchivo(b.archivo);
            }
        });
    }

    private void abrirArchivo(String ruta) {
        try {
            File file = new File(ruta);

            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir archivo");
        }
    }
}