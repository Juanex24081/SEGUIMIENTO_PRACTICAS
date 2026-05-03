package seguimiento_practicas.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelDetalleSesion extends JPanel {

    public PanelDetalleSesion(int numero) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Sesión: " + numero));
        add(new JLabel("Convenio: Empresa XYZ"));
        add(new JLabel("Asesor: Carlos Pérez"));
        add(new JLabel("Docente: Ana López"));
        add(new JLabel("Estado: Pendiente"));

        add(Box.createVerticalStrut(10));

        JButton btnSubir = new JButton("Subir Bitácora");

        btnSubir.addActionListener(e -> subirArchivo());

        add(btnSubir);

        add(Box.createVerticalStrut(10));

        add(new JLabel("Calificación: -"));
        add(new JLabel("Comentarios: Sin revisar"));
    }

    private void subirArchivo() {

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this, "Archivo subido correctamente ✅");
        }
    }
}