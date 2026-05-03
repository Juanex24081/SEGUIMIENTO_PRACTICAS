package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PanelBitacoras extends JPanel {

    public PanelBitacoras() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Bitácoras Entregadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        DefaultListModel<String> modelo = new DefaultListModel<>();

        modelo.addElement("Sesión 1 - Entregada");
        modelo.addElement("Sesión 2 - Pendiente");

        JList<String> lista = new JList<>(modelo);

        add(new JScrollPane(lista), BorderLayout.CENTER);
    }
}