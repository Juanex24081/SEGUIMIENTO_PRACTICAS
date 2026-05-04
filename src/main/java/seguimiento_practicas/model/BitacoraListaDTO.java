package seguimiento_practicas.model;

public class BitacoraListaDTO {

    public int idSesion;
    public String archivo;
    public String estado;
    public int calificacion;
    public String fecha;

    public BitacoraListaDTO(int idSesion, String archivo, String estado, int calificacion, String fecha) {
        this.idSesion = idSesion;
        this.archivo = archivo;
        this.estado = estado;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }
}