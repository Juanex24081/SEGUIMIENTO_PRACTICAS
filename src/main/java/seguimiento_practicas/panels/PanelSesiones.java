package seguimiento_practicas.panels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        add(new JScrollPane(contenedor));

        cargarSesiones(contenedor);
    }


    private void cargarSesiones(JPanel contenedor) {

        contenedor.removeAll();

        Long idUsuario = UsuarioSesion.usuarioActual.getId();

        ArrayList<SesionDTO> sesiones =
                dao.listarPorEstudiante(idUsuario);

        for (SesionDTO s : sesiones) {

            add(new CardSesion(
                    s,
                    () -> abrirSesion(s)
            ));

            contenedor.add(Box.createVerticalStrut(15));
        }

        contenedor.revalidate();
        contenedor.repaint();
    }

    private void abrirSesion(SesionDTO sesion) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Detalle Sesión");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        dialog.add(new PanelDetalleSesion(sesion));

        dialog.setModal(true);
        dialog.setVisible(true);
    }
}