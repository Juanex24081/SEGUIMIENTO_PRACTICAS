package seguimiento_practicas.panels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import seguimiento_practicas.dao.BitacoraDAO;
import seguimiento_practicas.model.BitacoraListaDTO;
import seguimiento_practicas.session.UsuarioSesion;
import seguimiento_practicas.ui_components.CardBitacora;

public class PanelBitacoras extends JPanel {

    private BitacoraDAO dao = new BitacoraDAO();

    public PanelBitacoras() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        cargarBitacoras();
    }

    private void cargarBitacoras() {

        Long id = UsuarioSesion.usuarioActual.getId();

        ArrayList<BitacoraListaDTO> lista = dao.listarPorEstudiante(id);

        for (BitacoraListaDTO b : lista) {

            add(new CardBitacora(b));
            add(Box.createVerticalStrut(10));
        }
    }
}