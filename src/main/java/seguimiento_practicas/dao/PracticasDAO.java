package seguimiento_practicas.dao;

import seguimiento_practicas.model.CrearGrupoDTO;
import seguimiento_practicas.util.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class PracticasDAO {

    // CREAR GRUPO
    public void crearGrupo(CrearGrupoDTO grupo) {

        String sql = "INSERT INTO grupos (id, nombre, id_docente, id_asesor) VALUES (seq_grupos.NEXTVAL,?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, grupo.getNombreGrupo());
            ps.setInt(2, grupo.getIdDocente());
            ps.setInt(3, grupo.getIdAsesor());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error crear grupo: " + e.getMessage());
        }
    }

    // LISTAR DOCENTES
    public ArrayList<Object[]> listarDocentes() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = "SELECT id, nombre FROM usuarios WHERE rol = 'DOCENTE'";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre")
                });
            }

        } catch (Exception e) {
            System.out.println("Error docentes: " + e.getMessage());
        }

        return lista;
    }

    // LISTAR ASESORES
    public ArrayList<Object[]> listarAsesores() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = "SELECT id, nombre FROM usuarios WHERE rol = 'ASESOR'";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre")
                });
            }

        } catch (Exception e) {
            System.out.println("Error asesores: " + e.getMessage());
        }

        return lista;
    }


    /* LISTAR GRUPOS */

    public ArrayList<Object[]> listarGrupos() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT g.id, g.nombre,
                d.nombre AS docente,
                a.nombre AS asesor
            FROM grupos g
            JOIN usuarios d ON g.id_docente = d.id
            JOIN usuarios a ON g.id_asesor = a.id
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("docente"),
                    rs.getString("asesor")
                });
            }

        } catch (Exception e) {
            System.out.println("Error listar grupos: " + e.getMessage());
        }

        return lista;
    }




    /* LISTAR ESTUDIANTES POR GRUPO */

    public ArrayList<String> listarEstudiantesPorGrupo(int idGrupo) {

        ArrayList<String> lista = new ArrayList<>();

        String sql = """
            SELECT u.nombre
            FROM grupo_estudiantes ge
            JOIN usuarios u ON ge.id_estudiante = u.id
            WHERE ge.id_grupo = ?
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGrupo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Error estudiantes grupo: " + e.getMessage());
        }

        return lista;
    }

}