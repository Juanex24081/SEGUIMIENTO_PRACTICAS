package seguimiento_practicas.panels;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import seguimiento_practicas.ui_components.CardSesion;

public class PanelSesiones extends JPanel {

    public PanelSesiones() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        add(Box.createVerticalStrut(10));

        add(new CardSesion(
            "Sesión 1",
            "PENDIENTE",
            "Empresa X",
            "Juan Pérez",
            () -> abrirDetalle(1)
        ));

        add(new CardSesion(
            "Sesión 2",
            "EN REVISIÓN",
            "Empresa Y",
            "Ana López",
            () -> abrirDetalle(2)
        ));

        add(new CardSesion(
            "Sesión 3",
            "ENTREGADA",
            "Empresa Z",
            "Carlos Ruiz",
            () -> abrirDetalle(3)
        ));
    }

    /*private void abrirSesion(int id) {
        JOptionPane.showMessageDialog(this, "Abrir sesión " + id);
    }*/

    // FALTA IMPLEMENTAR

    private void abrirDetalle(int numeroSesion) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Detalle Sesión " + numeroSesion);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        dialog.add(new PanelDetalleSesion(numeroSesion));

        dialog.setVisible(true);
    }
}

