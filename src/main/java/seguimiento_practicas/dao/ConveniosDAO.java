package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import seguimiento_practicas.model.CrearConvenioDTO;
import seguimiento_practicas.util.Conexion;

public class ConveniosDAO {

    // CREAR CONVENIO
    public void crearConvenio(CrearConvenioDTO convenio) {

        String sql = "INSERT INTO convenios (id, empresa, fecha_inicio, fecha_fin, estado, id_asesor) VALUES (seq_convenios.NEXTVAL,?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement   ps = con.prepareStatement(sql)) {

            ps.setString(1, convenio.getEmpresa());

            ps.setDate(2, java.sql.Date.valueOf(convenio.getFechaInicio()));
            ps.setDate(3, java.sql.Date.valueOf(convenio.getFechaFin()));
            
            ps.setString(4, convenio.getEstado());
            ps.setInt(5, convenio.getIdAsesor());

            ps.executeUpdate();

        } catch (Exception e) {
        e.printStackTrace();
        
        /*catch (Exception e) {
            System.out.println("Error crear convenio: " + e.getMessage());*/
            
        }
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


    /* LISTAR CONVENIOS */

    public ArrayList<Object[]> listarConvenios() {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT c.id, 
            c.empresa,
            c.fecha_inicio,
            c.fecha_fin,
            a.nombre AS asesor
            FROM convenios c
            JOIN usuarios a ON c.id_asesor = a.id
        """;

        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("empresa"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("asesor")
                });
            }

        } catch (Exception e) {
            System.out.println("Error listar convenios: " + e.getMessage());
        }

        return lista;
    }
}