/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import seguimiento_practicas.util.Conexion;

public class UsuarioDAO {
    public ResultSet validarUsuario(String correo, String pass) {

        String sql = "SELECT * FROM usuarios WHERE correo=? AND contraseña=?";

        try {
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, correo);
            ps.setString(2, pass);

            return ps.executeQuery();

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
            return null;
        }
    }

     public void insertar(String nombre, String correo, String pass, String rol) {
        String sql = "INSERT INTO usuarios(nombre, correo, contraseña, rol) VALUES(?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, pass);
            ps.setString(4, rol);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet listar() {
        String sql = "SELECT * FROM usuarios";
        try {
            Connection con = Conexion.conectar();
            Statement st = con.createStatement();
            return st.executeQuery(sql);
            
        } catch (Exception e) {
            return null;
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

