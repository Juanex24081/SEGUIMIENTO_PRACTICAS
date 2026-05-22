package seguimiento_practicas.ui_components;
/* 

NO IMPLEMENTADO
NO IMPLEMENTADO
NO IMPLEMENTADO
NO IMPLEMENTADO
NO IMPLEMENTADO
NO IMPLEMENTADO




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import seguimiento_practicas.model.CrearConvenioDTO;

public class CardConvenio extends JPanel {

    public CardConvenio(CrearConvenioDTO c) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);

        JLabel sesion = new JLabel("Sesión #" + c.id);
        sesion.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel empresa = new JLabel("Empresa: " + c.getEmpresa());

        info.add(sesion);
        info.add(empresa);

        JLabel fecha_inicio = new JLabel("Fecha Inicio: " + c.fecha_inicio);
        info.add(fecha_inicio);

        JLabel fecha_fin = new JLabel("Fecha Inicio: " + c.fecha_fin);
        info.add(fecha_fin);


        add(info, BorderLayout.CENTER);
    }
}*/