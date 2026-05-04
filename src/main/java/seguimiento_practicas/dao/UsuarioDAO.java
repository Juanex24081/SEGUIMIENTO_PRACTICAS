package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import seguimiento_practicas.model.Usuario;
import seguimiento_practicas.util.Conexion;


public class UsuarioDAO {

    /* LOGIN */
    public Usuario login(String correo, String pass) {

        String sql = "SELECT * FROM usuarios WHERE correo=? AND contrasena=?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, correo);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getLong("cedula")
                );
            }

        } catch (Exception e) {
            System.out.println("Error login DAO: " + e.getMessage());
        }

        return null;
    }


    /* LISTAR USUARIOS */

        public ArrayList<Usuario> listar() {

        ArrayList<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try {
            Connection con = Conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Usuario u = new Usuario(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getLong("cedula")
                );

                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error listar DAO: " + e.getMessage());
        }

        return lista;
    }


    /* INSERTAR USUARIO */

        public void insertar(Usuario u) {

        String sql = "INSERT INTO usuarios(nombre, correo, contrasena, rol) VALUES (?,?,?,?)";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getRol());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar DAO: " + e.getMessage());
        }
    }

    
    /* ELIMINAR USUARIO */

        public void eliminar(long id) {

        String sql = "DELETE FROM usuarios WHERE id=?";

        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar DAO: " + e.getMessage());
        }
    }
}





