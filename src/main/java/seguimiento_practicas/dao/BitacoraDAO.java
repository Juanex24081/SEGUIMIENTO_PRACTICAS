package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seguimiento_practicas.model.BitacoraResultadoDTO;
import seguimiento_practicas.model.BitacoraListaDTO;
import seguimiento_practicas.util.Conexion;

public class BitacoraDAO {

    /* ----------------------------------------------------------------------------------------
    --------------- PANEL SESIONES (PARA SUBIR LAS BITÁCORAS Y VER LOS RESULTADOS) ------------
    ------------------------------------------------------------------------------------------- */

    public BitacoraResultadoDTO obtenerPorSesionYEstudiante(int idSesion, Long idEstudiante) {

        String sql = """
            SELECT 
                calificacion,
                comentario,
                estado
            FROM bitacoras
            WHERE id_sesion = ? AND id_estudiante = ?
        """;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSesion);
            ps.setLong(2, idEstudiante);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new BitacoraResultadoDTO(
                        rs.getInt("calificacion"),
                        rs.getString("comentario"),
                        rs.getString("estado")
                );
            }

        } catch (Exception e) {
            System.out.println("BitacoraDAO: " + e.getMessage());
        }

        return null;
    }

    /* SUBIR BITÁCORA (CRUDO) */
    public void insertar(Long idEstudiante, int idSesion, String archivo) {

        String sql = """
            INSERT INTO bitacoras 
            (id_estudiante, id_sesion, archivo, estado, fecha_envio)
            VALUES (?, ?, ?, 'ENVIADA', SYSDATE)
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idEstudiante);
            ps.setInt(2, idSesion);
            ps.setString(3, archivo);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insert bitacora: " + e.getMessage());
        }
    }


    /* SUBIR BITÁCORA (SOBREESCRIBE SI YA HAY UN ARCHIVO SUBIDO) (MÉTODO USADO QUE LLAMA A INSERTAR) */
    public void guardar(Long idEstudiante, int idSesion, String archivo) {

        String check = "SELECT COUNT(*) FROM bitacoras WHERE id_estudiante=? AND id_sesion=?";

        try (Connection con = Conexion.getConnection();
            PreparedStatement psCheck = con.prepareStatement(check)) {

            psCheck.setLong(1, idEstudiante);
            psCheck.setInt(2, idSesion);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {

                String update = """
                    UPDATE bitacoras 
                    SET archivo=?, estado='ENVIADA', fecha_envio=SYSDATE
                    WHERE id_estudiante=? AND id_sesion=?
                """;

                try (PreparedStatement ps = con.prepareStatement(update)) {
                    ps.setString(1, archivo);
                    ps.setLong(2, idEstudiante);
                    ps.setInt(3, idSesion);
                    ps.executeUpdate();
                }

            } else {

                insertar(idEstudiante, idSesion, archivo);
            }

        } catch (Exception e) {
            System.out.println("Error guardar bitacora: " + e.getMessage());
        }
    }



    /* ----------------------------------------------------------------------------------------
    --------------------- PANEL BITÁCORAS (LISTA DE BITÁCORAS Y DETALLES) ---------------------
    ------------------------------------------------------------------------------------------- */

    public ArrayList<BitacoraListaDTO> listarPorEstudiante(Long idEstudiante) {

        ArrayList<BitacoraListaDTO> lista = new ArrayList<>();

        String sql = """
            INSERT INTO bitacoras
            (id_estudiante, id_sesion, archivo, estado, fecha_envio)
            VALUES (?, ?, ?, 'ENVIADA', SYSDATE)
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idEstudiante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new BitacoraListaDTO(
                    rs.getInt("id_sesion"),
                    rs.getString("archivo"),
                    rs.getString("estado"),
                    rs.getInt("calificacion"),
                    rs.getDate("fecha_envio") != null
                        ? rs.getDate("fecha_envio").toString()
                        : "Sin fecha"
                ));
            }

        } catch (Exception e) {
            System.out.println("Error listar bitacoras: " + e.getMessage());
        }

        return lista;
    }
}