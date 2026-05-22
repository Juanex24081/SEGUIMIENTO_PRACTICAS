package seguimiento_practicas.dao;

import seguimiento_practicas.model.ResumenDTO;
import seguimiento_practicas.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResumenDAO {

    public ResumenDTO obtenerResumen(long idEstudiante) {

        String sql = """
            SELECT
                u.nombre AS estudiante,
                NVL(SUM(s.horas),0) AS horas,
                g.nombre AS grupo,
                d.nombre AS docente,
                COUNT(es.id_sesion) AS sesiones,
                NVL(AVG(b.calificacion),0) AS promedio

            FROM usuarios u

            LEFT JOIN grupo_estudiantes ge
                ON ge.id_estudiante = u.id

            LEFT JOIN grupos g
                ON g.id = ge.id_grupo

            LEFT JOIN usuarios d
                ON d.id = g.id_docente

            LEFT JOIN estudiante_sesion es
                ON es.id_estudiante = u.id

            LEFT JOIN sesiones s
                ON s.id = es.id_sesion

            LEFT JOIN bitacoras b
                ON b.id_estudiante = u.id

            WHERE u.id = ?

            GROUP BY
                u.nombre,
                g.nombre,
                d.nombre
        """;

        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setLong(1, idEstudiante);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new ResumenDTO(
                        rs.getString("estudiante"),
                        rs.getInt("horas"),
                        rs.getString("grupo"),
                        rs.getString("docente"),
                        rs.getInt("sesiones"),
                        rs.getDouble("promedio")
                );
            }

        } catch (Exception e) {
            System.out.println("Error resumen: " + e.getMessage());
        }

        return null;
    }
}