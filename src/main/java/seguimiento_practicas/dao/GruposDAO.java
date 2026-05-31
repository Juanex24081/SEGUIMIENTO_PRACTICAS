package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seguimiento_practicas.model.CrearGrupoDTO;
import seguimiento_practicas.util.Conexion;

import seguimiento_practicas.session.UsuarioSesion;

public class GruposDAO {

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

    // LISTAR DOCENTE UNICO POR ID
    public ArrayList<Object[]> listarDocenteUnico() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = "SELECT id, nombre FROM usuarios WHERE rol = 'DOCENTE' AND id = ?";

        Long idDocente = UsuarioSesion.usuarioActual.getId();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idDocente);

            ResultSet rs = ps.executeQuery();

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

    public ArrayList<String> obtenerEstudiantesVista(int idGrupo) {

        ArrayList<String> lista = new ArrayList<>();

        String sql = """
            SELECT estudiante
            FROM vw_grupos_estudiantes
            WHERE id_grupo = ?
            ORDER BY estudiante
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idGrupo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(
                    rs.getString("estudiante")
                );

            }

        } catch (Exception e) {

            System.out.println(
                "Error vista estudiantes: "
                + e.getMessage()
            );
        }

        return lista;
    }

    public ArrayList<Object[]> listarReporteGruposDirector() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT *
            FROM vw_reporte_grupos
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(new Object[]{

                    rs.getInt("id_grupo"),
                    rs.getString("nombre_grupo"),
                    rs.getString("docente"),
                    rs.getString("asesor"),
                    rs.getInt("total_estudiantes"),
                    rs.getString("id_docente")

                });

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return lista;
    }

    public ArrayList<Object[]> listarReporteGruposDocente(Long idDocente) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT *
            FROM vw_reporte_grupos
            WHERE id_docente = ?
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setLong(1, idDocente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{

                    rs.getInt("id_grupo"),
                    rs.getString("nombre_grupo"),
                    rs.getString("docente"),
                    rs.getString("asesor"),
                    rs.getInt("total_estudiantes"),
                    rs.getString("id_docente")

                });

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return lista;
    }

    public ArrayList<Object[]> obtenerSesionesGrupo(int idGrupo) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT id,
                fecha,
                horas,
                estado
            FROM vw_grupos_sesiones
            WHERE id_grupo = ?
            ORDER BY fecha
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idGrupo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{

                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getInt("horas"),
                    rs.getString("estado")

                });

            }

        } catch (Exception e) {

            System.out.println(
                    "Error sesiones grupo: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    public ArrayList<Object[]> obtenerBitacorasSesion(int idSesion) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT estudiante,
                estado,
                calificacion,
                fecha_envio
            FROM vw_sesion_bitacoras
            WHERE id_sesion = ?
            ORDER BY estudiante
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idSesion);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Object[]{

                    rs.getString("estudiante"),
                    rs.getString("estado"),
                    rs.getInt("calificacion"),
                    rs.getDate("fecha_envio")

                });

            }

        } catch (Exception e) {

            System.out.println(
                    "Error bitácoras: "
                    + e.getMessage()
            );
        }

        return lista;
    }
}