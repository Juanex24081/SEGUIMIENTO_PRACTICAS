/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguimiento_practicas;
import java.util.ArrayList;

public class Datos {

    public static ArrayList<Object[]> listaUsuarios = new ArrayList<>();

    static {
        listaUsuarios.add(new Object[]{"Juan", "123", "ESTUDIANTE"});
        listaUsuarios.add(new Object[]{"Ana", "456", "DOCENTE"});
        listaUsuarios.add(new Object[]{"Luis", "789", "ASESOR"});
    }
}
