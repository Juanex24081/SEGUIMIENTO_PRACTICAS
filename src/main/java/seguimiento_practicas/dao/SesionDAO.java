package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seguimiento_practicas.model.SesionDTO;
import seguimiento_practicas.util.Conexion;

public class SesionDAO {

    public ArrayList<SesionDTO> listarPorDocente(Long idDocente) {

        ArrayList<SesionDTO> lista = new ArrayList<>();

        String sql = """
            SELECT 
                s.id AS id,
                s.estado AS estado,
                c.empresa AS empresa,
                u.nombre AS docente,
                s.fecha AS fecha
            FROM sesiones s
            JOIN convenios c ON s.id_convenio = c.id
            JOIN usuarios u ON s.id_docente = u.id
            JOIN estudiante_sesion es ON es.id_sesion = s.id
            WHERE s.id_docente = ?
        """;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idDocente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new SesionDTO(
                    rs.getInt("id"),
                    rs.getString("estado"),
                    rs.getString("empresa"),
                    rs.getString("docente"),
                    rs.getString("fecha")
                ));
            }

        } catch (Exception e) {
            System.out.println("SesionDAO: " + e.getMessage());
        }

        return lista;
    }

    public ArrayList<SesionDTO> listarPorAsesor(Long idAsesor) {

        /* DEBUG */
        System.out.println("Buscando sesiones para asesor: " + idAsesor);

        ArrayList<SesionDTO> lista = new ArrayList<>();

        String sql = """
            SELECT 
                s.id AS id,
                s.estado AS estado,
                c.empresa AS empresa,
                u.nombre AS docente,
                s.fecha AS fecha
            FROM sesiones s
            JOIN convenios c ON s.id_convenio = c.id
            JOIN usuarios u ON s.id_docente = u.id
            JOIN estudiante_sesion es ON es.id_sesion = s.id
        """;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idAsesor);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new SesionDTO(
                    rs.getInt("id"),
                    rs.getString("estado"),
                    rs.getString("empresa"),
                    rs.getString("docente"),
                    rs.getString("fecha")
                ));
            }

        } catch (Exception e) {
            System.out.println("SesionDAO: " + e.getMessage());
        }

        return lista;
    }


    public ArrayList<SesionDTO> listarPorEstudiante(Long idEstudiante) {

        /* DEBUG */
        System.out.println("Buscando sesiones para estudiante: " + idEstudiante);

        ArrayList<SesionDTO> lista = new ArrayList<>();

        String sql = """
            SELECT 
                s.id AS id,
                s.estado AS estado,
                c.empresa AS empresa,
                u.nombre AS docente,
                s.fecha AS fecha
            FROM sesiones s
            JOIN convenios c ON s.id_convenio = c.id
            JOIN usuarios u ON s.id_docente = u.id
            JOIN estudiante_sesion es ON es.id_sesion = s.id
            WHERE es.id_estudiante = ?
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idEstudiante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new SesionDTO(
                    rs.getInt("id"),
                    rs.getString("estado"),
                    rs.getString("empresa"),
                    rs.getString("docente"),
                    rs.getString("fecha")
                ));
            }

        } catch (Exception e) {
            System.out.println("SesionDAO estudiante: " + e.getMessage());
        }

        return lista;
    }

    // CLASS GRUPOS (SESIONES POR GRUPO)

    public ArrayList<Object[]> obtenerSesionesGrupo(int idGrupo) {

    ArrayList<Object[]> lista = new ArrayList<>();

    String sql = """
        SELECT id,
               fecha,
               horas,
               estado,
               total_estudiantes
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
}