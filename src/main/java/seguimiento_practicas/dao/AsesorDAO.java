package seguimiento_practicas.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seguimiento_practicas.model.BitacoraAsesorDTO;
import seguimiento_practicas.model.ComentarioDTO;
import seguimiento_practicas.util.Conexion;

public class AsesorDAO {

    // =========================
    // LISTAR BITÁCORAS
    // =========================

    public ArrayList<BitacoraAsesorDTO> listarBitacoras(int idAsesor) {

        ArrayList<BitacoraAsesorDTO> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM VISTA_ASESOR_BITACORAS
            WHERE id_asesor = ?
        """;

        try (

            Connection con =
                    Conexion.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)

        ) {

            ps.setInt(1, idAsesor);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(

                    new BitacoraAsesorDTO(

                        rs.getInt("id_bitacora"),

                        rs.getString("estudiante"),

                        rs.getInt("id_sesion"),

                        rs.getString("archivo"),

                        rs.getString("estado"),

                        rs.getInt("calificacion"),

                        rs.getString("fecha_envio")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    // =========================
    // LISTAR SESIONES
    // =========================

    public ArrayList<BitacoraAsesorDTO> listarSesiones(int idAsesor) {

        ArrayList<BitacoraAsesorDTO> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM VISTA_ASESOR_BITACORAS
            WHERE id_asesor = ?
        """;

        try (

            Connection con =
                    Conexion.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)

        ) {

            ps.setInt(1, idAsesor);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(

                    new BitacoraAsesorDTO(

                        rs.getInt("id_bitacora"),

                        rs.getString("estudiante"),

                        rs.getInt("id_sesion"),

                        rs.getString("archivo"),

                        rs.getString("estado"),

                        rs.getInt("calificacion"),

                        rs.getString("fecha_envio")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // LISTAR COMENTARIOS
    // =========================

    public ArrayList<ComentarioDTO>listarComentarios(int idBitacora) {

        ArrayList<ComentarioDTO> lista =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM VISTA_COMENTARIOS_BITACORA
            WHERE id_bitacora = ?
            ORDER BY fecha_comentario
        """;

        try (

            Connection con =
                    Conexion.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)

        ) {

            ps.setInt(1, idBitacora);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(

                    new ComentarioDTO(

                        rs.getInt("id"),

                        rs.getString("usuario"),

                        rs.getString("rol"),

                        rs.getString("comentario"),

                        rs.getString("fecha_comentario")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // COMENTAR BITÁCORA
    // =========================

    public void comentarBitacora(int idBitacora,int idUsuario,String comentario) {
        String sql =
                "{CALL sp_comentar_bitacora(?,?,?)}";

        try (

            Connection con = Conexion.getConnection();

            CallableStatement cs = con.prepareCall(sql)

        ) {

            con.setAutoCommit(false);

            cs.setInt(1, idBitacora);
            cs.setInt(2, idUsuario);
            cs.setString(3, comentario);

            cs.execute();

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}