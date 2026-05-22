package seguimiento_practicas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import seguimiento_practicas.dao.ResumenDAO;
import seguimiento_practicas.model.ResumenDTO;
import seguimiento_practicas.session.UsuarioSesion;

public class PanelResumen extends JPanel {

    public PanelResumen() {

        ResumenDAO dao = new ResumenDAO();

        long idUsuario = UsuarioSesion.usuarioActual.getId();

        ResumenDTO r = dao.obtenerResumen(idUsuario);



        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("RESUMEN GENERAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        add(titulo, BorderLayout.NORTH);

        // ================= CONTENEDOR =================
        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        cards.setBackground(Color.WHITE);

        cards.add(crearCard(
                "Nombre Estudiante",
                r.getNombreEstudiante()
        ));

        cards.add(crearCard(
                "Horas cursadas",
                r.getHoras() + "h"
        ));

        cards.add(crearCard(
                "Grupo",
                r.getGrupo()
        ));

        cards.add(crearCard(
                "Docente",
                r.getDocente()
        ));

        cards.add(crearCard(
                "Sesiones completadas",
                String.valueOf(r.getSesiones())
        ));

        cards.add(crearCard(
                "Calificación Promedio",
                String.format("%.1f", r.getPromedio())
        ));

        add(cards, BorderLayout.CENTER);
    }

    // ================= CARD =================
    private JPanel crearCard(String titulo, String valor) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(new Color(245,245,245));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 28));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);

        return card;
    }
}