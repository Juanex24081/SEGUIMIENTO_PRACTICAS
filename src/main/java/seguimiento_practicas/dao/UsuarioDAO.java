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

    public ArrayList<Object[]> listarUsuarios() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = "SELECT ID, NOMBRE, ROL FROM usuarios";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("rol")
                });
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }


    /* INSERTAR USUARIO */

    public void insertar(String nombre, String correo, String pass, String rol) {

        String sql = """
            INSERT INTO usuarios (id, nombre, correo, contrasena, rol)
            VALUES (usuarios_seq.NEXTVAL, ?, ?, ?, ?)
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, pass);
            ps.setString(4, rol);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /* ELIMINAR USUARIO */

    public void eliminarUsuario(int id) {

        String sql = "DELETE FROM usuarios WHERE ID = ?";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
        }
    }


    public void actualizarUsuario(int id, String nombre, String rol) {

        String sql = "UPDATE usuarios SET nombre=?, rol=? WHERE ID=?";

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, rol);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
        }
    }
}





