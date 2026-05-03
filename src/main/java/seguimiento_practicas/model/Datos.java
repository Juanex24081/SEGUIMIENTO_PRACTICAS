package seguimiento_practicas.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import seguimiento_practicas.util.Conexion;

public class Datos {

    public static ArrayList<Object[]> listaUsuarios = new ArrayList<>();

    static {
        cargarUsuarios();
    }

    public static void cargarUsuarios() {

        listaUsuarios.clear();

        String sql = "SELECT id, nombre, correo, rol FROM usuarios";

        try {
            Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Object[] fila = new Object[4];

                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("correo");
                fila[3] = rs.getString("rol");

                listaUsuarios.add(fila);
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error cargando usuarios: " + e.getMessage());
        }
    }
}



/*package seguimiento_practicas.model;

import java.util.ArrayList;

public class Datos {

    public static ArrayList<Object[]> listaUsuarios = new ArrayList<>();

    static {
        listaUsuarios.add(new Object[]{"Juan", "123", "ESTUDIANTE"});
        listaUsuarios.add(new Object[]{"Ana", "456", "DOCENTE"});
        listaUsuarios.add(new Object[]{"Luis", "789", "ASESOR"});
    }
}
 */