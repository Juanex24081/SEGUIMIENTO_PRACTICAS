package seguimiento_practicas.panels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import seguimiento_practicas.dao.SesionDAO;
import seguimiento_practicas.model.SesionDTO;
import seguimiento_practicas.session.UsuarioSesion;
import seguimiento_practicas.ui_components.CardSesion;

public class PanelSesiones extends JPanel {

    private SesionDAO dao = new SesionDAO();

    public PanelSesiones() {

        /* DEBUG */
        System.out.println("Usuario logueado ID: " + UsuarioSesion.usuarioActual.getId());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        cargarSesiones();
    }

    private void cargarSesiones() {

        removeAll();

        Long idUsuario = UsuarioSesion.usuarioActual.getId();

        ArrayList<SesionDTO> sesiones =
                dao.listarPorEstudiante(idUsuario);

        for (SesionDTO s : sesiones) {

            add(new CardSesion(
                    s,
                    () -> abrirSesion(s.id)
            ));

            add(Box.createVerticalStrut(10));
        }

        revalidate();
        repaint();
    }

    private void abrirSesion(int idSesion) {
        JOptionPane.showMessageDialog(this, "Abrir sesión " + idSesion);
    }
}